package com.hhnz.jco.business.writeoff;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hhnz.account.mapper.CMerchReceiveVerifieMapper;
import com.hhnz.account.mapper.OmInvoiceVMapper;
import com.hhnz.account.model.CMerchReceiveVerifie;
import com.hhnz.account.model.CMerchReceiveVerifieExample;
import com.hhnz.account.model.OmInvoiceV;
import com.hhnz.account.model.OmInvoiceVExample;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.jco.DefaultRFCInvoker;
import com.hhnz.jco.RFCConstants;
import com.hhnz.jco.RFCInvoker;
import com.hhnz.jco.business.base.AbstractRfcCaller;
import com.hhnz.jco.business.base.BaseResultDTO;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.CommonResult;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.jco.business.writeoff.InputDTO.Header;
import com.hhnz.jco.business.writeoff.InputDTO.Item;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.organization.mapper.CompanyOrgMapper;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.JsonUtil;

/**
 * 账户调整传SAP
 * @author: chaoyang.ren
 * @date:2016年9月21日
 * @time:下午4:45:31
 * @email:chaoyang.ren@foxmail.com
 */
@Component("writeOffRFC")
@Transactional
public class WriteOffRFC extends AbstractRfcCaller {
	private static final Log LOG = LogFactory.getLog(WriteOffRFC.class);
	@Resource
	private OmInvoiceVMapper invoiceMapper;
	@Autowired
	private CMerchReceiveVerifieMapper receiveVerifieMapper;
	@Autowired
	private IorganizationService organizationService;
	@Resource
	private ICustomerService customerService;
	@Resource
	private CompanyOrgMapper companyOrgMapper;
	
	/**
	 * 异步数据处理
	 * @author: chaoyang.ren 
	 * @date:2016年11月18日  上午10:28:14
	 * @param input
	 * @param simpleName
	 */
	public void executeInThread(InputDTO input, String simpleName){
		String inputJson = JsonUtil.toJSON(input);
		RFCCallback c = null;
		if(StringUtils.isNotBlank(simpleName)){
			c = ApplicationContextUtil.getBean(simpleName);
		}
		super.executeInThread(new RfcRedoDto(inputJson, RfcExeType.WRITEOFF, c));
	}

	
	@Override
	public BaseResultDTO execute(RfcRedoDto rfcDto) {
		RFCInvoker rfcInvoker = new DefaultRFCInvoker();
		String json = null;
		RFCCallback c = rfcDto.getRfcCallback();
		try {
			json = rfcInvoker.invokeRFC(rfcDto.getRfcExeType().getFuncName(), rfcDto.getJsonParam());
		} catch (Exception e) {
			LOG.error("从RFC接口获取数据失败！", e);
			//执行失败后缓存参数
			failureRedo(rfcDto);
			ResultDTO result = new ResultDTO();
			result.setResult(CommonResult.error(e));
			//执行错误后的回调
			if(c != null){
				InputDTO input = JsonUtil.fromJSON(rfcDto.getJsonParam(), InputDTO.class);
				c.errorCallBack(CallbackParam.of(Long.valueOf(input.getHeader().getCrmRelatedId()),result.getResult()));
			}
			return result;
		}
		ResultDTO result = JsonUtil.fromJSON(json, ResultDTO.class);
		CommonResult cr = result.getResult();
		if(!cr.getTYPE().equals(RFCConstants.SUCCESS_FLAG)){
			LOG.warn(cr.getMESSAGE());
			//执行错误后的回调
			InputDTO input = JsonUtil.fromJSON(rfcDto.getJsonParam(), InputDTO.class);
			if(c != null){
				c.errorCallBack(CallbackParam.of(Long.valueOf(input.getHeader().getCrmRelatedId()),cr));
			}
			return result;
		}else{
			InputDTO input = JsonUtil.fromJSON(rfcDto.getJsonParam(), InputDTO.class);
			//执行成功后的回调
			if(c != null){
				c.successCallBack(CallbackParam.of(input.getHeader().getCrmRelatedId(), result.getVoucher(), cr));
			}
		}
		return result;
	}
	
	public String constructParam(Serializable id){
		InputDTO inputDto = constructInputParam(Long.valueOf(id.toString()));
		return JsonUtil.toJSON(inputDto);
	}

	/**
	 * 构建传入参数
	 * @author: chaoyang.ren 
	 * @date:2016年11月18日  上午11:12:33
	 * @param accountLogId
	 * @return
	 */
	public InputDTO constructInputParam(Long verifiedId){
		CMerchReceiveVerifie verifie = receiveVerifieMapper.selectByPrimaryKey(verifiedId);
		return constructInputParam(verifie);
	}
	
	public InputDTO constructInputParam(CMerchReceiveVerifie verifie){
		InputDTO input = new InputDTO();
		Header header = input.new Header();
		List<Item> items = new ArrayList<Item>();
		OmInvoiceVExample ex = new OmInvoiceVExample();
		ex.createCriteria().andInvoiceNoEqualTo(verifie.getInvoiceNo());
		List<OmInvoiceV> invoices = invoiceMapper.selectByExample(ex);
		CMerchReceiveVerifieExample rex = new CMerchReceiveVerifieExample();
		rex.createCriteria().andInvoiceNoEqualTo(verifie.getInvoiceNo());
		
		if(invoices != null && !invoices.isEmpty()){
			OmInvoiceV invoice  = invoices.get(0);
			//头信息
			header.setOperation(OperationType.of(invoice.getType()));
			BigDecimal total = invoice.getTotalPrice().add(invoice.getTotalTax());
			//invoice.getVerifieAmt()是该发票历史总核销金额+当次核销金额
			BigDecimal balance = total.subtract(invoice.getVerifieAmt());
			header.setBalance(balance.toPlainString());
			header.setText(header.getOperation().getDesc());
			CrmSalesOrganization org = organizationService.findById(verifie.getOrganizationId());
			String sapId = org.getSapId();
			String companyCode = companyOrgMapper.selectBySalesOrg(sapId);
			header.setCompanyCode(StringUtils.isBlank(companyCode)?sapId:companyCode);
			header.setCrmRelatedId(verifie.getId());
			Long sapCustomerId = invoice.getCustomerId();
			CMerchCustBase c = customerService.findSimpleCustBaseById(sapCustomerId);
			header.setSapCustomerId(invoice.getCustomerId().toString());
			if(c != null){
				boolean isRetail = c.getCustType().equals("5");
				boolean isNoNeedInvoice = c.getIsInvoice().equals("1");
				if(isRetail && isNoNeedInvoice){
					LOG.info("=====一次性客户应收发票核销");
					header.setSapCustomerId("Y004");
				}
			}
			header.setSapCustomerId(invoice.getCustomerId().toString());
			//负数即核销足够且有多于应收发票的账款
			if(balance.compareTo(balance.abs()) != 0){
				header.setVoucher(verifie.getVerifiedId().toString());
			}else{
				header.setVoucher(invoice.getInvoiceNo());
			}
			SimpleDateFormat sdf = new SimpleDateFormat(RFCConstants.SDF_PATTERN);
			header.setWriteOffDate(sdf.format(verifie.getVerifieDate()));
			
			//行信息
			//item1:本次核销凭证，资金上账或者冲销发票
			Item item = input.new Item();
			item.setItemNo("1");
			item.setVoucherType(VoucherType.of(verifie.getType()));
			item.setVoucher(verifie.getVerifiedId().toString());
			BigDecimal currentVeri = BigDecimalASME.divide(verifie.getVerifieAmt());
			item.setAmount(currentVeri);
			item.setYear(verifie.getYear());
			items.add(item);
			
			//item2:核销的应收发票
			Item item2 = input.new Item();
			item2.setItemNo("2");
			item2.setVoucherType(VoucherType.V);
			item2.setVoucher(invoice.getInvoiceNo());
			//发票当前金额为发票总金额-历史核销金额，由于balance减去了此次核销金额，因此，加上此次核销金额后及为现发票剩余金额
			item2.setAmount((balance.add(currentVeri)).abs());
			item2.setYear(invoice.getPeriod().substring(0, 4));
			items.add(item2);
			
			input.setHeader(header);
			input.setItems(items);
			return input;
		}else{
			LOG.error("构建核销参数失败，无法找到对应的核销发票！");
			return null;
		}
	}

	@Override
	public String constructParam(Object obj) {
		Assert.isTrue(obj instanceof CMerchReceiveVerifie);
		CMerchReceiveVerifie verifie = (CMerchReceiveVerifie) obj;
		InputDTO inputDto = constructInputParam(verifie);
		return JsonUtil.toJSON(inputDto);
	}
}

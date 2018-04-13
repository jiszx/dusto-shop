package com.hhnz.jco.business.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hhnz.account.mapper.CMerchCustAccountLogMapper;
import com.hhnz.account.mapper.CMerchCustAdjustMapper;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAdjust;
import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.jco.DefaultRFCInvoker;
import com.hhnz.jco.RFCConstants;
import com.hhnz.jco.RFCInvoker;
import com.hhnz.jco.business.account.InputDTO.Header;
import com.hhnz.jco.business.account.InputDTO.Item;
import com.hhnz.jco.business.base.AbstractRfcCaller;
import com.hhnz.jco.business.base.BaseResultDTO;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.CommonResult;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.organization.mapper.CompanyOrgMapper;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.JsonUtil;

/**
 * 保证金上账传SAP
 * @author: chaoyang.ren
 * @date:2016年12月13日
 * @time:上午9:32:12
 * @email:chaoyang.ren@foxmail.com
 */
@Component("marginsRFC")
@Transactional(propagation=Propagation.REQUIRED)
public class MarginsRFC extends AbstractRfcCaller {
	private static final Log LOG = LogFactory.getLog(MarginsRFC.class);
	
	@Resource
	private CMerchCustAccountLogMapper accountLogMapper;
	@Resource
	private CMerchCustAdjustMapper custAdjustMapper;
	@Resource
	private IorganizationService organizationService;
	@Resource
    private CMerchCustBaseMapper merchCustBaseMapper;
	@Resource
	private CompanyOrgMapper companyOrgMapper;
	
	/**
	 * 调用接口更新账户凭证
	 * @author: chaoyang.ren 
	 * @date:2016年10月9日  上午10:43:28
	 * @param input
	 */
	public BaseResultDTO execute(InputDTO input){
		String inputJson = JsonUtil.toJSON(input);
		return this.execute(new RfcRedoDto(inputJson, RfcExeType.MARGINS));
	}
	
	/**
	 * 异步的数据处理
	 * @author: chaoyang.ren 
	 * @date:2016年10月9日  上午10:43:28
	 * @param input
	 */
	public void executeInThread(InputDTO input, String simpleName){
		String inputJson = JsonUtil.toJSON(input);
		RFCCallback c = null;
		if(StringUtils.isNotBlank(simpleName)){
			c = ApplicationContextUtil.getBean(simpleName);
		}
		super.executeInThread(new RfcRedoDto(inputJson, RfcExeType.MARGINS, c));
	}

	/**
	 * 保证金上账调用接口更新账户凭证
	 * @author: chaoyang.ren 
	 * @date:2016年12月13日  上午9:35:32
	 * @param inputJson 输入参数{@link RfcRedoDto}
	 */
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
			CMerchCustAccountLog cal = accountLogMapper.selectByPrimaryKey(Long.valueOf(input.getHeader().getCrmRelatedId()));
			cal.setSapVoucherId(result.getVoucher());
			accountLogMapper.updateByPrimaryKeySelective(cal);
			//执行成功后的回调
			if(c != null){
				c.successCallBack(CallbackParam.of(cal.getId(), result.getVoucher(), cr));
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
	 * <p>保证金上账接口只有在账户进行保证金调整时调用,返回{@code instance of} {@link InputDTO}，其他情况此参数构造方法返回{@code null}值</p>
	 * 
	 * <p>明细{@code Item}行说明：<br/>
	 * 调整时需同时有01和11记账码的至少两个行项目<br/>
	 * </p>
	 * 
	 * @author: chaoyang.ren 
	 * @date:2016年12月13日  上午9:35:32
	 * @param accountLogId
	 * @return
	 */
	public InputDTO constructInputParam(Long accountLogId){
		CMerchCustAccountLog cal = accountLogMapper.selectByPrimaryKey(accountLogId);
		return constructInputParam(cal);
	}
	
	public InputDTO constructInputParam(CMerchCustAccountLog cal){
		InputDTO input = new InputDTO();
		final String itemNo1 = "1";
		final String itemNo2 = "2";
		if(cal == null){
			LOG.error("==== 没有找到对应的账户变更记录！");
			return null;
		}
		Header header = input.new Header();
		
		header.setCrmRelatedId(cal.getId().toString());
		header.setReferTo(cal.getId().toString());
		//销售组织作为公司存入
		CrmSalesOrganization org = organizationService.findById(cal.getOrganizationId());
		String sapId = org.getSapId();
		//保证金公司代码1570
		//String companyCode = companyOrgMapper.selectBySalesOrg(sapId);
		String companyCode ="1570";
		header.setCompanyCode(StringUtils.isBlank(companyCode)?sapId:companyCode);
		CMerchCustBase cust = merchCustBaseMapper.selectByPrimaryKey(cal.getMerchCustId());
		header.setText("CRM供应商"+cust.getName()+"("+cust.getSapCustomerId()+")保证金");
		String type = cal.getType();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		//明细
		List<Item> items = new ArrayList<Item>();
		Item i1 = input.new Item();
		i1.setItemNo(itemNo1);
		Item i2 = input.new Item();
		i2.setItemNo(itemNo2);
		//当公司码为1160时，所有行项目增加业务范围1161
		if("1160".equals(header.getCompanyCode())){
			i1.setBusinessScope("1161");
			i2.setBusinessScope("1161");
		}
		//账户调整保证金
		if("3".equals(type) && "4".equals(cal.getAccountType())){
			CMerchCustAdjust ca = custAdjustMapper.selectByPrimaryKey(cal.getOrderId());
			header.setMoneyType(OperationType.MARGINS.getCode());
			header.setVoucherType(OperationType.MARGINS.getVoucherType());
			header.setCurrency("CNY");
			Date updateTs = ca.getUpdateTs() == null?ca.getCreateTs():ca.getUpdateTs();
			//账户调整暂时由调整时间作为收款时间，更新时间作为过账时间
			header.setReceiveDate(sdf.format(ca.getCreateTs()));
			header.setAccountDate(sdf.format(updateTs));
			String amt = BigDecimalASME.divide(ca.getAmt().abs()).toString();
			//变动金额是正数时，保证金上账，负数时保证金转出
			boolean addFlag = (BigDecimal.ZERO.compareTo(ca.getAmt()) <= 0);
			String headText = header.getText()+(addFlag?"上账":"转出");
			//
			i1.setAccountCode(addFlag?"40":"50");
			i1.setResonCode("112");
			i1.setSubject(ca.getBankAccount());//公司的银行科目（非客户来款银行）
			i1.setAmt(amt);
			i1.setForeignAmt(amt);
			i1.setText(headText);
			
			i2.setAccountCode(addFlag?"39":"29");
			i2.setFlag("3");
			i2.setAmt(amt);
			i2.setForeignAmt(amt);
			i2.setText(headText);
			i2.setSupplier(cust.getSapCustomerId());
			i2.setBillReceivedDate(sdf.format(updateTs));
			
			items.add(i1);
			items.add(i2);
		}
		//其余情况不做调整
		else{
			return null;
		}
		input.setHeader(header);
		input.setItems(items);
		return input;
	}

	@Override
	public String constructParam(Object obj) {
		Assert.isTrue(obj instanceof CMerchCustAccountLog);
		CMerchCustAccountLog cal = (CMerchCustAccountLog) obj;
		InputDTO inputDto = constructInputParam(cal);
		return JsonUtil.toJSON(inputDto);
	}
}

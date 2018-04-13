package com.hhnz.jco.business.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.hhnz.account.mapper.CMerchUpaccountMapper;
import com.hhnz.account.model.CMerchCustAccountLog;
import com.hhnz.account.model.CMerchCustAdjust;
import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.crm.mapper.TMaterialFactoryMapper;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
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
 * 账户调整传SAP
 * @author: chaoyang.ren
 * @date:2016年9月21日
 * @time:下午4:45:31
 * @email:chaoyang.ren@foxmail.com
 */
@Component("accountAdjustRFC")
@Transactional(propagation=Propagation.REQUIRED)
public class AccountAdjustRFC extends AbstractRfcCaller {
	private static final Log LOG = LogFactory.getLog(AccountAdjustRFC.class);
	
	@Resource
	private TMaterialFactoryMapper materialFactoryMapper;
	@Resource
	private CMerchCustAccountLogMapper accountLogMapper;
	@Resource
	private CMerchUpaccountMapper upAccountMapper;
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
	 * @date:2016年8月9日  下午5:07:28
	 * @param input
	 */
	public BaseResultDTO execute(InputDTO input){
		String inputJson = JsonUtil.toJSON(input);
		return this.execute(new RfcRedoDto(inputJson, RfcExeType.ACCOUNT));
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
		super.executeInThread(new RfcRedoDto(inputJson, RfcExeType.ACCOUNT, c));
	}

	/**
	 * 调用接口更新账户凭证
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
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
	 * <p>账户接口只有在资金上账和账户进行现金调整时调用，其他情况此参数构造方法返回{@code null}值</p>
	 * 
	 * <p>明细{@code Item}行说明：<br/>
	 * 根据收款类型不同，现金收款时需同时有40和11记账码的至少两个行项目<br/>
	 * 票据收款时需同时有09和11记账码的至少两个行项目<br/>
	 * 调整时需同时有01和11记账码的至少两个行项目<br/>
	 * </p>
	 * 
	 * @author: chaoyang.ren 
	 * @date:2016年9月28日  下午3:58:59
	 * @param accountLogId
	 * @return
	 * 资金上账和账户进行现金调整时,返回{@code instance of} {@link InputDTO};
	 * 其他情况返回{@code null}
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
		//销售组织/公司
		CrmSalesOrganization org = organizationService.findById(cal.getOrganizationId());
		String sapId = org.getSapId();
		
		//推送资金上账操作人信息
		TEmployee e = ApplicationContextUtil.getCurrentUser();
		if(e != null){
			header.setOperationUser(e.getLoginName());
		}
		
		String companyCode = companyOrgMapper.selectBySalesOrg(sapId);
		CMerchCustBase cust = merchCustBaseMapper.selectByPrimaryKey(cal.getMerchCustId());
		
		// 合作盐业公司1421下的 零售客户推送时，公司推送为1420
        if ("5".equals(cust.getCustType()) && cust.getPid() != null) {
          CMerchCustBase transfer = merchCustBaseMapper.selectByPrimaryKey(cust.getPid());
          if (transfer != null && transfer.getPid()!=null && "70".equals(transfer.getCustType())) {
            CMerchCustBase saltCop = merchCustBaseMapper.selectByPrimaryKey(transfer.getPid());
            if(saltCop!=null && "1421".equals(saltCop.getSapCustomerId())){
            	companyCode = "1420";
            }
          }
        }
		header.setCompanyCode(StringUtils.isBlank(companyCode)?sapId:companyCode);
		//日期+XX配送商+“代收货款”/日期+“收”+客户名称+“货款”
		
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
		//资金上账
		if("1".equals(type)){
			//零售商
			String headText = null;
			if("2".equals(cust.getCustType())){
				Long pid = cust.getPid();
				//配送商
				CMerchCustBase pcust = merchCustBaseMapper.selectByPrimaryKey(pid);
				String pcustName = pcust.getName();
				headText = pcustName+"代收货款";
			}
			//经销商
			else if("1".equals(cust.getCustType())){
				headText = "收"+cust.getName()+"货款";
			}
			//其他
			else{
				headText = "收"+cust.getName()+"货款";
			}
			
			CMerchUpaccount upaccount = upAccountMapper.selectByPrimaryKey(cal.getOrderId());
			String payType = upaccount.getPayType();
			String amt = BigDecimalASME.divide(upaccount.getPayAmt()).toString();
			//现金
			if("1".equals(payType)){
				headText = upaccount.getPayDate()+headText;
				header.setMoneyType(OperationType.CASH.getCode());
				header.setVoucherType(OperationType.CASH.getVoucherType());
				
				//明细
				i1.setAccountCode("40");
				i1.setSubject(upaccount.getBankAccount());//公司的银行科目（非客户来款银行）
				i1.setAmt(amt);
				i1.setResonCode("100");
				i1.setForeignAmt(amt);
				i1.setText(headText);
				
				i2.setAccountCode("11");
				i2.setAmt(amt);
				i2.setForeignAmt(amt);
				i2.setText(upaccount.getPayDate()+"收"+cust.getName()+"货款");
				i2.setCustomerId(cust.getSapCustomerId());
				i2.setName(cust.getId()+cust.getName());
				i2.setCity(cust.getCityName());
				items.add(i1);
				items.add(i2);
			}
			//票据
			else if("2".equals(payType)){
				header.setMoneyType(OperationType.BILL.getCode());
				header.setVoucherType(OperationType.BILL.getVoucherType());
				String itemText = sdf.format(upaccount.getCreateTs())+"收"+cust.getName()+"承兑汇票";
				headText = itemText;
				
				//明细
				i1.setAccountCode("09");
				i1.setFlag("W");
				i1.setAmt(amt);
				i1.setForeignAmt(amt);
				i1.setText(itemText);
				i1.setCustomerId(cust.getSapCustomerId());
				i1.setBillNo(upaccount.getBillNo());
				i1.setBillDate(sdf.format(upaccount.getBillOutDate()));
				//获取银行字典信息
				try {
					Map<String,List<TDict>> map = ApplicationContextUtil.get(SessionKey.DICT_NAME);
					List<TDict> dict = map.get("BANK");
					for (TDict d : dict) {
						if(StringUtils.isNoneBlank(d.getChooseVal()) && d.getChooseVal().equals(upaccount.getBillBank())){
							i1.setBillBank(d.getShowText());
							break;
						}
					}
				} catch (Exception e1) {
					LOG.error("票据上账时获取银行信息失败！", e1);
				}
				if(StringUtils.isBlank(i1.getBillBank())){
					i1.setBillBank(upaccount.getBillBank());
				}
				i1.setBillReceivedDate(sdf.format(upaccount.getBillInDate()));
				i1.setBillPerson(upaccount.getBillOutName());
				i1.setReceivePerson(upaccount.getBillInName());
				
				i2.setAccountCode("11");
				i2.setAmt(amt);
				i2.setForeignAmt(amt);
				i2.setText(itemText);
				i2.setCustomerId(cust.getSapCustomerId());
				
				items.add(i1);
				items.add(i2);
			}
			header.setText(headText);
			header.setReceiveDate(sdf.format(upaccount.getCreateTs()));
			header.setAccountDate(sdf.format(upaccount.getUpdateTs()));
		}
		//账户调整现金
		else if("3".equals(type) && "1".equals(cal.getAccountType())){
			CMerchCustAdjust ca = custAdjustMapper.selectByPrimaryKey(cal.getOrderId());
			header.setMoneyType(OperationType.ADJUST.getCode());
			header.setVoucherType(OperationType.ADJUST.getVoucherType());
			Date updateTs = ca.getUpdateTs() == null?ca.getCreateTs():ca.getUpdateTs();
			String headText = ca.getReason();//TODO reason code to text
			header.setText(headText);
			//账户调整暂时由调整时间作为收款时间，更新时间作为过账时间
			header.setReceiveDate(sdf.format(ca.getCreateTs()));
			header.setAccountDate(sdf.format(updateTs));
			String amt = BigDecimalASME.divide(ca.getAmt().abs()).toString();
			//变动金额是正数时，客户资金增加，关联客户资金减少
			boolean addFlag = (BigDecimal.ZERO.compareTo(ca.getAmt()) <= 0);
			//客户间调整明细-分别为客户可用资金余额增加方与客户可用资金余额减少方
			if("1".equals(ca.getAdjustDirection())){
				
				
				i1.setAccountCode(addFlag?"11":"01");
				i1.setAmt(amt);
				i1.setForeignAmt(amt);
				i1.setText(headText);
				i1.setCustomerId(cust.getSapCustomerId().toString());
				i1.setName(cust.getId()+cust.getName());
				i1.setCity(cust.getCityName());
				
				CMerchCustBase tcust = merchCustBaseMapper.selectByPrimaryKey(ca.getTargetMerchCustId());
				i2.setAccountCode(addFlag?"01":"11");
				i2.setAmt(amt);
				i2.setForeignAmt(amt);
				i2.setText(headText);
				i2.setCustomerId(tcust.getSapCustomerId());
				i2.setName(tcust.getId()+tcust.getName());
				i2.setCity(tcust.getCityName());
				
				items.add(i1);
				items.add(i2);
			}
			//银行科目调整
			else if("2".equals(ca.getAdjustDirection())){
				//明细
				i1.setAccountCode(addFlag?"40":"50");
				i1.setSubject(ca.getBankAccount());//公司的银行科目（非客户来款银行）
				i1.setAmt(amt);
				i1.setForeignAmt(amt);
				i1.setResonCode("100");
				i1.setText(headText);
				
				i2.setAccountCode(addFlag?"11":"01");
				i2.setAmt(amt);
				i2.setForeignAmt(amt);
				i2.setText(headText);
				i2.setCustomerId(cust.getSapCustomerId());
				i2.setName(cust.getId()+cust.getName());
				i2.setCity(cust.getCityName());
				items.add(i1);
				items.add(i2);
			}
			else{
				return null;
			}
			
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

package com.hhnz.jco.business.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hhnz.customer.mapper.CMerchCustBaseMapper;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustDistribution;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.jco.DefaultRFCInvoker;
import com.hhnz.jco.RFCConstants;
import com.hhnz.jco.RFCInvoker;
import com.hhnz.jco.business.base.AbstractRfcCaller;
import com.hhnz.jco.business.base.CallbackParam;
import com.hhnz.jco.business.base.CommonResult;
import com.hhnz.jco.business.base.RFCCallback;
import com.hhnz.jco.business.customer.InputDTO.Customer;
import com.hhnz.jco.business.customer.InputDTO.UnloadingPort;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.organization.mapper.CompanyOrgMapper;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.JsonUtil;

/**
 * @author: chaoyang.ren
 * @date:2016年8月9日
 * @time:下午4:23:23
 * @email:chaoyang.ren@foxmail.com
 */
@Component("customerRFC")
@Transactional
public class CustomerRFC extends AbstractRfcCaller {
	private static final Log LOG = LogFactory.getLog(CustomerRFC.class);
	@Resource
    private CMerchCustBaseMapper merchCustBaseMapper;
	@Resource
    private RuntimeService runtimeService;
	@Resource
    private ProcessEngine processEngine;
	@Resource
	private TaskService taskService;
	@Autowired
	private ICustomerService customerService; 
	@Autowired
	private IorganizationService organizationService;
	@Resource
	private CustomerContractService contractService;
	@Resource
	private CompanyOrgMapper companyOrgMapper;
	
	/**
	 * 客户主数据传SAP
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
	 * @param input
	 */
	public ResultDTO execute(InputDTO input){
		String inputJson = JsonUtil.toJSON(input);
		return this.execute(new RfcRedoDto(inputJson, RfcExeType.CUSTOMER));
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
		super.executeInThread(new RfcRedoDto(inputJson, RfcExeType.CUSTOMER, c));
	}
	
	/**
	 * 客户主数据传SAP
	 * @author: chaoyang.ren 
	 * @date:2016年8月9日  下午5:07:28
	 * @param inputJson 输入参数{@link RfcRedoDto}
	 */
	@Override
	public ResultDTO execute(RfcRedoDto rfcDto) {
		LOG.info("===== CALL EXECUTE METHOD!");
		RFCInvoker rfcInvoker = new DefaultRFCInvoker();
		String json = null;
		RFCCallback c = rfcDto.getRfcCallback();
		try {
			json = rfcInvoker.invokeRFC(rfcDto.getRfcExeType().getFuncName(), rfcDto.getJsonParam());
		} catch (Exception e) {
			LOG.error("从RFC接口获取数据失败！", e);
			//执行失败后缓存参数
			failureRedo(rfcDto);
			//如果失败次数达到上限,则申请失败
			InputDTO input = JsonUtil.fromJSON(rfcDto.getJsonParam(), InputDTO.class);
			CMerchCustBase mb = merchCustBaseMapper.selectByPrimaryKey(Long.valueOf(input.getCustomer().getId()));
			processKeepOn(mb.getProcessId(), false, "接口传输失败！"+e.getMessage());
			ResultDTO result = new ResultDTO();
			result.setResult(CommonResult.error(e));
			//执行错误后的回调
			if(c != null){
				c.errorCallBack(CallbackParam.of(mb.getId()));
			}
			return result;
		}
		ResultDTO result = JsonUtil.fromJSON(json, ResultDTO.class);
		CommonResult cresult = result.getResult();
		if(!cresult.getTYPE().equals(RFCConstants.SUCCESS_FLAG)){
			LOG.warn(cresult.getMESSAGE());
			//如果返回错误则直接申请失败
			InputDTO input = JsonUtil.fromJSON(rfcDto.getJsonParam(), InputDTO.class);
			CMerchCustBase mb = merchCustBaseMapper.selectByPrimaryKey(Long.valueOf(input.getCustomer().getId()));
			processKeepOn(mb.getProcessId(), false, cresult.getMESSAGE());
			//执行错误后的回调
			if(c != null){
				c.errorCallBack(CallbackParam.of(mb.getId(),cresult));
			}
			return result;
		}
		//执行成功后触发流程
		CMerchCustBase merchCustBase = merchCustBaseMapper.selectByPrimaryKey(Long.valueOf(result.getCustomerId()));
		merchCustBase.setSapCustomerId(result.getSapCustomerId());
		merchCustBaseMapper.updateByPrimaryKeySelective(merchCustBase);
		processKeepOn(merchCustBase.getProcessId(), true, StringUtils.EMPTY, result.getSapCustomerId());
		//执行成功后的回调
		if(c != null){
			c.successCallBack(CallbackParam.of(merchCustBase.getId(),result.getSapCustomerId(),cresult));
		}
		return result;
	}
	
	/**
	 * 下一步流程
	 * @author: chaoyang.ren 
	 * @date:2016年9月28日  上午11:07:21
	 * @param processId
	 * @param isSuccess
	 * @param sapId
	 */
	private void processKeepOn(String processId, boolean isSuccess, String msg, String... sapId){
		try {
			Execution execution = runtimeService.createExecutionQuery().processInstanceId(processId).activityId("RECV_SAP").singleResult();
			if(execution != null){
				String id = execution.getId();
				Authentication.setAuthenticatedUserId("sap接口");
				taskService.addComment(null, processId, msg);
				this.runtimeService.setVariable(processId,"MSG",msg);
				this.runtimeService.setVariable(processId,"FLAG",isSuccess?1:0);
				if(isSuccess){
					this.runtimeService.setVariable(processId,"sapId",sapId[0]);
				}
				this.runtimeService.signal(id);
			}else{
				LOG.warn("客户推送SAP后未触发客户创建的'RECV_SAP'流程");
			}
		} catch (Exception e) {
			LOG.error("客户推送SAP后未触发客户创建的'RECV_SAP'流程",e);
		}
	}
	
	public String constructParam(Serializable id){
		InputDTO inputDto = constructInputParam(Long.valueOf(id.toString()));
		return JsonUtil.toJSON(inputDto);
	}
	
	/**
	 * 构造接口输入参数
	 * @author: chaoyang.ren 
	 * @date:2016年9月28日  上午11:07:14
	 * @param id
	 * @return
	 */
	public InputDTO constructInputParam(Long id){
		//处理SAP传入参数
        CMerchCustBase custBase = customerService.findCustBaseById(id);
		return constructInputParam(custBase);
	}
	
	/**
	 * 构造接口输入参数
	 * @author: chaoyang.ren 
	 * @date:2016年9月28日  上午11:07:14
	 * @param id
	 * @return
	 */
	public InputDTO constructInputParam(CMerchCustBase custBase){
		//处理SAP传入参数
		InputDTO input = new InputDTO();
		Customer c = input.new Customer();
		c.setId(custBase.getId().toString());
		c.setName(custBase.getName());
		c.setName2(custBase.getName());
		c.setAbbrName(custBase.getAbbrName());
//		c.setAddedTaxNo(custBase.getInvoiceTaxNum());
		c.setAddedTaxNo(custBase.getBusinessLicense());
		String sapCustomerId = custBase.getSapCustomerId();
		if(StringUtils.isNotBlank(sapCustomerId) && sapCustomerId.startsWith(RFCConstants.X_FLAG)){
			c.setSapCustomerId(sapCustomerId.substring(1, sapCustomerId.length()));
		}
		//产品组
		CrmSalesOrganization org = organizationService.findById(custBase.getOrganizationId());
		String sapId = null;
		if(org != null){
			sapId = org.getSapId();
			CrmSalesOrganization g = organizationService.findById(org.getPid());
			if(g != null){
				c.setProductGroup(RFCConstants.GROUP_MAPPING.get(g.getId()));
			}
		}
		//目前均默认分销渠道"10"
		c.setChannel("10");
		c.setSalesOrg(sapId);
		c.setTel(custBase.getTel());
		c.setContacter(custBase.getContactName());
		
		//公司
		String companyCode = companyOrgMapper.selectBySalesOrg(sapId);
		c.setCompanyCode(StringUtils.isBlank(companyCode)?sapId:companyCode);
		//销售区域
		//c.setSalesAreaCode("Y0002");
		//销售部门
		//c.setSalesDepartCode("Y300");
		/*
		 * 工厂和库存地点不传SAP
		 * modified by chaoyang 20161019
		 */
		/*//工厂
		c.setFactory("1100");
		//库存地点
		c.setVwLocation(custBase.getCustType());*/
		/*
		 * 账户组
		 * 售达方Z001,送达方Z002
		 * 售达方可以作为送达方使用，但当客户地址与送达方地址不一致时，需调用两次接口分别传输售达方与送达方
		 */
		if("6".equals(custBase.getCustType()) || "70".equals(custBase.getCustType())){
			c.setAccountGroup(RFCConstants.SEND_TO);
		}else{
			c.setAccountGroup(RFCConstants.SALE_TO);
		}
		// 合作盐业公司下的 零售客户 销售组织改为1420 公司代码1420
        if ("5".equals(custBase.getCustType()) && custBase.getPid() != null) {
          CMerchCustBase transfer = customerService.findCustBaseById(custBase.getPid());
          if (transfer != null && transfer.getPid()!=null && "70".equals(transfer.getCustType())) {
            CMerchCustBase saltCop = customerService.findCustBaseById(transfer.getPid());
            if(saltCop!=null && "1421".equals(saltCop.getSapCustomerId())){
              c.setSalesOrg("1420");
              c.setCompanyCode("1420");
            }
          }
        }
		input.setCustomer(c);
		
		//no banks currently
		/*List<CustomerBank> banks = new ArrayList<InputDTO.CustomerBank>();
		CustomerBank b1 = input.new CustomerBank();
		b1.setAccountName(custBase.getInvoiceAccountName());
		b1.setAccountNo(custBase.getInvoiceAccount());
		b1.setBankNo(custBase.getInvoiceBankName());//sap code too.
		banks.add(b1);
		input.setBanks(banks);*/
		
		//到站信息
		List<UnloadingPort> ports = new ArrayList<InputDTO.UnloadingPort>();
		for (CMerchCustDistribution dis : custBase.getDistributions()) {
			String site = StringUtils.isBlank(dis.getSite())?custBase.getAddress():dis.getSite();
			if(StringUtils.isNotBlank(site)){
				UnloadingPort port = input.new UnloadingPort();
				port.setName(site);
				//如果有重复的站点，则只传输一条，重复传输会造成SAP异常：插入时表 KNVA, 科目 1100600 中的系统错误
				if(!ports.contains(port)){
					ports.add(port);
				}
			}
		}
		input.setPorts(ports);
		return input;
	}

	@Override
	public String constructParam(Object obj) {
		Assert.isTrue(obj instanceof CMerchCustBase);
		CMerchCustBase custBase = (CMerchCustBase) obj;
		InputDTO inputDto = constructInputParam(custBase);
		return JsonUtil.toJSON(inputDto);
	}
	
}

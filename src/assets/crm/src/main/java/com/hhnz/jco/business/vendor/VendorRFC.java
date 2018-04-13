package com.hhnz.jco.business.vendor;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hhnz.crm.model.TEmployee;
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
import com.hhnz.jco.business.vendor.InputDTO.Vendor;
import com.hhnz.jco.enu.RfcExeType;
import com.hhnz.jco.job.RfcRedoDto;
import com.hhnz.organization.mapper.CompanyOrgMapper;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.JsonUtil;

/**
 * @author: chaoyang.ren
 * @date:2016年12月21日
 * @time:下午4:43:39
 * @email:chaoyang.ren@foxmail.com
 */
@Component("vendorRFC")
@Transactional
public class VendorRFC extends AbstractRfcCaller {
	private static final Log LOG = LogFactory.getLog(VendorRFC.class);

	@Autowired
	private ICustomerService customerService;
//	@Autowired
//	private IorganizationService organizationService;
	@Resource
	private CompanyOrgMapper companyOrgMapper;
	
	@Override
	public BaseResultDTO execute(RfcRedoDto rfcDto) {
		LOG.info("===== CALL EXECUTE METHOD!");
		RFCInvoker rfcInvoker = new DefaultRFCInvoker();
		String json = null;
		RFCCallback c = rfcDto.getRfcCallback();
		InputDTO input = JsonUtil.fromJSON(rfcDto.getJsonParam(), InputDTO.class);
		try {
			json = rfcInvoker.invokeRFC(rfcDto.getRfcExeType().getFuncName(), rfcDto.getJsonParam());
		} catch (Exception e) {
			LOG.error("从RFC接口获取数据失败！", e);
			//执行失败后缓存参数
			failureRedo(rfcDto);
			//如果失败次数达到上限,则申请失败
			ResultDTO result = new ResultDTO();
			result.setResult(CommonResult.error(e));
			//执行错误后的回调
			if(c != null){
				c.errorCallBack(CallbackParam.of(Long.valueOf(input.getVendor().getCrmRelatedId())));
			}
			return result;
		}
		ResultDTO result = JsonUtil.fromJSON(json, ResultDTO.class);
		CommonResult cresult = result.getResult();
		//如果返回错误则直接申请失败
		if(!cresult.getTYPE().equals(RFCConstants.SUCCESS_FLAG)){
			LOG.warn(cresult.getMESSAGE());
			//执行错误后的回调
			if(c != null){
				c.errorCallBack(CallbackParam.of(Long.valueOf(input.getVendor().getCrmRelatedId()),cresult));
			}
			return result;
		}
		//执行成功后的回调
		if(c != null){
			c.successCallBack(CallbackParam.of(Long.valueOf(input.getVendor().getCrmRelatedId()),result.getSapCustomerId(),cresult));
		}
		return result;
	}

	@Override
	public String constructParam(Serializable id) {
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
		Vendor c = input.new Vendor();
		c.setCrmRelatedId(custBase.getId().toString());
		c.setName(custBase.getName());
//		c.setTaxNo(custBase.getInvoiceTaxNum());
		c.setTaxNo(custBase.getBusinessLicense());
		String sapCustomerId = custBase.getSapCustomerId();
		if(StringUtils.isNotBlank(sapCustomerId) && sapCustomerId.startsWith(RFCConstants.X_FLAG)){
			c.setSapCustomerId(sapCustomerId.substring(1, sapCustomerId.length()));
		}
		//产品组
//		String orgId = custBase.getOrganizationId();
		String sapId = null;
		//公司
		String companyCode = null;
		/*//辽宁益盐堂的仓储服务商推向SAP的供应商建档接口参数调整到1570公司下；
		if("T0103".equalsIgnoreCase(orgId)){
			companyCode = "1570";
		}else{
			CrmSalesOrganization org = organizationService.findById(orgId);
			if(org != null){
				sapId = org.getSapId();
			}
			companyCode = companyOrgMapper.selectBySalesOrg(sapId);
		}*/
		companyCode = "1570";//供应商均推送至1570
		c.setCompany(StringUtils.isBlank(companyCode)?sapId:companyCode);
		c.setStreet(custBase.getAddress());
		c.setSort(custBase.getAbbrName());
		//操作人信息
		TEmployee e = ApplicationContextUtil.getCurrentUser();
		if(e != null){
			c.setCreateName(e.getLoginName());
		}else{
			c.setCreateName("system");
		}
		input.setVendor(c);
		return input;
	}

	/**
	 * @author: chaoyang.ren 
	 * @date:2016年12月21日  下午5:47:29
	 * @param vendor
	 * @param simpleName
	 */
	public void executeInThread(InputDTO vendor,
			String simpleName) {
		String inputJson = JsonUtil.toJSON(vendor);
		RFCCallback c = null;
		if(StringUtils.isNotBlank(simpleName)){
			c = ApplicationContextUtil.getBean(simpleName);
		}
		super.executeInThread(new RfcRedoDto(inputJson, RfcExeType.VENDOR, c));
	}

	@Override
	public String constructParam(Object obj) {
		Assert.isTrue(obj instanceof CMerchCustBase);
		CMerchCustBase custBase = (CMerchCustBase) obj;
		InputDTO inputDto = constructInputParam(custBase);
		return JsonUtil.toJSON(inputDto);
	}
}

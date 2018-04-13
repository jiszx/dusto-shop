package com.hhnz.salepolicy.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.order.model.OrderMaterial;
import com.hhnz.organization.mapper.CrmSalesOrganizationMapper;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.model.CrmSalesOrganizationExample;
import com.hhnz.salepolicy.mapper.OmPolicyCustMapper;
import com.hhnz.salepolicy.mapper.OmPolicyHeadersMapper;
import com.hhnz.salepolicy.mapper.OmPolicyLinesAllVMapper;
import com.hhnz.salepolicy.mapper.OmPolicyLinesMapper;
import com.hhnz.salepolicy.mapper.OmPolicyTypeMapper;
import com.hhnz.salepolicy.mapper.OmPolicyWriteOffMapper;
import com.hhnz.salepolicy.mapper.PolicyUtilMapper;
import com.hhnz.salepolicy.model.OmPolicyCust;
import com.hhnz.salepolicy.model.OmPolicyCustExample;
import com.hhnz.salepolicy.model.OmPolicyHeaders;
import com.hhnz.salepolicy.model.OmPolicyLines;
import com.hhnz.salepolicy.model.OmPolicyLinesAllV;
import com.hhnz.salepolicy.model.OmPolicyLinesAllVExample;
import com.hhnz.salepolicy.model.OmPolicyLinesExample;
import com.hhnz.salepolicy.model.OmPolicyType;
import com.hhnz.salepolicy.model.OmPolicyTypeExample;
import com.hhnz.salepolicy.model.OmPolicyWriteOff;
import com.hhnz.salepolicy.model.OmPolicyWriteOffExample;
import com.hhnz.salepolicy.model.PolicyCustArea;
import com.hhnz.salepolicy.model.PolicyMaterialModel;
import com.hhnz.salepolicy.model.PolicySearchModel;
import com.hhnz.salepolicy.service.SalePolicyService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.JsonUtil;
import com.hhnz.util.db.Page;

@Service
@Transactional
public class SalePolicyServiceImpl implements SalePolicyService {
	
	@Resource
	private OmPolicyTypeMapper typemapper;
	
	@Resource
	private PolicyUtilMapper  utilmapper;
	
	@Resource
	private OmPolicyHeadersMapper headmapper;
	
	@Resource
	private OmPolicyLinesMapper  linemapper;
	
	@Resource
	private OmPolicyLinesAllVMapper  linesvmapper;
	
	@Resource
	private CrmSalesOrganizationMapper orgmapper;
	
	@Resource
	private OmPolicyCustMapper policycustmapper;
	
	@Resource
	private OmPolicyWriteOffMapper  writeoffmapper;
	@Override
	public AjaxDTO findPolicyTypeList(String states, AjaxDTO bean) {
		// TODO Auto-generated method stub
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		OmPolicyTypeExample ex = new OmPolicyTypeExample();
		ex.setPage(page);
		List<OmPolicyType> list = this.typemapper.selectByExample(ex);
		int total = this.typemapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}
	
	@Override
	public Integer addPolicyType(OmPolicyType model) {
		// TODO Auto-generated method stub
		return this.typemapper.insert(model);
	}

	@Override
	public Integer delPolicyType(Long id) {
		// TODO Auto-generated method stub
		return  this.typemapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer editPolicyType(OmPolicyType model) {
		// TODO Auto-generated method stub
		return this.typemapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public AjaxDTO getpolicyType() {
		// TODO Auto-generated method stub
		AjaxDTO dto = new AjaxDTO();
		OmPolicyTypeExample ex = new OmPolicyTypeExample();
		List<OmPolicyType> list = this.typemapper.selectByExample(ex);
		int total = this.typemapper.countByExample(ex);
		dto.setRows(list);
		dto.setTotal(total);
		return dto;
	}

	/*@Override
	public AjaxDTO getpolicyorg(Long userid) {
		AjaxDTO dto = new AjaxDTO();
		List<PolicyOrgModel> list = new ArrayList<PolicyOrgModel>();
		list = this.utilmapper.getPolicyOrg(userid);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}*/

	@Override
	public AjaxDTO getPolicyMaterial(String orgid) {
		AjaxDTO dto = new AjaxDTO();
		List<PolicyMaterialModel> list = this.utilmapper.getPolicyMaterial(orgid);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}

	@Override
	public Integer addHeader(OmPolicyHeaders policyheader) {
		// TODO Auto-generated method stub
		return this.headmapper.insert(policyheader);
	}

	/*@Override
	public Integer addLine(OmPolicyLines line) {
		// TODO Auto-generated method stub
		return this.linemapper.insert(line);
	}*/

	@Override
	public AjaxDTO getPolicyList(Map<String, Object> params) {
		AjaxDTO dto = new AjaxDTO();
		List<PolicySearchModel> list = this.utilmapper.getPolicyList(params);
		dto.setRows(list);
		dto.setTotal(list.size());
		return dto;
	}
	
	@Override
	public PolicySearchModel getPolicyDetail(Map<String, Object> params){
	  PolicySearchModel policy = this.utilmapper.getPolicyList(params).get(0);
	  List<OrderMaterial> materials = utilmapper.getPolicyMaterials(params);
	  policy.setMaterials(materials);
	  return policy;
	}

	@Override
	public String submit(String states, Long headerid) {
		// TODO Auto-generated method stub
		OmPolicyHeaders  policy = this.headmapper.selectByPrimaryKey(headerid);
		policy.setStates(states);
		int result =this.headmapper.updateByPrimaryKeySelective(policy);
		return result==1?"200":"500";
	}

	@Override
	public String delpolicy(Long headerid) {
		this.headmapper.deleteByPrimaryKey(headerid);
		OmPolicyLinesExample  ex = new OmPolicyLinesExample();
		OmPolicyLinesExample.Criteria ext = ex.createCriteria();
		ext.andHeaderIdEqualTo(headerid);
		this.linemapper.deleteByExample(ex);
		OmPolicyCustExample  custex = new OmPolicyCustExample();
		OmPolicyCustExample.Criteria custext= custex.createCriteria();
		custext.andHeaderIdEqualTo(headerid);
		this.policycustmapper.deleteByExample(custex);
		return "200";
	}

	@Override
	public Map<String,Object> getpolicyBYid(Long id) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		OmPolicyHeaders  policy = new OmPolicyHeaders();
		policy= this.headmapper.selectByPrimaryKey(id);
		OmPolicyType  policytype= this.typemapper.selectByPrimaryKey(policy.getPolicyType());
		
		CrmSalesOrganization org = StringUtils.isEmpty(policy.getDept())?this.orgmapper.selectByPrimaryKey(policy.getOrganizationId()):this.orgmapper.selectByPrimaryKey(policy.getDept());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		changeShowAmt(policy);
		map.put("policy", policy);
		map.put("bdate", sdf.format(policy.getbDate()));
		map.put("eDate", sdf.format(policy.geteDate()));
		map.put("policyType", policytype);
		map.put("dept", org.getName());
		return map;
	}
	
	private void changeShowAmt(OmPolicyHeaders policy){
	  if(policy!=null){
	    policy.setAmt(BigDecimalASME.divide(policy.getAmt()));
	  }
	}

	@Override
	public AjaxDTO getPolicyLines(Long id) {
		// TODO Auto-generated method stub
		OmPolicyLinesAllVExample  ex  =new OmPolicyLinesAllVExample();
		OmPolicyLinesAllVExample.Criteria ext = ex.createCriteria();
		ext.andHeaderIdEqualTo(id);
		List<OmPolicyLinesAllV> lines = new ArrayList<OmPolicyLinesAllV>();
		lines = this.linesvmapper.selectByExample(ex);
		AjaxDTO dto =new AjaxDTO();
		dto.setRows(lines);
		dto.setTotal(lines.size());
		return dto;
	}

	@Override
	public int delPolicyLine(Long id) {
		// TODO Auto-generated method stub
		/*OmPolicyLines line = this.linemapper.selectByPrimaryKey(id);
		OmPolicyHeaders policyheader =  this.headmapper.selectByPrimaryKey(line.getHeaderId());
		if(policyheader.getStates()=="1"){
			//如果是编辑状态的销售政策，物理删除
			return this.linemapper.deleteByPrimaryKey(id);
		}else{
			//逻辑删除
			line.setStates("2");
			return this.linemapper.updateByPrimaryKeySelective(line);
		}*/
		return this.linemapper.deleteByPrimaryKey(id);
	}

	@Override
	public int editPolicyLine(OmPolicyLines line) {
		if(line.getId() !=null && !StringUtils.isEmpty(line.getId())){
			return this.linemapper.updateByPrimaryKeySelective(line);
		}else{
			return this.linemapper.insert(line);
		}
	}

	@Override
	public List<CrmSalesOrganization> getSalepolicyArea(String orgid) {
		CrmSalesOrganizationExample ex = new CrmSalesOrganizationExample();
		CrmSalesOrganizationExample.Criteria ext = ex.createCriteria();
		ext.andIdLike(orgid+"%");
		ext.andLevelsLessThanOrEqualTo("5");
		return this.orgmapper.selectByExample(ex);
	}

	@Override
	public List<PolicyCustArea> getAreacCust(String orgids) {
		String[] orgid= orgids.split(",");
		List<String> orgs  = new ArrayList<String>();
		for(String id: orgid){
			orgs.add(id.replaceAll(" ", ""));
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orgid", orgs);
		return this.utilmapper.getAreaCust(params);
	}

	@Override
	public String addLine(Long headerid, String lines, String orgids,
			String custs) {
		OmPolicyHeaders header= this.headmapper.selectByPrimaryKey(headerid);
		if(!StringUtils.isEmpty(lines)){
			List<OmPolicyLines> list=new ArrayList<OmPolicyLines>();
			list=JsonUtil.fromJSON(lines, new TypeToken<List<OmPolicyLines>>() {}.getType());
			if(list !=null && list.size()>0){
				for(OmPolicyLines line:list){
					line.setHeaderId(headerid);
					line.setId(null);
					line.setStates("1");
					try{
						this.linemapper.insert(line);					
					}catch(Exception e){
						return "500";
					}
				}
			}
		}
		if(!StringUtils.isEmpty(custs)){
			//客户不为空
			String[] cust = custs.split(",");
			for(String merch :cust){
				OmPolicyCust  customer = new OmPolicyCust();
				customer.setHeaderId(headerid);
				customer.setMerchCustId(Long.parseLong(merch.replaceAll(" ", "")));
				customer.setState("1");
				this.policycustmapper.insert(customer);
			}
		}
		if(!StringUtils.isEmpty(orgids) && StringUtils.isEmpty(custs)){
			//客户为空，执行范围不为空的情况下
			List<PolicyCustArea> list = getAreacCust(orgids);
			if(list !=null && list.size()>0){
				for(PolicyCustArea custarea :list){
					OmPolicyCust  customer = new OmPolicyCust();
					customer.setHeaderId(headerid);
					customer.setMerchCustId(custarea.getId());
					customer.setState("1");
					this.policycustmapper.insert(customer);
				}
			}
		}
		header.setAttribute1(orgids);
		header.setAttrbute2(custs);
		this.headmapper.updateByPrimaryKeySelective(header);
		return "200";
	}

	@Override
	public OmPolicyHeaders getpolicyById(Long headerid) {
		// TODO Auto-generated method stub
		return this.headmapper.selectByPrimaryKey(headerid);
	}

	@Override
	public int updatePolicyHeader(OmPolicyHeaders policy) {
		// TODO Auto-generated method stub
		return this.headmapper.updateByPrimaryKeySelective(policy);
	}

	@Override
	public List<CrmSalesOrganization> getPolicyArea(Long id) {
		// TODO Auto-generated method stub
		return this.utilmapper.getPolicyArea(id);
	}

	@Override
	public List<CMerchCustBase> getPolicyCustArea(Long id) {
		// TODO Auto-generated method stub
		return this.utilmapper.getPolicyCustArea(id);
	}

	@Override
	public AjaxDTO writeofflist(Long policyid,AjaxDTO bean) {
		// TODO Auto-generated method stub
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        OmPolicyWriteOffExample  ex = new OmPolicyWriteOffExample();
        OmPolicyWriteOffExample.Criteria ext = ex.createCriteria();
        ext.andPolicyIdEqualTo(policyid);
        ex.setPage(page);
        ex.setOrderByClause("create_ts ");
        List<OmPolicyWriteOff> list = this.writeoffmapper.selectByExample(ex);
        int total = this.writeoffmapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
		return bean;
	}

}

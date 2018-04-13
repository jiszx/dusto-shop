package com.hhnz.organization.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.organization.mapper.CrmOrgAreaMapper;
import com.hhnz.organization.mapper.CrmOrganizationInvoicesMapper;
import com.hhnz.organization.mapper.CrmOrganizationInvoicesVMapper;
import com.hhnz.organization.mapper.CrmSalesOrganizationMapper;
import com.hhnz.organization.mapper.CrmStationMapper;
import com.hhnz.organization.model.CrmOrgArea;
import com.hhnz.organization.model.CrmOrgAreaExample;
import com.hhnz.organization.model.CrmOrganizationInvoices;
import com.hhnz.organization.model.CrmOrganizationInvoicesV;
import com.hhnz.organization.model.CrmOrganizationInvoicesVExample;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.model.CrmSalesOrganizationExample;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.organization.model.CrmStationExample;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.pub.mapper.TAreaMapper;
import com.hhnz.pub.model.TArea;
import com.hhnz.pub.model.TAreaExample;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

/**
 * Created by yang on 2016-7-6.
 */
@Service
@Transactional
public class OrganizationServiceImpl implements IorganizationService {


    @Autowired
    private CrmSalesOrganizationMapper mapper;

    @Autowired
    private CrmOrgAreaMapper areaMapper;

    @Autowired
    private TAreaMapper aMapper;
    @Autowired
    private CrmStationMapper stationMapper;

    @Resource
    private CrmOrganizationInvoicesVMapper invoicesvmapper;
    
    @Resource
    private CrmOrganizationInvoicesMapper infomapper;
    @Override
    public List<CrmSalesOrganization> findAll() throws Exception {
        CrmSalesOrganizationExample ex = new CrmSalesOrganizationExample();
        ex.createCriteria().andTypeNotEqualTo("-1").andStatesEqualTo("0");
        ex.setOrderByClause("ID ASC");
        return this.mapper.selectByExample(ex);
    }
    
    @Override
    public List<CrmSalesOrganization> findPid(String pid) {
    	CrmSalesOrganizationExample ex = new CrmSalesOrganizationExample();
    	if(StringUtils.hasLength(pid)){
    		ex.createCriteria().andStatesEqualTo("0").andPidEqualTo(pid);
    	}else{
    		ex.createCriteria().andStatesEqualTo("0").andPidEqualTo("0");
    	}
    	ex.setOrderByClause("ID ASC");
    	return this.mapper.selectByExample(ex);
    }

    @Override
    public Integer addOrg(CrmSalesOrganization model) throws Exception {
        CrmSalesOrganizationExample ex = new CrmSalesOrganizationExample();
        ex.createCriteria().andPidEqualTo(model.getPid());
        ex.setOrderByClause("id desc");
        List<CrmSalesOrganization> orgs = this.mapper.selectByExample(ex);
        if(orgs.size()>0){
            CrmSalesOrganization maxOrg = orgs.get(0);
            String maxId = maxOrg.getId();
            String order = maxId.replaceAll(model.getPid(),"");
            Integer orderNum = Integer.parseInt(order);
            int newId = orderNum+1;
            String id = model.getPid()+String.format("%02d",newId);
            String level = ((id.length()-1)/2) +"";
            model.setLevels(level);
            model.setId(id);
        }else{
            model.setId(model.getPid()+"01");
            String level = (model.getId().length()-1)/2 +"";
            model.setLevels(level);
        }
//        if(model.getId().length() == 9){
//            model.setLevels("1");
//        }else{
//            model.setLevels("0");
//        }
        model.setStates("0");
        return this.mapper.insert(model);
    }

    @Override
    public Integer editOrg(CrmSalesOrganization model) throws Exception {
        return this.mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public Integer orgGrantArea(String id, List<String> auths) throws Exception {
        CrmOrgAreaExample ex = new CrmOrgAreaExample();
        ex.createCriteria().andOrgIdEqualTo(id);
        int res = this.areaMapper.deleteByExample(ex);
        if(res != -1){
            for(String aid:auths){
                if("0000".equals(aid)){
                    continue;
                }else{
                    CrmOrgArea key = new CrmOrgArea();
                    key.setId(id+aid);
                    key.setOrgId(id);
                    key.setAreaId(aid);
                   TArea area = this.aMapper.selectByPrimaryKey(aid);
                    key.setName(area.getName());
                    if("0000".equals(area.getPid())){
                        key.setPid(id);
                    }else{
                        key.setPid(id+area.getPid());
                    }
                    res+=this.areaMapper.insert(key);
                }
            }
        }

        return res;
    }

    @Override
    public List<String> findArea(String id) throws Exception {
        CrmOrgAreaExample ex = new CrmOrgAreaExample();
        ex.createCriteria().andOrgIdLike(id+"%");
        List<CrmOrgArea> keys = this.areaMapper.selectByExample(ex);
        List<String> ids = new ArrayList<String>();
        for (CrmOrgArea key:keys) {
            ids.add(key.getAreaId());
        }
        return ids;
    }

    @Override
    public List<CrmOrgArea> findOrgArea() throws Exception {
        return this.areaMapper.selectByExample(new CrmOrgAreaExample());
    }
    
    
    @Override
    public List<TArea> findAreas(String id, String filter, TEmployee user) throws RuntimeException{
      TAreaExample ex = new TAreaExample();
      if(StringUtils.hasLength(id)){
        ex.createCriteria().andPidEqualTo(id);
      }else{
        if("1".equals(filter)){
          CrmStationExample stationEx = new CrmStationExample();
          stationEx.createCriteria().andSalesrepIdEqualTo(user.getId());
          List<CrmStation> stations = stationMapper.selectByExample(stationEx);
          List<String> areas = new ArrayList<>();
          for(CrmStation station:stations){
            areas.add(station.getOrgAreaId());
          }
          ex.createCriteria().andIdIn(areas);
        }else{
          ex.createCriteria().andPidEqualTo("0000");
        }
        
      }
      return this.aMapper.selectByExample(ex);
    }

    @Override
    public List<CrmSalesOrganization> findByLevel(String leve) throws Exception {
        CrmSalesOrganizationExample ex =new CrmSalesOrganizationExample();
        ex.createCriteria().andLevelsEqualTo(leve);
        return this.mapper.selectByExample(ex);
    }

	/*@Override
	public List<CrmSalesOrganization> getcompanybyid(String orgid) {
		// TODO Auto-generated method stub
		CrmSalesOrganizationExample ex =new CrmSalesOrganizationExample();
		CrmSalesOrganizationExample.Criteria ext =  ex.createCriteria();
		ext.andLevelsEqualTo("2");
		ext.andIdLike(orgid+"%");
		List<CrmSalesOrganization> list =this.mapper.selectByExample(ex);
		for(int i=0;i<list.size();i++){
			CrmSalesOrganization  org= this.mapper.selectByPrimaryKey(list.get(i).getPid());
			list.get(i).setName(org.getName()+"-"+list.get(i).getName());
		}
		return list;
	}*/

    @Override
    public CrmSalesOrganization findById(String id) {
        CrmSalesOrganizationExample ex = new CrmSalesOrganizationExample();
        ex.createCriteria().andStatesEqualTo("0").andIdEqualTo(id);
        List<CrmSalesOrganization> orgs = this.mapper.selectByExample(ex);
        if(orgs != null && orgs.size()>0){
        	return orgs.get(0);
        }
        return null;
    }

	/* (non-Javadoc)
	 * @see com.hhnz.organization.service.IorganizationService#findBySapId(java.lang.String)
	 */
	@Override
	public CrmSalesOrganization findBySapId(String sapId) {
		CrmSalesOrganizationExample ex = new CrmSalesOrganizationExample();
        ex.createCriteria().andStatesEqualTo("0").andSapIdEqualTo(sapId);
        List<CrmSalesOrganization> orgs = this.mapper.selectByExample(ex);
        if(orgs != null && orgs.size()>0){
        	for (CrmSalesOrganization crmSalesOrganization : orgs) {
				if(crmSalesOrganization.getId().length() == 5){
					return crmSalesOrganization;
				}
			}
        }
        return null;
	}

    @Override
    public List<CrmSalesOrganization> findLessLevel(String level) throws Exception {
        CrmSalesOrganizationExample ex =new CrmSalesOrganizationExample();
        ex.createCriteria().andTypeNotEqualTo("-1").andLevelsLessThanOrEqualTo(level);
        return this.mapper.selectByExample(ex);
    }

	@Override
	public AjaxDTO getInfoInvoices(AjaxDTO bean) {
		AjaxDTO dto = new AjaxDTO();
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        CrmOrganizationInvoicesVExample ex=new CrmOrganizationInvoicesVExample();
        List<CrmOrganizationInvoicesV> list = this.invoicesvmapper.selectByExample(ex);
        int total = this.invoicesvmapper.countByExample(ex);
        dto.setRows(list);
        dto.setTotal(total);
		return dto;
	}

	@Override
	public Integer maintainInfoInvoices(CrmOrganizationInvoices model) {
		CrmOrganizationInvoices  info = this.infomapper.selectByPrimaryKey(model.getOrganizationId());
		if(info !=null){
			//更新
			return this.infomapper.updateByPrimaryKeySelective(model);
		}else{
			return this.infomapper.insert(model);
		}
	}
}

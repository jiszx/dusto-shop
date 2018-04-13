package com.hhnz.cost.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.cost.mapper.CrmCostBalancesVMapper;
import com.hhnz.cost.mapper.CrmCostLogVMapper;
import com.hhnz.cost.model.CrmCostBalancesV;
import com.hhnz.cost.model.CrmCostBalancesVExample;
import com.hhnz.cost.model.CrmCostLogV;
import com.hhnz.cost.model.CrmCostLogVExample;
import com.hhnz.cost.service.ICostBalanceService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

@Service
@Transactional
public class CostBalanceServiceImpl implements ICostBalanceService {
	@Resource
	private CrmCostBalancesVMapper  banlanceVMapper;
	
	@Resource
	private CrmCostLogVMapper  logvmapper;
	public AjaxDTO balancelist(AjaxDTO bean,String regionId, String organizationId, String costTypeid) {
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        CrmCostBalancesVExample  ex = new CrmCostBalancesVExample();
        CrmCostBalancesVExample.Criteria ext = ex.createCriteria();
        if(!StringUtils.isEmpty(regionId)){
        	ext.andRegionIdEqualTo(regionId);
        }
        if(!StringUtils.isEmpty(organizationId)){
        	ext.andOrgidEqualTo(organizationId);
        }
        if(!StringUtils.isEmpty(costTypeid)){
        	ext.andCostTypeidEqualTo(costTypeid);
        }
        ex.setPage(page);
        ex.setOrderByClause("period desc");
        List<CrmCostBalancesV> list = this.banlanceVMapper.selectByExample(ex);
        int total = this.banlanceVMapper.countByExample(ex);
        AjaxDTO dto = new AjaxDTO();
        dto.setRows(list);
        dto.setTotal(total);
		return dto;
	}

	@Override
	public CrmCostBalancesV getCostBalanceById(Long id) {
		// TODO Auto-generated method stub
		CrmCostBalancesVExample  ex = new CrmCostBalancesVExample();
		ex.createCriteria().andIdEqualTo(id);
		return this.banlanceVMapper.selectByExample(ex).get(0);
	}

	@Override
	public AjaxDTO getDetailList(AjaxDTO bean, Long id) {
		// TODO Auto-generated method stub
		Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        CrmCostLogVExample ex = new CrmCostLogVExample();
        //CrmCostLogVExample.Criteria ext = ex.createCriteria();
        ex.setPage(page);
        List<CrmCostLogV> list = this.logvmapper.selectByExample(ex);
        int total = this.logvmapper.countByExample(ex);
        bean.setRows(list);
        bean.setTotal(total);
		return bean;
	}

	@Override
	public String getMaxPeriod() {
		return this.banlanceVMapper.getMaxPeriod();
	}

	@Override
	public String upBalance(String period) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("p_period", period);
		this.banlanceVMapper.upBalance(map);
		return map.get("p_result").toString();
	}

}

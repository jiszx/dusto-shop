package com.hhnz.cost.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.cost.mapper.CrmCostAdjustMapper;
import com.hhnz.cost.mapper.CrmCostAdjustVMapper;
import com.hhnz.cost.mapper.CrmCostMainVMapper;
import com.hhnz.cost.model.CrmCostAdjust;
import com.hhnz.cost.model.CrmCostAdjustV;
import com.hhnz.cost.model.CrmCostAdjustVExample;
import com.hhnz.cost.model.CrmCostAdjustVExample.Criteria;
import com.hhnz.cost.model.CrmCostMainV;
import com.hhnz.cost.model.CrmCostMainVExample;
import com.hhnz.cost.service.ICostAdjustService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
import com.hhnz.util.db.Page;

@Service
@Transactional
public class CostAdjustServiceImpl implements ICostAdjustService {
	@Resource
	private CrmCostAdjustVMapper vmapper;
	@Resource
	private CrmCostMainVMapper mainmapper;
	@Resource
	private CrmCostAdjustMapper adjustMapper;
	
    @Override
    public AjaxDTO findBalanceList(AjaxDTO bean, CrmCostMainV cost) {
      return findBalanceList(bean, cost, null);
    }

	@Override
	public AjaxDTO findBalanceList(AjaxDTO bean, CrmCostMainV cost, List<String> provs) {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		CrmCostMainVExample ex = new CrmCostMainVExample();
		ex.setPage(page);
		if(StringUtils.isNotEmpty(cost.getOrganizationId())){
		  ex.or(ex.createCriteria().andOrganizationIdEqualTo(cost.getOrganizationId()));
		}
		if(StringUtils.isNotEmpty(cost.getRegionId())){
		  ex.or(ex.createCriteria().andRegionIdEqualTo(cost.getRegionId()));
		}
		if(StringUtils.isNotEmpty(cost.getCostTypeid()) && cost.getCostTypeid()!="0"){
		  ex.or(ex.createCriteria().andCostTypeidEqualTo((String)cost.getCostTypeid()));
		}
		ex.setProvs(provs);
		List<CrmCostMainV> list = this.mainmapper.selectByExample(ex);
		int total = this.mainmapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public AjaxDTO findAdjustList(CrmCostAdjustV adjust, AjaxDTO bean) {
		Page page = new Page();
		page.setLimit(bean.getLimit());
		page.setOffset(bean.getOffset());
		CrmCostAdjustVExample ex = new CrmCostAdjustVExample();
		ex.setPage(page);
		Criteria param = ex.createCriteria();
		if(StringUtils.isNoneEmpty(adjust.getReginId())){
		  param.andReginIdEqualTo(adjust.getReginId());
		}
		if(StringUtils.isNotEmpty(adjust.getOrganizationId())){
		  param.andOrganizationIdEqualTo(adjust.getOrganizationId());
		}
		if(adjust.getCostTypeid()!=null && adjust.getCostTypeid()!="0"){
		  param.andCostTypeidEqualTo(adjust.getCostTypeid());
		}
		ex.setOrderByClause("create_ts desc");
		List<CrmCostAdjustV> list = this.vmapper.selectByExample(ex);
		int total = this.vmapper.countByExample(ex);
		bean.setRows(list);
		bean.setTotal(total);
		return bean;
	}
	
	@Override
	public int saveAdjust(CrmCostAdjust adjust){
	  adjust.setType("1"); // 手动调整
	  adjust.setStates("1");
	  //DateTime now = DateTime.now();
	  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
	  adjust.setPeriod(sf.format(new Date()));
	  adjust.setCreateTs(new Date());
	  BigDecimal amt = adjust.getAdjustAmt();
	  adjust.setAdjustAmt(BigDecimalASME.multiply(amt==null?BigDecimal.ZERO:amt));
	  return adjustMapper.insert(adjust);
	}
	
	@Override
	public int deleteAdjust(Long id){
	  return adjustMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int editAdjust(CrmCostAdjust adjust){
	  CrmCostAdjust record = adjustMapper.selectByPrimaryKey(adjust.getId());
	  BigDecimal amt = adjust.getAdjustAmt();
	  record.setAdjustAmt(BigDecimalASME.multiply(amt==null?BigDecimal.ZERO:amt));
	  record.setReason(adjust.getReason());
	  return adjustMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public int censor(Long id){
	  CrmCostAdjust record = adjustMapper.selectByPrimaryKey(id);
	  if(StringUtils.equals("1", record.getStates())){
	    record.setStates("2");
	    adjustMapper.updateByPrimaryKey(record);
	    return 1;
	  }
	  return 0;
	}
	
	@Override
	public int updateProcessId(Long id, String processId){
	  CrmCostAdjust record = adjustMapper.selectByPrimaryKey(id);
	  record.setProcessId(processId);
	  return adjustMapper.updateByPrimaryKey(record);
	}

	@Override
	public CrmCostAdjust findCostAdjustById(Long id) {
		return this.adjustMapper.selectByPrimaryKey(id);
	}

	@Override
	public int update(CrmCostAdjust adjust) throws Exception {
		return this.adjustMapper.updateByPrimaryKeySelective(adjust);
	}

	@Override
	public Integer updateCost() {
		return this.vmapper.updateCostMain();
	}
	
	@Override
	public CrmCostAdjustV costAdjustDetail(Long id){
	  CrmCostAdjustVExample ex = new CrmCostAdjustVExample();
	  ex.createCriteria().andIdEqualTo(id);
	  List<CrmCostAdjustV> costs = vmapper.selectByExample(ex);
	  if(costs.isEmpty()){
	    return null;
	  }
	  return costs.get(0);
	}

}

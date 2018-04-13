package com.hhnz.cost.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hhnz.cost.mapper.CrmBudgetMainMapper;
import com.hhnz.cost.mapper.CrmBudgetMainVMapper;
import com.hhnz.cost.model.CrmBudgetMain;
import com.hhnz.cost.model.CrmBudgetMainV;
import com.hhnz.cost.model.CrmBudgetMainVExample;
import com.hhnz.cost.model.CrmBudgetMainVExample.Criteria;
import com.hhnz.cost.service.ICostBudgetService;
import com.hhnz.crm.mapper.TMaterialFactoryMapper;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TMaterialFactory;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;


@Service
public class CostBudgetServiceImpl implements ICostBudgetService {

  @Resource
  private CrmBudgetMainMapper budgetMapper;
  @Resource
  private CrmBudgetMainVMapper budgetVMapper;
  @Resource
  private TMaterialFactoryMapper materialFactoryMapper;

  @Override
  public AjaxDTO findBudgets(AjaxDTO condition, CrmBudgetMainV budget) {
    Page page = new Page();
    page.setLimit(condition.getLimit());
    page.setOffset(condition.getOffset());
    CrmBudgetMainVExample ex = new CrmBudgetMainVExample();
    Criteria param = ex.createCriteria();
    if (StringUtils.isNotEmpty(budget.getOrganizationId())) {
      param.andOrganizationIdEqualTo(budget.getOrganizationId());
    }
    if (StringUtils.isNotEmpty(budget.getRegionId())) {
      param.andRegionIdEqualTo(budget.getRegionId());
    }
    if (StringUtils.isNotEmpty(budget.getProvinceId())) {
      param.andProvinceIdEqualTo(budget.getProvinceId());
    }
    if (StringUtils.isNotEmpty(budget.getCostType())) {
      param.andCostTypeEqualTo(budget.getCostType());
    }
    if (StringUtils.isNotEmpty(budget.getPeriod())) {
      param.andPeriodEqualTo(budget.getPeriod());
    }
    if (StringUtils.isNotEmpty(budget.getCustname())) {
      param.andCustnameLike("%" + budget.getCustname() + "%");
    }
    ex.setPage(page);
    ex.setOrderByClause("create_ts desc");
    List<CrmBudgetMainV> list = this.budgetVMapper.selectByExample(ex);
    int total = this.budgetVMapper.countByExample(ex);
    condition.setRows(list);
    condition.setTotal(total);
    return condition;
  }
  
  public int materialFactoryImport(String[][] text){
    int startRow = 0;
    int startCol = 0;
    loop: for (int i = 0; i < text.length; i++) {
      for (int j = 0; j < text[i].length; j++) {
        if (StringUtils.isNotEmpty(text[i][j])) {
          startRow = i+1;
          startCol = j;
          break loop;
        }
      }
    }
    for(int i=startRow;i<text.length;i++){
      TMaterialFactory tmf = new TMaterialFactory();
      if(text[i][startCol+2].equals("1")){
        tmf.setFacotryId("1100");
      }else{
        continue;
      }
      tmf.setMaterialId(text[i][startCol]);
      tmf.setStates("1");
      materialFactoryMapper.insert(tmf);
    }
    for(int i=startRow;i<text.length;i++){
      TMaterialFactory tmf = new TMaterialFactory();
      if(text[i][startCol+3].equals("1")){
        tmf.setFacotryId("1320");
      }else{
        continue;
      }
      tmf.setMaterialId(text[i][startCol]);
      tmf.setStates("1");
      materialFactoryMapper.insert(tmf);
    }
    for(int i=startRow;i<text.length;i++){
      TMaterialFactory tmf = new TMaterialFactory();
      if(text[i][startCol+4].equals("1")){
        tmf.setFacotryId("1420");
      }else{
        continue;
      }
      tmf.setMaterialId(text[i][startCol]);
      tmf.setStates("1");
      materialFactoryMapper.insert(tmf);
    }
    return 0;
  }

  @Override
  public int importBudget(String[][] text, TEmployee user) {
    int result = 0;
    if (text == null) {
      return 0;
    }
    int startRow = 0;
    int startCol = 0;
    loop: for (int i = 0; i < text.length; i++) {
      for (int j = 0; j < text[i].length; j++) {
        if (StringUtils.isNotEmpty(text[i][j])) {
          startRow = i+1;
          startCol = j;
          break loop;
        }
      }
    }
    
    List<CrmBudgetMain> budgets = new ArrayList<>();
    for(int i=startRow;i<text.length;i++){
      CrmBudgetMain budget = transfer(text[i], startCol, user);
      int validate = validateBudget(budget);
      if(validate==0){
        return 0;
      }
      budgets.add(budget);
    }
    for(CrmBudgetMain budget:budgets){
      result +=budgetMapper.insert(budget);
    }

    return result;
  }
  
  private int validateBudget(CrmBudgetMain budget){
    boolean isAnyNull = isAnyNull(budget.getAmt(), budget.getOrganizationId(), budget.getPeriod(), 
        budget.getRegionId(), budget.getCostSubject(), budget.getProvinceId(), budget.getMerchCustId(),
        budget.getCostType(), budget.getStates());
    return isAnyNull?0:1;
  }
  
  private boolean isAnyNull(Object... values){
    if(values==null){
      return true;
    }
    for(Object value:values){
      if(value==null){
        return true;
      }
    }
    return false;
  }
  
  private CrmBudgetMain transfer(String[] text, int startCol, TEmployee user){
    CrmBudgetMain budget = new CrmBudgetMain();
    budget.setOrganizationId(text[startCol]);
    budget.setRegionId(text[startCol+1]);
    budget.setProvinceId(text[startCol+2]);
    budget.setMerchCustId(Long.parseLong(text[startCol+3]));
    budget.setCostType(text[startCol+4]);
    budget.setCostSubject(text[startCol+5]);
    budget.setPeriod(text[startCol+6]);
    budget.setAmt(Long.parseLong(text[startCol+7]));
    
    budget.setCreateOid(user.getId());
    budget.setCreateTs(new Date());
    budget.setType(budget.getPeriod().indexOf('-')>0?"2":"1");
    budget.setStates("1");
    return budget;
  }

}

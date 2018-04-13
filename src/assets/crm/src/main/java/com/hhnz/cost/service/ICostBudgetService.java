package com.hhnz.cost.service;

import com.hhnz.cost.model.CrmBudgetMainV;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.util.AjaxDTO;

public interface ICostBudgetService {

  AjaxDTO findBudgets(AjaxDTO condition, CrmBudgetMainV budget);

  int importBudget(String[][] text, TEmployee user);

}

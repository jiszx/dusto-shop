package com.hhnz.cost.service;

import java.util.List;

import com.hhnz.cost.model.CrmCostAdjust;
import com.hhnz.cost.model.CrmCostAdjustV;
import com.hhnz.cost.model.CrmCostMainV;
import com.hhnz.util.AjaxDTO;

public interface ICostAdjustService {

  AjaxDTO findBalanceList(AjaxDTO bean, CrmCostMainV cost);

  AjaxDTO findAdjustList(CrmCostAdjustV adjust, AjaxDTO bean);
  
  AjaxDTO findBalanceList(AjaxDTO bean, CrmCostMainV cost, List<String> provs);

  int saveAdjust(CrmCostAdjust adjust);

  int deleteAdjust(Long id);

  int editAdjust(CrmCostAdjust adjust);

  int censor(Long id);

  CrmCostAdjust findCostAdjustById(Long id);

  int updateProcessId(Long id, String processId);

  int update(CrmCostAdjust adjust)throws Exception;

  Integer updateCost();

  CrmCostAdjustV costAdjustDetail(Long id);
}

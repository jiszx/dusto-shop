package com.hhnz.monitor.service;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.util.AjaxDTO;

public interface IAppManageService {

  AjaxDTO findAttachment(AjaxDTO page);

  int saveAttachment(TAttachment record, TEmployee user);

}

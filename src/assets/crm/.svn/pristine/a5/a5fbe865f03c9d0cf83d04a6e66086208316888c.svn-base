package com.hhnz.monitor.service;

import java.io.IOException;
import java.util.List;

import com.hhnz.monitor.model.CrmExport;
import com.hhnz.util.AjaxDTO;

public interface CommonExportService {

  String generateExcel(String exportKey, List<Long> stations) throws IOException;

  AjaxDTO list(AjaxDTO page);

  int saveOrUpdate(CrmExport bean);

  int deleteExportConfig(CrmExport bean);

}

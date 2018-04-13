package com.hhnz.receivable.service;

import java.io.IOException;
import java.util.Map;

import com.hhnz.util.AjaxDTO;

public interface ArOverdueService {

	AjaxDTO selectArOverdueList(Map<String, Object> params);

	AjaxDTO selectArOverdueDetails(AjaxDTO bean, Long merchId, Long arPeriod);

	String exportDetails(Long merchId, Long arPeriod) throws IOException;

	String exportAll(Map<String, Object> params)throws IOException;

}

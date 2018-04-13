package com.hhnz.logistics.service;

import java.util.List;
import java.util.Map;

import com.hhnz.logistics.dto.LogisticsRdcAreaDTO;
import com.hhnz.util.AjaxDTO;

public interface ILogisticsRdcAreaService {

	AjaxDTO selectUserRdcList(Map<String, Object> params);

	List<String> getRdcAreaByUserId(Long id);

	int addUserRdc(Long userId, String rdcCode);

	Integer saveRdcArea(Long id, List<String> areas);

	Integer del(Long id);

	List<LogisticsRdcAreaDTO> getexportExl(Map<String, Object> params);
}

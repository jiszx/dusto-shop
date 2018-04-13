package com.hhnz.logistics.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.logistics.dto.LogisticsRdcAreaDTO;


public interface LogisticsRdcAreaMapper {

	List<LogisticsRdcAreaDTO> selectUserRdcList(Map<String, Object> params);

	int countUserRdcList(Map<String, Object> params);

	List<LogisticsRdcAreaDTO> getexportExl(Map<String, Object> params);

}

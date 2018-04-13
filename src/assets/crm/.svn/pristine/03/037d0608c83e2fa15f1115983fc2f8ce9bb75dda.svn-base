package com.hhnz.monitor.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hhnz.monitor.model.CrmExport;

public interface CrmExportMapper {

  List<Map<String, ?>> executeSql(@Param(value = "sql") String sql);
  
  CrmExport findByKey(String key);
  
  List<CrmExport> findAll(Map<String, ?> param);
  
  int countFindAll(Map<String, ?> param);
  
  int insert(CrmExport bean);
  
  int update(CrmExport bean);
  
  int deleteById(long id);

}

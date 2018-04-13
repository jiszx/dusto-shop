package com.hhnz.monitor.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hhnz.monitor.model.TLog;
import com.hhnz.monitor.model.TLogExample;

public interface TLogMapper {
    int countByExample(TLogExample example);

    int deleteByExample(TLogExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(TLog record);

    int insertSelective(TLog record);

    List<TLog> selectByExample(TLogExample example);

    TLog selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") TLog record, @Param("example") TLogExample example);

    int updateByExample(@Param("record") TLog record, @Param("example") TLogExample example);

    int updateByPrimaryKeySelective(TLog record);

    int updateByPrimaryKey(TLog record);
}
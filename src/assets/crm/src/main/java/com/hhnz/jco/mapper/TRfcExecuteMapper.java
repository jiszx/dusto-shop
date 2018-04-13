package com.hhnz.jco.mapper;

import com.hhnz.jco.model.TRfcExecute;
import com.hhnz.jco.model.TRfcExecuteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRfcExecuteMapper {
    int countByExample(TRfcExecuteExample example);

    int deleteByExample(TRfcExecuteExample example);

    int deleteByPrimaryKey(String serialNo);

    int insert(TRfcExecute record);

    int insertSelective(TRfcExecute record);

    List<TRfcExecute> selectByExample(TRfcExecuteExample example);

    TRfcExecute selectByPrimaryKey(String serialNo);

    int updateByExampleSelective(@Param("record") TRfcExecute record, @Param("example") TRfcExecuteExample example);

    int updateByExample(@Param("record") TRfcExecute record, @Param("example") TRfcExecuteExample example);

    int updateByPrimaryKeySelective(TRfcExecute record);

    int updateByPrimaryKey(TRfcExecute record);
}
package com.hhnz.jco.mapper;

import com.hhnz.jco.model.TRfcExecuteHistory;
import com.hhnz.jco.model.TRfcExecuteHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRfcExecuteHistoryMapper {
    int countByExample(TRfcExecuteHistoryExample example);

    int deleteByExample(TRfcExecuteHistoryExample example);

    int deleteByPrimaryKey(String serialNo);

    int insert(TRfcExecuteHistory record);

    int insertSelective(TRfcExecuteHistory record);

    List<TRfcExecuteHistory> selectByExample(TRfcExecuteHistoryExample example);

    TRfcExecuteHistory selectByPrimaryKey(String serialNo);

    int updateByExampleSelective(@Param("record") TRfcExecuteHistory record, @Param("example") TRfcExecuteHistoryExample example);

    int updateByExample(@Param("record") TRfcExecuteHistory record, @Param("example") TRfcExecuteHistoryExample example);

    int updateByPrimaryKeySelective(TRfcExecuteHistory record);

    int updateByPrimaryKey(TRfcExecuteHistory record);
}
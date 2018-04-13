package com.hhnz.config.mapper;

import com.hhnz.config.model.TScheduleJob;
import com.hhnz.config.model.TScheduleJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TScheduleJobMapper {
    int countByExample(TScheduleJobExample example);

    int deleteByExample(TScheduleJobExample example);

    int deleteByPrimaryKey(Long jid);

    int insert(TScheduleJob record);

    int insertSelective(TScheduleJob record);

    List<TScheduleJob> selectByExample(TScheduleJobExample example);

    TScheduleJob selectByPrimaryKey(Long jid);

    int updateByExampleSelective(@Param("record") TScheduleJob record, @Param("example") TScheduleJobExample example);

    int updateByExample(@Param("record") TScheduleJob record, @Param("example") TScheduleJobExample example);

    int updateByPrimaryKeySelective(TScheduleJob record);

    int updateByPrimaryKey(TScheduleJob record);
}
package com.hhnz.config.mapper;

import com.hhnz.config.model.ProcessDeploy;
import com.hhnz.config.model.ProcessDeployExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProcessDeployMapper {
    int countByExample(ProcessDeployExample example);

    int deleteByExample(ProcessDeployExample example);

    int insert(ProcessDeploy record);

    int insertSelective(ProcessDeploy record);

    List<ProcessDeploy> selectByExample(ProcessDeployExample example);

    int updateByExampleSelective(@Param("record") ProcessDeploy record, @Param("example") ProcessDeployExample example);

    int updateByExample(@Param("record") ProcessDeploy record, @Param("example") ProcessDeployExample example);
}
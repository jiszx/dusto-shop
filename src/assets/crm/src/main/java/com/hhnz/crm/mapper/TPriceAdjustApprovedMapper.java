package com.hhnz.crm.mapper;

import com.hhnz.crm.model.TPriceAdjustApproved;
import com.hhnz.crm.model.TPriceAdjustApprovedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPriceAdjustApprovedMapper {
    int countByExample(TPriceAdjustApprovedExample example);

    int deleteByExample(TPriceAdjustApprovedExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TPriceAdjustApproved record);

    int insertSelective(TPriceAdjustApproved record);

    List<TPriceAdjustApproved> selectByExample(TPriceAdjustApprovedExample example);

    TPriceAdjustApproved selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TPriceAdjustApproved record, @Param("example") TPriceAdjustApprovedExample example);

    int updateByExample(@Param("record") TPriceAdjustApproved record, @Param("example") TPriceAdjustApprovedExample example);

    int updateByPrimaryKeySelective(TPriceAdjustApproved record);

    int updateByPrimaryKey(TPriceAdjustApproved record);

	void insertApprovedAdjustBatch(@Param("adjustApprovedList") List<TPriceAdjustApproved> adjustApprovedList);
}
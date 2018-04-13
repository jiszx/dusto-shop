package com.hhnz.account.mapper;

import com.hhnz.account.model.CMerchAccountAllocation;
import com.hhnz.account.model.CMerchAccountAllocationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CMerchAccountAllocationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    int countByExample(CMerchAccountAllocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    int deleteByExample(CMerchAccountAllocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    int insert(CMerchAccountAllocation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    int insertSelective(CMerchAccountAllocation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    List<CMerchAccountAllocation> selectByExample(CMerchAccountAllocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    CMerchAccountAllocation selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    int updateByExampleSelective(@Param("record") CMerchAccountAllocation record, @Param("example") CMerchAccountAllocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    int updateByExample(@Param("record") CMerchAccountAllocation record, @Param("example") CMerchAccountAllocationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    int updateByPrimaryKeySelective(CMerchAccountAllocation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_ACCOUNT_ALLOCATION
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    int updateByPrimaryKey(CMerchAccountAllocation record);
}
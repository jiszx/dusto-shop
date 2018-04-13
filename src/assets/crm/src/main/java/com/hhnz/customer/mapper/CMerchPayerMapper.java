package com.hhnz.customer.mapper;

import com.hhnz.customer.model.CMerchPayerExample;
import com.hhnz.customer.model.CustomerPayerDetail;
import com.hhnz.customer.model.CMerchPayer;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CMerchPayerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_PAYER
     *
     * @mbggenerated Wed Mar 29 15:04:53 CST 2017
     */
    int countByExample(CMerchPayerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_PAYER
     *
     * @mbggenerated Wed Mar 29 15:04:53 CST 2017
     */
    int deleteByExample(CMerchPayerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_PAYER
     *
     * @mbggenerated Wed Mar 29 15:04:53 CST 2017
     */
    int deleteByPrimaryKey(CMerchPayer key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_PAYER
     *
     * @mbggenerated Wed Mar 29 15:04:53 CST 2017
     */
    int insert(CMerchPayer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_PAYER
     *
     * @mbggenerated Wed Mar 29 15:04:53 CST 2017
     */
    int insertSelective(CMerchPayer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_PAYER
     *
     * @mbggenerated Wed Mar 29 15:04:53 CST 2017
     */
    List<CMerchPayer> selectByExample(CMerchPayerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_PAYER
     *
     * @mbggenerated Wed Mar 29 15:04:53 CST 2017
     */
    int updateByExampleSelective(@Param("record") CMerchPayer record, @Param("example") CMerchPayerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_PAYER
     *
     * @mbggenerated Wed Mar 29 15:04:53 CST 2017
     */
    int updateByExample(@Param("record") CMerchPayer record, @Param("example") CMerchPayerExample example);
    
    List<CustomerPayerDetail> selectPayerDetail(Map<String, ?> param);
    
    int countPayerdetail(Map<String, ?> param);
}
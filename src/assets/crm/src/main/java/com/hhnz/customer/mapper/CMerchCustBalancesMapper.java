package com.hhnz.customer.mapper;

import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CMerchCustBalancesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    int countByExample(CMerchCustBalancesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    int deleteByExample(CMerchCustBalancesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    int insert(CMerchCustBalances record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    int insertSelective(CMerchCustBalances record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    List<CMerchCustBalances> selectByExample(CMerchCustBalancesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    CMerchCustBalances selectByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    int updateByExampleSelective(@Param("record") CMerchCustBalances record, @Param("example") CMerchCustBalancesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    int updateByExample(@Param("record") CMerchCustBalances record, @Param("example") CMerchCustBalancesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    int updateByPrimaryKeySelective(CMerchCustBalances record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_BALANCES
     *
     * @mbggenerated Tue Aug 23 16:30:57 CST 2016
     */
    int updateByPrimaryKey(CMerchCustBalances record);
}
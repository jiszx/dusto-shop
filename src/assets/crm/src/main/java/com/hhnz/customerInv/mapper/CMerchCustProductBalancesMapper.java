package com.hhnz.customerInv.mapper;

import com.hhnz.customerInv.model.CMerchCustProductBalances;
import com.hhnz.customerInv.model.CMerchCustProductBalancesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CMerchCustProductBalancesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    int countByExample(CMerchCustProductBalancesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    int deleteByExample(CMerchCustProductBalancesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    int insert(CMerchCustProductBalances record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    int insertSelective(CMerchCustProductBalances record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    List<CMerchCustProductBalances> selectByExample(CMerchCustProductBalancesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    CMerchCustProductBalances selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    int updateByExampleSelective(@Param("record") CMerchCustProductBalances record, @Param("example") CMerchCustProductBalancesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    int updateByExample(@Param("record") CMerchCustProductBalances record, @Param("example") CMerchCustProductBalancesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    int updateByPrimaryKeySelective(CMerchCustProductBalances record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PRODUCT_BALANCES
     *
     * @mbggenerated Wed Dec 07 10:11:35 CST 2016
     */
    int updateByPrimaryKey(CMerchCustProductBalances record);
}
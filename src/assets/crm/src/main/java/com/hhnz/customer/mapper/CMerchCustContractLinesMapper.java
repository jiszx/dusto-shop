package com.hhnz.customer.mapper;

import com.hhnz.customer.model.CMerchCustContractLines;
import com.hhnz.customer.model.CMerchCustContractLinesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CMerchCustContractLinesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    int countByExample(CMerchCustContractLinesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    int deleteByExample(CMerchCustContractLinesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    int insert(CMerchCustContractLines record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    int insertSelective(CMerchCustContractLines record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    List<CMerchCustContractLines> selectByExample(CMerchCustContractLinesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    CMerchCustContractLines selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    int updateByExampleSelective(@Param("record") CMerchCustContractLines record, @Param("example") CMerchCustContractLinesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    int updateByExample(@Param("record") CMerchCustContractLines record, @Param("example") CMerchCustContractLinesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    int updateByPrimaryKeySelective(CMerchCustContractLines record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_CONTRACT_LINES
     *
     * @mbggenerated Thu Aug 18 15:37:36 CST 2016
     */
    int updateByPrimaryKey(CMerchCustContractLines record);
}
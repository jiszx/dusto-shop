package com.hhnz.receivable.mapper;

import com.hhnz.receivable.model.CMerchArBalance;
import com.hhnz.receivable.model.CMerchArBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CMerchArBalanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    int countByExample(CMerchArBalanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    int deleteByExample(CMerchArBalanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    int insert(CMerchArBalance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    int insertSelective(CMerchArBalance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    List<CMerchArBalance> selectByExample(CMerchArBalanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    CMerchArBalance selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    int updateByExampleSelective(@Param("record") CMerchArBalance record, @Param("example") CMerchArBalanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    int updateByExample(@Param("record") CMerchArBalance record, @Param("example") CMerchArBalanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    int updateByPrimaryKeySelective(CMerchArBalance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_AR_BALANCE
     *
     * @mbggenerated Fri Dec 02 11:05:31 CST 2016
     */
    int updateByPrimaryKey(CMerchArBalance record);
}
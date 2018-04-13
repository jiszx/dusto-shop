package com.hhnz.crm.mapper;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TEmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TEmployeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    int countByExample(TEmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    int deleteByExample(TEmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    int insert(TEmployee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    int insertSelective(TEmployee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    List<TEmployee> selectByExample(TEmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    TEmployee selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    int updateByExampleSelective(@Param("record") TEmployee record, @Param("example") TEmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    int updateByExample(@Param("record") TEmployee record, @Param("example") TEmployeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    int updateByPrimaryKeySelective(TEmployee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_EMPLOYEE
     *
     * @mbggenerated Fri Sep 23 10:05:50 CST 2016
     */
    int updateByPrimaryKey(TEmployee record);
    
    List<TEmployee> selectByJob(@Param("jid") String jid);
}
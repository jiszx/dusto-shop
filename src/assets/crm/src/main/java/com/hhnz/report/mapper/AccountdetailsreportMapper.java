package com.hhnz.report.mapper;

import com.hhnz.report.model.Accountdetailsreport;
import com.hhnz.report.model.AccountdetailsreportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountdetailsreportMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACCOUNTDETAILSREPORT
     *
     * @mbggenerated Thu Nov 17 09:44:16 CST 2016
     */
    int countByExample(AccountdetailsreportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACCOUNTDETAILSREPORT
     *
     * @mbggenerated Thu Nov 17 09:44:16 CST 2016
     */
    int deleteByExample(AccountdetailsreportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACCOUNTDETAILSREPORT
     *
     * @mbggenerated Thu Nov 17 09:44:16 CST 2016
     */
    int insert(Accountdetailsreport record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACCOUNTDETAILSREPORT
     *
     * @mbggenerated Thu Nov 17 09:44:16 CST 2016
     */
    int insertSelective(Accountdetailsreport record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACCOUNTDETAILSREPORT
     *
     * @mbggenerated Thu Nov 17 09:44:16 CST 2016
     */
    List<Accountdetailsreport> selectByExample(AccountdetailsreportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACCOUNTDETAILSREPORT
     *
     * @mbggenerated Thu Nov 17 09:44:16 CST 2016
     */
    int updateByExampleSelective(@Param("record") Accountdetailsreport record, @Param("example") AccountdetailsreportExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ACCOUNTDETAILSREPORT
     *
     * @mbggenerated Thu Nov 17 09:44:16 CST 2016
     */
    int updateByExample(@Param("record") Accountdetailsreport record, @Param("example") AccountdetailsreportExample example);
}
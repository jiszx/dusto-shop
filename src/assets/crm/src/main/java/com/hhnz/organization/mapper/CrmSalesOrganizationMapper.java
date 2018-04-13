package com.hhnz.organization.mapper;

import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.model.CrmSalesOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmSalesOrganizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    int countByExample(CrmSalesOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    int deleteByExample(CrmSalesOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    int insert(CrmSalesOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    int insertSelective(CrmSalesOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    List<CrmSalesOrganization> selectByExample(CrmSalesOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    CrmSalesOrganization selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    int updateByExampleSelective(@Param("record") CrmSalesOrganization record, @Param("example") CrmSalesOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    int updateByExample(@Param("record") CrmSalesOrganization record, @Param("example") CrmSalesOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    int updateByPrimaryKeySelective(CrmSalesOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_SALES_ORGANIZATION
     *
     * @mbggenerated Mon Aug 01 11:51:43 CST 2016
     */
    int updateByPrimaryKey(CrmSalesOrganization record);
}
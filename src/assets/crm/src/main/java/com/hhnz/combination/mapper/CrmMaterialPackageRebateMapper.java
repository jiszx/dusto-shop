package com.hhnz.combination.mapper;

import com.hhnz.combination.model.CrmMaterialPackageRebate;
import com.hhnz.combination.model.CrmMaterialPackageRebateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmMaterialPackageRebateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    int countByExample(CrmMaterialPackageRebateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    int deleteByExample(CrmMaterialPackageRebateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    int insert(CrmMaterialPackageRebate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    int insertSelective(CrmMaterialPackageRebate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    List<CrmMaterialPackageRebate> selectByExample(CrmMaterialPackageRebateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    CrmMaterialPackageRebate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    int updateByExampleSelective(@Param("record") CrmMaterialPackageRebate record, @Param("example") CrmMaterialPackageRebateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    int updateByExample(@Param("record") CrmMaterialPackageRebate record, @Param("example") CrmMaterialPackageRebateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    int updateByPrimaryKeySelective(CrmMaterialPackageRebate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_REBATE
     *
     * @mbggenerated Wed Dec 21 11:20:23 CST 2016
     */
    int updateByPrimaryKey(CrmMaterialPackageRebate record);
}
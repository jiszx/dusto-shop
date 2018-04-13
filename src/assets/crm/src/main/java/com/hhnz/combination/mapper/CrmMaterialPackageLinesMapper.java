package com.hhnz.combination.mapper;

import com.hhnz.combination.model.CrmMaterialPackageLines;
import com.hhnz.combination.model.CrmMaterialPackageLinesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmMaterialPackageLinesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    int countByExample(CrmMaterialPackageLinesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    int deleteByExample(CrmMaterialPackageLinesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    int insert(CrmMaterialPackageLines record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    int insertSelective(CrmMaterialPackageLines record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    List<CrmMaterialPackageLines> selectByExample(CrmMaterialPackageLinesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    CrmMaterialPackageLines selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    int updateByExampleSelective(@Param("record") CrmMaterialPackageLines record, @Param("example") CrmMaterialPackageLinesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    int updateByExample(@Param("record") CrmMaterialPackageLines record, @Param("example") CrmMaterialPackageLinesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    int updateByPrimaryKeySelective(CrmMaterialPackageLines record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_MATERIAL_PACKAGE_LINES
     *
     * @mbggenerated Tue Dec 20 17:23:22 CST 2016
     */
    int updateByPrimaryKey(CrmMaterialPackageLines record);
}
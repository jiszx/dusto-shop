package com.hhnz.organization.mapper;

import com.hhnz.organization.model.CrmStation;
import com.hhnz.organization.model.CrmStationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmStationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    int countByExample(CrmStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    int deleteByExample(CrmStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    int insert(CrmStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    int insertSelective(CrmStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    List<CrmStation> selectByExample(CrmStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    CrmStation selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    int updateByExampleSelective(@Param("record") CrmStation record, @Param("example") CrmStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    int updateByExample(@Param("record") CrmStation record, @Param("example") CrmStationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    int updateByPrimaryKeySelective(CrmStation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_STATION
     *
     * @mbggenerated Tue Jul 26 17:05:06 CST 2016
     */
    int updateByPrimaryKey(CrmStation record);
}
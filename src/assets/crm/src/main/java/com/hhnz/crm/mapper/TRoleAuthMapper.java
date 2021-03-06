package com.hhnz.crm.mapper;

import com.hhnz.crm.model.TRoleAuthExample;
import com.hhnz.crm.model.TRoleAuthKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleAuthMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ROLE_AUTH
     *
     * @mbggenerated Sat Jul 02 12:15:49 CST 2016
     */
    int countByExample(TRoleAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ROLE_AUTH
     *
     * @mbggenerated Sat Jul 02 12:15:49 CST 2016
     */
    int deleteByExample(TRoleAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ROLE_AUTH
     *
     * @mbggenerated Sat Jul 02 12:15:49 CST 2016
     */
    int deleteByPrimaryKey(TRoleAuthKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ROLE_AUTH
     *
     * @mbggenerated Sat Jul 02 12:15:49 CST 2016
     */
    int insert(TRoleAuthKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ROLE_AUTH
     *
     * @mbggenerated Sat Jul 02 12:15:49 CST 2016
     */
    int insertSelective(TRoleAuthKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ROLE_AUTH
     *
     * @mbggenerated Sat Jul 02 12:15:49 CST 2016
     */
    List<TRoleAuthKey> selectByExample(TRoleAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ROLE_AUTH
     *
     * @mbggenerated Sat Jul 02 12:15:49 CST 2016
     */
    int updateByExampleSelective(@Param("record") TRoleAuthKey record, @Param("example") TRoleAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ROLE_AUTH
     *
     * @mbggenerated Sat Jul 02 12:15:49 CST 2016
     */
    int updateByExample(@Param("record") TRoleAuthKey record, @Param("example") TRoleAuthExample example);

}
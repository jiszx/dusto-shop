package com.hhnz.crm.mapper;

import com.hhnz.crm.model.TFactoryV;
import com.hhnz.crm.model.TFactoryVExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFactoryVMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_FACTORY_V
     *
     * @mbggenerated Tue Jul 26 09:30:28 CST 2016
     */
    int countByExample(TFactoryVExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_FACTORY_V
     *
     * @mbggenerated Tue Jul 26 09:30:28 CST 2016
     */
    int deleteByExample(TFactoryVExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_FACTORY_V
     *
     * @mbggenerated Tue Jul 26 09:30:28 CST 2016
     */
    int insert(TFactoryV record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_FACTORY_V
     *
     * @mbggenerated Tue Jul 26 09:30:28 CST 2016
     */
    int insertSelective(TFactoryV record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_FACTORY_V
     *
     * @mbggenerated Tue Jul 26 09:30:28 CST 2016
     */
    List<TFactoryV> selectByExample(TFactoryVExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_FACTORY_V
     *
     * @mbggenerated Tue Jul 26 09:30:28 CST 2016
     */
    int updateByExampleSelective(@Param("record") TFactoryV record, @Param("example") TFactoryVExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_FACTORY_V
     *
     * @mbggenerated Tue Jul 26 09:30:28 CST 2016
     */
    int updateByExample(@Param("record") TFactoryV record, @Param("example") TFactoryVExample example);
}
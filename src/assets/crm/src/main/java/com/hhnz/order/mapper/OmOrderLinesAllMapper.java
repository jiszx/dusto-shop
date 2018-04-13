package com.hhnz.order.mapper;

import com.hhnz.order.model.OmOrderLinesAll;
import com.hhnz.order.model.OmOrderLinesAllExample;
import com.hhnz.order.model.OrderLinesDetials;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OmOrderLinesAllMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    int countByExample(OmOrderLinesAllExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    int deleteByExample(OmOrderLinesAllExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    int insert(OmOrderLinesAll record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    int insertSelective(OmOrderLinesAll record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    List<OmOrderLinesAll> selectByExample(OmOrderLinesAllExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    OmOrderLinesAll selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    int updateByExampleSelective(@Param("record") OmOrderLinesAll record, @Param("example") OmOrderLinesAllExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    int updateByExample(@Param("record") OmOrderLinesAll record, @Param("example") OmOrderLinesAllExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    int updateByPrimaryKeySelective(OmOrderLinesAll record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_ORDER_LINES_ALL
     *
     * @mbggenerated Mon Sep 12 15:48:07 CST 2016
     */
    int updateByPrimaryKey(OmOrderLinesAll record);
    
    int receiveGoods(OrderLinesDetials orderLine);
}
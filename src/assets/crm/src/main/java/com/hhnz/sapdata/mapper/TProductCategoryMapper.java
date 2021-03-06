package com.hhnz.sapdata.mapper;

import com.hhnz.sapdata.model.TProductCategory;
import com.hhnz.sapdata.model.TProductCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProductCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    int countByExample(TProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    int deleteByExample(TProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    int insert(TProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    int insertSelective(TProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    List<TProductCategory> selectByExample(TProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    TProductCategory selectByPrimaryKey(String id);
    
    /**
     * 获取品牌，品名，内包装列表
     * 
     */
    List<TProductCategory> getCategorys();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    int updateByExampleSelective(@Param("record") TProductCategory record, @Param("example") TProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    int updateByExample(@Param("record") TProductCategory record, @Param("example") TProductCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    int updateByPrimaryKeySelective(TProductCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PRODUCT_CATEGORY
     *
     * @mbggenerated Tue Jul 26 11:45:27 CST 2016
     */
    int updateByPrimaryKey(TProductCategory record);
}
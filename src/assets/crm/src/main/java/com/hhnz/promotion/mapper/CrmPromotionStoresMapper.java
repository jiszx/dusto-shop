package com.hhnz.promotion.mapper;

import com.hhnz.promotion.model.CrmPromotionStores;
import com.hhnz.promotion.model.CrmPromotionStoresExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrmPromotionStoresMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    int countByExample(CrmPromotionStoresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    int deleteByExample(CrmPromotionStoresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    int insert(CrmPromotionStores record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    int insertSelective(CrmPromotionStores record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    List<CrmPromotionStores> selectByExample(CrmPromotionStoresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    CrmPromotionStores selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    int updateByExampleSelective(@Param("record") CrmPromotionStores record, @Param("example") CrmPromotionStoresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    int updateByExample(@Param("record") CrmPromotionStores record, @Param("example") CrmPromotionStoresExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    int updateByPrimaryKeySelective(CrmPromotionStores record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_STORES
     *
     * @mbggenerated Tue Aug 02 13:24:49 CST 2016
     */
    int updateByPrimaryKey(CrmPromotionStores record);
}
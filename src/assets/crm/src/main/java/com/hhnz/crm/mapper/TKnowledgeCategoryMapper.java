package com.hhnz.crm.mapper;

import com.hhnz.crm.model.TKnowledgeCategory;
import com.hhnz.crm.model.TKnowledgeCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TKnowledgeCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    int countByExample(TKnowledgeCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    int deleteByExample(TKnowledgeCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    int insert(TKnowledgeCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    int insertSelective(TKnowledgeCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    List<TKnowledgeCategory> selectByExample(TKnowledgeCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    TKnowledgeCategory selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    int updateByExampleSelective(@Param("record") TKnowledgeCategory record, @Param("example") TKnowledgeCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    int updateByExample(@Param("record") TKnowledgeCategory record, @Param("example") TKnowledgeCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    int updateByPrimaryKeySelective(TKnowledgeCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_CATEGORY
     *
     * @mbggenerated Mon Jul 25 11:12:51 CST 2016
     */
    int updateByPrimaryKey(TKnowledgeCategory record);
}
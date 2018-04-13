package com.hhnz.crm.mapper;

import com.hhnz.crm.model.TKnowledgeAttachment;
import com.hhnz.crm.model.TKnowledgeAttachmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TKnowledgeAttachmentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_ATTACHMENT
     *
     * @mbggenerated Tue Jul 19 22:59:25 CST 2016
     */
    int countByExample(TKnowledgeAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_ATTACHMENT
     *
     * @mbggenerated Tue Jul 19 22:59:25 CST 2016
     */
    int deleteByExample(TKnowledgeAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_ATTACHMENT
     *
     * @mbggenerated Tue Jul 19 22:59:25 CST 2016
     */
    int insert(TKnowledgeAttachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_ATTACHMENT
     *
     * @mbggenerated Tue Jul 19 22:59:25 CST 2016
     */
    int insertSelective(TKnowledgeAttachment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_ATTACHMENT
     *
     * @mbggenerated Tue Jul 19 22:59:25 CST 2016
     */
    List<TKnowledgeAttachment> selectByExample(TKnowledgeAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_ATTACHMENT
     *
     * @mbggenerated Tue Jul 19 22:59:25 CST 2016
     */
    int updateByExampleSelective(@Param("record") TKnowledgeAttachment record, @Param("example") TKnowledgeAttachmentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_KNOWLEDGE_ATTACHMENT
     *
     * @mbggenerated Tue Jul 19 22:59:25 CST 2016
     */
    int updateByExample(@Param("record") TKnowledgeAttachment record, @Param("example") TKnowledgeAttachmentExample example);
}
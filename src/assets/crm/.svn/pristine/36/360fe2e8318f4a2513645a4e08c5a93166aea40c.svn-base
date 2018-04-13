package com.hhnz.crm.service;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TKnowledgeAttachment;
import com.hhnz.crm.model.TKnowledgeCategory;
import com.hhnz.crm.model.TKnowledgeMain;
import com.hhnz.util.AjaxDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2016-7-19.
 */
public interface IKnowledgeService {
    AjaxDTO findList(Map<String, Object> stringObjectHashMap, AjaxDTO bean)throws Exception;

    Integer add(TAttachment model)throws Exception;

    Integer update(TAttachment model)throws Exception;

    Integer del(Long id)throws Exception;

    List<TKnowledgeCategory> findAllCategory()throws Exception;

    void fillSizeAndUrl(List<TAttachment> atts);
}

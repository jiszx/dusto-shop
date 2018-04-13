package com.hhnz.crm.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hhnz.crm.mapper.TAttachmentMapper;
import com.hhnz.crm.mapper.TKnowledgeCategoryMapper;
import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TAttachmentExample;
import com.hhnz.crm.model.TKnowledgeCategory;
import com.hhnz.crm.model.TKnowledgeCategoryExample;
import com.hhnz.crm.service.IKnowledgeService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.db.Page;

/**
 * Created by yang on 2016-7-19.
 */
@Service
@Transactional
public class KnowledgeServiceImpl implements IKnowledgeService {

    @Autowired
    private TAttachmentMapper mapper;

    @Autowired
    private TKnowledgeCategoryMapper categoryMapper;

    @Override
    public AjaxDTO findList(Map<String, Object> stringObjectHashMap, AjaxDTO bean) throws Exception {
        Page page = new Page();
        page.setLimit(bean.getLimit());
        page.setOffset(bean.getOffset());
        TAttachmentExample ae = new TAttachmentExample();
        ae.setPage(page);
        TAttachmentExample.Criteria aec = ae.createCriteria();
        aec.andObjectNameEqualTo(TKnowledgeCategory.class.getSimpleName());
        if(stringObjectHashMap.containsKey("category")){
            aec.andObjectKeyLike((String) stringObjectHashMap.get("category")+"%");
        }
        if(StringUtils.hasLength(bean.getSearch())){
            aec.andAttachmentNameLike(bean.getSearch()+"%");
        }
        List<TAttachment> list = this.mapper.selectByExample(ae);
        int total = this.mapper.countByExample(ae);
        bean.setRows(list);
        bean.setTotal(total);
        return bean;
    }
    
  @Override
  public void fillSizeAndUrl(List<TAttachment> atts) {
	String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
    for (TAttachment att : atts) {
      att.setFileName(baseUrl + att.getObjectName()+att.getFileName());
      File file = new File(att.getFilePath());
      if (file.exists()) {
        long kb = file.length() / 1024;
        att.setSize(String.valueOf(kb > 1 ? kb : 1));
      }
    }
  }

    @Override
    public Integer add(TAttachment model) throws Exception {
        model.setObjectKey("KNOWLEDGE");
        model.setUploadTs(new Date());
        return this.mapper.insert(model);
    }

    @Override
    public Integer update(TAttachment model) throws Exception {
        return this.mapper.updateByPrimaryKeySelective(model);
    }
    @Override
    public Integer del(Long id) throws Exception {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<TKnowledgeCategory> findAllCategory() throws Exception {
        return this.categoryMapper.selectByExample(new TKnowledgeCategoryExample());
    }
}

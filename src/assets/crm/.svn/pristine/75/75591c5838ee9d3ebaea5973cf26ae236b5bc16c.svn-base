package com.hhnz.monitor.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhnz.crm.mapper.TAttachmentMapper;
import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TAttachmentExample;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.monitor.service.IAppManageService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.db.Page;

@Service
@Transactional
public class AppManageServiceImpl implements IAppManageService {

  @Resource
  private TAttachmentMapper attachmentMapper;

  @Override
  public AjaxDTO findAttachment(AjaxDTO page) {
    Page p = new Page();
    p.setLimit(page.getLimit());
    p.setOffset(page.getOffset());
    TAttachmentExample ex = new TAttachmentExample();
    ex.createCriteria().andObjectNameEqualTo(StringUtils.isEmpty(page.getSearch())?"appmanage":page.getSearch());
    ex.setPage(p);
    ex.setOrderByClause("upload_ts desc");
    List<TAttachment> list = this.attachmentMapper.selectByExample(ex);
    int total = this.attachmentMapper.countByExample(ex);
    page.setRows(list);
    page.setTotal(total);
    return page;
  }

  @Override
  public int saveAttachment(TAttachment record, TEmployee user) {
    record.setUploadTs(new Date());
    record.setUploadOid(String.valueOf(user.getId()));
    return attachmentMapper.insert(record);
  }
}

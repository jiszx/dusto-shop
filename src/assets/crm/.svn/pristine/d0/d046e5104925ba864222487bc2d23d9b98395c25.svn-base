package com.hhnz.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TKnowledgeCategory;
import com.hhnz.crm.service.IKnowledgeService;
import com.hhnz.util.AjaxDTO;

@Controller
@RequestMapping("/api/knowledge")
public class ApiKnowledgeController extends BaseController {

  @Resource
  private IKnowledgeService knowledgeService;

  // 知识库分类
  @ResponseBody
  @RequestMapping(value = "/category", method = RequestMethod.GET)
  public List<TKnowledgeCategory> treeList() throws Exception {
    return this.knowledgeService.findAllCategory();
  }

  // 知识库列表
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public List<?> listPage(String category, AjaxDTO bean) throws Exception {
    limitVerify(bean);
    Map<String, Object> map = new HashMap<String, Object>();
    if (StringUtils.isNoneEmpty(category)) {
      map.put("category", category);
    }
    List<TAttachment> rows = this.knowledgeService.findList(map, bean).getRows();
    knowledgeService.fillSizeAndUrl(rows);
    return rows;
  }

}

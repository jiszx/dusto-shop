package com.hhnz.api.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.hhnz.account.model.CMerchUpaccount;
import com.hhnz.account.service.IUpAccountService;
import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TNotes;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IHomeService;
import com.hhnz.crm.service.INotesService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.dto.ResponseResult;
import com.hhnz.monitor.service.IAppManageService;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.organization.model.CrmOrgArea;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.organization.service.IorganizationService;
import com.hhnz.pub.model.TArea;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.ResponseMessage;
import com.hhnz.util.ResponseMessage;
import com.hhnz.util.exception.HHNZException;

@Controller
@RequestMapping("/api/common")
public class ApiCommonController extends BaseController {

  @Resource
  private INotesService notesService;
  @Resource
  private IAttachmentService attachmentService;
  @Resource
  private IorganizationService organizationService;
  @Resource
  private OrderUtilService orderUtilService;
  @Resource
  private IHomeService homeService;
  @Resource
  private OrderService orderservice;
  @Resource
  private IUpAccountService upaccountService;
  @Resource
  private IPowerOrgService  powerService;
  @Resource
  private IAppManageService appService;


  /**
   * 获取公告列表
   * 
   * @param page
   * @param request
   * @return
   * @throws Exception
   */
  @ResponseBody
  @RequestMapping(value = "/note", method = RequestMethod.GET)
  public ResponseResult getNotes(AjaxDTO page, HttpServletRequest request) throws Exception {
    page = limitVerify(page);
    Map<String, Object> params = new HashMap<String, Object>();
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    params.put("DEPT", user.getOrganizationId());
    params.put("STATE", "0");
    List<TNotes> rows = this.notesService.findNotes(params, page).getRows();
    notesService.fillNoteUrl(rows);
    return ResponseResult.builder().data(rows).build();
  }

  /**
   * 获取公告详情
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @ResponseBody
  @RequestMapping(value = "/note/detail", method = RequestMethod.GET)
  public ModelAndView noteDetail(Long id) throws Exception {
    Map<String, Object> res = new HashMap<>();
    ModelAndView mv = new ModelAndView();
    AjaxDTO page = new AjaxDTO();
    TNotes note = this.notesService.findById(id);
    if (!"0".equals(note.getStat())) {
      throw new IllegalAccessException();
    }
    List<TAttachment> attas =
        this.attachmentService.findAttachment(TNotes.class.getSimpleName(), id + "", page);
    res.put("note", note);
    res.put("attas", attas);
    mv.setViewName("app/index");
    mv.addObject("note", note);
    mv.addObject("attas", attas);
    return mv;
    // return res;
  }

  /**
   * 获取所有销售组织
   * 
   * @return
   * @throws Exception
   */
  @ResponseBody
  @RequestMapping(value = "/org/list", method = RequestMethod.GET)
  public List<CrmSalesOrganization> getOrgs() throws Exception {
    return this.organizationService.findAll();
  }

  /**
   * 获取某一层级的销售组织
   * 
   * @param level
   * @return
   * @throws Exception
   */
  @ResponseBody
  @RequestMapping(value = "/org/list/level", method = RequestMethod.GET)
  public List<CrmSalesOrganization> orgLevel(String level) throws Exception {
    return this.organizationService.findByLevel(level);
  }


  /**
   * 获取销售组织及省市县的层级关系
   * 
   * @return
   * @throws Exception
   */
  @ResponseBody
  @RequestMapping(value = "/area/list", method = RequestMethod.GET)
  public List<CrmSalesOrganization> listArea() throws Exception {
    List<CrmSalesOrganization> list = new ArrayList<CrmSalesOrganization>();
    List<CrmSalesOrganization> plist = this.organizationService.findAll();
    List<CrmOrgArea> areas = this.organizationService.findOrgArea();
    list.addAll(plist);
    for (CrmOrgArea cc : areas) {
      CrmSalesOrganization temp = new CrmSalesOrganization();
      temp.setId(cc.getId());
      temp.setPid(cc.getPid());
      temp.setName(cc.getName());
      temp.setAbbrName(cc.getName());
      list.add(temp);
    }
    return list;
  }

  /**
   * 根据父节点获取地方的子节点
   * 
   * @param id
   * @return
   * @throws RuntimeException
   */
  @ResponseBody
  @RequestMapping(value = "/area/children", method = RequestMethod.GET)
  public ResponseResult findChildren(String id, String filter, HttpServletRequest request) throws RuntimeException {
    TEmployee user = loginApiUser(request);
    if(user==null){
      return ResponseMessage.tokenExpired();
    }
    List<TArea> areas = this.organizationService.findAreas(id, filter, user);
    return ResponseResult.builder().data(areas).build();
  }

  @ResponseBody
  @RequestMapping(value = "/customerShip", method = RequestMethod.GET)
  public ResponseResult getCustomerShip(Long merchid, HttpServletRequest request) throws Exception {
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    Map<String, Object> userInfo = homeService.findUserSessionInfo(user.getLoginName());
    UserStations userstation = (UserStations) userInfo.get(SessionKey.CURR_STATION);// 用户当前岗位信息
    AjaxDTO dto = this.orderUtilService.getCustomerShip(merchid, userstation.getOrgid());
    return ResponseResult.builder().data(dto.getRows()).build();
  }
  
  @ResponseBody
  @RequestMapping(value = "/count", method = RequestMethod.GET)
  public ResponseResult count(Long stationId, Long noteid, AjaxDTO page, HttpServletRequest request) throws Exception {
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    Map<String, Object> userInfo = userInfo(request);
    List<UserJobs> userjobs = userJobs(userInfo);
    UserStations userstations = userStation(userInfo, stationId);
    Map<String, Object> params = new HashMap<String, Object>();
    List<Long> stationids =  this.powerService.getUserStations(user, userjobs, userstations);//获取用户可以查看的岗位
    params.put("stations", stationids);
    
    params.put("states", "2");
    int waitAudit = orderservice.findOrderList(params).getRows().size();
    params.put("states", "5");
    int waitSendProduct = orderservice.findOrderList(params).getRows().size();
    params.put("states", "7");
    int waitRecieve = orderservice.findOrderList(params).getRows().size();
    page.setLimit(100);
    Map<String, Object> upaccountParm = new HashMap<>();
    AjaxDTO upaccs = upaccountService.getSalesAccountList(upaccountParm, page);
    int upAccountVerify = upaccs.getTotal();
    Map<String, Object> noteParam = new HashMap<>();
    noteParam.put("DEPT", user.getOrganizationId());
    noteParam.put("STATE", "0");
    if(noteid!=null && noteid!=0){
      noteParam.put("noteid", noteid);
    }
    int noteNum = this.notesService.findNotes(noteParam, page).getRows().size();
    Map<String, Integer> result = new HashMap<>();
    result.put("orderWaitAudit", waitAudit);
    result.put("orderWaitSend", waitSendProduct);
    result.put("orderWaitRecieve", waitRecieve);
    result.put("upAccount", upAccountVerify);
    result.put("note", noteNum>0?1:0);
    return ResponseResult.builder().data(result).build();
  }

  @ResponseBody
  @RequestMapping(value = "/upgrade", method = RequestMethod.GET)
  public Map<String, String> appUpgrade(String version) {
    Map<String, String> result = new HashMap<>();
    AjaxDTO page = new AjaxDTO();
    page.setLimit(1);
    AjaxDTO atts = appService.findAttachment(page);
    if(Strings.isNullOrEmpty(version) || atts.getRows()==null || atts.getRows().isEmpty()){
      return result;
    }
    TAttachment att = (TAttachment) atts.getRows().get(0);
    String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
    String lastestVersion = att.getObjectKey();
    String description = att.getAttachmentName();
    String url = baseUrl+att.getFileName();
    String force = att.getAttachmentType();
    if(!version.equals(lastestVersion)){
      result.put("version", lastestVersion);
      result.put("description", description);
      result.put("url", url);
      result.put("force", force);
      File file = new File(att.getFilePath());
      if (file.exists()) {
        long kb = file.length() / 1024;
        result.put("size", String.valueOf(kb > 1 ? kb : 1)+"KB");
      }
    }
    return result;
  }
  
  @RequestMapping(value = "/about", method = RequestMethod.GET)
  public String about(){
    return "app/about";
  }
  
  @RequestMapping(value = "/agreement", method = RequestMethod.GET)
  public String agreement(){
    return "app/agreement";
  }

}

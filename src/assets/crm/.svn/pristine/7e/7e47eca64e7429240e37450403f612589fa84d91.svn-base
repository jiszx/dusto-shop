package com.hhnz.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.dto.ResponseResult;
import com.hhnz.pub.model.PowerOrg;
import com.hhnz.pub.service.IPowerOrgService;
//import com.hhnz.salepolicy.model.PolicyOrgModel;
import com.hhnz.salepolicy.model.PolicySearchModel;
import com.hhnz.salepolicy.service.SalePolicyService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ResponseMessage;

@Controller
@RequestMapping("/api/policy")
public class ApiPolicyController extends BaseController {

  @Resource
  private SalePolicyService policyService;
  @Resource
  private IPowerOrgService  orgservice;

  // 获取销售政策
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ResponseResult listPage(AjaxDTO page, HttpServletRequest request) throws Exception {
    limitVerify(page);
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    Map<String, Object> userInfo = userInfo(request);
    List<UserJobs> jobs = userJobs(userInfo);
    AjaxDTO orgdto = orgservice.powerOrg(jobs);
    List<PowerOrg> orgs= orgdto.getRows();
    List<String> orgids = new ArrayList<String>();
    if(orgs !=null && orgs.size()>0){
        for(PowerOrg org:orgs){
            orgids.add(org.getOrgid());
        }
    }
    Map<String,Object> params = new HashMap<String, Object>();
    params.put("bpage", page.getOffset());
    params.put("epage", page.getLimit()+page.getOffset());
//    params.put("orgid", orgids); // 暂时不用控制权限
    AjaxDTO dto = this.policyService.getPolicyList(params);
    return ResponseResult.builder().data(dto.getRows()).build();

  }
  
  @ResponseBody
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public PolicySearchModel policyDetail(Long id, HttpServletRequest request){
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("bpage", 0);
    params.put("epage", 10);
    params.put("policyid", id);
    return policyService.getPolicyDetail(params);
  }
}

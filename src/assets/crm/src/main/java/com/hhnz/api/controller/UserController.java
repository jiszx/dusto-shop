package com.hhnz.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhnz.api.cache.CacheService;
import com.hhnz.api.cache.model.LoginCacheModel;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IEmployeeService;
import com.hhnz.customer.model.ContractDetail;
import com.hhnz.customer.service.CustomerContractService;
import com.hhnz.dto.ResponseResult;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.organization.service.IStationService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ResponseMessage;
import com.hhnz.util.exception.HHNZException;


@Controller
@RequestMapping("/api")
public class UserController extends BaseController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  @Autowired
  private IEmployeeService service;
  @Autowired
  private CacheService cacheService;
  @Autowired
  private IStationService stationService;
  @Autowired
  private CustomerContractService contractService;

  /**
   * api用户登录
   * 
   * @author: chaoyang.ren
   * @date:2016年8月8日 上午10:36:09
   * @param user
   * @param pwd
   * @param memi
   * @param request
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseResult login(@RequestParam("userId") String user, String pwd, String imei,
      HttpServletRequest request, AjaxDTO bean) {
    Assert.notNull(user, "username can not be empty");
    Assert.notNull(pwd, "password can not be empty");
    Map<String, Object> result = new HashMap<>();
    try {
      TEmployee employee = service.findByPK(user);
      logger.info("login:find user");
      if (employee == null) {
        return ResponseMessage.loginError();
      }
      Md5PasswordEncoder encoder = new Md5PasswordEncoder();
      String encodedPwd = encoder.encodePassword(pwd, null);
      if (employee.getPasswd().equals(encodedPwd)) {
        String uuid;// 如果已生成token且在有效期内再次登录，则沿用
        logger.info("login: redis get uuid start");
        uuid = cacheService.get(user);
        if (StringUtils.isBlank(uuid)) {
        	uuid = UUID.randomUUID().toString().replace("-", "");
        }
        LoginCacheModel cm = new LoginCacheModel(employee, imei);
        logger.info("login: redis put user start");
        cacheService.put(user, uuid);
        cacheService.put(uuid, cm);
        logger.info("login: redis put user end");
        
        // 取岗位
        limitVerify(bean);
        String userType = "-1";
        List<UserStations> stations = this.stationService.findUserStations(employee.getId());
        logger.info("login: get station");
        if(!stations.isEmpty()){
          result.put("station", stations.get(0));
          userType="0";
        }
        result.put("name", employee.getName());
        result.put("icon", "");
        result.put("accesstoken", uuid);
        result.put("userType","1".equals(employee.getUserType())?employee.getUserType():userType);//1配送商
        result.put("merchid", employee.getMerchId());
        ContractDetail contract = null;
        try{
        	 contract = contractService.merchContractdetail(employee.getMerchId());//查找合同
        }catch(Exception e){
        	result.put("contractType", 1);//没有合同
        }finally{
        	if(contract != null){
        		if(contract.getContract() != null){
        			result.put("contractType", 0);//有合同
        		}else{
        			result.put("contractType", 1);//没有合同
        		}
            }else{
            	result.put("contractType", 1);//没有合同
            }
        }         
               
        return ResponseResult.builder().data(result).build();
      } else {
        logger.info("API用户登录，用户密码错误!");
        return ResponseMessage.loginError();
      }
    } catch (Exception e) {
      logger.error("API用户登录异常!", e);
      throw new HHNZException("用户登录异常!");
    }
  }

  /**
   * 用户退出
   * 
   * @author: chaoyang.ren
   * @date:2016年8月8日 下午3:03:31
   * @param user
   * @param pwd
   * @param memi
   * @param request
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public Integer logout(HttpServletRequest request) {
    cacheService.delete(this.getToken(request));
    return 1;
  }
  
  @ResponseBody
  @RequestMapping(value = "/password/change", method = RequestMethod.POST)
  public ResponseResult changePassword(String password, String newpassword, String passwordrepeat, HttpServletRequest request) throws Exception {
    TEmployee user = loginApiUser(request);
    if(user==null){
      return ResponseMessage.tokenExpired();
    }
    Md5PasswordEncoder encoder = new Md5PasswordEncoder();
    TEmployee employee = service.findByPK(user.getLoginName());
    int result = 0;
    if(!StringUtils.equals(newpassword, passwordrepeat)){
      return ResponseMessage.newPasswordNotEqual();
    }
    if(!StringUtils.equals(encoder.encodePassword(password, null), employee.getPasswd())){
      return ResponseMessage.passwordError();
    }
    employee.setPasswd(encoder.encodePassword(newpassword, null));
    result = service.updateEmployee(employee);
    return ResponseResult.builder().data(result).build();
  }

  /**
   * 获取个人岗位信息
   * 
   * @param page
   * @param station
   * @param request
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/station", method = RequestMethod.GET)
  public ResponseResult station(AjaxDTO page, CrmStation station, HttpServletRequest request) {
    limitVerify(page);
    TEmployee user = loginApiUser(request);
    if (user == null) {
      return ResponseMessage.tokenExpired();
    }
    station.setSalesrepId(user.getId());
    List<UserStations> rows = this.stationService.findUserStations(user.getId());
    return ResponseResult.builder().data(rows).build();
  }
  
}

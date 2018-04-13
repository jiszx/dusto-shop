package com.hhnz.customer.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.RuntimeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hhnz.account.mapper.CMerchCustAccountMapper;
import com.hhnz.account.model.CMerchCustAccount;
import com.hhnz.account.model.CMerchCustAccountExample;
import com.hhnz.aop.token.annotation.Token;
import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TDict;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IDictService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.dto.CustomerBaseDTO;
import com.hhnz.customer.dto.CustomerInvDTO;
import com.hhnz.customer.enu.CustomerBaseStateEnu;
import com.hhnz.customer.mapper.CMerchCustBalancesMapper;
import com.hhnz.customer.model.CMerchCustBalances;
import com.hhnz.customer.model.CMerchCustBalancesExample;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.model.CMerchCustInv;
import com.hhnz.customer.model.CMerchCustStation;
import com.hhnz.customer.service.ICustomerInvService;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.customer.validator.CustBaseValidator;
import com.hhnz.customer.validator.CustReceiverValidator;
import com.hhnz.jco.business.customer.CustomerRFC;
import com.hhnz.jco.business.customer.InputDTO;
import com.hhnz.jco.business.customer.ResultDTO;
import com.hhnz.order.mapper.OrderUtilMapper;
import com.hhnz.order.model.OrderCustomer;
import com.hhnz.organization.model.CrmStation;
import com.hhnz.organization.service.IStationService;
import com.hhnz.process.service.IProcessService;
import com.hhnz.pub.enu.ChangeStatus;
import com.hhnz.pub.model.Change;
import com.hhnz.pub.model.ChangeVar;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.pub.service.IChangeService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.receivable.mapper.CMerchArBalanceMapper;
import com.hhnz.receivable.model.CMerchArBalance;
import com.hhnz.receivable.model.CMerchArBalanceExample;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;
import com.hhnz.util.BeanUtils;
import com.hhnz.util.Constants;
import com.hhnz.util.FileTypeJudge;
import com.hhnz.util.Files;
import com.hhnz.util.io.excel.util.Excel2007Util;

/**
 * Created by yang on 2016-7-6.
 */
@Controller
@RequestMapping("/customer/")
public class CustomerController {
    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerInvService customerInvService;
    @Autowired
    private IChangeService changeService;
    @Autowired
    private IAttachmentService attaService;
    @Autowired
    private IProcessService processService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private CustomerRFC customerRFC;
    @Resource
    private IPowerOrgService  powerService;
    @Resource
    private IStationService stationService;
    @Resource
	private OrderUtilMapper orderUtilMapper;
    @Resource
    private IDictService dictService;
    @Resource
    private CMerchCustAccountMapper accountmapper;
    @Resource
	private CMerchCustBalancesMapper balanceMapper;
    
    @Resource
	private CMerchArBalanceMapper  armapper;
    @Value("${upload.file.path}")
    private String basePath;
    
    @RequestMapping("index{type}.html")
    public String addPage(@PathVariable(value="type")Integer type)throws Exception{
    	/*
    	 * 1	合作盐业公司
    	 * 2	配送商
    	 * 3	KA
    	 * 4	盐业公司
    	 * 7	仓储服务商
    	 * 70	合作仓储服务商
    	 */
		return "customer/index"+type;
    }
    
    @RequestMapping("/addRetail.html")
    public String addRetailPage()throws Exception{
        return "customer/addOrUpdateRetail";
    }
    
    @RequestMapping(value = "/payer.html", method = RequestMethod.GET)
    public String payerPage(){
      return "customer/payer";
    }
    
    /**
     * 客户详情查看
     * @author: chaoyang.ren 
     * @date:2016年10月18日  上午10:39:14
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("view{id}.html")
    public ModelAndView view(@PathVariable(value="id")Long id)throws Exception{
        CMerchCustBase custBase = customerService.findCustBaseById(id);
        ModelAndView modelAndView = new ModelAndView("customer/index"+custBase.getCustType());
        if("70".equals(custBase.getCustType())){
        	Long pid = custBase.getPid();
            String saleToName;
            if(pid != null){
            	CMerchCustBase cb = customerService.findCustBaseById(pid);
            	saleToName = cb==null?StringUtils.EMPTY:cb.getName();
            }else{
            	saleToName = StringUtils.EMPTY;
            }
            modelAndView.addObject("saleToName", saleToName);
    	}
		modelAndView.setViewName("customer/view"+custBase.getCustType());
        List<TAttachment> attachments = attaService.findAttachment(CMerchCustBase.class.getSimpleName(), String.valueOf(custBase.getId()), null);
        String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
        for (TAttachment attachment:attachments){
            String type = null;
            try {
              type =
                  FileTypeJudge.getType(baseUrl + attachment.getObjectName() + attachment.getFileName());
            } catch (IOException ioe) {
              logger.warn(ioe.getMessage(), ioe);
            }
        	attachment.setAttachmentType(type);
        }
        custBase.setAttachments(attachments);
        if(custBase.getPid()!=null){
          CMerchCustBase pcust = customerService.findCustBaseById(custBase.getPid());
          modelAndView.addObject("pcust", pcust);
        }
        if(custBase.getPayer()!=null){
          CMerchCustBase payer = customerService.findCustBaseById(custBase.getPayer());
          modelAndView.addObject("payer", payer);
        }
        modelAndView.addObject("custBase", custBase);
        return modelAndView;
    }
    
    @RequestMapping("/viewRetail/{id}.html")
    public ModelAndView viewRetail(@PathVariable(value="id")Long id)throws Exception{
        ModelAndView modelAndView = new ModelAndView("customer/viewRetail");
        CMerchCustBase custBase = customerService.findCustBaseById(id);
        if(custBase.getPid()!=null){
          CMerchCustBase pcust = customerService.findCustBaseById(custBase.getPid());
          modelAndView.addObject("pcust", pcust);
        }
        List<TAttachment> attachments = attaService.findAttachment(CMerchCustBase.class.getSimpleName(), String.valueOf(custBase.getId()), null);
        String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
        for (TAttachment attachment:attachments){
            attachment.setAttachmentType(FileTypeJudge.getType(baseUrl+attachment.getObjectName()+attachment.getFileName()));
        }
        custBase.setAttachments(attachments);
        modelAndView.addObject("custBase", custBase);
        return modelAndView;
    }

    @RequestMapping("setPrice.html")
    public String price()throws Exception{
        return "customer/setCustmerPrice";
    }
    
    /**
     * 保存并继续编辑
     * @author: chaoyang.ren 
     * @date:2016年8月23日  上午10:48:01
     * @param custBase
     * @param result
     * @param request
     * @param files
     * @param delAtts
     * @param delDists
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping("partlySave.html")
    public ModelAndView saveCustomerAndRedirect(@Valid CMerchCustBase custBase, BindingResult result, HttpServletRequest request, @RequestParam(required=false,value="files") MultipartFile[] files, String delAtts, String delDists, RedirectAttributes redirectAttributes)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        CustBaseValidator validator = new CustBaseValidator();
        validator.validate(custBase, result);
        if(custBase.getId() != null){
		    List<TAttachment> attachments = attaService.findAttachment(CMerchCustBase.class.getSimpleName(), String.valueOf(custBase.getId()), null);
		    custBase.setAttachments(attachments);
        }
        if (result.hasErrors()) {
            String view = "redirect:/customer/index"+custBase.getCustType()+".html";
            modelAndView.setViewName(view);
            redirectAttributes.addFlashAttribute("custBase", custBase);
            redirectAttributes.addFlashAttribute("msg",result.getFieldError().getDefaultMessage());
            return modelAndView;
        }
        ModelAndView saveModelAndView =  this.saveCustomer(custBase, result, request, files, delAtts, delDists, "customer", redirectAttributes);
        if(result.hasGlobalErrors()){
            return saveModelAndView;
        }
        modelAndView.setViewName("redirect:edit"+custBase.getId()+".html");
        redirectAttributes.addFlashAttribute("custBase", custBase);
        return modelAndView;
    }
    
    /**
     * 保存并提交审批
     * @author: chaoyang.ren 
     * @date:2016年8月23日  上午10:47:46
     * @param custBase
     * @param result
     * @param request
     * @param files
     * @param delAtts
     * @param delDists
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @Token
    @RequestMapping("submit.html")
    public ModelAndView saveCustomerAndApply(@Valid CMerchCustBase custBase, BindingResult result, HttpServletRequest request, @RequestParam(required=false,value="files") MultipartFile[] files, String delAtts, String delDists, RedirectAttributes redirectAttributes)throws Exception{
        ModelAndView modelAndView = new ModelAndView("redirect:list.html");
        CustBaseValidator validator = new CustBaseValidator();
        validator.validate(custBase, result);
        if(custBase.getId() != null){
		    List<TAttachment> attachments = attaService.findAttachment(CMerchCustBase.class.getSimpleName(), String.valueOf(custBase.getId()), null);
		    custBase.setAttachments(attachments);
        }
        if (result.hasErrors()) {
            String view = "redirect:/customer/index"+custBase.getCustType()+".html";
            modelAndView.setViewName(view);
            redirectAttributes.addFlashAttribute("custBase", custBase);
            redirectAttributes.addFlashAttribute("msg",result.getFieldError().getDefaultMessage());
            return modelAndView;
        }
        ModelAndView saveModelAndView =  this.saveCustomer(custBase, result, request, files, delAtts, delDists, "customer", redirectAttributes);
        if(result.hasGlobalErrors()){
            return saveModelAndView;
        }
        String applyMsg =  this.applyCustomer(custBase.getId(), request);
        if(!Constants.SUCCESS_CODE_STR.equals(applyMsg)){
            redirectAttributes.addFlashAttribute("msg",applyMsg);
        }
        return modelAndView;
    }
    
    /**
     * 保存客户
     * @author: chaoyang.ren 
     * @date:2016年8月23日  上午10:48:38
     * @param custBase
     * @param result
     * @param request
     * @param files
     * @param delAtts
     * @param delDists
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @Token
    @RequestMapping(value="save.html",method=RequestMethod.POST)
    public ModelAndView saveCustomer(@Valid CMerchCustBase custBase, BindingResult result, HttpServletRequest request, @RequestParam(required=false,value="files") MultipartFile[] files, String delAtts, String delDists, String isRetail, RedirectAttributes redirectAttributes) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        CustBaseValidator validator = new CustBaseValidator();
        validator.validate(custBase, result);
        String view = "redirect:/customer/index"+custBase.getCustType()+".html";
        if("1".equals(isRetail)){
          view = "redirect:/customer/addRetail.html";
        }
        if(custBase.getId() != null){
		    List<TAttachment> attachments = attaService.findAttachment(CMerchCustBase.class.getSimpleName(), String.valueOf(custBase.getId()), null);
		    custBase.setAttachments(attachments);
        }
        if (result.hasErrors()) {
            modelAndView.setViewName(view);
            redirectAttributes.addFlashAttribute("custBase", custBase);
            redirectAttributes.addFlashAttribute("msg",result.getFieldError().getDefaultMessage());
            return modelAndView;
        }
        //用户信息
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        //岗位信息
        UserStations stations = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);
        if(null == stations && !"5".equals(custBase.getCustType())){
            result.addError(new ObjectError("station", "无法获取当前用户的岗位信息！"));
            modelAndView.setViewName(view);
            redirectAttributes.addFlashAttribute("custBase", custBase);
            redirectAttributes.addFlashAttribute("msg","无法获取当前用户的岗位信息！");
            return modelAndView;
        }
        custBase.setUpdateOid(custBase.getCreateOid());
        custBase.setUpdateTs(custBase.getCreateTs());
        //修改客户
        if(custBase.getId() != null){
            CMerchCustBase existedOne = customerService.findCustBaseById(custBase.getId());
            //修改已提交客户时仅可修改到站信息
            if(!CustomerBaseStateEnu.POTENTIAL.getCode().equals(existedOne.getStates())){
                existedOne.setDistributions(custBase.getDistributions());
                customerService.update(existedOne,delDists);
                modelAndView.setViewName(view);
                redirectAttributes.addFlashAttribute("custBase", existedOne);
                redirectAttributes.addFlashAttribute("msg","客户修改完成，由于该客户已提交，仅允许修改送货地址信息！");
                return modelAndView;
            }
            //if(existedOne == null || !existedOne.getCreateOid().equals(emp.getId())){
            /*if(existedOne == null){
                result.addError(new ObjectError("custBase", "错误的参数！"));
                modelAndView.setViewName(view);
                redirectAttributes.addFlashAttribute("custBase", custBase);
                redirectAttributes.addFlashAttribute("msg","错误的参数！");
                return modelAndView;
            }*/
            custBase.setCreateOid(existedOne.getCreateOid());
            custBase.setCreateTs(existedOne.getCreateTs());
            custBase.setOrganizationId(existedOne.getOrganizationId());
            custBase.setIsAttachment(existedOne.getIsAttachment());
            custBase.setStates(existedOne.getStates());
            customerService.update(custBase,delDists);
            // 删除需要删除的附件信息
            if(StringUtils.isNotBlank(delAtts)){
                String[] delAttArray = delAtts.split(",");
                for (String delAtt : delAttArray) {
                    Long attId = null;
                    try {
                        attId = Long.valueOf(delAtt);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    attaService.delAttachent(CMerchCustBase.class.getSimpleName(), String.valueOf(custBase.getId()), attId);
                }
            }
        }
        //新增客户
        else{
            if("5".equals(custBase.getCustType())){
              CMerchCustBase distributor = customerService.findCustBaseById(custBase.getPid());
              custBase.setOrganizationId(distributor.getOrganizationId());
            }else{
              custBase.setOrganizationId(stations==null?emp.getOrganizationId():stations.getStationorgid().substring(0, 5));
            }
            custBase.setCreateOid(emp.getId());
            custBase.setCreateTs(new Date());
            if(stations!=null && stations.getStationid()!=null){
              CMerchCustStation custStation = new CMerchCustStation();
              custStation.setUpdateOid(emp.getId());
              custStation.setUpdateTs(new Date());
              custStation.setCreateOid(emp.getId());
              custStation.setCreateTs(new Date());
              custStation.setStates("1");
              custStation.setStationId(stations.getStationid());
              custBase.setCustStation(custStation);
            }
            custBase.setIsAttachment((files != null && files.length >0)?"1":"0");
            custBase.setStates(CustomerBaseStateEnu.POTENTIAL.getCode());
            custBase = customerService.add(custBase);
        }
        // 插入附件信息
        if(files != null && files.length > 0){
            attaService.addAttachment(CMerchCustBase.class.getSimpleName(), String.valueOf(custBase.getId()), String.valueOf(custBase.getCreateOid()), files);
        }
        modelAndView.setViewName("1".equals(isRetail) ? "redirect:retail.html" : "redirect:list.html");
        return modelAndView;
    }
    
    @RequestMapping("/edit{id}.html")
    public ModelAndView edit(@PathVariable(value="id")Long id) throws Exception{
        CMerchCustBase custBase = customerService.findCustBaseById(id);
        ModelAndView modelAndView = new ModelAndView("customer/index"+custBase.getCustType());
		modelAndView.setViewName("customer/index"+custBase.getCustType());
        List<TAttachment> attachments = attaService.findAttachment(CMerchCustBase.class.getSimpleName(), String.valueOf(custBase.getId()), null);
        custBase.setAttachments(attachments);
        modelAndView.addObject("custBase", custBase);
        return modelAndView;
    }

    @RequestMapping("/change/change{id}.html")
    public ModelAndView change(@PathVariable(value="id")Long id,RedirectAttributes redirectAttributes) {
        CMerchCustBase custBase = customerService.findCustBaseById(id);
        ModelAndView modelAndView = new ModelAndView("customer/change/change"+custBase.getCustType());
        
        Change latestChange = changeService.getLatestChange(CMerchCustBase.class.getSimpleName(),custBase.getId().toString());
        //当前没有变更或者变更状态不为审批时才可变更
        if((latestChange != null && ChangeStatus.SUBMITED.equals(latestChange.getStatEnu()))
        		|| !(CustomerBaseStateEnu.FORMAL.getCode().equals(custBase.getStates()))){
        	modelAndView.addObject("readonly","true");
        }
        CMerchCustBase changeVo ;
        if(latestChange != null && !ChangeStatus.VALID.equals(latestChange.getStatEnu()) && !ChangeStatus.DELETED.equals(latestChange.getStatEnu())){
        	changeVo = new CMerchCustBase();
            BeanUtils.copyProperties(custBase, changeVo);
        	Long changeId = latestChange.getId();
        	CMerchCustBase changedVals = this.changeService.getChangeBean(CMerchCustBase.class,changeId);
        	BeanUtils.copyPropertiesIgnoreNil(changedVals, changeVo);
        }else{
        	changeVo = custBase;
        	latestChange = new Change();
        }
        TDict dict = dictService.findByCityId(Long.valueOf(custBase.getCityId()));
    	if(dict == null){
    		custBase.setRdcName("没有对应的RDC");
    	}else{
    		custBase.setRdcName(dict.getShowText());
    	}
        modelAndView.addObject("change",latestChange);
        modelAndView.addObject("custBase", custBase);
        modelAndView.addObject("changeVo", changeVo);
        return modelAndView;
    }
    
    @RequestMapping("/change/view{id}.html")
    public ModelAndView viewChange(@PathVariable(value="id")Long id,RedirectAttributes redirectAttributes) {
    	CMerchCustBase custBase = customerService.findCustBaseById(id);
    	ModelAndView modelAndView = new ModelAndView("customer/change/view"+custBase.getCustType());
    	
    	Change latestChange = changeService.getLatestChange(CMerchCustBase.class.getSimpleName(),custBase.getId().toString());
    	//当前没有变更或者变更状态不为审批时才可变更
		modelAndView.addObject("readonly","true");
		CMerchCustBase changeVo ;
        if(latestChange != null && !ChangeStatus.VALID.equals(latestChange.getStatEnu()) && !ChangeStatus.DELETED.equals(latestChange.getStatEnu())){
        	changeVo = new CMerchCustBase();
            BeanUtils.copyProperties(custBase, changeVo);
        	Long changeId = latestChange.getId();
        	CMerchCustBase changedVals = this.changeService.getChangeBean(CMerchCustBase.class,changeId);
        	BeanUtils.copyPropertiesIgnoreNil(changedVals, changeVo);
        }else{
        	changeVo = custBase;
        }
    	TDict dict = dictService.findByCityId(Long.valueOf(custBase.getCityId()));
    	if(dict == null){
    		custBase.setRdcName("没有对应的RDC");
    	}else{
    		custBase.setRdcName(dict.getShowText());
    	}
    	if(custBase.getPayer()!=null){
    	  CMerchCustBase custPayer = customerService.findCustBaseById(custBase.getPayer());
    	  modelAndView.addObject("payername",custPayer.getName());
    	}
    	if(changeVo.getPayer()!=null){
    	  CMerchCustBase custPayer = customerService.findCustBaseById(changeVo.getPayer());
    	  modelAndView.addObject("changepayer",custPayer.getName());
    	}
    	modelAndView.addObject("change",latestChange);
    	modelAndView.addObject("custBase", custBase);
    	modelAndView.addObject("changeVo", changeVo);
    	return modelAndView;
    }

    @RequestMapping("/change/save.html")
    public ModelAndView saveChange(Long changeId,String changeTitle,CMerchCustBase changeVo,RedirectAttributes redirectAttributes)throws Exception{
    	Change change = new Change();
    	change.setChangeTitle(changeTitle);
    	CMerchCustBase existedOne = customerService.findCustBaseById(changeVo.getId());
    	ModelAndView modelAndView = new ModelAndView("redirect:change"+existedOne.getId()+".html");
    	
    	Change latestChange = null;
    	if(changeId == null){
    		change.setId(changeService.getChangeId());
    	}else{
    		change.setId(changeId);
    		latestChange = changeService.findById(changeId);
    	}
    	/*TEmployee emp = ApplicationContextUtil.getCurrentUser();
        if(custBase == null || !custBase.getCreateOid().equals(emp.getId())){
            redirectAttributes.addFlashAttribute("msg","非本人创建客户，无法修改！");
            return modelAndView;
        }*/
    	if((latestChange != null && ChangeStatus.SUBMITED.equals(latestChange.getStatEnu()))
    			|| !(CustomerBaseStateEnu.FORMAL.getCode().equals(existedOne.getStates()))){
    		redirectAttributes.addFlashAttribute("msg","该客户不是正式客户或变更处于审批中");
    		modelAndView.addObject("msg", "该客户不是正式客户或变更处于审批中");
    		return modelAndView;
    	}
    	change.init();
    	change.setObjectKey(existedOne.getId().toString());
    	change.setObjectName(CMerchCustBase.class.getSimpleName());
		List<ChangeVar> cvl = ChangeVar.constuct(change, existedOne, changeVo);
		if(cvl.isEmpty()){
			redirectAttributes.addFlashAttribute("msg","未做变更");
			modelAndView.addObject("msg", "未做变更");
		}else{
			change.setChangeVars(cvl);
			changeService.save(change);
		}
        return modelAndView;
    }
    
    @RequestMapping("/change/submit.html")
    public ModelAndView saveAndApply(Long changeId,String changeTitle,CMerchCustBase changeVo,RedirectAttributes redirectAttributes)throws Exception{
    	if(changeId == null){
    		changeId = changeService.getChangeId();
    	}
    	ModelAndView modelAndView = saveChange(changeId, changeTitle, changeVo, redirectAttributes);
    	if(modelAndView.getModel().get("msg") != null && StringUtils.isNotBlank(modelAndView.getModel().get("msg").toString())){
    		return modelAndView;
    	}
    	applyChange(changeId, changeVo.getId());
    	return modelAndView;
    }
    
    @RequestMapping("/change/apply.html")
    public @ResponseBody String applyChange(Long changeId, Long custBaseId) throws Exception {
    	CMerchCustBase existedOne = customerService.findCustBaseById(custBaseId);
    	Map<String,Object> params = new HashMap<>();
        String processName = "cust_change_apply_1";
        String viewPage = "customer/change/view"+custBaseId+".html";
        CMerchCustBase changeVo = this.changeService.getChangeBean(CMerchCustBase.class,changeId);
        BeanUtils.copyPropertiesIgnoreNil(changeVo, existedOne);
        TEmployee emp = ApplicationContextUtil.getCurrentUser();
        if("5".equals(existedOne.getCustType())){
          processName = "retail_cust_change_1";
          params.put("TICKET", "3".equals(existedOne.getIsInvoice()) ? 1 : 0);
        }else if("70".equals(existedOne.getCustType())){
          processName = "cust_change_type70";
        }else if("6".equals(existedOne.getCustType())){
        	processName = "receiver_change";
            params.put("groupType","CW");
            params.put("level","01");
            params.put("startUser",emp.getLoginName());
        }
        params.put("key",custBaseId);
        params.put("changeId",changeId);
        params.put("name", existedOne.getName());
        params.put("userId",emp.getId());
        params.put("TICKET","3".equals(existedOne.getIsInvoice())?"1":"0");
        params.put("viewPage",viewPage);
        //对象主键
        this.processService.startProcess(params, processName, emp.getLoginName());
        return Constants.SUCCESS_CODE_STR;
    }
    
    /**
     * 不存在客户SAP编号时获取SAP反馈信息
     * @author: chaoyang.ren 
     * @date:2016年10月18日  上午10:28:46
     * @param customerId
     * @return
     */
    @RequestMapping("/feedback")
    public @ResponseBody String feedback(Long customerId) {
    	CMerchCustBase custBase = customerService.findCustBaseById(customerId);
    	if(custBase == null){
    		return StringUtils.EMPTY;
    	}
    	String processId = custBase.getProcessId();
    	if(StringUtils.isBlank(processId)){
    		return StringUtils.EMPTY;
    	}
    	return (String) runtimeService.getVariable(processId, "MSG");
    }
    
    @RequestMapping("/editRetail/{id}.html")
    public ModelAndView editRetail(@PathVariable(value="id")Long id) throws Exception{
        ModelAndView modelAndView = new ModelAndView("customer/addOrUpdateRetail");
        CMerchCustBase custBase = customerService.findCustBaseById(id);
        List<TAttachment> attachments = attaService.findAttachment(CMerchCustBase.class.getSimpleName(), String.valueOf(custBase.getId()), null);
        custBase.setAttachments(attachments);
        modelAndView.addObject("custBase", custBase);
        return modelAndView;
    }
    
    /**
     * 删除未提交审批的客户
     * @author: chaoyang.ren 
     * @date:2016年8月23日  上午10:48:38
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/del")
    public @ResponseBody String delCustomer(Long id, HttpServletRequest request) throws Exception{
        CMerchCustBase existedOne = customerService.findCustBaseById(id);
        if(existedOne == null){
            return "不存在的客户！";
        }
        if(!CustomerBaseStateEnu.POTENTIAL.getCode().equals(existedOne.getStates())){
            return "客户状态不允许删除！";
        }
        customerService.delete(id);
        //删除附件
        List<TAttachment> attaments = attaService.findAttachment(CMerchCustBase.class.getSimpleName(), String.valueOf(existedOne.getId()), null);
        if(attaments != null && attaments.size() > 0){
            for (TAttachment att : attaments) {
                attaService.delAttachent(CMerchCustBase.class.getSimpleName(), String.valueOf(existedOne.getId()), att.getId());
            }
        }
        return Constants.SUCCESS_CODE_STR;
    }

    /**
     * 客户提交
     * @author: chaoyang.ren 
     * @date:2016年8月23日  上午10:48:38
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/apply")
    public @ResponseBody String applyCustomer(Long id, HttpServletRequest request) throws Exception{
        CMerchCustBase existedOne = customerService.findCustBaseById(id);
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);

        /*if(existedOne == null || !existedOne.getCreateOid().equals(emp.getId())){
            return "不存在的客户或没有足够的权限！";
        }*/
        if(!CustomerBaseStateEnu.POTENTIAL.getCode().equals(existedOne.getStates())){
            return "客户已申请过审批！";
        }
        if(!"5".equals(existedOne.getCustType()) && (existedOne.getCustStation() == null || (existedOne.getCustStation().getStationId() == null))){
            return "客户还未指定岗位！";
        }
        CMerchCustBase pMerch = null;
        if(existedOne.getPid()!=null){
          pMerch = customerService.findCustBaseById(existedOne.getPid());
        }
        // 合作仓储服务商的零售商
        if(isSpecialRetail(existedOne, pMerch)){ // 所属盐业公司为1421，推送sap
          
        }else if("5".equals(existedOne.getCustType()) && pMerch!=null && "70".equals(pMerch.getCustType())){
			existedOne.setStates(CustomerBaseStateEnu.FORMAL.getCode());
			existedOne.setCode(existedOne.genCode());
			customerService.save(existedOne);
			// 添加零售商账户
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
			CMerchCustAccountExample ex = new CMerchCustAccountExample();
			ex.createCriteria().andMerchCustIdEqualTo(existedOne.getId())
					.andOrganizationIdEqualTo(existedOne.getOrganizationId());
			List<CMerchCustAccount> accounts = accountmapper
					.selectByExample(ex);
			if (accounts.isEmpty()) {
				CMerchCustAccount account = new CMerchCustAccount();
				account.setOrganizationId(existedOne.getOrganizationId());
				account.setSubsidyAmt(new BigDecimal(Double.valueOf(0)));
				account.setCashAmt(new BigDecimal(Double.valueOf(0)));
				account.setBondAmt(new BigDecimal(Double.valueOf(0)));
				account.setCreditAmt(new BigDecimal(Double.valueOf(0)));
				account.setMerchCustId(existedOne.getId());
				account.setContractCreditAmt(BigDecimal.ZERO);
				this.accountmapper.insert(account);
			}

			CMerchCustBalancesExample balanceex = new CMerchCustBalancesExample();
			CMerchCustBalancesExample.Criteria bext = balanceex
					.createCriteria();
			bext.andMerchCustIdEqualTo(existedOne.getId());
			bext.andOrganizationIdEqualTo(existedOne.getOrganizationId());
			bext.andPeriodEqualTo(sf.format(new Date()));
			bext.andAccountTypeEqualTo("1");// 现金账户
			List<CMerchCustBalances> balances = balanceMapper
					.selectByExample(balanceex);
			if (balances.isEmpty()) {
				// 添加科目余额表
				CMerchCustBalances cb = new CMerchCustBalances();
				cb.setMerchCustId(existedOne.getId());
				cb.setOrganizationId(existedOne.getOrganizationId());
				cb.setcAmt(new BigDecimal(Double.valueOf(0)));
				cb.setdAmt(new BigDecimal(Double.valueOf(0)));
				cb.setPtd(new BigDecimal(Double.valueOf(0)));
				cb.setYtd(new BigDecimal(Double.valueOf(0)));
				cb.setAccountType("1");// 现金账户
				cb.setPeriod(sf.format(new Date()));
				this.balanceMapper.insert(cb);
			}

			// 添加客户应收账款期间数据
			CMerchArBalanceExample arEx = new CMerchArBalanceExample();
			CMerchArBalanceExample.Criteria arExt = arEx.createCriteria();
			arExt.andMerchCustIdEqualTo(existedOne.getId());
			arExt.andOrganizationIdEqualTo(existedOne.getOrganizationId());
			List<CMerchArBalance> ars = this.armapper.selectByExample(arEx);
			if (ars.isEmpty()) {
				CMerchArBalance ar = new CMerchArBalance();
				ar.setMerchCustId(existedOne.getId());
				ar.setOrganizationId(existedOne.getOrganizationId());
				ar.setYtd(BigDecimal.ZERO);
				ar.setdAmt(BigDecimal.ZERO);
				ar.setcAmt(BigDecimal.ZERO);
				ar.setPtd(BigDecimal.ZERO);
				ar.setPeriod(sf.format(new Date()));
				this.armapper.insert(ar);
			}
			return Constants.SUCCESS_CODE_STR;
        }
        Map<String,Object> params = new HashMap<>();
        String processName = "costomer_apply";
        String viewPage = "customer/view"+id+".html";
        if("5".equals(existedOne.getCustType())){
          viewPage = "customer/viewRetail/"+id+".html";
          processName = "retail_apply";
          params.put("TICKET", "3".equals(existedOne.getIsInvoice()) ? 1 : 0);
        }
        if("3".equals(existedOne.getCustType())){
          processName = "costomer_apply_KA";
        }
//        if("70".equals(existedOne.getCustType())){
//          processName = "costomer_apply_70";
//        }
        params.put("SEARCH_KEY", existedOne.getName());
        params.put("key",id);
        params.put("name", existedOne.getName());
        if(station!=null && StringUtils.isNotEmpty(station.getStationorgid()) && station.getStationorgid().length()>6){
          params.put("orgId",station.getStationorgid().substring(0,7));
        }
        params.put("TICKET","3".equals(existedOne.getIsInvoice())?"1":"0");
        params.put("level","01");
        params.put("userId",emp.getId());
        params.put("viewPage",viewPage);
        //启动流程
        this.processService.startProcess(params, processName, emp.getLoginName());
        return Constants.SUCCESS_CODE_STR;
    }
    
    boolean isSpecialRetail(CMerchCustBase retail, CMerchCustBase transfer){
      if("5".equals(retail.getCustType()) && transfer!=null && "70".equals(transfer.getCustType())){
        CMerchCustBase saltCop = customerService.findCustBaseById(transfer.getPid());
        if(saltCop!=null && "1421".equals(saltCop.getSapCustomerId())){
          return true;
        }
      }
      return false;
    }
    
    /**
     * 根据销售组织查询岗位列表
     * @author: chaoyang.ren 
     * @date:2016年8月24日  下午4:10:40
     * @param salesOrgId
     * @return
     */
    @RequestMapping("station")
    public @ResponseBody AjaxDTO searchPosition(AjaxDTO bean, CrmStation station, String salesOrgId){
    	return this.stationService.findStations(bean, station, salesOrgId);
    }
    
    @RequestMapping("findCustomer")
    public @ResponseBody AjaxDTO findCustomer(AjaxDTO bean, String custType){
    	CustomerBaseDTO customerBasedto = new CustomerBaseDTO();
    	customerBasedto.setCustType(custType);
    	customerBasedto.setStatus(CustomerBaseStateEnu.FORMAL.getCode());
    	AjaxDTO custs = customerService.findCustBase(customerBasedto, bean, null);
    	return custs;
    }
    
    /**
     * 指定客户岗位
     * @author: chaoyang.ren 
     * @date:2016年8月24日  下午6:03:45
     * @param custId
     * @param positionId
     * @param request
     * @return
     */
    @RequestMapping("setPosition")
    public @ResponseBody String setPosition(Long custId,Long positionId,HttpServletRequest request){
        CMerchCustBase existedOne = customerService.findCustBaseById(custId);
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        if(existedOne == null /*|| !existedOne.getCreateOid().equals(emp.getId())*/){
            return "不存在的客户！";
        }
        if(custId == null || positionId == null){
            return "错误的参数!";
        }
        
        /*if(existedOne.getCustStation() != null && !CustomerBaseStateEnu.POTENTIAL.getCode().equals(existedOne.getStates())){
            return "客户状态不支持修改！";
        }*/
        CMerchCustStation custStation = new CMerchCustStation();
        custStation.setUpdateOid(emp.getId());
        custStation.setUpdateTs(new Date());
        custStation.setCreateOid(emp.getId());
        custStation.setCreateTs(new Date());
        custStation.setMerchCustId(custId);
        custStation.setStates("1");
        custStation.setStationId(positionId);
        customerService.setPosition(custStation);
        return Constants.SUCCESS_CODE_STR;
    }
    
    /**
     * 手动发送客户至SAP
     * @author: chaoyang.ren
     * @date:2016年9月27日  下午5:34:29
     * @param id
     * @return
     */
    @RequestMapping("manualSend")
    public @ResponseBody String manualSendSap(Long id){
    	InputDTO input = customerRFC.constructInputParam(id);
    	ResultDTO resultDto = customerRFC.execute(input);
    	return resultDto.getResult().getTYPE();
    }

    /**
     * 客户列表页面
     * @author: chaoyang.ren 
     * @date:2016年8月20日  上午10:36:21
     * @return
     * @throws Exception
     */
    @RequestMapping("list.html")
    public String listPage()throws Exception{
        return "customer/list";
    }
    
    @RequestMapping(value = "/retail.html", method = RequestMethod.GET)
    public String retailPage()throws Exception{
        return "customer/retail";
    }
    
    /**
     * 客户列表查询
     * @author: chaoyang.ren 
     * @date:2016年8月20日  上午10:36:21
     * @param customerBasedto
     * @param bean
     * @param request
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public AjaxDTO list(CustomerBaseDTO customerBasedto, AjaxDTO bean, String type, HttpServletRequest request){
      TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);//用户信息
      @SuppressWarnings("unchecked")
      List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);//用户职位信息
      UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);//用户当前岗位
      List<Long> stationids =  this.powerService.getUserStations(user, jobs, station);//获取用户可以查看的岗位
      AjaxDTO custs = customerService.findCustBase(customerBasedto, bean, stationids);
      if(user.getMerchId()!=null){
        if(custs.getRows().isEmpty()){
          List<CMerchCustBase> rows = new ArrayList<>();
          CMerchCustBase mineCust = customerService.findCustBaseById(user.getMerchId());
          rows.add(mineCust);
          custs.setRows(rows);
          custs.setTotal(1);
        }
      }
      return custs;
    }

    @RequestMapping("page.html")
    public String paper()throws Exception{
        return "customer/contract";
    }
    
    @RequestMapping("addPaper.html")
    public String addPaper(String custType, Model model)throws Exception{
        model.addAttribute("custType", custType);
        return "customer/addPaper";
    }
    
    /**
     * 客户库存上报
     * @return
     * @throws Exception
     */
    @RequestMapping("/custinv.html")
    public String custinv() throws Exception{
        return "customer/custinv";
    }
    
    /**
     * 客户库存上报查询
     * @return
     * @throws Exception
     */
    @RequestMapping("/custinv/list")
    @ResponseBody
    public AjaxDTO custinvList(CustomerInvDTO invDTO,AjaxDTO bean, HttpServletRequest request) throws Exception{
    	@SuppressWarnings("unchecked")
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		List<Long> stationids =  this.powerService.getUserStations(user, userjobs, station);//获取用户可以查看的岗位
		invDTO.setStationIds(stationids);
        return customerInvService.find(invDTO,bean);
    }
    
    /**
     * 客户库存上报添加
     * @return
     * @throws Exception
     */
    @RequestMapping("/custinv/add")
    @ResponseBody
    public int custinvAdd(CMerchCustInv inv, HttpServletRequest request) throws Exception{
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        inv.setOrganizationId(emp.getOrganizationId());
        inv.setSalesrepId(emp.getId());
        inv.setSalesrepName(emp.getName());
        return customerInvService.add(inv);
    }
    
    /**
     * 客户库存上报修改
     * @return
     * @throws Exception
     */
    @RequestMapping("/custinv/edit")
    @ResponseBody
    public int custinvEdit(CMerchCustInv inv) throws Exception{
        return customerInvService.save(inv);
    }
    
    /**
     * 客户库存上报删除
     * @return
     * @throws Exception
     */
    @RequestMapping("/custinv/delete")
    @ResponseBody
    public int custinvDelete(Long id) throws Exception{
        return customerInvService.delete(id);
    }
    
    /**
     * 根据当前登录用户获取客户列表
     * @return
     * @throws Exception
     */
    @RequestMapping("/findCusts")
    @ResponseBody
    public List<CMerchCustBase> findCusts(HttpServletRequest request) throws Exception{
        //User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        List<CMerchCustBase> custs = customerService.findCustsByEmployee(emp.getId());
        return custs;
    }
    
    @ResponseBody
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public int importRetail(@RequestParam("file") MultipartFile file, HttpServletRequest request, Long pid){
      TEmployee user = (TEmployee) request.getSession().getAttribute("user");
      UserStations stations = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);
      String suffix = Files.excelSuffix(file.getOriginalFilename());
      if (suffix == null) {
        return 0;
      }
      String filename = UUID.randomUUID().toString() + suffix;
      String path = Files.standardFolderPath(basePath) + filename;
      Files.storeFile(file, path);
      String[][] text = Excel2007Util.read(path,0);
      File fi = new File(path);
      fi.delete();
      return customerService.importRetail(text, user, pid, stations);
    }
    
    @ResponseBody
    @RequestMapping(value = "/retails", method = RequestMethod.GET)
    public AjaxDTO retails(Long merchid, AjaxDTO page, CustomerBaseDTO customerBasedto){
      return customerService.findRetails(customerBasedto, page, merchid);
    }
    
    /**
     * 新增送达方页面
     * @author: chaoyang.ren 
     * @date:2016年11月7日  下午2:22:30
     * @return
     * @throws Exception
     */
    @RequestMapping("receiver/index.html")
    public String addReceiver()throws Exception{
        return "customer/index4receiver";
    }
    
    /**
     * 编辑送达方
     * @author: chaoyang.ren 
     * @date:2016年11月7日  下午4:48:16
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value="receiver/edit{id}.html",method=RequestMethod.GET)
    public ModelAndView editReceiver(@PathVariable(value="id")Long id) throws Exception{
        ModelAndView modelAndView = new ModelAndView("customer/index4receiver");
        CMerchCustBase custBase = customerService.findCustBaseById(id);
        modelAndView.addObject("custBase", custBase);
        return modelAndView;
    }
    
    /**
     * 保存送达方并提交审批
     * @author: chaoyang.ren 
     * @date:2016年11月8日  上午9:20:47
     * @param custBase
     * @param result
     * @param request
     * @param files
     * @param delAtts
     * @param delDists
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @Token
    @RequestMapping("receiver/submit.html")
    public ModelAndView saveReceiverAndApply(@Valid CMerchCustBase custBase, BindingResult result, HttpServletRequest request, String delDists, RedirectAttributes redirectAttributes)throws Exception{
        ModelAndView modelAndView = new ModelAndView("redirect:list.html");
        CustReceiverValidator validator = new CustReceiverValidator();
        validator.validate(custBase, result);
        if (result.hasErrors()) {
            modelAndView.setViewName("redirect:/customer/receiver/index.html");
            redirectAttributes.addFlashAttribute("custBase", custBase);
            redirectAttributes.addFlashAttribute("msg",result.getFieldError().getDefaultMessage());
            return modelAndView;
        }
        ModelAndView saveModelAndView =  this.saveReceiver(custBase, result, request, delDists, redirectAttributes);
        if(result.hasGlobalErrors()){
            return saveModelAndView;
        }
        String applyMsg =  this.applyReceiver(custBase.getId(), request);
        if(!Constants.SUCCESS_CODE_STR.equals(applyMsg)){
            redirectAttributes.addFlashAttribute("msg",applyMsg);
        }
        return modelAndView;
    }
    
    /**
     * 送达方提交
     * @author: chaoyang.ren 
     * @date:2016年11月8日  上午9:35:12
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("receiver/apply")
	public @ResponseBody String applyReceiver(Long id, HttpServletRequest request) throws Exception{
        CMerchCustBase existedOne = customerService.findCustBaseById(id);
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        /*if(existedOne == null || !existedOne.getCreateOid().equals(emp.getId())){
            return "不存在的送达方或没有足够的权限！";
        }*/
        if(!CustomerBaseStateEnu.POTENTIAL.getCode().equals(existedOne.getStates())){
            return "送达方已申请过审批！";
        }
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("key",id);
        params.put("name", existedOne.getName());
        params.put("groupType","CW");
        params.put("level","01");
        params.put("startUser",emp.getLoginName());
        params.put("viewPage","customer/receiver/view"+id+".html");
        //key 对象主键
        this.processService.startProcess(params,"receiver_create",emp.getLoginName());
        return Constants.SUCCESS_CODE_STR;
    }
    
    /**
     * 保存送达方客户信息
     * @author: chaoyang.ren 
     * @date:2016年11月7日  下午2:24:16
     * @param custBase
     * @param result
     * @param request
     * @param delDists
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
	@Token
	@RequestMapping(value = "receiver/save.html", method = RequestMethod.POST)
	public ModelAndView saveReceiver(@Valid CMerchCustBase custBase,BindingResult result, HttpServletRequest request, String delDists, RedirectAttributes redirectAttributes) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		CustReceiverValidator validator = new CustReceiverValidator();
		validator.validate(custBase, result);
		String view = "redirect:/customer/receiver/index.html";
		if (result.hasErrors()) {
			modelAndView.setViewName(view);
			redirectAttributes.addFlashAttribute("custBase", custBase);
			redirectAttributes.addFlashAttribute("msg", result.getFieldError()
					.getDefaultMessage());
			return modelAndView;
		}
		// 送达方判断
		Long pid = custBase.getPid();
		if (pid == null) {
			modelAndView.setViewName(view);
			redirectAttributes.addFlashAttribute("custBase", custBase);
			redirectAttributes.addFlashAttribute("msg", "错误的参数！");
			return modelAndView;
		}
		CMerchCustBase saleTo = customerService.findCustBaseById(pid);
		if (saleTo == null) {
			modelAndView.setViewName(view);
			redirectAttributes.addFlashAttribute("custBase", custBase);
			redirectAttributes.addFlashAttribute("msg", "不存在的送达方！");
			return modelAndView;
		}
		//合作盐业公司时，送达方类型改为仓储服务商，否则处理为送达方
		if("1".equals(saleTo.getCustType())){
			custBase.setCustType("7");
		}else{
			custBase.setCustType("6");
		}
		// 用户信息
		TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		custBase.setUpdateOid(custBase.getCreateOid());
		custBase.setUpdateTs(custBase.getCreateTs());
		// 修改
		if (custBase.getId() != null) {
			CMerchCustBase existedOne = customerService
					.findCustBaseById(custBase.getId());
			if (!CustomerBaseStateEnu.POTENTIAL.getCode().equals(
					existedOne.getStates())) {
				existedOne.setDistributions(custBase.getDistributions());
                customerService.update(existedOne,delDists);
                modelAndView.setViewName(view);
                redirectAttributes.addFlashAttribute("custBase", existedOne);
                redirectAttributes.addFlashAttribute("msg","送达方修改完成，由于该送达方已提交，仅修改了送货地址信息！");
				return modelAndView;
			}
			if (existedOne == null
					|| !existedOne.getCreateOid().equals(emp.getId())) {
				result.addError(new ObjectError("custBase", "错误的参数！"));
				modelAndView.setViewName(view);
				redirectAttributes.addFlashAttribute("custBase", custBase);
				redirectAttributes.addFlashAttribute("msg", "错误的参数！");
				return modelAndView;
			}
			custBase.setCreateOid(existedOne.getCreateOid());
			custBase.setCreateTs(existedOne.getCreateTs());
			custBase.setOrganizationId(existedOne.getOrganizationId());
			custBase.setIsAttachment(existedOne.getIsAttachment());
			custBase.setStates(existedOne.getStates());
			customerService.update(custBase, delDists);
		}
		// 新增
		else {
			custBase.setCreateOid(emp.getId());
			custBase.setCreateTs(new Date());
			custBase.setOrganizationId(saleTo.getOrganizationId());
			custBase.setIsAttachment("0");
			custBase.setStates(CustomerBaseStateEnu.POTENTIAL.getCode());
			custBase = customerService.add(custBase);
		}
		modelAndView.setViewName("redirect:list.html");
		return modelAndView;
	}
    
    /**
     * 送达方列表页面
     * @author: chaoyang.ren 
     * @date:2016年11月8日  下午1:18:12
     * @return
     * @throws Exception
     */
    @RequestMapping("receiver/list.html")
    public String receiverListPage()throws Exception{
        return "customer/list4receiver";
    }
    
    /**
     * 送达方列表查询
     * @author: chaoyang.ren 
     * @date:2016年11月8日  下午1:17:20
     * @param customerBasedto
     * @param bean
     * @param type
     * @param request
     * @return
     */
    @RequestMapping("receiver/list")
    @ResponseBody
	public AjaxDTO receiverList(CustomerBaseDTO customerBasedto, AjaxDTO bean,
			String type, HttpServletRequest request) {
		customerBasedto.setCustType("6,70");
		//当没有售达方条件时才确定售达方范围
		if(StringUtils.isBlank(customerBasedto.getPid())){
			UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位
			TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);// 用户信息
			/*@SuppressWarnings("unchecked")
			List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
			List<Long> stationids = this.powerService.getUserStations(user, jobs, station);// 获取用户可以查看的岗位
			//根据岗位查询售达方客户
			Map<String, Object> param = new HashMap<String,Object>();
			param.put("stationid", stationids);
			param.put("custTypeList", new String[]{"1","4","7","70","9"});
			param.put("type", "1");
	        List<OrderCustomer> custBaseList = orderUtilMapper.getUserCustomerInfo(param);
			List<Long> pids = new ArrayList<Long>();
			if(custBaseList != null && custBaseList.size()>0){
				for (OrderCustomer oc : custBaseList) {
					pids.add(oc.getId());
				}
			}else{
				//当没有售达方时，则不存在查询送达方的前提条件，直接返回;
				bean.setTotal(0);
				bean.setRows(null);
				return bean;
			}
			customerBasedto.setPids(pids);*/
			//只根据销售组织确定送达方查询范围
			if(user.getRoleId() != 1){//不是管理员
				if(station == null){
					bean.setTotal(0);
					bean.setRows(null);
					return bean;
				}else{
					customerBasedto.setSalesOrg(station.getOrgid());
				}
			}
		}
		AjaxDTO custs = customerService.findCustBase(customerBasedto, bean, null);
		
		
		return custs;
	}
    
    /**
     * 送达方详情查看
     * @author: chaoyang.ren 
     * @date:2016年11月8日  上午11:46:29
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("receiver/view{id}.html")
    public ModelAndView viewReceiver(@PathVariable(value="id")Long id)throws Exception{
        ModelAndView modelAndView = new ModelAndView("customer/view4receiver");
        CMerchCustBase custBase = customerService.findCustBaseById(id);
        modelAndView.addObject("custBase", custBase);
        Long pid = custBase.getPid();
        String saleToName;
        if(pid != null){
        	CMerchCustBase cb = customerService.findCustBaseById(pid);
        	saleToName = cb==null?StringUtils.EMPTY:cb.getName();
        }else{
        	saleToName = StringUtils.EMPTY;
        }
        modelAndView.addObject("custBase", custBase);
        modelAndView.addObject("saleToName", saleToName);
        return modelAndView;
    }
    
    @ResponseBody
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String generateExcel(HttpServletRequest request) throws IOException{
      TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);//用户信息
      @SuppressWarnings("unchecked")
      List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);//用户职位信息
      UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);//用户当前岗位
      List<Long> stationids =  this.powerService.getUserStations(user, jobs, station);//获取用户可以查看的岗位
      return customerService.generateExcel(stationids);
    }
    
    @RequestMapping(value="/retailCustomer",method =RequestMethod.GET)
    @ResponseBody
    public  AjaxDTO  retailCustomers(@ModelAttribute  AjaxDTO  bean,String orgid,String custname,HttpServletRequest request){
    	 TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);//用户信息
         @SuppressWarnings("unchecked")
         List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);//用户职位信息
         UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);//用户当前岗位
         List<Long> stationids =  this.powerService.getUserStations(user, jobs, station);//获取用户可以查看的岗位
    	return this.customerService.selectRetailCustomers(user,stationids,bean,orgid,custname);
    }
    
    @ResponseBody
    @RequestMapping(value = "/generate/retail", method = RequestMethod.GET)
    public String generateRetailExcel(AjaxDTO bean, String orgid, String custname,
        HttpServletRequest request) throws IOException {
      TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);// 用户信息
      @SuppressWarnings("unchecked")
      List<UserJobs> jobs = (List<UserJobs>) request.getSession().getAttribute(SessionKey.JOBS);// 用户职位信息
      UserStations station =
          (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);// 用户当前岗位
      List<Long> stationids = this.powerService.getUserStations(user, jobs, station);// 获取用户可以查看的岗位
      return customerService.generateRetailExcel(user, stationids, bean, orgid, custname);
    }
    
    @RequestMapping(value="/updateStates",method =RequestMethod.POST)
    @ResponseBody
    public String updateStates(Long merchId,String states) throws Exception{
    	return this.customerService.updateStates(merchId,states);
    }
}

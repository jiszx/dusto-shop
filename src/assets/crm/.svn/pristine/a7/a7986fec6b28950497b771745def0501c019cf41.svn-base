package com.hhnz.account.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.account.model.CMerchCustAdjust;
import com.hhnz.account.model.CMerchCustAdjustV;
import com.hhnz.account.service.IAdjustAccountService;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.BigDecimalASME;
/**
 * 客户资金调整Controller
 * @author dell
 *
 */
@Controller
@RequestMapping("account/adjust")
public class AdjustAccountController {
	
	@Resource
	private IAdjustAccountService service;
	
	@RequestMapping("/adjustList.html")
	public String adjustList()throws Exception{
		return "account/adjustList";
	}

	/**
	 * 保证金
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adjustList2.html")
	public String adjustList2()throws Exception{
		return "account/adjustBZJ";
	}
	
	@RequestMapping("/adjustList3.html")
    public String adjustList3()throws Exception{
        return "account/adjustBZJ2";
    }
	
	@RequestMapping("adjustAudit.html")
    public ModelAndView policyDetial(Long id) throws Exception{
    	ModelAndView mv = new ModelAndView("account/adjustAudit");
    	CMerchCustAdjustV adjust = new CMerchCustAdjustV();
    	adjust.setId(id);
    	AjaxDTO dto =new AjaxDTO();
    	dto.setLimit(1);
    	dto=this.service.findAccountAdjustList(adjust,dto);
    	CMerchCustAdjustV vo = new CMerchCustAdjustV();
    	if(dto.getRows().size()>0){
    		vo=(CMerchCustAdjustV) dto.getRows().get(0);
    		vo.setAmt(BigDecimalASME.divide(vo.getAmt()));
    	}
    	mv.addObject("adjust", vo);
    	return mv;
    }
	//调整信息手工录入
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public @ResponseBody Integer  addaccount(@ModelAttribute CMerchCustAdjust model,HttpServletRequest request) throws Exception{
    	TEmployee user =(TEmployee) request.getSession().getAttribute("user");
    	model.setCreateOid(user.getId());
    	model.setCreateTs(new Date());
    	model.setAmt(BigDecimalASME.multiply(model.getAmt()));
    	model.setType("1");
    	model.setStates("1");
    	return this.service.addAccountAdjust(model);
    }
    
  //调整信息手工录入
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public @ResponseBody Integer  editaccount(@ModelAttribute CMerchCustAdjust model,HttpServletRequest request) throws Exception{
    	TEmployee user =(TEmployee) request.getSession().getAttribute("user");
    	model.setUpdateOid(user.getId());
    	model.setUpdateTs(new Date());
    	model.setAmt(BigDecimalASME.multiply(model.getAmt()));
    	model.setType("1");
    	model.setStates("1");
    	//model.setOrganizationName(org.getName());
    	//model.setCreateName(user.getName());
    	return this.service.updateAdjust(model);
    }
    
    //获取资金调整list
	@RequestMapping("list")
    public @ResponseBody AjaxDTO listPage(@ModelAttribute AjaxDTO bean,CMerchCustAdjustV adjust) throws Exception {
        AjaxDTO dto = this.service.findAccountAdjustList(adjust,bean);
        return dto;
    }
	
	@RequestMapping(value = "del",method = RequestMethod.POST)
    public @ResponseBody Integer del(@RequestParam("id") Long id)throws Exception{
        return this.service.delAdjust(id);
    }
	
	@RequestMapping(value="audit",method = RequestMethod.POST)
	public @ResponseBody Integer audit(@RequestParam("id") Long id,String states,HttpServletRequest request) throws Exception{
		if("2".equals(states)){
    		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    		Map<String, Object> param = new HashMap<String, Object>();
    		param.put("key", id);
    		param.put("startUser",user.getLoginName());
    		param.put("groupType", "admin");
    		param.put("level", "1");
    		param.put("name", "调整编号:"+id);
    		param.put("viewPage","account/adjust/adjustAudit.html?id="+id);
    		return this.service.startprocess(id,param,user);
		}else{			
			return this.service.audit(id,states);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/sap", method = RequestMethod.GET)
	public String sapMessage(Long id){
	  CMerchCustAdjust adjust = service.findAdjustById(id);
	  return adjust.getAttribute2();
	}
}

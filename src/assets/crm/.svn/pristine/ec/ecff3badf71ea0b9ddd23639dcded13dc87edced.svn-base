package com.hhnz.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.customer.model.CMerchCustBase;
import com.hhnz.customer.service.ICustomerService;
import com.hhnz.order.dto.DistributeOrderDTO;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.organization.model.CrmSalesOrganization;
import com.hhnz.util.AjaxDTO;

/**
 * 
 * @author dell
 *
 */
@Controller
@RequestMapping("order/distributor")
public class DistributorOrderController {
  @Resource
  private OrderService orderservice;
  @Resource
  private OrderUtilService service;
	
  @Autowired
  private ICustomerService customerService;
	@RequestMapping("distributorIndex.html")
	public String distributorIndex() throws Exception{
		return "order/distributorIndex";
	}
	
	/**
	 * 配送商详情页面
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("distributeOrderDetails")
	@ResponseBody
	 public ModelAndView distributeOrderDetails(@ModelAttribute DistributeOrderDTO  order,String type, String audit, String taskId) throws Exception{
		String page = StringUtils.isEmpty(type)?"order/distributorDetails":"order/distributorEditIndex";
        ModelAndView mv = new ModelAndView(page);
        mv.addObject("order", order);
        mv.addObject("audit", audit);
        mv.addObject("taskId", taskId);
       // mv.addObject("type",StringUtils.isEmpty(type)?"0":type);
        return mv;
    }
	
	/**
	 * 配送商订单修改
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderedit.html")
    public ModelAndView orderedit(Long id) throws Exception{
        ModelAndView mv = new ModelAndView("order/distributorEdit");
        OmOrderHeadersAll order = this.orderservice.getOrderBYid(id);
        CMerchCustBase  cust = this.customerService.findCustBaseById(order.getMerchCustId());
        mv.addObject("cust",cust);
        mv.addObject("order", order);
        mv.addObject("id", id);
        return mv;
    }
	
	@RequestMapping("orderEditValidate")
	@ResponseBody
	public String orderEditValidate(Long id) throws Exception{
		 OmOrderHeadersAll order = this.orderservice.getOrderBYid(id);
		 return "1".equals(order.getStates())?"200":"500";
	}
	/**
	 * 配送商订单查询
	 * @param bean
	 * @param lpno
	 * @param custname
	 * @param bdate
	 * @param edate
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("distributorOrderList")
	public @ResponseBody AjaxDTO distributorOrderList(@ModelAttribute AjaxDTO bean,
			String lpno, String custname, String bdate, String edate,
			HttpServletRequest request) throws Exception{
		TEmployee  user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		Map<String, Object> params = new HashMap<String, Object>();
		List<CrmSalesOrganization> orglist = (List<CrmSalesOrganization>) request.getSession().getAttribute(SessionKey.ORG_INFO);
		List<String>  orgids = new ArrayList<String>();
		if(orglist !=null && orglist.size()>0){
			for(CrmSalesOrganization org :orglist){
				orgids.add(org.getId());
			}
		}
		if(user.getMerchId()!=null){
		  params.put("merchid", user.getMerchId());
		}
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(custname)){
		  params.put("merchname", custname);
		}
		params.put("usertype", user.getUserType());
		params.put("orgids", orgids);
		params.put("userid", user.getId());
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset()+bean.getLimit());
		bean = this.service.findDistributeOrders(params);
		return bean;
	}
	
	/**
	 * 配送商订单详情查询
	 * @param merchid
	 * @param lpno
	 * @param shipid
	 * @param states
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("distributorDetailList")
	@ResponseBody
	public AjaxDTO distributorDetailList(Long merchid,String lpno,
			Long shipid,String states,String isMatched,AjaxDTO bean) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("merchid", merchid);
		params.put("lpno", lpno);
		params.put("shipid", shipid);
		params.put("states", states);
		params.put("isMatched", isMatched);
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset()+bean.getLimit());
		bean = this.service.getDistributeDetailList(params);
		return bean;
	}
	
	@RequestMapping("distributorEditList")
	@ResponseBody
	public AjaxDTO distributorEditList(Long merchid,String lpno,
			Long shipid,String states,String isMatched,AjaxDTO bean) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("merchid", merchid);
		params.put("lpno", lpno);
		params.put("shipid", shipid);
		params.put("states", states);
		params.put("isMatched", isMatched);
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset()+bean.getLimit());
		bean = this.service.distributorEditList(params);
		return bean;
	}
	
	@ResponseBody
	@RequestMapping(value = "/match", method = RequestMethod.POST)
	public int orderMatch(String batchNum, String upaccounts, Long merchid, String processId){
	  return 0;
	}
	
	/**
	 * 配送商订单删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delorder")
	@ResponseBody
	public Map<String,Object> delorder(Long id) throws Exception{
		//Map<String,Object> result = new HashMap<String, Object>();
		return this.service.delDistributeOrder(id);
	}
}

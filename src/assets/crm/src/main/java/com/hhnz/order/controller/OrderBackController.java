package com.hhnz.order.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.UserJobs;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.order.model.OmOrderHeadersAll;
import com.hhnz.order.service.OrderBackService;
import com.hhnz.order.service.OrderService;
import com.hhnz.order.service.OrderUtilService;
import com.hhnz.process.service.IProcessService;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.pub.service.IPowerOrgService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.ApplicationContextUtil;

import net.sf.json.JSONArray;
/**
 * 退货Controller
 * @author hhnz-skevin 2016-08-31
 *
 */
@Controller
@RequestMapping("orderBack")
public class OrderBackController {
	
	@Resource
	private OrderBackService  backservice;
	
	@Resource
	private IProcessService processService;
	
	@Resource
	private OrderUtilService orderUtilservice;
	
	@Resource
	private IPowerOrgService powerService;
	
	@Resource
	private OrderService orderservice;
	
	@Autowired
	private IAttachmentService attaService;

	@RequestMapping("orderBackList.html")
	public String orderBack() throws Exception{
		return "order/orderBackList";
	}
	@RequestMapping("addOrderBack.html")
	public String addOrderBack() {
		return "order/addOrderBack";
	}
	
	/**
	 * 获取退货订单头信息
	 * @return
	 */
	@RequestMapping("backOrderHeaderList")
	@ResponseBody
	public AjaxDTO backOrderHeaderList(){
		return this.backservice.selectBackOrderHeaderList();
	}
	
	/**
	 * 退货订单详情页面
	 * @param id
	 * @param type
	 * @param isCenter
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderBackDetails.html")
	public ModelAndView orderdetails(Long id) throws Exception {
		OmOrderHeadersAll order = orderservice.getOrderBYid(id);
		ModelAndView mv = new ModelAndView();
		String page = "order/orderBackDetails";
		mv.setViewName(page);
		String custname = this.orderUtilservice.getcustnameByorderId(id);
		mv.addObject("custname", custname);
		AjaxDTO lines = orderservice.orderLineDetails(id);
		JSONArray linedata = JSONArray.fromObject(lines.getRows());
		mv.addObject("order", order);
		if(order.getAttribute2() !=null) {
			OmOrderHeadersAll saltOrder = orderservice.getOrderBYid(Long.parseLong(order.getAttribute2()));
			String saltCustNamt = this.orderUtilservice.getcustnameByorderId(saltOrder.getId());
			mv.addObject("saltOrder", saltOrder);
			mv.addObject("saltCustNamt", saltCustNamt);
		}
		mv.addObject("lines", linedata);
		mv.addObject("id", id);
		List<TAttachment> attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(order.getId()), null);
		mv.addObject("attachments", attachments);
		String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
		mv.addObject("attachmentBASEURI", baseUrl);
		return mv;
	}
	
	/**
	 * 审批页面
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderBackAuditHtml.html")
	public ModelAndView orderBackAuditHtml(Long id) throws Exception {
		OmOrderHeadersAll order = orderservice.getOrderBYid(id);
		ModelAndView mv = new ModelAndView();
		String page = "order/orderBackAuditHtml";
		mv.setViewName(page);
		String custname = this.orderUtilservice.getcustnameByorderId(id);
		mv.addObject("custname", custname);
		AjaxDTO lines = orderservice.orderLineDetails(id);
		JSONArray linedata = JSONArray.fromObject(lines.getRows());
		mv.addObject("order", order);
		mv.addObject("lines", linedata);
		mv.addObject("id", id);
		List<TAttachment> attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(order.getId()), null);
		mv.addObject("attachments", attachments);
		String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
		mv.addObject("attachmentBASEURI", baseUrl);
		return mv;
	}
	/**
	 * 退货订单修改
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderBackEdit.html")
	public ModelAndView orderBackEdit(Long id) throws Exception {
		OmOrderHeadersAll order = orderservice.getOrderBYid(id);
		ModelAndView mv = new ModelAndView();
		String page = "order/orderBackEdit";
		mv.setViewName(page);
		String custname = this.orderUtilservice.getcustnameByorderId(id);
		mv.addObject("custname", custname);
		mv.addObject("order", order);
		mv.addObject("id", id);
		//获取附近信息
		List<TAttachment> attachments = attaService.findAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(order.getId()), null);
		mv.addObject("attachments", attachments);
		String baseUrl = (String) ApplicationContextUtil.get("attachmentBASEURI");
		mv.addObject("attachmentBASEURI", baseUrl);
		return mv;
	}
	/**
	 * 获取原订单对应订单行数据
	 * @param headerid
	 * @return
	 */
	@RequestMapping("getOldOrderLine")
	@ResponseBody
	public AjaxDTO getOldOrderLine(Long headerid){
		return this.backservice.selectOldOrderLine(headerid);
	}
	
	@RequestMapping("auditlinedata")
	@ResponseBody
	public AjaxDTO getAuditlinedata(Long headerid){
		return this.backservice.selectAuditLineData(headerid);
	}
	/**
	 * 添加退货订单
	 * @param orderid
	 * @param lines
	 * @param backReason
	 * @param request
	 * @return
	 */
	@RequestMapping("addBackOrder")
	public @ResponseBody String addBackOrder(Long orderid,String lines,String backReason,String remark,HttpServletRequest request){
		TEmployee user = (TEmployee)request.getSession().getAttribute(SessionKey.USER_INFO);
		return this.backservice.addBackOrder(orderid,lines,backReason,remark,user);
	}
	
	/**
	 * 获取退货订单List
	 * @param custname
	 * @param orgid
	 * @param states
	 * @param oldorderid
	 * @param orderid
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("list")
	public @ResponseBody AjaxDTO getBackOrderList(AjaxDTO bean,String custname,
			String orgid, String states, Long oldorderid, Long orderid,
			HttpServletRequest request) {
		TEmployee user = (TEmployee) request.getSession().getAttribute(
				SessionKey.USER_INFO);
		List<UserJobs> userjobs = (List<UserJobs>) request.getSession()
				.getAttribute(SessionKey.JOBS);// 用户职位信息
		UserStations station = (UserStations) request.getSession()
				.getAttribute(SessionKey.CURR_STATION);// 用户当前岗位信息
		List<Long> stationids = this.powerService.getUserStations(user,
				userjobs, station);// 获取用户可以查看的岗位
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custname", custname);
		params.put("orgid", orgid);
		params.put("states", states);
		params.put("oldorderid", oldorderid);
		params.put("orderid", orderid);
		params.put("stationids", stationids);
		params.put("begin", bean.getOffset());
		params.put("end", bean.getOffset() + bean.getLimit());
		return this.backservice.selectBackOrderList(params);
	}
	/**
	 * 获取修改行数据
	 * @param headerid
	 * @return
	 */
	@RequestMapping("editLineData")
	public @ResponseBody AjaxDTO selectEditLineData(Long headerid){
		return this.backservice.selectEditLineData(headerid);
	}
	/**
	 * 修改行数据
	 * @param spiltId
	 * @param num
	 * @return
	 */
	@RequestMapping("editNum")
	public @ResponseBody void editNum(Long  spiltId,BigDecimal num){
		 this.backservice.editNum(spiltId,num);
	}
	/**
	 * 审批
	 * @param id
	 * @param states
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("doAudit")
	@ResponseBody
	public String doAudit(Long id,String states,HttpServletRequest request) throws Exception{
		TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
		//验证退货订单
		String flag = this.backservice.validateBackOrder(id);
		if(!"S".equals(flag)) {
			return flag;
		}
		this.backservice.startProcess(user,id,states);
		//生成对应的合作盐业公司退货订单
		this.backservice.addStorageOrder(id);
		return "S";
	}
	
	@RequestMapping("editAllocatePrice")
	@ResponseBody
	public String editAllocatePrice(Long id,BigDecimal price) throws Exception{
	  return this.backservice.editAllocatePrice(id,price);
	}
	
	@ResponseBody
	@RequestMapping(value="backOrderEditSave",method = RequestMethod.POST)
	public  String backOrderEditSave(Long orderid,@RequestParam(value="files") MultipartFile[] files,String  backReason,String remark,
			HttpServletRequest request) throws Exception{
		TEmployee user =(TEmployee)request.getSession().getAttribute(SessionKey.USER_INFO);
		OmOrderHeadersAll  order = this.orderservice.getOrderBYid(orderid);
		order.setAttribute6(backReason);
		order.setUpdateTs(new Date());
		order.setUpdateOid(user.getId());
		order.setRemark(remark);
		this.orderservice.updateOrderHeader(order);
		this.attaService.addAttachment(OmOrderHeadersAll.class.getSimpleName(), String.valueOf(orderid), String.valueOf(user.getId()), files);
		return "1";
	}
}

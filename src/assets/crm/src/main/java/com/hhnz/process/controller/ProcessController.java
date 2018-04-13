package com.hhnz.process.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.order.model.OmOrderDeliveredV;
import com.hhnz.order.service.OrderService;
import com.hhnz.process.dto.NextProcessDTO;
import com.hhnz.process.service.IProcessService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.Constants;

/**
 * Created by yang on 2016-7-11.
 */
@Controller
@RequestMapping("/process/")
public class ProcessController {

    @Autowired
    private IProcessService service;
    @Resource
	private OrderService orderservice;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("/myprocess.html")
    public String myProcessPage()throws Exception{
        return "process/mine_process";
    }

    @RequestMapping("/forMe.html")
    public String forMePage()throws Exception{
        return "process/for_me";
    }

    @RequestMapping("/showInfo.html")
    public String showInfo()throws Exception{
        return "process/showInfo";
    }



    @RequestMapping("/history.html")
    public String historyPage()throws Exception{
        return "process/history";
    }

    @RequestMapping("signal")
    public @ResponseBody
    String start(String pid,String act) throws Exception {
        String id = processEngine.getRuntimeService()
                .createExecutionQuery()
                .processInstanceId(pid)
                .activityId(act)
                .singleResult().getId();
        this.runtimeService.setVariable(pid,"SAPID","0000");//流程号  接受节点  
        this.runtimeService.signal(id);
        return "OK";
    }
    
    @RequestMapping(value="goNext",method=RequestMethod.POST)
    public @ResponseBody String goNext(NextProcessDTO npd) throws Exception {
    	String id = processEngine.getRuntimeService()
    			.createExecutionQuery()
    			.processInstanceId(npd.getPid())
    			.activityId(npd.getAct())
    			.singleResult().getId();
    	Map<String,Object> variables = npd.getVariables();
    	if(variables != null){
    		for (Entry<String, Object> entry : variables.entrySet()) {
    			this.runtimeService.setVariable(npd.getPid(),entry.getKey(),entry.getValue());
    		}
    	}
    	this.runtimeService.signal(id);
    	//sap取消订单时，处理其他并行流程为完成
    	if("RECV_CANCLE_ORDER".equals(npd.getAct().trim())){
    		//订单取消时查询SAP发货数量加入流程
    		Long orderId = (Long) runtimeService.getVariable(npd.getPid(), "key");
    		List<OmOrderDeliveredV> allDeliveryItems = orderservice.getOrderDeliveryItemsByOrderId(orderId);
    		BigDecimal result = BigDecimal.ZERO;
    		for (OmOrderDeliveredV di : allDeliveryItems) {
    			String numString = di.getNum();
    			BigDecimal num = new BigDecimal(numString);
    			result = result.add(num);
    		}
    		List<Task> tasks = taskService.createTaskQuery().processInstanceId(npd.getPid()).active().list();
    		Map<String,Object> params = new HashMap<String,Object>();
    		params.put("SKIP",1);
    		params.put("recvCount",result);
    		for (Task task : tasks) {
    			this.taskService.complete(task.getId(),params);
    		}
    		
    		//物流反馈触发
    		String wlid = runtimeService.createExecutionQuery()
        			.processInstanceId(npd.getPid())
        			.activityId("RECV_WL")
        			.singleResult().getId();
        	this.runtimeService.signal(wlid);
    	}
    	return Constants.SUCCESS_CODE_STR;
    }

    @RequestMapping("listMy")
    public @ResponseBody
    AjaxDTO listPage(@ModelAttribute AjaxDTO bean,String his,HttpServletRequest request) throws Exception {
        Map<String,Object> param = new HashMap<String, Object>();
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        if(StringUtils.hasLength(his)){
            param.put("his",true);
        }
        param.put("user",emp.getLoginName());
        return this.service.findMyProcess(param,bean);
    }

    @RequestMapping("waitMe")
    public @ResponseBody
    AjaxDTO listWaitMe(@ModelAttribute AjaxDTO bean,HttpServletRequest request) throws Exception {
    	TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    	Map<String, Object> params = new HashMap<String,Object>();
    	params.put("oper", emp);
        return this.service.findMyTask(params,bean);
    }

    @RequestMapping("look")
    public ModelAndView look(String taskId)throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("process/look");
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        String urlKey = task.getTaskDefinitionKey()+"viewPage";
        String url = this.service.findViewUrl(urlKey,task.getProcessInstanceId());
        if(!StringUtils.hasLength(url)){
            url=this.service.findViewUrl("viewPage",task.getProcessInstanceId());
            if(url!=null && !url.contains("taskId=")){
              url = url.indexOf('?')!=-1?url+"&taskId="+taskId:url+"?taskId="+taskId;
            }
            List<Comment> comments = this.taskService.getProcessInstanceComments(task.getProcessInstanceId());
            mv.addObject("viewPage",url);
            mv.addObject("taskID",task.getId());
            mv.addObject("comments",comments);
        }else{
          String redirectUrl = url.indexOf('?')!=-1?"redirect:/"+url+"&taskId="+taskId:"redirect:/"+url+"?taskId="+taskId;
          return new ModelAndView(redirectUrl);
        }
        return  mv;
    }
//
//    @RequestMapping("look1")
//    public ModelAndView look1()throws Exception{
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("process/look");
//        //Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
//        //String urlKey = task.getTaskDefinitionKey()+"viewPage";
//        //String url = this.service.findViewUrl(urlKey,task.getProcessInstanceId());
//        //List<Comment> comments = this.taskService.getProcessInstanceComments(task.getProcessInstanceId());
//        mv.addObject("viewPage","order/auditdetails.html?id=1");
//        //mv.addObject("taskID",task.getId());
//        //mv.addObject("comments",comments);
//        return  mv;
//    }
//
//    @RequestMapping("look2")
//    public ModelAndView look2()throws Exception{
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("process/look");
//        //Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
//        //String urlKey = task.getTaskDefinitionKey()+"viewPage";
//        //String url = this.service.findViewUrl(urlKey,task.getProcessInstanceId());
//        //List<Comment> comments = this.taskService.getProcessInstanceComments(task.getProcessInstanceId());
//        mv.addObject("viewPage","order/addWL.html?id=1");
//        //mv.addObject("taskID",task.getId());
//        //mv.addObject("comments",comments);
//        return  mv;
//    }

    @RequestMapping("commit")
    public @ResponseBody
    int commit(String taskId, String comment, HttpSession session) throws Exception {
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstId = task.getProcessInstanceId();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("FLAG",1);
        TEmployee emp = (TEmployee) session.getAttribute(SessionKey.USER_INFO);
        this.identityService.setAuthenticatedUserId(emp.getLoginName());
        this.taskService.addComment(taskId,processInstId, Strings.nullToEmpty(comment));
        this.taskService.complete(taskId,params);
        return 1;
    }

    @RequestMapping("rollBack")
    public @ResponseBody
    int rollBack(String taskId, String comment, HttpSession session) throws Exception {
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstId = task.getProcessInstanceId();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("FLAG",0);
        TEmployee emp = (TEmployee) session.getAttribute(SessionKey.USER_INFO);
        this.identityService.setAuthenticatedUserId(emp.getLoginName());
        this.taskService.addComment(taskId,processInstId, Strings.nullToEmpty(comment));
        this.taskService.complete(taskId,params);
        return 1;
    }


    /**
     * 流程图
     * @param response httpservletresponse
     */
    @RequestMapping("view")
    public void view(@RequestParam("id") String id, HttpServletResponse response){
        InputStream in=null;
        OutputStream out=null;
        try{

//            List<HistoricActivityInstance> hisActs = this.historyService.createHistoricActivityInstanceQuery().processInstanceId(id).finished().list();
//            //得到正在执行的Activity的Id
//            List<String> activityIds = new ArrayList<String>();
//            for (HistoricActivityInstance exe : hisActs) {
////                List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
////                activityIds.addAll(ids);
//                activityIds.add(exe.getActivityId());
//            }
            BpmnModel bpmnModel = repositoryService.getBpmnModel(this.runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult().getProcessDefinitionId());
            //in = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator().generateDiagram(bpmnModel,"png",runtimeService.getActiveActivityIds(id),1.0);
            in = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator().generateDiagram(bpmnModel,"png",runtimeService.getActiveActivityIds(id),new ArrayList<String>(),
                    processEngine.getProcessEngineConfiguration().getActivityFontName(),
                    processEngine.getProcessEngineConfiguration().getLabelFontName(),
                    processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
                    null,1.0);
            //in = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator().generatePngDiagram(bpmnModel);
            int len =0;
            byte[] data=new byte[1024];
            String fileName = URLEncoder.encode("流程.png", "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/x-png;charset=UTF-8");
            out = response.getOutputStream();
            while ((len=in.read(data))>0){
                out.write(data, 0, len);
            }
        }catch(Exception e){
            System.err.println(e);
            e.printStackTrace();
        }finally{
          IOUtils.closeQuietly(in);
          IOUtils.closeQuietly(out);
        }


    }

    @RequestMapping("viewCommons")
    @ResponseBody
    public List<Comment>  viewCommons(String processId)throws Exception{

        List<Comment> comments = this.taskService.getProcessInstanceComments(processId);
        return comments;

    }


    /**
     * 流程图
     * @param response httpservletresponse
     */
    @RequestMapping("viewHis")
    public void viewHis(@RequestParam("id") String id, HttpServletResponse response){
        InputStream in=null;
        OutputStream out=null;
        try{
            List<HistoricActivityInstance> hisActs = this.historyService.createHistoricActivityInstanceQuery().processInstanceId(id).finished().list();
//            //得到正在执行的Activity的Id
            List<String> activityIds = new ArrayList<String>();
            for (HistoricActivityInstance exe : hisActs) {
                activityIds.add(exe.getActivityId());
            }
            BpmnModel bpmnModel = repositoryService.getBpmnModel(this.historyService.createHistoricProcessInstanceQuery().processInstanceId(id).singleResult().getProcessDefinitionId());
            in = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator().generateDiagram(bpmnModel,"png",activityIds,new ArrayList<String>(),
                    processEngine.getProcessEngineConfiguration().getActivityFontName(),
                    processEngine.getProcessEngineConfiguration().getLabelFontName(),
                    processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
                    null,1.0);
            int len =0;
            byte[] data=new byte[1024];
            String fileName = URLEncoder.encode("流程.png", "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/x-png;charset=UTF-8");
            out = response.getOutputStream();
            while ((len=in.read(data))>0){
                out.write(data, 0, len);
            }
        }catch(Exception e){
            System.err.println(e);
            e.printStackTrace();
        }finally{
          IOUtils.closeQuietly(in);
          IOUtils.closeQuietly(out);
        }
    }

    @RequestMapping("startProcess")
	public @ResponseBody String startProcess(String processKey, String startUserName, HttpServletRequest request) {
		this.identityService.setAuthenticatedUserId(startUserName);
		@SuppressWarnings("unchecked")
		Map<String, Object> parameterMap = request.getParameterMap();
		Map<String, Object> parameter = new HashMap<String, Object>();
		for (Entry<String, Object> entry : parameterMap.entrySet()) {
			if(entry.getValue() != null && entry.getValue().getClass().isArray()){
				Object[] obj = (Object[]) entry.getValue();
				parameter.put(entry.getKey(), obj[0]);
			}else{
				parameter.put(entry.getKey(), entry.getValue());
			}
		}
		ProcessInstance instance = this.runtimeService.startProcessInstanceByKey(processKey, parameter);
		return instance.getId();
	}
}

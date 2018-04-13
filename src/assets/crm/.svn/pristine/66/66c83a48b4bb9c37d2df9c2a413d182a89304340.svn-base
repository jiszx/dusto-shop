package com.hhnz.crm.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hhnz.crm.model.TAttachment;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TNotes;
import com.hhnz.crm.service.INotesService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.dto.RespMsg;
import com.hhnz.pub.service.IAttachmentService;
import com.hhnz.util.AjaxDTO;
import com.hhnz.util.JsonUtil;

/**                               
 * 描述: 公告类控制层                         
 * JDK 版本: 1.7*                            
 * 创建人: 杨成锡                                                       
 */
@Controller
@RequestMapping("system/notes")
public class NotesController {

	/**
	 * 附件服务接口
	 */
    @Autowired
    private IAttachmentService attaService;

    /**
     * 公告服务接口
     */
    @Autowired
    private INotesService service;

    /**
     * 公告首页列表
     * @return 列表视图
     */
    @RequestMapping("index.html")
    public String indexPage()throws Exception{
        return "system/notes";
    }
    /**
     * 新增公告视图
     * @return 新增页面视图
     */
    @RequestMapping("notesAdd.html")
    public String addPage()throws Exception{
        return "system/notes_add";
    }

    /**
     * 公告详情页面
     * @param id 公告ID
     * @return 视图模型
     * @throws Exception 异常
     */
    @RequestMapping("noteDetail/{id}.html")
    public ModelAndView detail(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView();
        TNotes vo = this.service.findById(id);
        if (!"0".equals(vo.getStat())) {
            mv.setViewName("404");
        } else {
            mv.setViewName("system/noteDetail");
            AjaxDTO page = null;
            List<TAttachment> attas = this.attaService.findAttachment(TNotes.class.getSimpleName(), id + "", page);
            mv.addObject("bean", vo);
            mv.addObject("attas", attas);
        }
        return mv;
    }
    /**
     * 模态框显示公告信息
     * @param id 公告ID
     * @return 视图模型
     * @throws Exception 异常信息
     */
    @RequestMapping("look/{id}.html")
    public ModelAndView look(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView();
        TNotes vo = this.service.findById(id);
        mv.setViewName("system/note_look");
        AjaxDTO page = null;
        List<TAttachment> attas = this.attaService.findAttachment(TNotes.class.getSimpleName(), id + "", page);
        mv.addObject("bean", vo);
        mv.addObject("attas", attas);
        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView addNotes(@ModelAttribute TNotes notes, @RequestParam("file") MultipartFile[] files, HttpServletRequest request) throws Exception {
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        notes.setCreator(emp.getLoginName());
        Long res = this.service.addNotes(notes);
        //附件处理
        this.attaService.addAttachment(TNotes.class.getSimpleName(), res + "", emp.getLoginName(), files);

        if (res > 0) {
            return new ModelAndView("redirect:/system/notes/index.html");
        } else {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("system/notes_add");
            mv.addObject("bean", notes);
            return mv;
        }
    }

    @RequestMapping("preEdit")
    public ModelAndView preEdit(@RequestParam Long id) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/notes_edit");
        TNotes vo = this.service.findById(id);
        AjaxDTO page = null;
        List<TAttachment> attas = this.attaService.findAttachment(TNotes.class.getSimpleName(), id + "", page);
        mv.addObject("bean", vo);
        mv.addObject("attas", attas);
        return mv;
    }

    @RequestMapping("uploadImg")
    public void uploadImg(@RequestParam(value = "upload", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response)throws Exception{
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        String res =  this.attaService.addAttachment(TNotes.class.getSimpleName(),emp.getLoginName(),file);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        RespMsg msg = new RespMsg();
        msg.setErrorCode(0);
        msg.setData(res);
        response.getWriter().write(JsonUtil.toJSON(msg));
        response.getWriter().close();
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute TNotes model,@RequestParam("file") MultipartFile[] files, HttpServletRequest request) throws Exception {
        int res = this.service.updateNotes(model);
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        //附件处理
        this.attaService.addAttachment(TNotes.class.getSimpleName(), model.getId() + "", emp.getLoginName(), files);
        if (res > 0) {
            return new ModelAndView("redirect:/system/notes/index.html");
        } else {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("system/notes_edit");
            mv.addObject("bean", model);
            return mv;
        }
    }

    @RequestMapping(value = "apply", method = RequestMethod.POST)
    @ResponseBody
    public int apply(@RequestParam("id") Long id, HttpServletRequest request) throws Exception {
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        int res = this.service.applyNotes(id, "2", emp.getLoginName());
        return res;
    }

    @RequestMapping(value = "passed", method = RequestMethod.POST)
    @ResponseBody
    public int passed(@RequestParam("id") Long id, HttpServletRequest request) throws Exception {
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        int res = this.service.applyNotes(id, "0", emp.getLoginName());
        return res;
    }

    @RequestMapping(value = "noPass", method = RequestMethod.POST)
    @ResponseBody
    public int noPass(@RequestParam("id") Long id, HttpServletRequest request) throws Exception {
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        int res = this.service.applyNotes(id, "1", emp.getLoginName());
        return res;
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer del(@RequestParam("id") Long id) throws Exception {
        return this.service.delNotes(id);
    }

    @RequestMapping(value = "delAtta", method = RequestMethod.POST)
    public
    @ResponseBody
    Integer delAtta(@RequestParam("nid") Long nid, @RequestParam("id") Long id) throws Exception {
        return this.attaService.delAttachent(TNotes.class.getSimpleName(), nid + "", id);
    }


    @RequestMapping("list")
    public
    @ResponseBody
    AjaxDTO listPage(@ModelAttribute AjaxDTO bean) throws Exception {
        AjaxDTO dto = this.service.findNotes(new HashMap<String, Object>(), bean);
        return dto;
    }
    
    /**
     * 首页公告查看全部
     * @author: chaoyang.ren 
     * @date:2016年9月6日  下午2:32:46
     * @return
     */
    @RequestMapping("showall.html")
    public String showAll() {
        return "system/notes_show_all";
    }
    
    /**
     * 首页公告查看全部获取公告列表
     * @author: chaoyang.ren 
     * @date:2016年9月6日  下午2:34:09
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping("showall_data")
    public @ResponseBody AjaxDTO showAllData(@ModelAttribute AjaxDTO bean, HttpServletRequest request) throws Exception {
    	Map<String,Object> params = new HashMap<String,Object>();
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        params.put("DEPT",emp.getOrganizationId());
        params.put("STATE","0");
        params.put("SORT","top_flag desc,create_ts desc");
        AjaxDTO dto = this.service.findNotes(params, bean);
        return dto;
    }
}

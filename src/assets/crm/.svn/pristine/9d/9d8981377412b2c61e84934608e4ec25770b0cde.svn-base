package com.hhnz.crm.controller;

import com.hhnz.crm.model.TAuthority;
import com.hhnz.crm.model.TEmployee;
import com.hhnz.crm.model.TTimeLine;
import com.hhnz.crm.model.UserStations;
import com.hhnz.crm.service.IHomeService;
import com.hhnz.crm.service.INotesService;
import com.hhnz.crm.util.SessionKey;
import com.hhnz.dto.TreeDTO;
import com.hhnz.util.AjaxDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 首页
 */
@Controller
@RequestMapping("/")
public class HomeController {
	private static final Log LOG = LogFactory.getLog(HomeController.class);
	/**
	 * 首页接口
	 */
    @Autowired
    private IHomeService service;
    
    /**
     * 时间线接口
     */
    @Autowired
    private ITimelineService lineService;

    /**
     * 公告接口
     */
    @Autowired
    private INotesService notesService;

    @RequestMapping("/index.jhtml")
    public String adminIndex(HttpServletRequest request)throws Exception{
        if(!"1".equals(request.getSession().getAttribute(SessionKey.ISLOGIN))){
            SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl.getAuthentication().getAuthorities();
            Integer rid=Integer.parseInt(authorities.get(0).toString());
            List<TreeDTO<TAuthority>> dtos = this.service.findAllAuthority(rid);
            Map<String,Object> res = this.service.findUserSessionInfo(securityContextImpl.getAuthentication().getName());
            for (Map.Entry<String, Object> ent : res.entrySet()) {
                request.getSession().setAttribute(ent.getKey(),ent.getValue());
            }
            request.getSession().setAttribute(SessionKey.USER_MENU,dtos);
            request.getSession().setAttribute(SessionKey.ISLOGIN,"1");
        }
        return "home2";
    }
    @RequestMapping("dashboard.html")
    public ModelAndView dashboard(HttpServletRequest request)throws Exception {
        AjaxDTO bean = new AjaxDTO();
        bean.setLimit(10);
        bean.setOffset(0);
        Map<String,Object> params = new HashMap<String,Object>();
        TEmployee emp = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
        params.put("DEPT",emp.getOrganizationId());
        params.put("STATE","0");
        AjaxDTO dto = this.notesService.findNotes(params, bean);
        ModelAndView mv =new ModelAndView();
        mv.setViewName("index");
        mv.addObject("notes",dto.getRows());
        
        
        List<TTimeLine> timelines = this.lineService.findTopline(emp.getId()+"");
        
        mv.addObject("lines",timelines);
        
        return mv;
    }

    @RequestMapping("code.codeImg")
    public String validateCode(HttpServletRequest request, HttpServletResponse response)throws Exception{
//        int width=60, height=20;
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        // 获取图形上下文
//        Graphics g = image.getGraphics();
//        //生成随机类
//        Random random = new Random();
//        // 设定背景色
//        g.setColor(getRandColor(200,250));
//        g.fillRect(0, 0, width, height);
//        //设定字体
//        g.setFont(new Font("Times New Roman",Font.PLAIN,18));
//        //画边框
//        //g.setColor(new Color());
//        //g.drawRect(0,0,width-1,height-1);
//        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
//        g.setColor(getRandColor(160,200));
//        for (int i=0;i<155;i++)
//        {
//            int x = random.nextInt(width);
//            int y = random.nextInt(height);
//            int xl = random.nextInt(12);
//            int yl = random.nextInt(12);
//            g.drawLine(x,y,x+xl,y+yl);
//        }
//        // 取随机产生的认证码(4位数字)
//        //String rand = request.getParameter("rand");
//        //rand = rand.substring(0,rand.indexOf("."));
//        String sRand="";
//
//        for (int i=0;sRand.length()<4;i++){
//            int j=random.nextInt(75)+48;
//            if(j>=58&&j<=64||j>=91&&j<=96){continue;}
//            String rand=String.valueOf((char)j).toUpperCase();
//            sRand+=rand;
//            // 将认证码显示到图象中
//            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
//            g.drawString(rand,13*(sRand.length()-1)+6,16);
//        }
//        // 将认证码存入SESSION
//        request.getSession().setAttribute("randcode",sRand);
//
//        // 图象生效
//        g.dispose();
//        g.
//        try{
//            JPEGCodec.createJPEGEncoder(response.getOutputStream());
//        }catch (Exception e){
//            throw e;
//        }
        return "code";

    }

    Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }


//    @RequestMapping("uploadImg.json")
//    @ResponseBody
//    //public String uploadImg()throws Exception{
//    public String uploadImg(@RequestParam(value = "upload", required = false) MultipartFile file)throws Exception{
//        String name= UUID.randomUUID().toString()+".jpg";
//        this.filePath="D:\\upload\\images\\";
//        file.transferTo(new File(this.filePath+name));
//        AjaxImageURI data = new AjaxImageURI();
//        data.setUrl("images/"+name);
//        return data.getUrl();
//    }
    /**
     * 切换岗位
     * @return
     */
	@SuppressWarnings({ "unchecked", "null" })
	@RequestMapping("swithstation")
    @ResponseBody
    public String swithStation(int id,HttpServletRequest request){
    	List<UserStations> stations = (List<UserStations>) request.getSession().getAttribute(SessionKey.STATIONS);
    	if(stations ==null && stations.size()==0){
    		return "岗位为空";
    	}
    	int flag =0;
    	for(UserStations station:stations){
    		if(station.getStationid()==id){
    			request.getSession().setAttribute(SessionKey.CURR_STATION, station);
    			flag =1;
    			continue;
    		}
    	}
		return flag ==1?"1":"切换岗位失败，未找到对应岗位";
    	
    }
    
    @RequestMapping("custNum")
    public @ResponseBody Long getCustNum(HttpServletRequest request) throws Exception{
    	UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);
    	if(station != null){
    		return this.service.getCustNumByStationId(station.getStationid());
    	}
    	LOG.info("当前用户没有岗位，首页获取客户数失败!");
    	return 0l;
    }
    
    @RequestMapping("auditOrder")
    public @ResponseBody Long auditOrder(HttpServletRequest request) throws Exception{
    	UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);
    	if(station != null){
    		return this.service.getauditOrder(station.getStationid());
    	}
    	LOG.info("当前用户没有岗位，首页获取待审批订单失败!");
    	return 0l;
    }
    @RequestMapping("mytaskNum")
    public @ResponseBody Integer mytaskNum(HttpServletRequest request) throws Exception{
    	TEmployee user = (TEmployee) request.getSession().getAttribute(SessionKey.USER_INFO);
    	return this.service.mytaskNum(user);
    }
    
    @RequestMapping("userorg")
    public @ResponseBody String getuserOrg(HttpServletRequest request) throws Exception{
    	String str ="";
    	UserStations station = (UserStations) request.getSession().getAttribute(SessionKey.CURR_STATION);
    	if(station !=null){
    		 str = this.service.getUserOrgInfo(station.getStationorgid());
    	}
    	return str;
    }
}

package com.hhnz.listener;

import com.hhnz.crm.model.TDict;
import com.hhnz.crm.service.IDictService;
import com.hhnz.crm.util.SessionKey;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * Created by yang on 2016-6-27.
 */
public class DictListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //初始化加载监听器
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        Map<String,List<TDict>> map = new TreeMap<String,List<TDict>>();
        IDictService service = webApplicationContext.getBean(IDictService.class);
        try {
            List<TDict> list = service.findAll();
            for(TDict dict:list){
                if("APPLICATION".equals(dict.getColName())){
                    servletContextEvent.getServletContext().setAttribute(dict.getChooseVal(),dict.getShowText());
                    continue;
                }
                if(!map.containsKey(dict.getColName())){
                    map.put(dict.getColName(),new ArrayList<TDict>());
                }
                map.get(dict.getColName()).add(dict);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        servletContextEvent.getServletContext().setAttribute(SessionKey.DICT_NAME,map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

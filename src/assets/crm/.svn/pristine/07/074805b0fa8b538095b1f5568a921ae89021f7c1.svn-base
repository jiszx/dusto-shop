package com.hhnz.process.service;

import com.hhnz.util.AjaxDTO;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang on 2016-7-11.
 */
public interface IProcessService {
    AjaxDTO findMyProcess(Map<String, Object> stringObjectHashMap, AjaxDTO bean)throws Exception;


    AjaxDTO findMyTask(Map<String, Object> stringObjectHashMap, AjaxDTO bean)throws Exception;

    /**
     * 启动流程
     * @param params 流程相关参数
     * @param key 流程名称
     * @param startUser 启动流程用户
     * @return 流程编号，需要更新到记录表中关联
     * @throws Exception
     */
    public String startProcess(Map<String,Object> params,String key,String startUser)throws Exception;

    String findViewUrl(String urlKey, String processInstanceId)throws Exception;

    String findTaskBtnInfo(String s, String processDefinitionId)throws Exception;
}

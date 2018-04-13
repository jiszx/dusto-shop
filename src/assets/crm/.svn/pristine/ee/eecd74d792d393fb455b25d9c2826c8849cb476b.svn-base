package com.hhnz.jco;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hhnz.util.JsonUtil;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRuntimeException;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

/**
 * Created by yang on 2016-7-15.
 */
public abstract class RFCInvoker {
	protected static final Log LOG = LogFactory.getLog(RFCInvoker.class);
	
    public String invokeRFC(String funcName,String json) throws JCoException{
        JCoFunction function = null;
        //连接sap，其实就类似于连接数据库
        LOG.info("=====获取sap destination连接");
        JCoDestination destination = SAPConn.connect();
        try {
            //调用函数
            function = destination.getRepository().getFunction(funcName);
            LOG.info("=====获取sap function完成："+function);
            //输入参数赋值
            dealWithInput(function, json);
            //执行远程方法
            LOG.info("=====开始执行rfc远程方法");
            function.execute(destination);
            LOG.info("=====rfc远程方法执行完成，function："+function);
            return receiveReturn(function);
        }catch (JCoException e) {
        	LOG.error(function,e);
        	throw e;
        }
    }
    
    /**
     * <h1>输入参数赋值</h1>
     * <p>
     * 规则：遍历传入json字符串的所有key-value.</br>
     * 1.如果当前的输入参数值是一个对象，则序列化为structure传入;</br>
     * 2.如果当前的输入参数值是一个数组，则序列化为table传入;</br>
     * 3.否则按正常输入参数importParameter传入
     * </p>
     * <p>
     * 注意:此处默认为structure中均为字符及可转换为字符的基础属性，</br>
     * 如果structure或table参数中含有子集structure或子table，则考</br>
     * 虑该结构是否可以简化，当确实无法简化时时需另行添加条件判断</br>
     * 进行处理或重新实现一个递归调用的方法
     * </p>
     * @author: chaoyang.ren 
     * @date:2016年8月18日  下午5:10:58
     * @param function
     * @param json
     */
    private void dealWithInput(JCoFunction function, String json){
    	JsonObject jsonObject = JsonUtil.toJsonObject(json);
        Iterator<Entry<String, JsonElement>> it = jsonObject.entrySet().iterator();
        JCoParameterList inputParam = function.getImportParameterList();
        //遍历所有参数
        while (it.hasNext()){
            //将当前传入的值赋予各个参数
            Entry<String, JsonElement> entry = it.next();
            JsonElement je = entry.getValue();
            String key = entry.getKey();
            //如果当前的输入参数值是一个对象，则序列化为structure传入
            if(je.isJsonObject()){
            	JsonObject structureData = (JsonObject)je;
            	Iterator<Entry<String, JsonElement>> ins = structureData.entrySet().iterator();
            	JCoStructure structure = inputParam.getStructure(key);
            	while(ins.hasNext()){
            		Entry<String, JsonElement> inentry = ins.next();
            		structure.setValue(inentry.getKey(), inentry.getValue().getAsString());
            	}
            	LOG.info(structure);
            }
            //如果当前的输入参数值是一个数组，则序列化为table传入
            else if(je.isJsonArray()){
            	JsonArray ija = (JsonArray)je;
            	JCoTable table = null;
				try {
					//table默认从tableParameter中获取
					table = function.getTableParameterList().getTable(key);
				} catch (JCoRuntimeException e) {
					//如果tableParameter中不存在则从inputParameter中获取
					table = inputParam.getTable(key);
				}
            	for (int i=0; i<ija.size(); i++ ) {
            		table.appendRow();
            		JsonObject rowData = (JsonObject) ija.get(i);
            		Iterator<Entry<String, JsonElement>> columnsData = rowData.entrySet().iterator();
                	while(columnsData.hasNext()){
                		Entry<String, JsonElement> column = columnsData.next();
                		table.setValue(column.getKey(), column.getValue().getAsString());
                	}
				}
            	LOG.info(table);
            }
            //否则按正常输入参数传入
            else{
            	inputParam.setValue(key, je.getAsString());
            }
        }
        LOG.info(inputParam);
    }
    
    /**
     * 获取SAP返回值
     * @author: chaoyang.ren 
     * @date:2016年8月9日  下午1:04:15
     * @param function
     * @return json string
     */
    protected abstract String receiveReturn(JCoFunction function);
}

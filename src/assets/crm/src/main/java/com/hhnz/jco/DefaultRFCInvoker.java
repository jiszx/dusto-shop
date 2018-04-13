package com.hhnz.jco;

import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hhnz.util.exception.HHNZException;
import com.sap.conn.jco.ConversionException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterField;
import com.sap.conn.jco.JCoParameterFieldIterator;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

/**
 * 默认的RFC调用
 * @author: chaoyang.ren
 * @date:2016年8月9日
 * @time:上午11:30:12
 * @email:chaoyang.ren@foxmail.com
 */
public class DefaultRFCInvoker extends RFCInvoker{

	/**
	 * <h1>获取SAP返回值</h1>
	 * <h3>处理方式：</h3>
	 * <p>
	 * 1.获取{@code TableParameterList}遍历其中所有域作为JsonArray,即每个table转为一个JsonArray对象</br>
	 * 2.获取{@code ExportParameterList}遍历其中所有域作为JsonObject或property.</br>
	 * 3.将所有JsonArray,JsonObject,properties添加到最终结果JsonObject中.</br>
	 * 4.对于两层以上的structure或table嵌套的复杂类型此类未做处理.</br>
	 * 5.{@code TableParameterList}中会包含传入的table参数，此类中会除去参数名以{@code IN_}开头的table
	 * </p>
	 */
	@Override
	protected String receiveReturn(JCoFunction function) {
		//从table中取值
        JCoParameterList tableList = function.getTableParameterList();
        //从ExportParameter中取值
        JCoParameterList outParamList = function.getExportParameterList();
        if(tableList == null && outParamList == null){
        	LOG.info("No parameterList returned from SAP!");
        	return null;
        }
        try {
        	JsonObject jObject = new JsonObject();
        	if(tableList != null){
        		//获取所有table值域
        		JCoParameterFieldIterator fieldIterator = tableList.getParameterFieldIterator();
        		//遍历table值域
        		while (fieldIterator.hasNextField()){
        			JCoParameterField field = fieldIterator.nextParameterField();
        			//忽略table中的传入参数
        			if(field.getName().trim().startsWith(RFCConstants.IN_PREFIX)){
        				continue;
        			}
        			//默认值域为table
        			JCoTable jtable = field.getTable();
        			LOG.info(jtable);
        			if(jtable.isEmpty()){
        				continue;
        			}
        			JsonArray jarray = new JsonArray();
        			//遍历table的行
        			for (int i = 0; i < jtable.getNumRows(); i++) {
        				jtable.setRow(i);
        				//获取table的字段
        				Iterator<JCoField> fields = jtable.iterator();
        				JsonObject jo = new JsonObject();
        				while (fields.hasNext()){
        					JCoField jf = fields.next();
        					if(!jf.isInitialized()){
        						continue;
        					}
        					jo.addProperty(jf.getName(), jf.getValue().toString());
        				}
        				jarray.add(jo);
        			}
        			//json中每个table为一个key-value
        			jObject.add(field.getName(), jarray);
        		}
        	}
			//获取所有export值域
        	if(outParamList != null){
        		JCoParameterFieldIterator paramIterator = outParamList.getParameterFieldIterator();
        		//遍历export值域
        		while (paramIterator.hasNextField()){
        			JCoParameterField field = paramIterator.nextParameterField();
        			try {
        				JCoStructure jstructure = field.getStructure();
        				JsonObject inJsonObj = new JsonObject();
        				for (JCoField jf : jstructure) {
        					inJsonObj.addProperty(jf.getName(), jf.getString());
        				}
        				jObject.add(field.getName(), inJsonObj);
        			} catch (ConversionException e) {
        				jObject.addProperty(field.getName(), field.getString());
        			}
        		}
        	}
			LOG.info(jObject.toString());
			return jObject.toString();
		} catch (Exception e) {
			LOG.error("RFC接口"+function.getName()+"解析数据处理失败！", e);
			throw new HHNZException("RFC getting data error!");
		}
	}

}

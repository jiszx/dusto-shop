package com.hhnz.pub;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hhnz.pub.model.Change;
import com.hhnz.pub.model.ChangeVar;
import org.springframework.stereotype.Component;

@Component
public class ChangeUtil {
	private Set<Class> config=new HashSet<Class>();
	public ChangeVar addChangeModel(Change vo, Class T, String key, Object dest) throws Exception {
		String modelName = T.getSimpleName();
		//创建变更字段列表
		Field fields[] = dest.getClass().getDeclaredFields();
		List<Field> fieldList = new ArrayList<Field>();
		Field[] baseFields = dest.getClass().getDeclaredFields();
		for(int i=0;i<baseFields.length;i++){
			fieldList.add(baseFields[i]);
		}
		if(!dest.getClass().getSuperclass().equals(Object.class)){
			Field[] superFields = dest.getClass().getSuperclass().getDeclaredFields();
			for(int i=0;i<superFields.length;i++){
				fieldList.add(superFields[i]);
			}
		}
		if(config.size()<=0){
			//没有手动配置
			//加载自己配置
			initConfig();
		}
		ChangeVar var = new ChangeVar();
		var.setBranchId(vo.getBranchId());
		var.setColumnName("all");
		var.setObjectKey(key);
		var.setObjectName(modelName);
		var.setChangeId(vo.getId());
		var.setChangeOid(vo.getChangeOid());
		var.setChangeType("A");
		var.setChangeTs(new Date());
		var.setOldValue("");
		var.setStat("2");
		return var;
	}
	protected void initConfig() {
		config.add(Integer.class);
		config.add(Long.class);
		config.add(Double.class);
		config.add(String.class);
		config.add(Float.class);
		config.add(Character.class);
		config.add(Short.class);
		config.add(Integer.class);
		config.add(Date.class);
		config.add(Integer.class);
	}
	public void setConfig(Set<Class> config) {
		this.config = config;
	}
}

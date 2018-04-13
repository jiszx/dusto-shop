package com.hhnz.pub.sqlbuilder;

import com.hhnz.pub.model.Change;
import com.hhnz.util.SQLBuild;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 商户信息变更sql构建
 *
 */
public class ChangeBuilder {
	/**
	 *  商户信息变更, 更新商户变更信息
	 * @param vo  变更信息对象参数
	 * @return  sql字符串
	 */
	public String update(Change vo){
		StringBuffer sb= new StringBuffer();
		sb.append("UPDATE T_CHANGE SET");
		if(vo.getChangeMemo() !=null && !"".equals(vo.getChangeMemo()) ){
			sb.append(" CHANGE_MEMO=#{changeMemo},");
		}
		if(vo.getBranchId() !=null && !"".equals(vo.getBranchId())){
			sb.append(" BRANCH_ID=#{branchId},");
		}
		if(vo.getChangeTitle() !=null && !"".equals(vo.getChangeTitle())){
			sb.append(" CHANGE_TITLE=#{changeTitle},");
		}
		if(vo.getStat() !=null && !"".equals(vo.getStat())){
			sb.append(" STAT=#{stat} ,");
		}
		if(vo.getHasAttachment() !=null && !"".equals(vo.getHasAttachment())){
			sb.append(" HAS_ATTACHMENT=#{hasAttachment} ,");
		}
		if(StringUtils.isNotBlank(vo.getProcessId())){
			sb.append(" PROCESS_ID=#{processId} ,");
		}
		sb.append(" CHECK_TS = SYSDATE WHERE ID=#{id}");
		return sb.toString();
	}
	
	/**
	 * 按条件查询变更历史
	 * @param map 相关参数
	 * @return sql字符串
	 */
	public String findByCondition(Map<String,Object> map){
		Map<String,Object> param = (Map<String,Object>) map.get("param");
		int page = (Integer)map.get("page");
		int pageSize = (Integer)map.get("pageSize");
		String sql=null;
		sql =" select * from t_change where 1=1";
		if(param.containsKey("changeId") && param.get("changeId")!=null && !"".equals(param.get("changeId"))){
			sql += " and change_id='"+param.get("changeId")+"' ";
		}
		if(param.containsKey("stat") && param.get("stat")!=null && !"".equals(param.get("stat"))){
			sql += " and stat='"+param.get("stat")+"' ";
		}
		if(param.containsKey("objectKey") && param.get("objectKey")!=null && !"".equals(param.get("objectKey"))){
			sql += " and object_key='"+param.get("objectKey")+"' ";
		}
		if(param.containsKey("objectName") && param.get("objectName")!=null && !"".equals(param.get("objectName"))){
			sql += " and object_name='"+param.get("objectName")+"' ";
		}
		if(param.containsKey("changeType") && param.get("changeType")!=null && !"".equals(param.get("changeType"))){
			sql += " and change_type='"+param.get("changeType")+"' ";
		}
		sql += " order by id desc ";
		return SQLBuild.pager(sql, page, pageSize);
		
	}
	/**
	 * 按条件查询变更历史总数
	 * @param map 相关参数
	 * @return  sql字符串
	 */
	public String countByCondition(Map<String,Object> map){
		Map<String,Object> param = (Map<String,Object>) map.get("param");		
		String sql=null;
		sql =" select count(*) from t_change where 1=1";
		if(param.containsKey("changeId") && param.get("changeId")!=null && !"".equals(param.get("changeId"))){
			sql += " and change_id='"+param.get("changeId")+"' ";
		}
		if(param.containsKey("stat") && param.get("stat")!=null && !"".equals(param.get("stat"))){
			sql += " and stat='"+param.get("stat")+"' ";
		}
		if(param.containsKey("objectKey") && param.get("objectKey")!=null && !"".equals(param.get("objectKey"))){
			sql += " and object_key='"+param.get("objectKey")+"' ";
		}
		if(param.containsKey("objectName") && param.get("objectName")!=null && !"".equals(param.get("objectName"))){
			sql += " and object_name='"+param.get("objectName")+"' ";
		}
		if(param.containsKey("changeType") && param.get("changeType")!=null && !"".equals(param.get("changeType"))){
			sql += " and change_type='"+param.get("changeType")+"' ";
		}
		return sql;
		
	}
}

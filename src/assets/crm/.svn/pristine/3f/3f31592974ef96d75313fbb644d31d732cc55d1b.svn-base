package com.hhnz.pub.sqlbuilder;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.hhnz.pub.model.ChangeVar;

/**
 * @author: chaoyang.ren
 * @date:Jan 5, 2017
 * @time:4:27:15 PM
 * @email:chaoyang.ren@foxmail.com
 */
public class ChangeVarBuilder {
	
	public String insertAll(Map<String, List<ChangeVar>> map) {
		List<ChangeVar> list = map.get("list");
		StringBuilder sb = new StringBuilder(256);
		sb.append("insert into t_change_var (ID, branch_id, change_id, object_key, object_name, column_name, change_type, change_value, old_value, change_oid, change_ts, stat) select SEQ_CHANGE_VAR_ID.NEXTVAL, branch_id, change_id, object_key, object_name, column_name, change_type, change_value, old_value, change_oid, change_ts, stat FROM");
		sb.append("(");
		MessageFormat messageFormat = new MessageFormat("#'{'list[{0}].branchId,jdbcType=VARCHAR} as branch_id,#'{'list[{0}].changeId,jdbcType=BIGINT} as change_id,#'{'list[{0}].objectKey,jdbcType=VARCHAR} as object_key,#'{'list[{0}].objectName,jdbcType=VARCHAR} as object_name,#'{'list[{0}].columnName,jdbcType=VARCHAR} as column_name,#'{'list[{0}].changeType,jdbcType=VARCHAR} as change_type,#'{'list[{0}].changeValue,jdbcType=VARCHAR} as change_value,#'{'list[{0}].oldValue,jdbcType=VARCHAR} as old_value,#'{'list[{0}].changeOid,jdbcType=VARCHAR} as change_oid,#'{'list[{0}].changeTs,jdbcType=DATE} as change_ts,#'{'list[{0}].stat,jdbcType=VARCHAR} as stat");
		for(int i = 0; i < list.size(); i++) {  
            sb.append("SELECT ");  
            sb.append(messageFormat.format(new Object[]{i}));  
            sb.append(" FROM DUAL ");  
            if(i<list.size()-1) {  
                sb.append(" UNION ALL ");  
            }  
        }
		sb.append(")");
		return sb.toString();
	}

}

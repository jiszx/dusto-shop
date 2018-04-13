package com.hhnz.encrypt.mapper;

import com.hhnz.encrypt.model.TEncryptKey;
import com.hhnz.encrypt.model.TEncryptKeyExample.Criteria;
import com.hhnz.encrypt.model.TEncryptKeyExample.Criterion;
import com.hhnz.encrypt.model.TEncryptKeyExample;
import java.util.List;
import java.util.Map;

import com.hhnz.util.SQLBuild;
import org.apache.ibatis.jdbc.SQL;

public class TEncryptKeySqlProvider {

    public String countByExample(TEncryptKeyExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("T_ENCRYPT_KEY");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(TEncryptKeyExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("T_ENCRYPT_KEY");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(TEncryptKey record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("T_ENCRYPT_KEY");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=DECIMAL}");
        }
        
        if (record.getKeyName() != null) {
            sql.VALUES("KEY_NAME", "#{keyName,jdbcType=VARCHAR}");
        }
        
        if (record.getSystemName() != null) {
            sql.VALUES("SYSTEM_NAME", "#{systemName,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyDesc() != null) {
            sql.VALUES("KEY_DESC", "#{keyDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyVersion() != null) {
            sql.VALUES("KEY_VERSION", "#{keyVersion,jdbcType=DECIMAL}");
        }
        
        if (record.getKeyState() != null) {
            sql.VALUES("KEY_STATE", "#{keyState,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyUpdateTs() != null) {
            sql.VALUES("KEY_UPDATE_TS", "#{keyUpdateTs,jdbcType=TIMESTAMP}");
        }
        
        if (record.getKeySendtype() != null) {
            sql.VALUES("KEY_SENDTYPE", "#{keySendtype,jdbcType=VARCHAR}");
        }
        
        if (record.getKeySenduri() != null) {
            sql.VALUES("KEY_SENDURI", "#{keySenduri,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyType() != null) {
            sql.VALUES("KEY_TYPE", "#{keyType,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyContent() != null) {
            sql.VALUES("KEY_CONTENT", "#{keyContent,jdbcType=BLOB}");
        }
        
        return sql.toString();
    }

    public String selectByExampleWithBLOBs(TEncryptKeyExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("ID");
        } else {
            sql.SELECT("ID");
        }
        sql.SELECT("KEY_NAME");
        sql.SELECT("SYSTEM_NAME");
        sql.SELECT("KEY_DESC");
        sql.SELECT("KEY_VERSION");
        sql.SELECT("KEY_STATE");
        sql.SELECT("KEY_UPDATE_TS");
        sql.SELECT("KEY_SENDTYPE");
        sql.SELECT("KEY_SENDURI");
        sql.SELECT("KEY_TYPE");
        sql.SELECT("KEY_CONTENT");
        sql.FROM("T_ENCRYPT_KEY");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String selectByExample(TEncryptKeyExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("ID");
        } else {
            sql.SELECT("ID");
        }
        sql.SELECT("KEY_NAME");
        sql.SELECT("SYSTEM_NAME");
        sql.SELECT("KEY_DESC");
        sql.SELECT("KEY_VERSION");
        sql.SELECT("KEY_STATE");
        sql.SELECT("KEY_UPDATE_TS");
        sql.SELECT("KEY_SENDTYPE");
        sql.SELECT("KEY_SENDURI");
        sql.SELECT("KEY_TYPE");
        sql.FROM("T_ENCRYPT_KEY");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        TEncryptKey record = (TEncryptKey) parameter.get("record");
        TEncryptKeyExample example = (TEncryptKeyExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("T_ENCRYPT_KEY");
        
        if (record.getId() != null) {
            sql.SET("ID = #{record.id,jdbcType=DECIMAL}");
        }
        
        if (record.getKeyName() != null) {
            sql.SET("KEY_NAME = #{record.keyName,jdbcType=VARCHAR}");
        }
        
        if (record.getSystemName() != null) {
            sql.SET("SYSTEM_NAME = #{record.systemName,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyDesc() != null) {
            sql.SET("KEY_DESC = #{record.keyDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyVersion() != null) {
            sql.SET("KEY_VERSION = #{record.keyVersion,jdbcType=DECIMAL}");
        }
        
        if (record.getKeyState() != null) {
            sql.SET("KEY_STATE = #{record.keyState,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyUpdateTs() != null) {
            sql.SET("KEY_UPDATE_TS = #{record.keyUpdateTs,jdbcType=TIMESTAMP}");
        }
        
        if (record.getKeySendtype() != null) {
            sql.SET("KEY_SENDTYPE = #{record.keySendtype,jdbcType=VARCHAR}");
        }
        
        if (record.getKeySenduri() != null) {
            sql.SET("KEY_SENDURI = #{record.keySenduri,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyType() != null) {
            sql.SET("KEY_TYPE = #{record.keyType,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyContent() != null) {
            sql.SET("KEY_CONTENT = #{record.keyContent,jdbcType=BLOB}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("T_ENCRYPT_KEY");
        
        sql.SET("ID = #{record.id,jdbcType=DECIMAL}");
        sql.SET("KEY_NAME = #{record.keyName,jdbcType=VARCHAR}");
        sql.SET("SYSTEM_NAME = #{record.systemName,jdbcType=VARCHAR}");
        sql.SET("KEY_DESC = #{record.keyDesc,jdbcType=VARCHAR}");
        sql.SET("KEY_VERSION = #{record.keyVersion,jdbcType=DECIMAL}");
        sql.SET("KEY_STATE = #{record.keyState,jdbcType=VARCHAR}");
        sql.SET("KEY_UPDATE_TS = #{record.keyUpdateTs,jdbcType=TIMESTAMP}");
        sql.SET("KEY_SENDTYPE = #{record.keySendtype,jdbcType=VARCHAR}");
        sql.SET("KEY_SENDURI = #{record.keySenduri,jdbcType=VARCHAR}");
        sql.SET("KEY_TYPE = #{record.keyType,jdbcType=VARCHAR}");
        sql.SET("KEY_CONTENT = #{record.keyContent,jdbcType=BLOB}");
        
        TEncryptKeyExample example = (TEncryptKeyExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("T_ENCRYPT_KEY");
        
        sql.SET("ID = #{record.id,jdbcType=DECIMAL}");
        sql.SET("KEY_NAME = #{record.keyName,jdbcType=VARCHAR}");
        sql.SET("SYSTEM_NAME = #{record.systemName,jdbcType=VARCHAR}");
        sql.SET("KEY_DESC = #{record.keyDesc,jdbcType=VARCHAR}");
        sql.SET("KEY_VERSION = #{record.keyVersion,jdbcType=DECIMAL}");
        sql.SET("KEY_STATE = #{record.keyState,jdbcType=VARCHAR}");
        sql.SET("KEY_UPDATE_TS = #{record.keyUpdateTs,jdbcType=TIMESTAMP}");
        sql.SET("KEY_SENDTYPE = #{record.keySendtype,jdbcType=VARCHAR}");
        sql.SET("KEY_SENDURI = #{record.keySenduri,jdbcType=VARCHAR}");
        sql.SET("KEY_TYPE = #{record.keyType,jdbcType=VARCHAR}");
        
        TEncryptKeyExample example = (TEncryptKeyExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TEncryptKey record) {
        SQL sql = new SQL();
        sql.UPDATE("T_ENCRYPT_KEY");
        
        if (record.getKeyName() != null) {
            sql.SET("KEY_NAME = #{keyName,jdbcType=VARCHAR}");
        }
        
        if (record.getSystemName() != null) {
            sql.SET("SYSTEM_NAME = #{systemName,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyDesc() != null) {
            sql.SET("KEY_DESC = #{keyDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyVersion() != null) {
            sql.SET("KEY_VERSION = #{keyVersion,jdbcType=DECIMAL}");
        }
        
        if (record.getKeyState() != null) {
            sql.SET("KEY_STATE = #{keyState,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyUpdateTs() != null) {
            sql.SET("KEY_UPDATE_TS = #{keyUpdateTs,jdbcType=TIMESTAMP}");
        }
        
        if (record.getKeySendtype() != null) {
            sql.SET("KEY_SENDTYPE = #{keySendtype,jdbcType=VARCHAR}");
        }
        
        if (record.getKeySenduri() != null) {
            sql.SET("KEY_SENDURI = #{keySenduri,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyType() != null) {
            sql.SET("KEY_TYPE = #{keyType,jdbcType=VARCHAR}");
        }
        
        if (record.getKeyContent() != null) {
            sql.SET("KEY_CONTENT = #{keyContent,jdbcType=BLOB}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=DECIMAL}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, TEncryptKeyExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}
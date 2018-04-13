package com.hhnz.pub.mapper;

import java.util.List;

import com.hhnz.pub.model.ChangeVar;
import com.hhnz.pub.sqlbuilder.ChangeVarBuilder;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;


public interface ChangeVarMapper {
	/**
	 * 删除变更信息
	 * @param changeId  变更id
	 * @param key 
	 * @return 删除标志
	 */
	@Delete("DELETE FROM T_CHANGE_VAR WHERE CHANGE_ID=#{id} AND OBJECT_KEY=#{key}")
	int deleteByChangeId(@Param("id") Long changeId, @Param("key") String key);
	/**
	 * 保存变更对象
	 * @param var 变更信息对象
	 * @return 添加标志
	 */
	@SelectKey(before=true,keyProperty="id",resultType=Integer.class,statement="SELECT SEQ_CHANGE_VAR_ID.NEXTVAL FROM DUAL")
	@Insert("INSERT INTO T_CHANGE_VAR(ID,BRANCH_ID,CHANGE_ID,OBJECT_KEY,OBJECT_NAME, COLUMN_NAME,CHANGE_TYPE,CHANGE_VALUE,OLD_VALUE,CHANGE_OID,CHANGE_TS,STAT)VALUES(#{id},#{branchId},#{changeId},#{objectKey},#{objectName},#{columnName},#{changeType},#{changeValue},#{oldValue},#{changeOid},sysdate,#{stat})")
	int add(ChangeVar var);
	
	@InsertProvider(type=ChangeVarBuilder.class,method="insertAll")
	void batchInsert(List<ChangeVar> vars);
	
	/**
	 * 获取变更源信息
	 * @param id
	 * @param objName
	 * @return
	 */
	@Select("SELECT * FROM T_CHANGE_VAR WHERE CHANGE_ID=#{id} AND OBJECT_NAME=#{obj}")
	@Results(value={
			@Result(id=true,column="ID",property="id",jdbcType=JdbcType.DECIMAL),
			@Result(column="BRANCH_ID",property="branchId",jdbcType=JdbcType.VARCHAR),
			@Result(column="CHANGE_ID",property="changeId",jdbcType=JdbcType.DECIMAL),
			@Result(column="OBJECT_KEY",property="objectKey",jdbcType=JdbcType.VARCHAR),
			@Result(column="OBJECT_NAME",property="objectName",jdbcType=JdbcType.VARCHAR),
			@Result(column="COLUMN_NAME",property="columnName",jdbcType=JdbcType.VARCHAR),
			@Result(column="CHANGE_TYPE",property="changeType",jdbcType=JdbcType.VARCHAR),
			@Result(column="CHANGE_VALUE",property="changeValue",jdbcType=JdbcType.VARCHAR),
			@Result(column="OLD_VALUE",property="oldValue",jdbcType=JdbcType.VARCHAR),
			@Result(column="CHANGE_OID",property="changeOid",jdbcType=JdbcType.VARCHAR),
			@Result(column="CHANGE_TS",property="changeTs",jdbcType=JdbcType.DATE),
			@Result(column="STAT",property="stat",jdbcType=JdbcType.VARCHAR)
	})
	List<ChangeVar> findByChangeId(@Param("id") Long id, @Param("obj") String objName);

	@Select("SELECT * FROM T_CHANGE_VAR WHERE CHANGE_ID=#{id} AND OBJECT_KEY=#{key} AND OBJECT_NAME=#{name}")
	@Results(value={
			@Result(id=true,column="ID",property="id"),
			@Result(column="BRANCH_ID",property="branchId"),
			@Result(column="CHANGE_ID",property="changeId"),
			@Result(column="OBJECT_KEY",property="objectKey"),
			@Result(column="OBJECT_NAME",property="objectName"),
			@Result(column="COLUMN_NAME",property="columnName"),
			@Result(column="CHANGE_TYPE",property="changeType"),
			@Result(column="CHANGE_VALUE",property="changeValue"),
			@Result(column="OLD_VALUE",property="oldValue"),
			@Result(column="CHANGE_OID",property="changeOid"),
			@Result(column="CHANGE_TS",property="changeTs"),
			@Result(column="STAT",property="stat")
	})
	List<ChangeVar> findByName(@Param("id") String dto, @Param("name") String obj, @Param("key") String id);
	
	@Select("SELECT * FROM T_CHANGE_VAR WHERE CHANGE_ID=#{id} AND OBJECT_KEY=#{key} AND OBJECT_NAME=#{name} AND COLUMN_NAME=#{columnName}")
	@Results(value={
			@Result(id=true,column="ID",property="id"),
			@Result(column="BRANCH_ID",property="branchId"),
			@Result(column="CHANGE_ID",property="changeId"),
			@Result(column="OBJECT_KEY",property="objectKey"),
			@Result(column="OBJECT_NAME",property="objectName"),
			@Result(column="COLUMN_NAME",property="columnName"),
			@Result(column="CHANGE_TYPE",property="changeType"),
			@Result(column="CHANGE_VALUE",property="changeValue"),
			@Result(column="OLD_VALUE",property="oldValue"),
			@Result(column="CHANGE_OID",property="changeOid"),
			@Result(column="CHANGE_TS",property="changeTs"),
			@Result(column="STAT",property="stat")
	})
	List<ChangeVar> findByColumnName(@Param("id") String dto, @Param("name") String obj, @Param("key") String id, @Param("columnName") String columnName);
	
	@Update("UPDATE T_CHANGE_VAR SET STAT=#{stat}  WHERE CHANGE_ID=#{changeId}")
	int updateStat(@Param("stat") String string, @Param("changeId") String changeId);
	
	@Delete("delete from t_change_Var where change_id=#{changeId} and object_name='MerchCustTerm'")
	void deleteTermChange(@Param("changeId") String changeId);

}

package com.hhnz.pub.mapper;

import java.util.List;
import java.util.Map;

import com.hhnz.pub.model.Change;
import com.hhnz.pub.model.ChangeVar;
import com.hhnz.pub.sqlbuilder.ChangeBuilder;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

/**
 * 
 * 变更信息映射
 *
 */
public interface ChangeMapper {

	/**
	 * 根据变更ID查询变更信息
	 * @param id 主键
	 * @return 查询变更信息
	 */
	@Select("SELECT * FROM T_CHANGE WHERE ID=#{id}")
	@Results(value={
			@Result(id=true,column="ID",property="id"),
			@Result(column="BRANCH_ID",property="branchId",jdbcType=JdbcType.VARCHAR),
			@Result(column="CHANGE_TITLE",property="changeTitle",jdbcType=JdbcType.VARCHAR),
			@Result(column="CHANGE_TYPE",property="changeType",jdbcType=JdbcType.VARCHAR),
			@Result(column="CHANGE_MEMO",property="changeMemo",jdbcType=JdbcType.VARCHAR),
			@Result(column="HAS_ATTACHMENT",property="hasAttachment",jdbcType=JdbcType.VARCHAR),
			@Result(column="CHANGE_OID",property="changeOid",jdbcType=JdbcType.VARCHAR),
			@Result(column="CHANGE_TS",property="changeTs",jdbcType=JdbcType.DATE),
			@Result(column="CHECK_TS",property="checkTs",jdbcType=JdbcType.DATE),
			@Result(column="CHECK_OID",property="checkOid",jdbcType=JdbcType.VARCHAR),
			@Result(column="OBJECT_NAME",property="objectName",jdbcType=JdbcType.VARCHAR),
			@Result(column="OBJECT_KEY",property="objectKey",jdbcType=JdbcType.VARCHAR),
			@Result(column="STAT",property="stat",jdbcType=JdbcType.VARCHAR)
	})
	Change findById(Long id);

	@Insert("INSERT INTO T_CHANGE_VAR(ID,BRANCH_ID,CHANGE_ID,OBJECT_KEY,OBJECT_NAME, COLUMN_NAME,CHANGE_TYPE,CHANGE_VALUE,OLD_VALUE,CHANGE_OID,CHANGE_TS,STAT)VALUES(#{id},#{branchId},#{changeId},#{objectKey},#{objectName},#{columnName},#{changeType},#{changeValue},#{oldValue},#{changeOid},sysdate,#{stat})")
	@SelectKey(before=true,keyProperty="id",resultType=Integer.class,statement="SELECT SEQ_CHANGE_VAR_ID.NEXTVAL FROM DUAL")
	int addChangeVar(ChangeVar var);
	/**
	 * 提交注销商户审批
	 * @param string 状态
	 * @param changeId 变更id
	 * @return 标志
	 */
	@Update("UPDATE T_CHANGE SET STAT=#{st}  WHERE ID=#{id}")
	int updateStat(@Param("st") String string, @Param("id") String changeId);
	/**
	 * 获取商户变更编号
	 * @return 变更编号
	 */
	@Select("SELECT SEQ_CHANGE_ID.NEXTVAL FROM DUAL")
	int findSEQ();
	/**
	 * 商户信息变更, 添加商户变更信息
	 * @param pojo  变更信息对象参数
	 * @return  添加标志
	 */
	@Insert("INSERT INTO T_CHANGE(ID,BRANCH_ID,CHANGE_TITLE,CHANGE_TYPE,CHANGE_MEMO,HAS_ATTACHMENT,CHANGE_OID,CHANGE_TS,STAT,OBJECT_KEY,OBJECT_NAME,PROCESS_ID)VALUES(#{id},#{branchId,jdbcType=VARCHAR},#{changeTitle,jdbcType=VARCHAR},#{changeType,jdbcType=VARCHAR},#{changeMemo,jdbcType=VARCHAR},#{hasAttachment,jdbcType=VARCHAR},#{changeOid,jdbcType=VARCHAR},sysdate,#{stat,jdbcType=VARCHAR},#{objectKey,jdbcType=VARCHAR},#{objectName,jdbcType=VARCHAR},#{processId,jdbcType=VARCHAR})")
	int add(Change pojo);
	/**
	 * 商户信息变更, 删除商户变更信息
	 * @param id 主键
	 * @return  删除标志
	 */
	@Delete("DELETE FROM T_CHANGE_VAR WHERE ID=#{id}")
	Integer delete(String id);
	/**
	 *  商户信息变更, 更新商户变更信息
	 * @param vo  变更信息对象参数
	 * @return 变更标示
	 */
	@UpdateProvider(type=ChangeBuilder.class,method="update")
	Integer update(Change vo);
	
	/*
	 * 更新主键对应的状态
	 */
	@Update("UPDATE T_CHANGE SET STAT=#{stat}  WHERE ID=#{id}")
	int updateStatById(@Param("id") String id, @Param("stat") String stat);
	
	
	/**
	 * 按条件查询变更历史
	 * @param param 相关参数
	 * @param page  当前页
	 * @param pageSize  大小
	 * @return Change的list集合
	 */
	@SelectProvider(type=ChangeBuilder.class,method="findByCondition")
	@Results(value={
			@Result(id=true,column="ID",property="id"),
			@Result(column="BRANCH_ID",property="branchId"),
			@Result(column="CHANGE_TITLE",property="changeTitle"),
			@Result(column="CHANGE_TYPE",property="changeType"),
			@Result(column="CHANGE_MEMO",property="changeMemo"),
			@Result(column="HAS_ATTACHMENT",property="hasAttachment"),
			@Result(column="CHANGE_OID",property="changeOid"),
			@Result(column="CHANGE_TS",property="changeTs"),
			@Result(column="CHECK_TS",property="checkTs"),
			@Result(column="CHECK_OID",property="checkOid"),
			@Result(column="OBJECT_NAME",property="objectName"),
			@Result(column="OBJECT_KEY",property="objectKey"),
			@Result(column="STAT",property="stat")
	})
	public List<Change> findByCondition(@Param("param") Map<String, Object> param, @Param("page") int page, @Param("pageSize") int pageSize);
	
	/**		
	 * 按条件查询变更历史总数
	 * @param param  相关参数
	 * @return  总数
	 */
	@SelectProvider(type=ChangeBuilder.class,method="countByCondition")
	public int countByCondition(@Param("param") Map<String, Object> param);

	@Select("SELECT * FROM T_CHANGE WHERE OBJECT_NAME='T_MERCH_CUST_BASE' AND OBJECT_KEY=#{id} AND CHANGE_TITLE=#{name} AND CHANGE_TYPE=#{type} AND STAT=#{stat}")
	@Results(value={
			@Result(id=true,column="ID",property="id"),
			@Result(column="BRANCH_ID",property="branchId"),
			@Result(column="CHANGE_TITLE",property="changeTitle"),
			@Result(column="CHANGE_TYPE",property="changeType"),
			@Result(column="CHANGE_MEMO",property="changeMemo"),
			@Result(column="HAS_ATTACHMENT",property="hasAttachment"),
			@Result(column="CHANGE_OID",property="changeOid"),
			@Result(column="CHANGE_TS",property="changeTs"),
			@Result(column="CHECK_TS",property="checkTs"),
			@Result(column="CHECK_OID",property="checkOid"),
			@Result(column="OBJECT_NAME",property="objectName"),
			@Result(column="OBJECT_KEY",property="objectKey"),
			@Result(column="STAT",property="stat")
	})
	List<Change> findAddTermPorcess(@Param("id") String id, @Param("type") String string, @Param("name") String string2, @Param("stat") String i);

	@Delete("delete from t_change_var where change_id = #{changeId}")
	Integer deleteChangeVar(@Param("changeId") String changeId);

	@Update("update t_merch_cust_term set stat = #{stat} where id = #{termId}")
	Integer updateTermStat(@Param("stat") String stat, @Param("termId") Integer termId);

	@Select("select count(*) from t_change where object_name = 'merchCustId' and object_key = #{merchCustId}")
	int isChangeInfoExist(@Param("merchCustId") String merchCustId);

	/**
	 * @author: chaoyang.ren 
	 * @date:Jan 5, 2017  9:53:31 AM
	 * @param simpleName
	 * @param id
	 */
	@Select("SELECT * FROM T_CHANGE C WHERE C.ID=(SELECT MAX(T.ID) FROM T_CHANGE T WHERE T.OBJECT_NAME = C.OBJECT_NAME AND T.OBJECT_KEY = C.OBJECT_KEY) AND C.OBJECT_NAME=#{0} AND C.OBJECT_KEY=#{1}")
	@Results(value={
			@Result(id=true,column="ID",property="id"),
			@Result(column="BRANCH_ID",property="branchId"),
			@Result(column="CHANGE_TITLE",property="changeTitle"),
			@Result(column="CHANGE_TYPE",property="changeType"),
			@Result(column="CHANGE_MEMO",property="changeMemo"),
			@Result(column="HAS_ATTACHMENT",property="hasAttachment"),
			@Result(column="CHANGE_OID",property="changeOid"),
			@Result(column="CHANGE_TS",property="changeTs"),
			@Result(column="CHECK_TS",property="checkTs"),
			@Result(column="CHECK_OID",property="checkOid"),
			@Result(column="OBJECT_NAME",property="objectName"),
			@Result(column="OBJECT_KEY",property="objectKey"),
			@Result(column="STAT",property="stat"),
			@Result(column="PROCESS_ID",property="processId")
	})
	Change findLatestChange(String simpleName, String id);

}

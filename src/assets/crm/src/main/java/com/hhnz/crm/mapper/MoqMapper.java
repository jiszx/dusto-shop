package com.hhnz.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.hhnz.crm.model.BusinessModelMoq;

/**
 * @author: chaoyang.ren
 * @date:Mar 16, 2017
 * @time:2:53:13 PM
 * @email:chaoyang.ren@foxmail.com
 * @version: 1.0
 */
public interface MoqMapper {

	/**
	 * 根据ID查询信息
	 * @param id 主键
	 * @return 查询信息
	 */
	@Select("SELECT * FROM T_BUSINESS_MODEL_MOQ WHERE ID=#{id}")
	@Results(value={
			@Result(id=true,column="ID",property="id"),
			@Result(column="MODEL_ID",property="modelId",jdbcType=JdbcType.BIGINT),
			@Result(column="MIN_ORDER_QUANTITY",property="minOrderQuantity",jdbcType=JdbcType.DECIMAL),
			@Result(column="RANGE",property="range",jdbcType=JdbcType.DECIMAL)
	})
	BusinessModelMoq findById(Long id);
	
	@Select("SELECT * FROM T_BUSINESS_MODEL_MOQ")
	@Results(value={
			@Result(id=true,column="ID",property="id"),
			@Result(column="MODEL_ID",property="modelId",jdbcType=JdbcType.BIGINT),
			@Result(column="MIN_ORDER_QUANTITY",property="minOrderQuantity",jdbcType=JdbcType.DECIMAL),
			@Result(column="RANGE",property="range",jdbcType=JdbcType.DECIMAL)
	})
	List<BusinessModelMoq> findAll();
	
    @Select("select * from T_BUSINESS_MODEL_MOQ where model_id=#{modelId}")
    @Results(value = {@Result(id = true, column = "ID", property = "id"),
        @Result(column = "MODEL_ID", property = "modelId", jdbcType = JdbcType.BIGINT),
        @Result(column = "MIN_ORDER_QUANTITY", property = "minOrderQuantity",
            jdbcType = JdbcType.DECIMAL),
        @Result(column = "RANGE", property = "range", jdbcType = JdbcType.DECIMAL)})
    BusinessModelMoq findByModelId(Long modelId);

	/**
	 * 根据id更新
	 * @author: chaoyang.ren 
	 * @date:Mar 17, 2017  10:58:25 AM
	 * @param moq
	 * @return
	 */
	@Update("UPDATE T_BUSINESS_MODEL_MOQ SET MIN_ORDER_QUANTITY=#{minOrderQuantity}, RANGE=#{range},MODEL_ID=#{modelId}  WHERE ID=#{id}")
	Integer update(BusinessModelMoq moq);
	
	/**
	 * 添加
	 * @author: chaoyang.ren 
	 * @date:Mar 17, 2017  11:01:48 AM
	 * @param moq
	 * @return
	 */
	@SelectKey(before=true,keyProperty="id",resultType=Long.class,statement="SELECT SEQ_BUSINESS_MODEL_MOQ.NEXTVAL FROM DUAL")
	@Insert("INSERT INTO T_BUSINESS_MODEL_MOQ(ID,MODEL_ID,MIN_ORDER_QUANTITY,RANGE)VALUES(#{id},#{modelId,jdbcType=BIGINT},#{minOrderQuantity,jdbcType=DECIMAL},#{range,jdbcType=DECIMAL})")
	Integer add(BusinessModelMoq moq);
}

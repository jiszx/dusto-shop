package com.hhnz.organization.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 杨 on 2016/12/28.
 */
public interface CompanyAreaMapper {

    @Select("select city_id from t_company_area where sap_code=#{id}")
    List<String> findCompanyAreaByiId(@Param("id") String id);

    @Delete("delete from t_company_area where sap_code = #{id}")
    int deleteByCid(@Param("id") String id)throws Exception;

    @Insert("insert into t_company_area(sap_code,city_id) values(#{id},#{aid})")
    int insert(@Param("id") String id, @Param("aid") String aid)throws Exception;
}

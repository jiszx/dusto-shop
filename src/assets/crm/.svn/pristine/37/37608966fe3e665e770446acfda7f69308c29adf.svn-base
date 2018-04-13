package com.hhnz.organization.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 杨 on 2016/12/27.
 */
public interface CompanyOrgMapper {
    @Select("select sale_org_id from t_company_sale_org where company_id=#{id}")
    List<String> selectByCompanyID(@Param("id") String id);

    @Insert("insert into t_company_sale_org(company_id,sale_org_id) values (#{id},#{aid})")
    int insert(@Param("id") String id, @Param("aid") String aid);

    @Delete("delete from t_company_sale_org where sale_org_id=#{id} ")
    int deleteByCid(@Param("id") String id);
    
    @Select("select company_id from t_company_sale_org where sale_org_id=#{id}")
    String selectBySalesOrg(@Param("id") String id);
}

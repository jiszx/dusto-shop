package com.hhnz.pub.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 杨 on 2016/12/16.
 */
public interface AreaRDCMapper {

    @Select("SELECT * FROM T_AREA_RDC where rdc_code=#{rdc}")
    public List<String> findRdcArea(@Param("rdc") String rdc);

    @Delete("DELETE FROM T_AREA_RDC where rdc_code=#{rdc}")
    int deleteByRdcCode(@Param("rdc")String id);

    @Insert("insert into T_AREA_RDC(rdc_code,city_id) values(#{id},#{aid})")
    int insert(@Param("id") String id,@Param("aid") String aid);
}

package com.hhnz.chart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.hhnz.chart.dto.ProvinceCount;
import com.hhnz.chart.dto.RDCArea;

/**
 * Created by 杨 on 2017/1/4.
 */
public interface ChartMapper {
    @Results(value={
            @Result(column="NAME",property="name"),
            @Result(column="CCM",property="count")
    })
    @Select("select aa.name as name,count(*) as ccm from c_merch_cust_base join t_area aa on aa.id = prov_id where prov_id is not null group by aa.name")
    List<ProvinceCount> findProvCount();

    @Results(value={
            @Result(column="tto",property="to"),
            @Result(column="fm",property="from"),
            @Result(column="ccount",property="count")
    })
    //@Select("SELECT ta.name as tto,td.show_text fm, count(cmcb.name) ccount FROM t_area_rdc tar, t_dict td, c_merch_cust_base cmcb, t_area ta where tar.rdc_code = td.choose_val and tar.city_id = cmcb.city_id and cmcb.prov_id = ta.id and cmcb.states = '3'  group by ta.name, td.show_text, tar.rdc_code, cmcb.prov_id")
    @Select("select prov.name tto,show_text fm,1 ccount from t_area_rdc tar,t_dict td,t_area city,t_area prov where tar.rdc_code = td.choose_val and city.id = tar.city_id and city.pid = prov.id group by show_text,prov.name")
    List<RDCArea> findRDCArea();

    @Select("select ta.name as name,sum(oha.order_amt) as amts from om_order_headers_all oha join c_merch_cust_base ccb on ccb.id = oha.merch_cust_id join t_area ta on ta.id = ccb.prov_id group by ta.name")
    @Results(value={
            @Result(column="NAME",property="name",id = true),
            @Result(column="amts",property="count")
    })
    List<ProvinceCount> countAmt();

    @Select("select aa.name fm,ab.name tto, 1 ccount from t_company_area ta join c_merch_cust_base cb on cb.sap_customer_id = ta.sap_code join t_area aa on aa.id = ta.city_id join t_area ab on ab.id = cb.city_id")
    @Results(value={
            @Result(column="tto",property="to"),
            @Result(column="fm",property="from"),
            @Result(column="ccount",property="count")
    })
    List<RDCArea> findCompany();


    @Results(value={
            @Result(column="rdcName",property="from"),
            @Result(column="cityName",property="to"),
            @Result(column="weigth",property="count")
    })
    @SelectProvider(type=ChartSqlProvider.class, method="selectOrderRdcArea")
    List<RDCArea> findRdcOrder();


}

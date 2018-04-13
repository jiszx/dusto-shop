package com.hhnz.chart.mapper;

/**
 * Created by 杨 on 2017/1/10.
 */
public class ChartSqlProvider {

    public String selectOrderRdcArea(){
       String sql="SELECT td.show_text rdcName," +
               "       tar.id cityId," +
               "       tar.name cityName," +
               "       sum(oodh.num*price) amt," +
               "       sum(case when tmb.unit ='TO' then" +
               "         to_number(oodh.num)" +
               "       else" +
               "         to_number(oodh.num)*to_number(nvl(tmb.specifications,1))/1000000" +
               "       end) weigth " +
               "  FROM (SELECT case" +
               "                 when ooha.order_type = '7' then" +
               "                  (SELECT cmcb.id" +
               "                     FROM c_merch_cust_base         cmcb," +
               "                          c_merch_cust_distribution cmcd" +
               "                    where cmcd.id = ooha.ship_id" +
               "                      and cmcd.merch_cust_id = cmcb.id)" +
               "                 else" +
               "                  ooha.ship_id" +
               "               end shipId," +
               "               ooha.id," +
               "               ooha.merch_cust_id," +
               "               ooha.sap_order_id" +
               "          FROM om_order_headers_all ooha" +
               //"         where ooha.order_type not in ('4', '5')" +
               " ) rec," +
               "       c_merch_cust_base cmcb," +
               "       t_area_rdc tar," +
               "       t_dict td," +
               "       om_order_delivered_history oodh," +
               "       om_order_spilts oos," +
               "       t_area tar," +
               "       t_material_base tmb" +
               " where rec.shipid= cmcb.id" +
               " and cmcb.city_id= tar.city_id" +
               " and tar.rdc_code= td.choose_val" +
               " and rec.sap_order_id= oodh.sap_order_id" +
               " and oos.header_id= rec.id" +
               " and oos.merch_cust_id= rec.merch_cust_id" +
               " and oos.orderitem_sap_no= oodh.orderitem_sap_no" +
               " and oos.material_id= tmb.sap_id" +
               " and cmcb.city_id= tar.id" +
               " and td.col_name = 'VIRTUAL_WAREHOUSE_CODE'" +
               " group by  td.show_text," +
               "       tar.id ," +
               "       tar.name";
       return sql;
    }
}

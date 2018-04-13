CREATE OR REPLACE VIEW C_MERCH_CUST_BASE_V AS
SELECT MCB.ID,
       MCB.NAME,
       MCB.SAP_CUSTOMER_ID,
       MCB.CUST_TYPE,
       MCB.CONTACT_NAME,
       MCB.CONTACT_TEL,
       D.SHOW_TEXT AS CUST_TYPE_NAME,
       SO1.ID AS SALES_ORG_ID,
       SO1.NAME AS SALES_ORG_NAME,
       SUBSTR(CRS.ORGANIZATION_ID,0,7) AS SALES_AREA_ID,
       (SELECT T.NAME FROM CRM_SALES_ORGANIZATION T WHERE T.ID=SUBSTR(CRS.ORGANIZATION_ID,0,7)) AS SALES_AREA_NAME,
       SUBSTR(CRS.ORGANIZATION_ID,0,9) AS SALES_PROV_ID,
       (SELECT T.NAME FROM CRM_SALES_ORGANIZATION T WHERE T.ID=SUBSTR(CRS.ORGANIZATION_ID,0,9)) AS SALES_PROV_NAME,
       CRS.ID AS POSITION_ID,
       CRS.NAME AS POSITION_NAME,
       MCB.CREATE_TS AS CREATE_TS,
       MCB.UPDATE_TS AS UPDATE_TS,
       E.NAME AS SALESMAN,
       MCB.STATES AS STATES,
       MCB.PID AS PID,
       MCB.PROCESS_ID AS PROCESS_ID,
       MCB.Business_License AS Business_License,
       DECODE(MCB.STATES,
              '1',
              '潜在客户',
              '2',
              '正式客户',
              '3',
              '休眠客户',
              '4',
              '失效客户',
              '') AS STATES_DESC,
       MCB.ORGANIZATION_ID
  FROM C_MERCH_CUST_BASE      mcb,
       T_EMPLOYEE             E,
       CRM_SALES_ORGANIZATION SO1,
       crm_station            crs,
       c_merch_cust_station   cmcs,
      (SELECT * FROM  t_dict t where
      t.COL_NAME='CUST_MERCH_TYPE') d
 where 1=1
  and mcb.organization_id= SO1.id
  and mcb.id= cmcs.merch_cust_id(+)
  and cmcs.station_id= crs.id(+)
  and mcb.cust_type =d.choose_val(+)
  and crs.salesrep_id = e.id(+);
  commit;
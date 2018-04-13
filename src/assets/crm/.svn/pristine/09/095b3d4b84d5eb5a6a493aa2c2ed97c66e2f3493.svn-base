CREATE OR REPLACE VIEW CRM_STATION_V AS SELECT te.name as salename,
((select name from crm_sales_organization where id=substr(CS.ORGANIZATION_ID, 0, 7)) || '-' || (select name from crm_sales_organization where id=CS.ORGANIZATION_ID)) as description,
cs."ID",cs."NAME",cs."ORGANIZATION_ID",cs."SALESREP_ID",cs."STATES",cs."CREATE_TS",cs."CREATE_OID",cs."UPDATE_TS",cs."UPDATE_OID",cs."ORG_AREA_ID"
  FROM crm_station cs, t_employee te
 where cs.salesrep_id = te.id(+);
commit;
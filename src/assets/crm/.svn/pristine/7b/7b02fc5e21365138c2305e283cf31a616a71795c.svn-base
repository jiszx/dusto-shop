CREATE OR REPLACE VIEW CRM_PROMOTION_LOG_V AS
SELECT cso.name as orgname,
       cpm.name as materialname,
       cps.name as storesname,
       te1.name as creater,
       nvl(te2.name,'') as auditer,
       cpp.name as purname,
       cpl.id,
       cpl.organization_id,
       cpl.num,
       cpl.type,
       cpl.promotion_id,
       cpl.create_ts,
       cpl.stores_id,
       cpl.audit_ts,
       cpl.states,
       cpl.pur_id,
       cpm.price,
       to_number(cpl.num*cpm.price) as amt
  FROM crm_promotion_log      cpl,
       crm_sales_organization cso,
       crm_promotion_material cpm,
       crm_promotion_stores   cps,
       t_employee             te1,
       t_employee             te2,
       crm_promotion_pur      cpp
 where cpl.organization_id = cso.id
   and cpl.promotion_id = cpm.id
   and cpl.stores_id = cps.id
   and cpl.create_oid = te1.id(+)
   and cpl.audit_oid= te2.id(+)
   and cpl.pur_id= cpp.id;

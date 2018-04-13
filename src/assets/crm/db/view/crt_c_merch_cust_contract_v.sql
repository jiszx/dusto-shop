create or replace view c_merch_cust_contract_v as select cso.                   name orgname,
       cmb.                   name custname,
       cmc.*
       from c_merch_cust_contract  cmc,
       crm_sales_organization cso,
       c_merch_cust_base      cmb
 where cmc.organization_id = cso. id(+)
   and cmc.merch_cust_id = cmb. id(+);

/*
   create or replace view c_merch_cust_contract_v as select cso.                   name orgname,
       cmb.                   name custname,
       cmc.*
       from c_merch_cust_contract  cmc,
       crm_sales_organization cso,
       c_merch_cust_base      cmb
 where cmc.organization_id = cso. id(+)
   and cmc.merch_cust_id = cmb. id(+);*/
commit;
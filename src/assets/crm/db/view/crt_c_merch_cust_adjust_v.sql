create or replace view c_merch_cust_adjust_v as
SELECT cmca.id,
       cmcb.name            as custname,
       cmca.merch_cust_id,
       cmcb.sap_customer_id,
       cso.name             as orgname,
       cmca.organization_id,
       cmca.amt,
       cmca.type,
       cmca.account_type,
       cmca.create_ts,
       cmca.create_oid,
       te.name,
       cmca.sku,
       cmca.order_id,
       cmca.order_line_id,
       cmca.order_num,
       cmca.order_price,
       cmca.states,
       cmca.reason,
       cmca.bank_account,
       cmca.target_merch_cust_id,
       cmca.adjust_direction
  FROM c_merch_cust_adjust    cmca,
       c_merch_cust_base      cmcb,
       crm_sales_organization cso,
       t_employee             te
 where cmca.merch_cust_id = cmcb.id
   and cmca.organization_id = cso.id
   and cmca.create_oid = te.id;
CREATE OR REPLACE VIEW C_MERCH_BALANCES_V AS SELECT cmc.name as custname,
       cso.name as orgname,
       cmc.sap_customer_id,
       cmcb."ID",cmcb."ORGANIZATION_ID",cmcb."MERCH_CUST_ID",cmcb."ACCOUNT_TYPE",cmcb."YTD",cmcb."D_AMT",cmcb."C_AMT",cmcb."PTD",cmcb."PERIOD"
  FROM c_merch_cust_base      cmc,
       c_merch_cust_balances  cmcb,
       crm_sales_organization cso
 where cmcb.merch_cust_id = cmc.id
   and cmcb.organization_id = cso.id;

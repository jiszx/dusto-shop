CREATE OR REPLACE VIEW C_MERCH_CUST_PRODUCT_V AS
SELECT CSO. NAME orgname,
       CMB. NAME custname,
       tmb.sku,
       TMB. CATEGORY,
       TMB.BRAND,
       TMB.SERIES,
       tmb.I_PACKAGE,
       nvl((SELECT tmp.price
          FROM T_MATERIAL_PRICE tmp
         where (tmp.edate + 0.99999) > SYSDATE
           and (tmp.BDATE + 0.00001) < SYSDATE
           and tmp.material_id = cmp.material_id
           and tmp.organization_id = cso.sap_id),0) base_price,
       ccc.states contractstates,
       CMP."ID",
       CMP."MERCH_CUST_ID",
       CMP."MATERIAL_ID",
       CMP."ORGANIZATION_ID",
       CMP."CONTRACT_ID",
       CMP."CONTRACT_LINEID",
       CMP."PRICE",
       CMP."CREATE_TS",
       CMP."CREATE_OID",
       CMP."UPDATE_TS",
       CMP."UPDATE_OID",
       CMP."H_PRICE",
       CMP."STATES"
  FROM C_MERCH_CUST_PRODUCT   cmp,
       C_MERCH_CUST_CONTRACT  ccc,
       CRM_SALES_ORGANIZATION cso,
       C_MERCH_CUST_BASE      cmb,
       T_MATERIAL_BASE        tmb
 WHERE CMP.MATERIAL_ID = TMB.SAP_ID
   AND CMP.ORGANIZATION_ID = CSO. ID(+)
   AND CMP.MERCH_CUST_ID = CMB. ID
   AND CMP.CONTRACT_ID = CCC. ID(+)
   AND CCC.States = '4';
 commit;

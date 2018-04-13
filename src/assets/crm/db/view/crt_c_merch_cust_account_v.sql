CREATE OR REPLACE VIEW C_MERCH_CUST_ACCOUNT_V AS SELECT
	cmb. NAME custname,
	cso. NAME orgname,
  cmb.sap_customer_id,
	NVL (CMA."CASH_AMT", 0) + NVL (CMA."CREDIT_AMT", 0) + NVL (CMA."SUBSIDY_AMT", 0) allamout,
	CMA.*
FROM
	C_MERCH_CUST_ACCOUNT cma,
	c_merch_cust_base cmb,
	crm_sales_organization cso
WHERE cma.ORGANIZATION_ID = cso. ID
and cma.organization_id= cmb.organization_id
AND cma.MERCH_CUST_ID = CMB. ID;
commit;
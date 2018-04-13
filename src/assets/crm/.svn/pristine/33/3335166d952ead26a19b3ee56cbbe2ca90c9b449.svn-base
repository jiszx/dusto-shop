--销售订单头表
-- Add/modify columns 
alter table OM_ORDER_HEADERS_ALL modify order_amt NUMBER(12,2);
alter table OM_ORDER_HEADERS_ALL modify discount_amt NUMBER(12,2);
alter table OM_ORDER_HEADERS_ALL modify amt NUMBER(12,2);
alter table OM_ORDER_HEADERS_ALL modify hb_amt NUMBER(12,2);
alter table OM_ORDER_HEADERS_ALL modify cash_amt NUMBER(12,2);
alter table OM_ORDER_HEADERS_ALL modify credit_amt NUMBER(12,2);
alter table OM_ORDER_HEADERS_ALL modify return_hb_amt NUMBER(12,2);
alter table OM_ORDER_HEADERS_ALL modify return_cash_amt NUMBER(12,2);
alter table OM_ORDER_HEADERS_ALL modify return_credit_amt NUMBER(12,2);

-- 订单行表
alter table OM_ORDER_LINES_ALL modify price NUMBER(12,2);
alter table OM_ORDER_LINES_ALL modify amt NUMBER(12,2);
alter table OM_ORDER_LINES_ALL modify discount_amt NUMBER(12,2);
alter table OM_ORDER_LINES_ALL modify order_amt NUMBER(12,2);
alter table OM_ORDER_LINES_ALL modify hb_amt NUMBER(12,2);
alter table OM_ORDER_LINES_ALL modify high_price NUMBER(12,2);
alter table OM_ORDER_LINES_ALL modify transport_price NUMBER(12,2);
alter table OM_ORDER_LINES_ALL modify order_price NUMBER(12,2);

--订单拆分表
alter table OM_ORDER_SPILTS modify amt NUMBER(12,2);
alter table OM_ORDER_SPILTS modify price NUMBER(12,2);

-- 客户余额表
alter table C_MERCH_CUST_ACCOUNT modify cash_amt NUMBER(14,2);
alter table C_MERCH_CUST_ACCOUNT modify credit_amt NUMBER(14,2);
alter table C_MERCH_CUST_ACCOUNT modify subsidy_amt NUMBER(14,2);
alter table C_MERCH_CUST_ACCOUNT modify bond_amt NUMBER(14,2);

-- 客户资金调整
alter table C_MERCH_CUST_ADJUST modify amt NUMBER(14,2);

-- 客户资金期间表
alter table C_MERCH_CUST_BALANCES modify ytd NUMBER(14,2);
alter table C_MERCH_CUST_BALANCES modify d_amt NUMBER(14,2);
alter table C_MERCH_CUST_BALANCES modify c_amt NUMBER(14,2);
alter table C_MERCH_CUST_BALANCES modify ptd NUMBER(14,2);

--合同表
alter table C_MERCH_CUST_CONTRACT modify credit_amt NUMBER(12,2);

-- 客户库存日志表
alter table C_MERCH_CUST_PRODUCT_LOG modify price NUMBER(14,2);
alter table C_MERCH_CUST_PRODUCT_LOG modify amt NUMBER(14,2);

-- 资金上账表
alter table C_MERCH_UPACCOUNT modify pay_amt NUMBER(14,2);
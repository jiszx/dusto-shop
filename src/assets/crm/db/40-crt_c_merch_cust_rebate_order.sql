-- Create table
drop table C_MERCH_CUST_REBATE_ORDER;
create table C_MERCH_CUST_REBATE_ORDER
(
  merch_cust_id   NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  order_header_id NUMBER(14) not null,
  rebate_id       NUMBER(10) not null,
  order_type      VARCHAR2(4) not null,
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  attribute5      VARCHAR2(300),
  order_line_id   NUMBER(10)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_REBATE_ORDER
  is '客户资金订单扣款明细表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_REBATE_ORDER.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_REBATE_ORDER.organization_id
  is '销售组织ID';
comment on column C_MERCH_CUST_REBATE_ORDER.order_header_id
  is '订单头ID';
comment on column C_MERCH_CUST_REBATE_ORDER.rebate_id
  is '返利表ID';
comment on column C_MERCH_CUST_REBATE_ORDER.order_type
  is '类型：1正向订单,2退货订单';
-- Create sequence 


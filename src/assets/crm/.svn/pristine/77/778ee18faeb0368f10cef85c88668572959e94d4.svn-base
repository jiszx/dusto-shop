-- Create table
create table C_MERCH_RECEIVE_VERIFIE
(
  id              NUMBER(10) not null,
  verified_id     NUMBER(10),
  invoice_no      VARCHAR2(30),
  verifie_amt     NUMBER(10),
  invoice_id      NUMBER(10),
  merch_cust_id   NUMBER(10),
  organization_id VARCHAR2(30),
  order_id        NUMBER(10),
  type            VARCHAR2(4),
  constraint C_MERCH_RECEIVE_VERIFIE primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_RECEIVE_VERIFIE
  is '收款核销';
-- Add comments to the columns 
comment on column C_MERCH_RECEIVE_VERIFIE.id
  is '主键ID';
comment on column C_MERCH_RECEIVE_VERIFIE.verified_id
  is '核销：资金上账ID/负数发票ID';
comment on column C_MERCH_RECEIVE_VERIFIE.invoice_no
  is '应收发票编号';
comment on column C_MERCH_RECEIVE_VERIFIE.verifie_amt
  is '核销金额';
comment on column C_MERCH_RECEIVE_VERIFIE.invoice_id
  is '应收发票主键ID';
comment on column C_MERCH_RECEIVE_VERIFIE.merch_cust_id
  is '客户编号';
comment on column C_MERCH_RECEIVE_VERIFIE.organization_id
  is '销售组织';
comment on column C_MERCH_RECEIVE_VERIFIE.order_id
  is '订单ID';
comment on column C_MERCH_RECEIVE_VERIFIE.type
  is '类型：1收款核心，2负数红冲';

-- Create table
drop table C_MERCH_CUST_ACCOUNT_LOG;
create table C_MERCH_CUST_ACCOUNT_LOG
(
    id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  type            VARCHAR2(4) not null,
  d_amt           NUMBER(12),
  c_amt           NUMBER(12),
  account_type    VARCHAR2(4) not null,
  order_id        NUMBER(14) not null,
  period          VARCHAR2(10) not null,
  create_ts       DATE not null,
  creater         VARCHAR2(30),
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  attribute5      VARCHAR2(300),
  sap_voucher_id  VARCHAR2(30),
  constraint C_MERCH_CUST_ACCOUNT_LOG primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_ACCOUNT_LOG
  is '客户资金流水表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_ACCOUNT_LOG.id
  is '主键ID';
comment on column C_MERCH_CUST_ACCOUNT_LOG.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_ACCOUNT_LOG.organization_id
  is '销售组织ID';
comment on column C_MERCH_CUST_ACCOUNT_LOG.type
  is '类型：1现金上账，2合同返利，3调整';
comment on column C_MERCH_CUST_ACCOUNT_LOG.d_amt
  is '增加金额';
comment on column C_MERCH_CUST_ACCOUNT_LOG.c_amt
  is '减少金额';
comment on column C_MERCH_CUST_ACCOUNT_LOG.account_type
  is '账户类型：现金，授信，货补，保证金';
comment on column C_MERCH_CUST_ACCOUNT_LOG.order_id
  is '对应ID(资金上账的id，或者调整表ID)';
comment on column C_MERCH_CUST_ACCOUNT_LOG.period
  is '所属期间';
comment on column C_MERCH_CUST_ACCOUNT_LOG.create_ts
  is '调整时间';
comment on column C_MERCH_CUST_ACCOUNT_LOG.sap_voucher_id
  is 'SAP凭证编号';
-- Add comments to the columns 
comment on column C_MERCH_CUST_ACCOUNT_LOG.attribute5
  is '备注';
-- Create sequence 

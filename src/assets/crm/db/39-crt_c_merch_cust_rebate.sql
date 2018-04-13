-- Create table 客户合同返利表
drop table C_MERCH_CUST_REBATE;
create table C_MERCH_CUST_REBATE
(
  id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10) not null,
  contract_lineid NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  create_ts       DATE not null,
  rebate_amt      NUMBER(12) not null,
  type            VARCHAR2(4) not null,
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  attribute5      VARCHAR2(300),
  purchase_amt    NUMBER(12) not null,
  states          VARCHAR2(4) not null,
  return_amt      NUMBER(12),
  constraint C_MERCH_CUST_REBATE primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_REBATE
  is '客户返利明细表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_REBATE.id
  is '主键ID';
comment on column C_MERCH_CUST_REBATE.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_REBATE.contract_lineid
  is '合同行ID';
comment on column C_MERCH_CUST_REBATE.organization_id
  is '销售组织ID';
comment on column C_MERCH_CUST_REBATE.create_ts
  is '返利时间';
comment on column C_MERCH_CUST_REBATE.rebate_amt
  is '返利金额';
comment on column C_MERCH_CUST_REBATE.type
  is '类型：1季度返利，2年度返利';
comment on column C_MERCH_CUST_REBATE.purchase_amt
  is '进货金额';
comment on column C_MERCH_CUST_REBATE.states
  is '状态';
-- Create sequence 

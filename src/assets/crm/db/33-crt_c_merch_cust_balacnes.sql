-- Create table
create table C_MERCH_CUST_BALANCES
(
  id              NUMBER(20) not null,
  organization_id VARCHAR2(20) not null,
  merch_cust_id   NUMBER(10) not null,
  account_type    VARCHAR2(4) not null,
  ytd             NUMBER(12) not null,
  d_amt           NUMBER(12) not null,
  c_amt           NUMBER(12) not null,
  ptd             NUMBER(12) not null,
  period          VARCHAR2(10) not null,
   constraint C_MERCH_CUST_BALANCES primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_BALANCES
  is '客户期间余额表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_BALANCES.id
  is '主键ID';
comment on column C_MERCH_CUST_BALANCES.organization_id
  is '销售组织';
comment on column C_MERCH_CUST_BALANCES.merch_cust_id
  is '客户';
comment on column C_MERCH_CUST_BALANCES.account_type
  is '账户类型';
comment on column C_MERCH_CUST_BALANCES.ytd
  is '期初';
comment on column C_MERCH_CUST_BALANCES.d_amt
  is '本期增加';
comment on column C_MERCH_CUST_BALANCES.c_amt
  is '本期减少';
comment on column C_MERCH_CUST_BALANCES.ptd
  is '期末';
comment on column C_MERCH_CUST_BALANCES.period
  is '期间';
-- Create sequence 

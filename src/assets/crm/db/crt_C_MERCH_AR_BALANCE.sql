-- Create table
drop table C_MERCH_AR_BALANCE;
create table C_MERCH_AR_BALANCE
(
  id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10),
  organization_id VARCHAR2(24),
  period          VARCHAR2(10),
  ytd             NUMBER(15),
  d_amt           NUMBER(15),
  c_amt           NUMBER(15),
  ptd             NUMBER(15),
  constraint C_MERCH_AR_BALANCE primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_AR_BALANCE
  is '应收账款余额表';
-- Add comments to the columns 
comment on column C_MERCH_AR_BALANCE.id
  is '主键ID';
comment on column C_MERCH_AR_BALANCE.merch_cust_id
  is '客户编号';
comment on column C_MERCH_AR_BALANCE.organization_id
  is '销售组织';
comment on column C_MERCH_AR_BALANCE.period
  is '期间';
comment on column C_MERCH_AR_BALANCE.ytd
  is '期初';
comment on column C_MERCH_AR_BALANCE.d_amt
  is '本期增加';
comment on column C_MERCH_AR_BALANCE.c_amt
  is '本期减少';
comment on column C_MERCH_AR_BALANCE.ptd
  is '期末';


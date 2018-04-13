-- Create table 客户资金余额表
drop table C_MERCH_CUST_ACCOUNT;
create table C_MERCH_CUST_ACCOUNT
(
 id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  cash_amt        NUMBER(12),
  credit_amt      NUMBER(12),
  subsidy_amt     NUMBER(12),
  bond_amt        NUMBER(12),
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  contract_id         NUMBER(10),
  contract_credit_amt NUMBER(12),
  constraint C_MERCH_CUST_ACCOUNT primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_ACCOUNT
  is '客户账户资金表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_ACCOUNT.id
  is '主键ID';
comment on column C_MERCH_CUST_ACCOUNT.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_ACCOUNT.organization_id
  is '销售组织ID';
comment on column C_MERCH_CUST_ACCOUNT.cash_amt
  is '金额';
comment on column C_MERCH_CUST_ACCOUNT.credit_amt
  is '授信额度';
comment on column C_MERCH_CUST_ACCOUNT.subsidy_amt
  is '货补金额';
comment on column C_MERCH_CUST_ACCOUNT.bond_amt
  is '保证金';

commit;
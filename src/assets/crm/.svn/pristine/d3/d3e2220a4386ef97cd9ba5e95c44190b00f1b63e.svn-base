-- Create table
drop  table C_MERCH_CUST_PRODUCT_BALANCES;
create table C_MERCH_CUST_PRODUCT_BALANCES
(
  id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10),
  period          VARCHAR2(10),
  ytd             NUMBER(13,3),
  d_num           NUMBER(13,3),
  c_num           NUMBER(13,3),
  ptd             NUMBER(13,3),
  organization_id VARCHAR2(24),
  states          VARCHAR2(4),
  contract_id     NUMBER(10),
  material_id     VARCHAR2(20),
  constraint C_MERCH_CUST_PRODUCT_BALANCES primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_PRODUCT_BALANCES
  is '客户产品期间表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_PRODUCT_BALANCES.id
  is '主键ID';
comment on column C_MERCH_CUST_PRODUCT_BALANCES.merch_cust_id
  is '客户编号';
comment on column C_MERCH_CUST_PRODUCT_BALANCES.period
  is '期间';
comment on column C_MERCH_CUST_PRODUCT_BALANCES.ytd
  is '期初';
comment on column C_MERCH_CUST_PRODUCT_BALANCES.d_num
  is '本期增加';
comment on column C_MERCH_CUST_PRODUCT_BALANCES.c_num
  is '本期减少';
comment on column C_MERCH_CUST_PRODUCT_BALANCES.ptd
  is '期末';
comment on column C_MERCH_CUST_PRODUCT_BALANCES.organization_id
  is '销售组织';
comment on column C_MERCH_CUST_PRODUCT_BALANCES.states
  is '状态';
comment on column C_MERCH_CUST_PRODUCT_BALANCES.contract_id
  is '合同ID';
comment on column C_MERCH_CUST_PRODUCT_BALANCES.material_id
  is '物料ID';


-- Create table客户代理产品信息
drop table C_MERCH_CUST_PRODUCT;
create table C_MERCH_CUST_PRODUCT
(
  id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10) not null,
  material_id     VARCHAR2(20) not null,
  organization_id VARCHAR2(20) not null,
  contract_id     NUMBER(10) not null,
  contract_lineid NUMBER(10) not null,
  price           NUMBER(10),
  create_ts       DATE not null,
  create_oid      NUMBER(10) not null,
  update_ts       DATE,
  update_oid      NUMBER(10),
  h_price         NUMBER(10),
  states          VARCHAR2(4),
  constraint PK_C_MERCH_CUST_PRODUCT primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_PRODUCT
  is '约束客户可购买的产品';
-- Add comments to the columns 
comment on column C_MERCH_CUST_PRODUCT.id
  is '主键ID';
comment on column C_MERCH_CUST_PRODUCT.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_PRODUCT.material_id
  is '物料ID';
comment on column C_MERCH_CUST_PRODUCT.organization_id
  is '销售组织';
comment on column C_MERCH_CUST_PRODUCT.contract_id
  is '合同ID';
comment on column C_MERCH_CUST_PRODUCT.contract_lineid
  is '合同行ID';
comment on column C_MERCH_CUST_PRODUCT.price
  is '物流价';
comment on column C_MERCH_CUST_PRODUCT.create_ts
  is '创建时间';
comment on column C_MERCH_CUST_PRODUCT.create_oid
  is '创建人';
comment on column C_MERCH_CUST_PRODUCT.update_ts
  is '更新时间';
comment on column C_MERCH_CUST_PRODUCT.update_oid
  is '更新人';
comment on column C_MERCH_CUST_PRODUCT.h_price
  is '高卖差价';
comment on column C_MERCH_CUST_PRODUCT.states
  is '状态';
-- Create sequence 
create sequence SEQ_MERCH_PRODUCT_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;

commit;

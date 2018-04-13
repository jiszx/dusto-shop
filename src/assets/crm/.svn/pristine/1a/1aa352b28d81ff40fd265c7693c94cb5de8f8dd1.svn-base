-- Create table客户库存信息表
drop table C_MERCH_CUST_INV;
create table C_MERCH_CUST_INV
(
  id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10) not null,
  organization_id VARCHAR2(30) not null,
  material_id     VARCHAR2(30),
  product_group   VARCHAR2(30),
  create_ts       DATE DEFAULT SYSDATE,
  inv_num         NUMBER(10) not null,
  unit            VARCHAR2(30),
  batch_num       VARCHAR2(30) not null,
  salesrep_id     NUMBER(10) not null,
  salesrep_name   VARCHAR2(30) not null,
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  attribute5      VARCHAR2(300),
  constraint C_MERCH_CUST_INV primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_INV
  is '客户库存信息表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_INV.id
  is '主键ID';
comment on column C_MERCH_CUST_INV.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_INV.organization_id
  is '销售组织ID';
comment on column C_MERCH_CUST_INV.material_id
  is '物料ID';
comment on column C_MERCH_CUST_INV.product_group
  is '产品组';
comment on column C_MERCH_CUST_INV.create_ts
  is '盘库时间';
comment on column C_MERCH_CUST_INV.inv_num
  is '库存数量';
comment on column C_MERCH_CUST_INV.unit
  is '单位';
comment on column C_MERCH_CUST_INV.batch_num
  is '批次号';
comment on column C_MERCH_CUST_INV.salesrep_id
  is '销售人员';
comment on column C_MERCH_CUST_INV.salesrep_name
  is '销售人员名称';
-- Create sequence 


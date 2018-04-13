-- Create table
drop table C_MERCH_CUST_PROUDCT_INV;
create table C_MERCH_CUST_PROUDCT_INV
(
  id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10),
  organization_id VARCHAR2(24),
  inv_num         NUMBER(13,3),
  frozen_num      NUMBER(13,3),
  create_ts       DATE,
  create_oid      NUMBER(10),
  contract_id     NUMBER(10),
  material_id     VARCHAR2(20),
  constraint C_MERCH_CUST_PROUDCT_INV primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_PROUDCT_INV
  is '客户产品库存表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_PROUDCT_INV.id
  is '主键ID';
comment on column C_MERCH_CUST_PROUDCT_INV.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_PROUDCT_INV.organization_id
  is '销售组织ID';
comment on column C_MERCH_CUST_PROUDCT_INV.inv_num
  is '库存数量';
comment on column C_MERCH_CUST_PROUDCT_INV.frozen_num
  is '冻结数量';
comment on column C_MERCH_CUST_PROUDCT_INV.create_ts
  is '创建时间';
comment on column C_MERCH_CUST_PROUDCT_INV.create_oid
  is '创建人';
comment on column C_MERCH_CUST_PROUDCT_INV.contract_id
  is '合同编号';
comment on column C_MERCH_CUST_PROUDCT_INV.material_id
  is '物料ID';


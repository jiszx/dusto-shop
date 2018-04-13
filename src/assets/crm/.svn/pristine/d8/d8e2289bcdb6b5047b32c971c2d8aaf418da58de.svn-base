-- Create table
drop table C_MERCH_CUST_PRODUCT_ADJUST;
create table C_MERCH_CUST_PRODUCT_ADJUST
(
  id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10),
  organization_id VARCHAR2(24),
  material_id     VARCHAR2(20),
  adjust_num      NUMBER(13,3),
  create_ts       DATE,
  type            VARCHAR2(4),
  create_oid      NUMBER(10),
  remark          VARCHAR2(300),
  reason          VARCHAR2(4),
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  attribute5      VARCHAR2(300),
  states          VARCHAR2(4),
  update_ts       DATE,
  update_oid      NUMBER(10),
  audit_ts        DATE,
  audit_oid       NUMBER(10),
  adjust_price    NUMBER(12),
  adjust_amt      NUMBER(12),
  constraint C_MERCH_CUST_PRODUCT_ADJUST primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_PRODUCT_ADJUST
  is '客户库存调整表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_PRODUCT_ADJUST.id
  is '主键ID';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.organization_id
  is '销售组织ID';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.material_id
  is '物料ID';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.adjust_num
  is '调整数量';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.create_ts
  is '时间';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.type
  is '类型：1调增，2调减';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.create_oid
  is '创建时间';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.remark
  is '备注';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.reason
  is '调整原因';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.states
  is '状态';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.update_ts
  is '更新时间';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.update_oid
  is '更新人';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.audit_ts
  is '审批时间';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.audit_oid
  is '审批人';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.adjust_price
  is '调整时物料单价';
comment on column C_MERCH_CUST_PRODUCT_ADJUST.adjust_amt
  is '调整金额';

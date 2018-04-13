-- Create table
drop table C_MERCH_CUST_PRODUCT_LOG;
create table C_MERCH_CUST_PRODUCT_LOG
(
  id            NUMBER(10) not null,
  material_id   VARCHAR2(20) not null,
  d_num         NUMBER(13,3),
  create_ts     DATE not null,
  create_oid    NUMBER(10),
  voucher_id    VARCHAR2(30) not null,
  merch_cust_id NUMBER(10) not null,
  period        VARCHAR2(10),
  attribute1    VARCHAR2(300),
  attribute2    VARCHAR2(300),
  attribute3    VARCHAR2(300),
  attribute4    VARCHAR2(300),
  remark        VARCHAR2(300),
  type          VARCHAR2(4),
  source        VARCHAR2(4),
  c_num         NUMBER(13,3),
  price         NUMBER(12),
  amt           NUMBER(12),
  constraint C_MERCH_CUST_PRODUCT_LOG primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_PRODUCT_LOG
  is '客户产品日志表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_PRODUCT_LOG.id
  is '主键ID';
comment on column C_MERCH_CUST_PRODUCT_LOG.material_id
  is '物理编码';
comment on column C_MERCH_CUST_PRODUCT_LOG.d_num
  is '增加数量';
comment on column C_MERCH_CUST_PRODUCT_LOG.create_ts
  is '操作时间';
comment on column C_MERCH_CUST_PRODUCT_LOG.create_oid
  is '操作人';
comment on column C_MERCH_CUST_PRODUCT_LOG.voucher_id
  is '凭证编号';
comment on column C_MERCH_CUST_PRODUCT_LOG.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_PRODUCT_LOG.period
  is '所属期间';
comment on column C_MERCH_CUST_PRODUCT_LOG.remark
  is '备注';
comment on column C_MERCH_CUST_PRODUCT_LOG.type
  is '1:出库，2入库';
comment on column C_MERCH_CUST_PRODUCT_LOG.source
  is '来源：1调拨单申请，2订单扣减，3订单退货，4手工调整';
comment on column C_MERCH_CUST_PRODUCT_LOG.c_num
  is '减少数量';
comment on column C_MERCH_CUST_PRODUCT_LOG.price
  is '单价(分)';
comment on column C_MERCH_CUST_PRODUCT_LOG.amt
  is '金额(分)';
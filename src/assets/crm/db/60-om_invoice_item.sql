--Drop table
drop table OM_INVOICE_ITEM;
-- Create table
create table OM_INVOICE_ITEM
(
  id            NUMBER(10) not null,
  order_id      NUMBER(10) not null,
  order_item_id NUMBER(10) not null,
  invoice_no    VARCHAR2(10) not null,
  material_id   VARCHAR2(10) not null,
  unit          VARCHAR2(3),
  num           NUMBER(13,3),
  price         NUMBER(11,2),
  amt           NUMBER(15,2),
  tax_rate      NUMBER(5,4),
  tax           NUMBER(13,2)
);
-- Add comments to the table 
comment on table OM_INVOICE_ITEM
  is '销售发票明细表';
-- Add comments to the columns 
comment on column OM_INVOICE_ITEM.order_id
  is '订单id';
comment on column OM_INVOICE_ITEM.order_item_id
  is '订单行项目ID';
comment on column OM_INVOICE_ITEM.invoice_no
  is '发票号';
comment on column OM_INVOICE_ITEM.material_id
  is '物料编码';
comment on column OM_INVOICE_ITEM.unit
  is '单位';
comment on column OM_INVOICE_ITEM.num
  is '数量';
comment on column OM_INVOICE_ITEM.price
  is '单价 单位：元';
comment on column OM_INVOICE_ITEM.amt
  is '金额 单位：元';
comment on column OM_INVOICE_ITEM.tax_rate
  is '税率';
comment on column OM_INVOICE_ITEM.tax
  is '税额 单位：元';
-- Create/Recreate primary, unique and foreign key constraints 
alter table OM_INVOICE_ITEM add constraint PK_OM_INVOICE_ITEM primary key (ID);
  
create sequence SEQ_INVOICE_ITEM
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;

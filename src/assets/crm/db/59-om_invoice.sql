--Drop table
drop table OM_INVOICE;
-- Create table
create table OM_INVOICE
(
  id          NUMBER(10) not null,
  customer_id NUMBER(10) not null,
  invoice_no  VARCHAR2(10) not null,
  total_price NUMBER(15,2) not null,
  total_tax   NUMBER(13,2) not null,
  period      VARCHAR2(6),
  draw_date   DATE,
  memo        VARCHAR2(300)
);
-- Add comments to the table 
comment on table OM_INVOICE
  is '销售发票表';
-- Add comments to the columns 
comment on column OM_INVOICE.customer_id
  is '客户ID';
comment on column OM_INVOICE.invoice_no
  is '发票号';
comment on column OM_INVOICE.total_price
  is '发票总额（不含税）单位：元';
comment on column OM_INVOICE.total_tax
  is '税额合计单位：元';
comment on column OM_INVOICE.period
  is '期间yyyyMM';
comment on column OM_INVOICE.draw_date
  is '开票日期';
comment on column OM_INVOICE.memo
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table OM_INVOICE
  add constraint PK_OM_INVOICE primary key (ID);
-- Add/modify columns 
alter table OM_INVOICE add writeoff_invoice VARCHAR2(10);
-- Add comments to the columns 
comment on column OM_INVOICE.writeoff_invoice
  is '冲销发票号，当此发票为冲销发票时，此字段为之前录入的发票号';
  
create sequence SEQ_INVOICE
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;

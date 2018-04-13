-- Create table
create table OM_INVOICES_CALLBACK
(
  id              NUMBER(10) not null,
  invoice_no      VARCHAR2(30),
  info_number     VARCHAR2(30),
  info_type_code  VARCHAR2(30),
  info_month      VARCHAR2(30),
  info_date       VARCHAR2(30),
  info_amount     NUMBER(12),
  info_tax_amount NUMBER(12),
  constraint OM_INVOICES_CALLBACK primary key (ID)
);
-- Add comments to the table 
comment on table OM_INVOICES_CALLBACK
  is '金穗发票回写信息';
-- Add comments to the columns 
comment on column OM_INVOICES_CALLBACK.invoice_no
  is '应收发票编号';
comment on column OM_INVOICES_CALLBACK.info_number
  is '金穗发票编号';
comment on column OM_INVOICES_CALLBACK.info_type_code
  is '发票十位代码';
comment on column OM_INVOICES_CALLBACK.info_month
  is '所属月份';
comment on column OM_INVOICES_CALLBACK.info_date
  is '开票时间';
comment on column OM_INVOICES_CALLBACK.info_amount
  is '含税金额';
comment on column OM_INVOICES_CALLBACK.info_tax_amount
  is '税额';
-- Create sequence 
create sequence SEQ_INVOICES_CALLBACK_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;
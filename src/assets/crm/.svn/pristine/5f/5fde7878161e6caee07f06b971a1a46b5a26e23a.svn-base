-- Create table
drop table CRM_TAX_INVOICE;
create table CRM_TAX_INVOICE
(
  id                 NUMBER(10) not null,
  sources_no         VARCHAR2(10),
  sources_id         NUMBER(10),
  create_ts          DATE,
  create_oid         NUMBER(10),
  msg                VARCHAR2(300),
  sources_type       VARCHAR2(4),
  invoices_type      VARCHAR2(4),
  info_number        VARCHAR2(30),
  info_type_code     VARCHAR2(30),
  info_month         VARCHAR2(10),
  info_date          VARCHAR2(30),
  info_amount        NUMBER(12,2),
  info_tax_amount    NUMBER(12,2),
  is_ismerge         VARCHAR2(4),
  type               VARCHAR2(4),
  extraction_code    VARCHAR2(50),
  xsf_bill_no        VARCHAR2(50),
  invoice_pdf_name   VARCHAR2(100),
  invoice_pdf_path   VARCHAR2(500),
  organization_id    VARCHAR2(24),
  merch_cust_id      NUMBER(10),
  update_ts          DATE,
  download_time      NUMBER(10),
  states             VARCHAR2(4),
  old_info_number    VARCHAR2(30),
  old_info_type_code VARCHAR2(30),
  bill_type          VARCHAR2(30),
  constraint PK_CRM_TAX_INVOICE primary key (ID)
);
-- Add comments to the table 
comment on table CRM_TAX_INVOICE
  is '金税发票表';
-- Add comments to the columns 
comment on column CRM_TAX_INVOICE.sources_no
  is '应收发票编号/销售订单编号';
comment on column CRM_TAX_INVOICE.sources_id
  is '应收发票表ID/销售订单ID';
comment on column CRM_TAX_INVOICE.create_ts
  is '开票时间';
comment on column CRM_TAX_INVOICE.create_oid
  is '操作人';
comment on column CRM_TAX_INVOICE.msg
  is '备注';
comment on column CRM_TAX_INVOICE.sources_type
  is '1:订单，2：应收发票';
comment on column CRM_TAX_INVOICE.invoices_type
  is '发票类型;2专票，3普通';
comment on column CRM_TAX_INVOICE.info_number
  is '金穗发票编号';
comment on column CRM_TAX_INVOICE.info_type_code
  is '发票10位代码';
comment on column CRM_TAX_INVOICE.info_month
  is '所属月份';
comment on column CRM_TAX_INVOICE.info_date
  is '开票时间';
comment on column CRM_TAX_INVOICE.info_amount
  is '开票金额';
comment on column CRM_TAX_INVOICE.info_tax_amount
  is '税额';
comment on column CRM_TAX_INVOICE.is_ismerge
  is '是否合并开票:2合并开票';
comment on column CRM_TAX_INVOICE.type
  is '类型：1纸质发票，2电子发票';
comment on column CRM_TAX_INVOICE.extraction_code
  is '电子发票提取码';
comment on column CRM_TAX_INVOICE.xsf_bill_no
  is '请求流水号';
comment on column CRM_TAX_INVOICE.invoice_pdf_name
  is '电子发票pdf名称';
comment on column CRM_TAX_INVOICE.invoice_pdf_path
  is '电子发票保持路径';
comment on column CRM_TAX_INVOICE.organization_id
  is '销售组织ID';
comment on column CRM_TAX_INVOICE.merch_cust_id
  is '客户ID';
comment on column CRM_TAX_INVOICE.update_ts
  is '更新时间';
comment on column CRM_TAX_INVOICE.download_time
  is '下载次数';
comment on column CRM_TAX_INVOICE.states
  is '状态：1有效';
comment on column CRM_TAX_INVOICE.bill_type
  is '开票类型：0蓝字发票，1红字发票';
-- Create sequence 
create sequence SEQ_TAX_INVOICE_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;

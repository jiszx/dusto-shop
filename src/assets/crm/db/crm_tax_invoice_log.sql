-- Create table
drop table CRM_TAX_INVOICE_LOG;
create table CRM_TAX_INVOICE_LOG
(
  id              NUMBER(10) not null,
  type            VARCHAR2(4),
  sources_type    VARCHAR2(4),
  create_ts       DATE,
  create_oid      NUMBER(10),
  sources_no      NUMBER(10),
  states          VARCHAR2(4),
  bill_type       VARCHAR2(30),
  extraction_code VARCHAR2(50),
  xsf_bill_no     VARCHAR2(50),
  info_number     VARCHAR2(30),
  info_type_code  VARCHAR2(30),
  response_code   VARCHAR2(10),
  constraint PK_CRM_TAX_INVOICE_LOG primary key (ID)
);
-- Add comments to the table 
comment on table CRM_TAX_INVOICE_LOG
  is '开票日志表';
-- Add comments to the columns 
comment on column CRM_TAX_INVOICE_LOG.id
  is '主键ID';
comment on column CRM_TAX_INVOICE_LOG.type
  is '类型：1纸质发票，2电子发票';
comment on column CRM_TAX_INVOICE_LOG.sources_type
  is '数据来源:1订单，2应收发票';
comment on column CRM_TAX_INVOICE_LOG.create_ts
  is '创建时间';
comment on column CRM_TAX_INVOICE_LOG.create_oid
  is '创建人';
comment on column CRM_TAX_INVOICE_LOG.sources_no
  is '数据来源ID，订单ID或者应收发票ID';
comment on column CRM_TAX_INVOICE_LOG.states
  is '状态：S成功，E失败，C开票中';
comment on column CRM_TAX_INVOICE_LOG.bill_type
  is '开票类型：0蓝字发票，1红字发票';
comment on column CRM_TAX_INVOICE_LOG.extraction_code
  is '发票提取码';
comment on column CRM_TAX_INVOICE_LOG.xsf_bill_no
  is '请求流水号';
comment on column CRM_TAX_INVOICE_LOG.info_number
  is '金穗发票编号';
comment on column CRM_TAX_INVOICE_LOG.info_type_code
  is '发票10位代码';
comment on column CRM_TAX_INVOICE_LOG.response_code
  is '返回结果';

-- Create sequence 
create sequence SEQ_TAX_INVOICE_LOG_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;

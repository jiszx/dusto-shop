-- Create table
create table CRM_ORGANIZATION_INVOICES
(
  organization_id VARCHAR2(24) not null,
  salebank        VARCHAR2(30),
  tax             VARCHAR2(4),
  saleaddr        VARCHAR2(1000),
  checker         VARCHAR2(20),
  salecompany     VARCHAR2(300),
  salebankno      VARCHAR2(50),
  saletel         VARCHAR2(20),
  saletaxno		  VARCHAR2(50),
  tax_item		  VARCHAR2(20),
  constraint CRM_ORGANIZATION_INVOICES primary key (ORGANIZATION_ID)
);
-- Add comments to the table 
comment on table CRM_ORGANIZATION_INVOICES
  is '开票信息';
-- Add comments to the columns 
comment on column CRM_ORGANIZATION_INVOICES.organization_id
  is '销售组织';
comment on column CRM_ORGANIZATION_INVOICES.salebank
  is '销方账户';
comment on column CRM_ORGANIZATION_INVOICES.tax
  is '税率';
comment on column CRM_ORGANIZATION_INVOICES.saleaddr
  is '销方地址';
comment on column CRM_ORGANIZATION_INVOICES.checker
  is '复核人';
comment on column CRM_ORGANIZATION_INVOICES.salecompany
  is '销售方名称';
comment on column CRM_ORGANIZATION_INVOICES.salebankno
  is '销售方账号';
comment on column CRM_ORGANIZATION_INVOICES.saletel
  is '销售方电话';
comment on column CRM_ORGANIZATION_INVOICES.saletaxno
  is '销售方税号';
comment on column CRM_ORGANIZATION_INVOICES.tax_item
  is '税目';
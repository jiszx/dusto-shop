-- Create table 组织结构表
drop table CRM_SALES_ORGANIZATION;
create table CRM_SALES_ORGANIZATION
(
   id           VARCHAR2(20) not null,
  pid          VARCHAR2(20) not null,
  levels       VARCHAR2(4) not null,
  type         VARCHAR2(4),
  name         VARCHAR2(100) not null,
  abbr_name    VARCHAR2(30),
  contact_name VARCHAR2(20),
  tel          VARCHAR2(20),
  states       VARCHAR2(4) not null,
  description  VARCHAR2(300),
  create_ts    DATE,
  update_ts    DATE,
  cancel_ts    DATE,
  sap_id       VARCHAR2(30),
  attribute1   VARCHAR2(300),
  attribute2   VARCHAR2(300),
  attribute3   VARCHAR2(300),
  attribute4   VARCHAR2(300),
  constraint CRM_SALES_ORGANIZATION primary key (ID)
);
-- Add comments to the table 
comment on table CRM_SALES_ORGANIZATION
  is '销售组织表';
-- Add comments to the columns 
comment on column CRM_SALES_ORGANIZATION.id
  is '主键ID';
comment on column CRM_SALES_ORGANIZATION.pid
  is '上级ID';
comment on column CRM_SALES_ORGANIZATION.levels
  is '等级';
comment on column CRM_SALES_ORGANIZATION.type
  is '类型';
comment on column CRM_SALES_ORGANIZATION.name
  is '销售组织名称';
comment on column CRM_SALES_ORGANIZATION.abbr_name
  is '销售组织简称';
comment on column CRM_SALES_ORGANIZATION.contact_name
  is '联系人姓名';
comment on column CRM_SALES_ORGANIZATION.tel
  is '联系座机';
comment on column CRM_SALES_ORGANIZATION.states
  is '销售组织状态';
comment on column CRM_SALES_ORGANIZATION.description
  is '描述';
comment on column CRM_SALES_ORGANIZATION.create_ts
  is '创建时间';
comment on column CRM_SALES_ORGANIZATION.update_ts
  is '更新时间';
comment on column CRM_SALES_ORGANIZATION.cancel_ts
  is '注销时间';
comment on column CRM_SALES_ORGANIZATION.sap_id
  is 'sapID';
comment on column CRM_SALES_ORGANIZATION.attribute1
  is '最大货补比例';
comment on column CRM_SALES_ORGANIZATION.attribute2
  is '最大计提比例';
comment on column CRM_SALES_ORGANIZATION.attribute3
  is '收款人';
comment on column CRM_SALES_ORGANIZATION.attribute4
  is '复核';
commit;
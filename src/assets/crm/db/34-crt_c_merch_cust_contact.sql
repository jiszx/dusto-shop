-- Create table 客户联系信息表
drop table C_MERCH_CUST_CONTACT;
create table C_MERCH_CUST_CONTACT
(
  id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  contact_name    VARCHAR2(50) not null,
  contact_tel     VARCHAR2(50),
  contact_mobile  VARCHAR2(50) not null,
  qq              VARCHAR2(50),
  email           VARCHAR2(50),
  weixin          VARCHAR2(50),
  demo            VARCHAR2(50),
  fax             VARCHAR2(50),
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  create_ts       DATE not null,
  create_oid      NUMBER(10) not null,
  update_ts       DATE,
  update_oid      NUMBER(10),
  attribute5      VARCHAR2(300),
  constraint C_MERCH_CUST_CONTACT primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_CONTACT
  is '客户联系信息';
-- Add comments to the columns 
comment on column C_MERCH_CUST_CONTACT.id
  is '主键ID';
comment on column C_MERCH_CUST_CONTACT.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_CONTACT.organization_id
  is '销售组织ID';
comment on column C_MERCH_CUST_CONTACT.contact_name
  is '联系人名称';
comment on column C_MERCH_CUST_CONTACT.contact_tel
  is '电话';
comment on column C_MERCH_CUST_CONTACT.contact_mobile
  is '手机';
comment on column C_MERCH_CUST_CONTACT.weixin
  is '微信';
comment on column C_MERCH_CUST_CONTACT.demo
  is '备注';
comment on column C_MERCH_CUST_CONTACT.fax
  is '传真';
comment on column C_MERCH_CUST_CONTACT.create_ts
  is '创建时间';
comment on column C_MERCH_CUST_CONTACT.create_oid
  is '创建人';
comment on column C_MERCH_CUST_CONTACT.update_ts
  is '更新时间';
comment on column C_MERCH_CUST_CONTACT.update_oid
  is '更新人';
-- Create sequence 

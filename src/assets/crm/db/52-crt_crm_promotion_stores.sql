-- Create table
-- 促销品仓库表
drop table CRM_PROMOTION_STORES;
create table CRM_PROMOTION_STORES
(
  id              NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  address         VARCHAR2(300) not null,
  contact         VARCHAR2(30) not null,
  contact_tel     VARCHAR2(20) not null,
  create_ts       DATE not null,
  create_oid      NUMBER(10) not null,
  update_ts       DATE,
  update_oid      NUMBER(10),
  name            VARCHAR2(300) not null,
  constraint CRM_PROMOTION_STORES primary key (ID)
);
-- Add comments to the table 
comment on table CRM_PROMOTION_STORES
  is '促销品仓库表';
-- Add comments to the columns 
comment on column CRM_PROMOTION_STORES.id
  is '主键ID';
comment on column CRM_PROMOTION_STORES.organization_id
  is '所属销售组织';
comment on column CRM_PROMOTION_STORES.address
  is '详细地址';
comment on column CRM_PROMOTION_STORES.contact
  is '联系人';
comment on column CRM_PROMOTION_STORES.contact_tel
  is '联系电话';
comment on column CRM_PROMOTION_STORES.create_ts
  is '创建时间';
comment on column CRM_PROMOTION_STORES.create_oid
  is '创建人';
comment on column CRM_PROMOTION_STORES.update_ts
  is '更新时间';
comment on column CRM_PROMOTION_STORES.update_oid
  is '更新人';
comment on column CRM_PROMOTION_STORES.name
  is '库房名称';
-- Create sequence 


commit;
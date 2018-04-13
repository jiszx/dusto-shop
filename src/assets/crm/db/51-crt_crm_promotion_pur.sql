--促销品采购信息表
-- Create table
create table CRM_PROMOTION_PUR
(
  id              NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  name            VARCHAR2(300) not null,
  contact         VARCHAR2(30) not null,
  contact_tel     VARCHAR2(20) not null,
  create_ts       DATE not null,
  create_oid      NUMBER(10) not null,
  update_ts       DATE,
  update_oid      NUMBER(10),
  constraint CRM_PROMOTION_PUR primary key (ID)
);
-- Add comments to the table 
comment on table CRM_PROMOTION_PUR
  is '促销品采购信息表';
-- Add comments to the columns 
comment on column CRM_PROMOTION_PUR.id
  is '主键ID';
comment on column CRM_PROMOTION_PUR.organization_id
  is '所属销售组织';
comment on column CRM_PROMOTION_PUR.name
  is '采购方名称';
comment on column CRM_PROMOTION_PUR.contact
  is '联系人';
comment on column CRM_PROMOTION_PUR.contact_tel
  is '联系电话';
comment on column CRM_PROMOTION_PUR.create_ts
  is '创建时间';
comment on column CRM_PROMOTION_PUR.create_oid
  is '创建人';
-- Create sequence 


commit;
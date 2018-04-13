--促销品物料信息表
-- Create table
create table CRM_PROMOTION_MATERIAL
(
  id              NUMBER(10) not null,
  name            VARCHAR2(300) not null,
  price           NUMBER(10) not null,
  unit            VARCHAR2(10) not null,
  description     VARCHAR2(300),
  create_ts       DATE not null,
  create_oid      NUMBER(10) not null,
  update_ts       DATE,
  update_oid      NUMBER(10),
  organization_id VARCHAR2(20) not null,
  constraint CRM_PROMOTION_MATERIAL primary key (ID)
);
-- Add comments to the table 
comment on table CRM_PROMOTION_MATERIAL
  is '促销品物料主表';
-- Add comments to the columns 
comment on column CRM_PROMOTION_MATERIAL.id
  is '编码';
comment on column CRM_PROMOTION_MATERIAL.name
  is '名称';
comment on column CRM_PROMOTION_MATERIAL.price
  is '单价';
comment on column CRM_PROMOTION_MATERIAL.unit
  is '规格';
comment on column CRM_PROMOTION_MATERIAL.description
  is '描述';
comment on column CRM_PROMOTION_MATERIAL.create_ts
  is '创建时间';
comment on column CRM_PROMOTION_MATERIAL.create_oid
  is '创建人';
comment on column CRM_PROMOTION_MATERIAL.update_ts
  is '更新时间';
comment on column CRM_PROMOTION_MATERIAL.update_oid
  is '更新人';
comment on column CRM_PROMOTION_MATERIAL.organization_id
  is '所属销售组织';
-- Create sequence 

commit;
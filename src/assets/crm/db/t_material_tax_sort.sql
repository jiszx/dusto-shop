-- Create table
create table T_MATERIAL_TAX_SORT
(
  id              NUMBER(10) not null,
  material_id     VARCHAR2(20) not null,
  sort_code       VARCHAR2(30) not null,
  tax_ratio       NUMBER(2) not null,
  organization_id VARCHAR2(10) not null,
  constraint PK_T_MATERIAL_TAX_SORT_ID primary key (ID)
);
-- Add comments to the table 
comment on table T_MATERIAL_TAX_SORT
  is '税收分类编码表';
-- Add comments to the columns 
comment on column T_MATERIAL_TAX_SORT.id
  is '主键ID';
comment on column T_MATERIAL_TAX_SORT.material_id
  is '物料编码';
comment on column T_MATERIAL_TAX_SORT.sort_code
  is '税收分类编码';
comment on column T_MATERIAL_TAX_SORT.tax_ratio
  is '税率';
comment on column T_MATERIAL_TAX_SORT.organization_id
  is '销售组织（SAPID）';
-- Create/Recreate indexes 
create index SORT_CODE_MATERIAL_ID on T_MATERIAL_TAX_SORT (MATERIAL_ID)
  tablespace TBS_CRMUSER
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table T_MATERIAL_TAX_SORT
  add constraint TAX_SORT_MATERIAL_ID unique (MATERIAL_ID)
  disable;

  -- Create sequence 
create sequence SEQ_TAX_SORT_ID
minvalue 1
maxvalue 999999999999
start with 861
increment by 1
cache 20;
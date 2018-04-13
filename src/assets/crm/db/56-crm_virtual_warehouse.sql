-- Create table
create table CRM_VIRTUAL_WAREHOUSE
(
  id          NUMBER(10) not null,
  factory_code VARCHAR2(20) not null,
  cust_type   VARCHAR2(4) not null,
  name        VARCHAR2(300),
  material_id VARCHAR2(20) not null,
  amt         NUMBER(13,3) default 0 not null,
  frozen_amt  NUMBER(13,3) default 0 not null,
  create_ts   DATE not null,
  create_oid  NUMBER(10) not null,
  update_ts   DATE,
  update_oid  NUMBER(10)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table CRM_VIRTUAL_WAREHOUSE
  is '虚拟仓库库存表';
-- Add comments to the columns 
comment on column CRM_VIRTUAL_WAREHOUSE.factory_code
  is '工厂编号';
comment on column CRM_VIRTUAL_WAREHOUSE.cust_type
  is '客户类型';
comment on column CRM_VIRTUAL_WAREHOUSE.name
  is '库房名称';
comment on column CRM_VIRTUAL_WAREHOUSE.material_id
  is '物料号';
comment on column CRM_VIRTUAL_WAREHOUSE.amt
  is '库存总量';
comment on column CRM_VIRTUAL_WAREHOUSE.frozen_amt
  is '冻结数量，预留';
comment on column CRM_VIRTUAL_WAREHOUSE.create_ts
  is '创建时间';
comment on column CRM_VIRTUAL_WAREHOUSE.create_oid
  is '创建人';
comment on column CRM_VIRTUAL_WAREHOUSE.update_ts
  is '更新时间';
comment on column CRM_VIRTUAL_WAREHOUSE.update_oid
  is '更新人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CRM_VIRTUAL_WAREHOUSE
  add constraint CRM_VIRTUAL_WAREHOUSE primary key (ID)
  using index 
  tablespace USERS
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

  
-- Create sequence 
create sequence SEQ_VIRTUAL_WAREHOUSE_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;
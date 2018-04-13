create table CRM_VIRTUAL_WAREHOUSE_ENTRY
(
  id          NUMBER(10) not null,
  factory_code VARCHAR2(20) not null,
  cust_type   VARCHAR2(4) not null,
  material_id VARCHAR2(20) not null,
  amt         NUMBER(12,2) default 0 not null,
  status      VARCHAR2(1) default '0' not null,
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
comment on table CRM_VIRTUAL_WAREHOUSE_ENTRY
  is '虚拟仓库入库表';
-- Add comments to the columns 
comment on column CRM_VIRTUAL_WAREHOUSE_ENTRY.factory_code
  is '工厂编号';
comment on column CRM_VIRTUAL_WAREHOUSE_ENTRY.cust_type
  is '客户类型';
comment on column CRM_VIRTUAL_WAREHOUSE_ENTRY.material_id
  is '物料号';
comment on column CRM_VIRTUAL_WAREHOUSE_ENTRY.amt
  is '入库数量';
comment on column CRM_VIRTUAL_WAREHOUSE_ENTRY.status
  is '状态';
comment on column CRM_VIRTUAL_WAREHOUSE_ENTRY.create_ts
  is '创建时间';
comment on column CRM_VIRTUAL_WAREHOUSE_ENTRY.create_oid
  is '创建人';
comment on column CRM_VIRTUAL_WAREHOUSE_ENTRY.update_ts
  is '更新时间';
comment on column CRM_VIRTUAL_WAREHOUSE_ENTRY.update_oid
  is '更新人';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CRM_VIRTUAL_WAREHOUSE_ENTRY
  add constraint CRM_VIRTUAL_WAREHOUSE_ENTRY primary key (ID)
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


create sequence SEQ_VIRTUAL_WAREHOUSE_ENTRY_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;
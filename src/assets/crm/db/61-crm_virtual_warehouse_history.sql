--Drop table
drop table CRM_VIRTUAL_WAREHOUSE_HISTORY;
-- Create table
create table CRM_VIRTUAL_WAREHOUSE_HISTORY
(
  id           NUMBER(10) not null,
  factory_code VARCHAR2(20) not null,
  cust_type    VARCHAR2(4) not null,
  material_id  VARCHAR2(20) not null,
  change_amt   NUMBER(13,3) default 0 not null,
  current_amt  NUMBER(13,3) default 0 not null,
  change_date  DATE not null
);
-- Add comments to the table 
comment on table CRM_VIRTUAL_WAREHOUSE_HISTORY
  is '虚拟仓库位调整记录';
-- Add comments to the columns 
comment on column CRM_VIRTUAL_WAREHOUSE_HISTORY.factory_code
  is '工厂编码';
comment on column CRM_VIRTUAL_WAREHOUSE_HISTORY.cust_type
  is '客户类型';
comment on column CRM_VIRTUAL_WAREHOUSE_HISTORY.material_id
  is '物料ID';
comment on column CRM_VIRTUAL_WAREHOUSE_HISTORY.change_amt
  is '变更数量';
comment on column CRM_VIRTUAL_WAREHOUSE_HISTORY.current_amt
  is '最新数量';
comment on column CRM_VIRTUAL_WAREHOUSE_HISTORY.change_date
  is '变更时间';

-- Create/Recreate primary, unique and foreign key constraints 
alter table CRM_VIRTUAL_WAREHOUSE_HISTORY add constraint PK_CRM_VW_HISTORY primary key (ID);
  
create sequence SEQ_VW_HISTORY
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;

-- Add/modify columns 
alter table CRM_VIRTUAL_WAREHOUSE_HISTORY add related_order_id number(14);
-- Add comments to the columns 
comment on column CRM_VIRTUAL_WAREHOUSE_HISTORY.related_order_id
  is '关联的订单号';
-- Add/modify columns 
alter table CRM_VIRTUAL_WAREHOUSE_HISTORY add change_no VARCHAR2(20);
-- Add comments to the columns 
comment on column CRM_VIRTUAL_WAREHOUSE_HISTORY.change_no
  is 'SAP库存变动的编号';
-- Add/modify columns 
alter table CRM_VIRTUAL_WAREHOUSE_HISTORY add CREATE_DATE DATE;
-- Add comments to the columns 
comment on column CRM_VIRTUAL_WAREHOUSE_HISTORY.CREATE_DATE
  is 'SAP中变更信息的创建时间';
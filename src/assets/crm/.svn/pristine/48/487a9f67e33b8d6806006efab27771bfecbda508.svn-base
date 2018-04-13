-- Create table
--促销品出入库
drop table CRM_PROMOTION_LOG;
create table CRM_PROMOTION_LOG
(
  id              NUMBER(10) not null,
  promotion_id    NUMBER(10) not null,
  num             NUMBER(10) not null,
  type            VARCHAR2(4) not null,
  organization_id VARCHAR2(20) not null,
  create_ts       DATE not null,
  create_oid      NUMBER(10) not null,
  stores_id       NUMBER(10) not null,
  audit_ts        DATE,
  audit_oid       NUMBER(10),
  states          VARCHAR2(4),
  pur_id          NUMBER(10),
  update_ts       DATE,
  update_oid      NUMBER(10),
  price           NUMBER(20),
  process_id      VARCHAR2(30),
   constraint CRM_PROMOTION_LOG primary key (ID)
);
-- Add comments to the table 
comment on table CRM_PROMOTION_LOG
  is '促销品出入库表';
-- Add comments to the columns 
comment on column CRM_PROMOTION_LOG.id
  is '主键ID';
comment on column CRM_PROMOTION_LOG.promotion_id
  is '促销品ID';
comment on column CRM_PROMOTION_LOG.num
  is '数量';
comment on column CRM_PROMOTION_LOG.type
  is '类型：1入库，2出库';
comment on column CRM_PROMOTION_LOG.organization_id
  is '所属销售组织';
comment on column CRM_PROMOTION_LOG.create_ts
  is '创建时间';
comment on column CRM_PROMOTION_LOG.create_oid
  is '创建人';
comment on column CRM_PROMOTION_LOG.stores_id
  is '库房ID';
comment on column CRM_PROMOTION_LOG.audit_ts
  is '审批时间';
comment on column CRM_PROMOTION_LOG.audit_oid
  is '审批人';
comment on column CRM_PROMOTION_LOG.states
  is '状态';
comment on column CRM_PROMOTION_LOG.pur_id
  is '采购方ID';
  comment on column CRM_PROMOTION_LOG.price
  is '单价';
comment on column CRM_PROMOTION_LOG.process_id
  is '流程ID';
-- Create sequence 


commit;
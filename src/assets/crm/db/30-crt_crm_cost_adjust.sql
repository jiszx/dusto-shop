drop table crm_cost_adjust;
-- create table
create table crm_cost_adjust
(
  id              NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  type            VARCHAR2(4) not null,
  cost_typeid     VARCHAR2(4) not null,
  regin_id        VARCHAR2(24) not null,
  cost_subjectid  NUMBER(10),
  adjust_amt      NUMBER(12) not null,
  reason          VARCHAR2(300),
  remark          VARCHAR2(300),
  order_id        NUMBER(10),
  create_ts       DATE,
  create_oid      NUMBER(17),
  audit_ts        DATE,
  audit_oid       NUMBER(10),
  period          VARCHAR2(10) not null,
  states          VARCHAR2(4) not null,
  process_id      VARCHAR2(32),
  prov_id         VARCHAR2(24),
  constraint CRM_COST_ADJUST primary key (ID)
);
-- add comments to the table
comment on table crm_cost_adjust
  is '费用调整表';
-- add comments to the columns
comment on column crm_cost_adjust.id
  is '主键id';
comment on column crm_cost_adjust.organization_id
  is '销售组织id';
comment on column crm_cost_adjust.create_ts
  is '创建时间';
comment on column crm_cost_adjust.create_oid
  is '创建人';
comment on column crm_cost_adjust.type
  is '调整类型：1手工调整，2计提，3销售政策';
comment on column crm_cost_adjust.cost_typeid
  is '费用类型';
comment on column crm_cost_adjust.cost_subjectid
  is '费用科目id';
comment on column crm_cost_adjust.adjust_amt
  is '调整金额';
comment on column crm_cost_adjust.reason
  is '调整原因';
comment on column crm_cost_adjust.remark
  is '备注';
comment on column crm_cost_adjust.regin_id
  is '大区';
comment on column crm_cost_adjust.order_id
  is 'crm销售订单号/销售政策号';
comment on column crm_cost_adjust.audit_ts
  is '最终审批通过时间';
comment on column crm_cost_adjust.audit_oid
  is '最终审批通过人';
comment on column crm_cost_adjust.period
  is '期间';
comment on column crm_cost_adjust.states
  is '状态';
comment on column crm_cost_adjust.process_id
  is '审批流程号';




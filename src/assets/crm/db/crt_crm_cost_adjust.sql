-- Create table
drop table CRM_COST_ADJUST;
create table CRM_COST_ADJUST
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
-- Add comments to the table 
comment on table CRM_COST_ADJUST
  is '费用调整表';
-- Add comments to the columns 
comment on column CRM_COST_ADJUST.id
  is '主键ID';
comment on column CRM_COST_ADJUST.organization_id
  is '销售组织ID';
comment on column CRM_COST_ADJUST.type
  is '调整类型：1手工调整，2计提，3销售政策';
comment on column CRM_COST_ADJUST.cost_typeid
  is '费用类型';
comment on column CRM_COST_ADJUST.regin_id
  is '大区';
comment on column CRM_COST_ADJUST.cost_subjectid
  is '费用科目ID';
comment on column CRM_COST_ADJUST.adjust_amt
  is '调整金额';
comment on column CRM_COST_ADJUST.reason
  is '调整原因';
comment on column CRM_COST_ADJUST.remark
  is '备注';
comment on column CRM_COST_ADJUST.order_id
  is 'CRM销售订单号/销售政策号';
comment on column CRM_COST_ADJUST.create_ts
  is '创建时间';
comment on column CRM_COST_ADJUST.create_oid
  is '创建人';
comment on column CRM_COST_ADJUST.audit_ts
  is '最终审批通过时间';
comment on column CRM_COST_ADJUST.audit_oid
  is '最终审批通过人';
comment on column CRM_COST_ADJUST.period
  is '期间';
comment on column CRM_COST_ADJUST.states
  is '状态';
comment on column CRM_COST_ADJUST.process_id
  is '审批流程主键';
comment on column CRM_COST_ADJUST.prov_id
  is '业务省';


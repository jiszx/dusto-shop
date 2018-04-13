-- Create table
drop table CRM_COST_LOG;
create table CRM_COST_LOG
(
  id              NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  type            VARCHAR2(4) not null,
  d_amt           NUMBER(30),
  c_amt           NUMBER(30),
  region_id       VARCHAR2(20) not null,
  cost_typeid     VARCHAR2(4) not null,
  period          VARCHAR2(10) not null,
  order_id        NUMBER(20) not null,
  cost_subjectid  NUMBER(10),
  reason          VARCHAR2(4),
  remark          VARCHAR2(300),
  constraint CRM_COST_LOG primary key (ID)
);
-- Add comments to the table 
comment on table CRM_COST_LOG
  is '费用流水表';
-- Add comments to the columns 
comment on column CRM_COST_LOG.id
  is '主键ID';
comment on column CRM_COST_LOG.organization_id
  is '销售组织';
comment on column CRM_COST_LOG.type
  is '类型：1费用调整，2政策申请，3订单计提';
comment on column CRM_COST_LOG.d_amt
  is '增加金额';
comment on column CRM_COST_LOG.c_amt
  is '减少金额';
comment on column CRM_COST_LOG.region_id
  is '大区';
comment on column CRM_COST_LOG.cost_typeid
  is '费用类型';
comment on column CRM_COST_LOG.period
  is '所属期间';
comment on column CRM_COST_LOG.order_id
  is '调整单号/政策ID/订单编号';
comment on column CRM_COST_LOG.cost_subjectid
  is '费用科目';
comment on column CRM_COST_LOG.reason
  is '调整原因';
comment on column CRM_COST_LOG.remark
  is '备注';


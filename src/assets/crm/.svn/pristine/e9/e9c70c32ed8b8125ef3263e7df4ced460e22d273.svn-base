-- Create table
drop table OM_POLICY_HEADERS;
create table OM_POLICY_HEADERS
(
  id              NUMBER(10) not null,
  dept            VARCHAR2(300),
  b_date          DATE,
  e_date          DATE,
  description     VARCHAR2(300),
  organization_id VARCHAR2(20),
  amt             NUMBER(12),
  cost_subjectid  VARCHAR2(20),
  states          VARCHAR2(4),
  balance_amt     NUMBER(12),
  create_ts       DATE,
  create_oid      NUMBER(10),
  audit_ts        DATE,
  audit_oid       NUMBER(10),
  cost_typeid     VARCHAR2(4),
  policy_type     NUMBER(10),
  process_id      VARCHAR2(30),
  attribute1      VARCHAR2(300),
  attrbute2       VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  attribute5      VARCHAR2(300),
  constraint OM_POLICY_HEADERS primary key (ID)
);
-- Add comments to the table 
comment on table OM_POLICY_HEADERS
  is '销售政策头表';
-- Add comments to the columns 
comment on column OM_POLICY_HEADERS.id
  is '主键ID';
comment on column OM_POLICY_HEADERS.dept
  is '申请部门';
comment on column OM_POLICY_HEADERS.b_date
  is '开始日期';
comment on column OM_POLICY_HEADERS.e_date
  is '结束日期';
comment on column OM_POLICY_HEADERS.description
  is '描述';
comment on column OM_POLICY_HEADERS.organization_id
  is '所属销售组织';
comment on column OM_POLICY_HEADERS.amt
  is '申请金额';
comment on column OM_POLICY_HEADERS.cost_subjectid
  is '成本中心ID';
comment on column OM_POLICY_HEADERS.states
  is '状态';
comment on column OM_POLICY_HEADERS.balance_amt
  is '可用余额';
comment on column OM_POLICY_HEADERS.create_ts
  is '创建时间';
comment on column OM_POLICY_HEADERS.create_oid
  is '创建人';
comment on column OM_POLICY_HEADERS.audit_ts
  is '审批通过时间';
comment on column OM_POLICY_HEADERS.audit_oid
  is '审批通过人';
comment on column OM_POLICY_HEADERS.cost_typeid
  is '费用类型ID';
comment on column OM_POLICY_HEADERS.policy_type
  is '政策类型';
comment on column OM_POLICY_HEADERS.process_id
  is '流程ID';
-- Create sequence 


commit;
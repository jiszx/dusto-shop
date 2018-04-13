-- Create table
create table OM_POLICY_ACCOUNT_LOG
(
  id              NUMBER(10) not null,
  policy_id       NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  order_header_id NUMBER(14) not null,
  order_line_id   NUMBER(10),
  order_spilt_id  NUMBER(10),
  policy_line_id  NUMBER(10) not null,
  create_ts       DATE not null,
  create_oid      NUMBER(10) not null,
  amt             NUMBER(12),
  remark          VARCHAR2(300),
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  attribute5      VARCHAR2(300),
  constraint OM_POLICY_ACCOUNT_LOG primary key (ID)
);
-- Add comments to the table 
comment on table OM_POLICY_ACCOUNT_LOG
  is '销售政策资金流水表';
-- Add comments to the columns 
comment on column OM_POLICY_ACCOUNT_LOG.id
  is '主键ID';
comment on column OM_POLICY_ACCOUNT_LOG.policy_id
  is '政策ID';
comment on column OM_POLICY_ACCOUNT_LOG.organization_id
  is '销售组织';
comment on column OM_POLICY_ACCOUNT_LOG.order_header_id
  is '订单头';
comment on column OM_POLICY_ACCOUNT_LOG.order_line_id
  is '订单行';
comment on column OM_POLICY_ACCOUNT_LOG.order_spilt_id
  is '拆分订单行';
comment on column OM_POLICY_ACCOUNT_LOG.policy_line_id
  is '政策行---对应sku';
comment on column OM_POLICY_ACCOUNT_LOG.create_ts
  is '创建时间';
comment on column OM_POLICY_ACCOUNT_LOG.create_oid
  is '创建人';
comment on column OM_POLICY_ACCOUNT_LOG.amt
  is '金额';
comment on column OM_POLICY_ACCOUNT_LOG.remark
  is '备注';
-- Create sequence 


commit;
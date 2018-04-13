-- Create table
drop table CRM_COST_BALANCES;
create table CRM_COST_BALANCES
(
  id              NUMBER(20) not null,
  period          VARCHAR2(10) not null,
  organization_id VARCHAR2(20) not null,
  ytd             NUMBER(12) not null,
  d_amt           NUMBER(12) not null,
  c_amt           NUMBER(12) not null,
  ptd             NUMBER(12) not null,
  cost_mainid     NUMBER(10) not null,
  constraint CRM_COST_BALANCES primary key (ID)
);
-- Add comments to the table 
comment on table CRM_COST_BALANCES
  is '费用科目余额表';
-- Add comments to the columns 
comment on column CRM_COST_BALANCES.id
  is '主键ID';
comment on column CRM_COST_BALANCES.period
  is '期间';
comment on column CRM_COST_BALANCES.organization_id
  is '销售组织';
comment on column CRM_COST_BALANCES.ytd
  is '期初';
comment on column CRM_COST_BALANCES.d_amt
  is '本期增加';
comment on column CRM_COST_BALANCES.c_amt
  is '本期减少';
comment on column CRM_COST_BALANCES.ptd
  is '期末';
comment on column CRM_COST_BALANCES.cost_mainid
  is '费用池主表ID';
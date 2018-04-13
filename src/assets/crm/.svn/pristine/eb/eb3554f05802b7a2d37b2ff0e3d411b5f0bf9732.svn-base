drop table CRM_BUDGET_MAIN;
create table CRM_BUDGET_MAIN
(
  id              NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  cost_type       VARCHAR2(4) not null,
  cost_subject    VARCHAR2(4) not null,
  merch_cust_id   NUMBER(10),
  org_id          VARCHAR2(20),
  region_id       VARCHAR2(20),
  province_id     VARCHAR2(20),
  amt_1           NUMBER(12),
  amt_2           NUMBER(12),
  amt_3           NUMBER(12),
  amt_4           NUMBER(12),
  amt_5           NUMBER(12),
  amt_6           NUMBER(12),
  amt_7           NUMBER(12),
  amt_8           NUMBER(12),
  amt_9           NUMBER(12),
  amt_10          NUMBER(12),
  amt_11          NUMBER(12),
  amt_12          NUMBER(12),
  amt             NUMBER(12) not null,
  create_ts       DATE not null,
  create_oid      NUMBER(12) not null,
  type            VARCHAR2(4) not null,
  period          VARCHAR2(20) not null,
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  attribute5      VARCHAR2(300),
  subject_name    VARCHAR2(50),
  states          VARCHAR2(4)
)
;
comment on table CRM_BUDGET_MAIN
  is '费用预算主表';
comment on column CRM_BUDGET_MAIN.id
  is '主键ID';
comment on column CRM_BUDGET_MAIN.organization_id
  is '销售组织ID';
comment on column CRM_BUDGET_MAIN.cost_type
  is '费用类型ID';
comment on column CRM_BUDGET_MAIN.cost_subject
  is '费用科目ID';
comment on column CRM_BUDGET_MAIN.merch_cust_id
  is '客户';
comment on column CRM_BUDGET_MAIN.org_id
  is '负担单位';
comment on column CRM_BUDGET_MAIN.region_id
  is '大区';
comment on column CRM_BUDGET_MAIN.province_id
  is '省区';
comment on column CRM_BUDGET_MAIN.amt_1
  is '1月预算';
comment on column CRM_BUDGET_MAIN.amt_2
  is '2月预算';
comment on column CRM_BUDGET_MAIN.amt_3
  is '3月预算';
comment on column CRM_BUDGET_MAIN.amt_4
  is '4月预算';
comment on column CRM_BUDGET_MAIN.amt_5
  is '5月预算';
comment on column CRM_BUDGET_MAIN.amt_6
  is '6月预算';
comment on column CRM_BUDGET_MAIN.amt_7
  is '7月预算';
comment on column CRM_BUDGET_MAIN.amt_8
  is '8月预算';
comment on column CRM_BUDGET_MAIN.amt_9
  is '9月预算';
comment on column CRM_BUDGET_MAIN.amt_10
  is '10月预算';
comment on column CRM_BUDGET_MAIN.amt_11
  is '11月预算';
comment on column CRM_BUDGET_MAIN.amt_12
  is '12月预算';
comment on column CRM_BUDGET_MAIN.amt
  is '年/季度 总金额';
comment on column CRM_BUDGET_MAIN.create_ts
  is '创建时间';
comment on column CRM_BUDGET_MAIN.create_oid
  is '创建人';
comment on column CRM_BUDGET_MAIN.type
  is '类型：1年度：2季度';
comment on column CRM_BUDGET_MAIN.period
  is '期间：年/季度 比如2016/2016-1';
comment on column CRM_BUDGET_MAIN.subject_name
  is '费用科目代码';
comment on column CRM_BUDGET_MAIN.states
  is '状态';

commit;
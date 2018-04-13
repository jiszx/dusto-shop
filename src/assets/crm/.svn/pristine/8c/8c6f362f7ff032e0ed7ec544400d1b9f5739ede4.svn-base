-- Create table
drop table OM_POLICY_TYPE;
create table OM_POLICY_TYPE
(
  id                NUMBER(10) not null,
  name              VARCHAR2(300) not null,
  type              VARCHAR2(4) not null,
  assessment        VARCHAR2(4),
  assessment_target VARCHAR2(30),
  benchmark         VARCHAR2(4),
  verification      VARCHAR2(4),
  description       VARCHAR2(1000),
  organization_id   VARCHAR2(20),
  create_ts         DATE,
  create_oid        NUMBER(10),
  update_ts         DATE,
  update_oid        NUMBER(10),
  audit_ts          DATE,
  audit_oid         NUMBER(10),
  states            VARCHAR2(4),
  constraint OM_POLICY_TYPE primary key (ID)
);
-- Add comments to the table 
comment on table OM_POLICY_TYPE
  is '销售政策类型表';
-- Add comments to the columns 
comment on column OM_POLICY_TYPE.name
  is '政策类型名称';
comment on column OM_POLICY_TYPE.type
  is '政策类型分类：1自动核销政策，2人工核销政策';
comment on column OM_POLICY_TYPE.assessment
  is '考核方式:1无，2销售l量，3销售额';
comment on column OM_POLICY_TYPE.assessment_target
  is '考核目标';
comment on column OM_POLICY_TYPE.benchmark
  is '基准：1活动，2订单';
comment on column OM_POLICY_TYPE.verification
  is '核销方式:1搭赠，2价格折扣，3返现金，4返货补，5促销品';
comment on column OM_POLICY_TYPE.description
  is '描述';
comment on column OM_POLICY_TYPE.organization_id
  is '销售组织ID';
 -- Create sequence 


commit;
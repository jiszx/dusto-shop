-- Create table 期间表
drop table GL_PERIODS;
create table GL_PERIODS
(
  id              NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  period          VARCHAR2(20) not null,
  b_date          VARCHAR2(20) not null,
  e_date          VARCHAR2(20) not null,
  states          VARCHAR2(4) not null,
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  constraint GL_PERIODS primary key (ID)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table GL_PERIODS
  is '期间表';
-- Add comments to the columns 
comment on column GL_PERIODS.id
  is '主键ID';
comment on column GL_PERIODS.organization_id
  is '销售组织ID';
comment on column GL_PERIODS.period
  is '期间';
comment on column GL_PERIODS.b_date
  is '期间开始日期';
comment on column GL_PERIODS.e_date
  is '期间结束日期';
comment on column GL_PERIODS.states
  is '状态：1打开，关闭';

-- Create sequence 

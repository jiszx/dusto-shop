-- Create table计划任务主表
drop table CRM_TASK_MAIN;
create table CRM_TASK_MAIN
(
  id               NUMBER(10) not null,
  task_type        VARCHAR2(4) not null,
  task_system      VARCHAR2(30) not null,
  task_description VARCHAR2(300) not null,
  schedule         VARCHAR2(100) not null,
  try_times        VARCHAR2(10) not null,
  states           VARCHAR2(4) not null,
  constraint CRM_TASK_MAIN primary key (ID)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table CRM_TASK_MAIN
  is '计划任务主表';
-- Add comments to the columns 
comment on column CRM_TASK_MAIN.id
  is '主键ID';
comment on column CRM_TASK_MAIN.task_type
  is '任务类型：';
comment on column CRM_TASK_MAIN.task_system
  is '任务系统名称';
comment on column CRM_TASK_MAIN.task_description
  is '任务说明';
comment on column CRM_TASK_MAIN.schedule
  is '执行时间schedule';
comment on column CRM_TASK_MAIN.try_times
  is '失败尝试次数';
comment on column CRM_TASK_MAIN.states
  is '状态';
-- Create sequence 


commit;
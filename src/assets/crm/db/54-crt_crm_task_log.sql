-- Create table计划任务执行表
drop table CRM_TASK_LOG;
create table CRM_TASK_LOG
(
  id           NUMBER(10) not null,
  task_main_id NUMBER(10) not null,
  task_name    VARCHAR2(300),
  bdate        DATE not null,
  edate        DATE not null,
  states       VARCHAR2(4) not null,
  remark       VARCHAR2(300),
  constraint CRM_TASK_LOG primary key (ID)
);
-- Add comments to the table 
comment on table CRM_TASK_LOG
  is '计划任务执行表';
-- Add comments to the columns 
comment on column CRM_TASK_LOG.id
  is '主键ID';
comment on column CRM_TASK_LOG.task_main_id
  is '任务ID';
comment on column CRM_TASK_LOG.task_name
  is '任务名称';
comment on column CRM_TASK_LOG.bdate
  is '开始时间';
comment on column CRM_TASK_LOG.edate
  is '结束时间';
comment on column CRM_TASK_LOG.states
  is '执行状态';
comment on column CRM_TASK_LOG.remark
  is '备注';
-- Create sequence 
commit;
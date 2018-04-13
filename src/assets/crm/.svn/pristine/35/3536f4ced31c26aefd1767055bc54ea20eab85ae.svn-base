DROP table T_RFC_EXECUTE_HISTORY;
-- Create table
create table T_RFC_EXECUTE_HISTORY
(
  serial_no     VARCHAR2(30) not null,
  execute_type  VARCHAR2(20),
  parameters    VARCHAR2(2000),
  status        VARCHAR2(10),
  failure_times NUMBER(5) default 0,
  RESULT VARCHAR2(2000),
  create_date   DATE
);
-- Add comments to the table 
comment on table T_RFC_EXECUTE_HISTORY
  is 'RFC任务队列执行历史表';
-- Add comments to the columns 
comment on column T_RFC_EXECUTE_HISTORY.serial_no
  is '任务序列号';
comment on column T_RFC_EXECUTE_HISTORY.execute_type
  is '任务类别';
comment on column T_RFC_EXECUTE_HISTORY.parameters
  is '参数';
comment on column T_RFC_EXECUTE_HISTORY.status
  is '状态';
comment on column T_RFC_EXECUTE_HISTORY.failure_times
  is '失败次数';
comment on column T_RFC_EXECUTE_HISTORY.RESULT
  is '执行结果';
comment on column T_RFC_EXECUTE_HISTORY.create_date
  is '创建时间';

-- Create/Recreate primary, unique and foreign key constraints 
alter table T_RFC_EXECUTE_HISTORY
  add constraint T_RFC_EXECUTE_HISTORY_PK primary key (SERIAL_NO);
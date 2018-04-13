DROP table T_RFC_EXECUTE;
-- Create table
create table T_RFC_EXECUTE
(
  serial_no     VARCHAR2(30) not null,
  execute_type  VARCHAR2(20),
  parameters    VARCHAR2(2000),
  status        VARCHAR2(10),
  failure_times NUMBER(5) default 0,
  RESULT VARCHAR2(2000),
  create_date   DATE,
  update_date   DATE
);
-- Add comments to the table 
comment on table T_RFC_EXECUTE
  is 'RFC任务队列执行表';
-- Add comments to the columns 
comment on column T_RFC_EXECUTE.serial_no
  is '任务序列号';
comment on column T_RFC_EXECUTE.execute_type
  is '任务类别';
comment on column T_RFC_EXECUTE.parameters
  is '参数';
comment on column T_RFC_EXECUTE.status
  is '状态';
comment on column T_RFC_EXECUTE.failure_times
  is '失败次数';
comment on column T_RFC_EXECUTE.RESULT
  is '执行结果';
comment on column T_RFC_EXECUTE.create_date
  is '创建时间';
comment on column T_RFC_EXECUTE.update_date
  is '修改时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_RFC_EXECUTE
  add constraint T_RFC_EXECUTE_PK primary key (SERIAL_NO);

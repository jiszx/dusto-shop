--日志表
drop table t_log;
create table T_LOG
(  
  id       Number(20) not null,
  oper_id      VARCHAR2(30),
  access_ip     VARCHAR2(40),
  class_name  VARCHAR2(60),
  method_name  VARCHAR2(60),
  op_params VARCHAR2(600),
  exception_info VARCHAR2(60),
  op_ts        DATE,
  CONSTRAINT "T_LOG_PK" primary key (ID)
);

create index T_LOG_I0 on T_LOG (oper_id);

comment on column t_log.id is '编号';
comment on column t_log.oper_id is '操作员号';
comment on column t_log.access_ip is '访问IP';
comment on column t_log.op_ts is '操作时间';
comment on column t_log.class_name is '类名';
comment on column t_log.exception_info IS '异常信息';
comment on column t_log.method_name is '方法名';
comment on column t_log.op_params is '操作说明';
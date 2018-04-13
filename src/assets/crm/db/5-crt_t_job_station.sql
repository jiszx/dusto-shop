drop table t_job_station;
drop table t_job_user;
create table t_job_station(
  id varchar2(50) primary key,
  name varchar2(100),
  org_id varchar2(20),
  job_type varchar2(10),
  data_view varchar2(20)
);
comment on column t_job_station.id is '主键';
comment on column t_job_station.name is '职位名称';
comment on column t_job_station.org_id is '销售组织ID';
comment on column t_job_station.job_type is '职位等级类型 1- 部门负责  2 普通职位';
comment on column t_job_station.data_view is '数据访问权限 0000000 值为0010100 表示对数据3有访问权限';
create table t_job_user(
  job_id varchar2(50),
  emp_id NUMBER(12),
  CONSTRAINT t_job_user_pk PRIMARY KEY (job_id,emp_id)
);

commit;
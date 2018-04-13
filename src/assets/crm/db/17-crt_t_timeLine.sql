-----时间线
drop table t_time_line;
create table t_time_line(
  id number(12) primary key,
  create_ts varchar2(10) not null,
  time_str varchar2(8) not null,
  title varchar2(100) not null,
  title_target varchar2(256),
  category varchar2(100),
  category_icon varchar2(50),
  content_desc varchar2(256),
  content_url varchar2(256),
  user_id varchar2(20),
  pub_state varchar2(1) DEFAULT '0',
  dept_id NUMBER(12),
  read_state varchar2(1) DEFAULT '0',
  close_state VARCHAR2(1) DEFAULT '0'
);

comment on column  t_time_line.id is '主键';
comment on column  t_time_line.create_ts is '创建时间';
comment on column  t_time_line.time_str is '时间点';
comment on column  t_time_line.title is '标题';
comment on column  t_time_line.title_target is '标题连接';
comment on column  t_time_line.category is '类别';
comment on column  t_time_line.category_icon is '类别ICON';
comment on column  t_time_line.content_desc is '内容描述';
comment on column  t_time_line.content_url is '内容连接';
comment on column  t_time_line.user_id is '用户编号';
comment on column  t_time_line.pub_state is '是否公用';
comment on column  t_time_line.dept_id is '部门ID';
comment on column  t_time_line.read_state is '阅读表示';
comment on column  t_time_line.close_state is '关闭标识';

commit;
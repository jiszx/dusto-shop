----用户角色表
drop table t_role_auth;
drop table t_role;
create table t_role(
  id          number(10) not null,
  role_name           varchar2(120) not null,
  role_desc           varchar2(500),
  create_ts date not null,
  update_ts date,
  role_type varchar2(10) default '1' not null,
  constraint t_role_pk primary key (id)
);
comment on column  t_role.id is '角色id';
comment on column  t_role.role_name is '角色名';
comment on column  t_role.role_desc is '角色描述';
comment on column  t_role.create_ts is '创建时间';
comment on column  t_role.update_ts is '修改时间';
commit;
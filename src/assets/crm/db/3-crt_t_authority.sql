---- 系统权限
drop table t_authority;
create table t_authority(
  res_id      number(11) not null primary key,
  res_name    varchar2(60) not null,
  res_type    number(1) not null,
  res_url     varchar2(255),
  other_res   varchar2(512),
  orders      number(5) default 0,
  pid         number(11),
  iconclass        varchar2(50),
  script_text varchar2(1000),
  update_ts   date,
  xtype       varchar2(10)
);
comment on column t_authority.res_id is '资源编号';
comment on column t_authority.res_name is '资源名称';
comment on column t_authority.res_type is '资源类型 1 功能 2菜单 3权限';
comment on column t_authority.res_url is '资源路径';
comment on column t_authority.other_res is '相关资源';
comment on column t_authority.orders is '顺序';
comment on column t_authority.pid is '资源上层id';
comment on column t_authority.iconclass is '资源图标';
comment on column t_authority.script_text is '资源脚本';
comment on column t_authority.update_ts is '资源版本';
comment on column t_authority.xtype is '是否功能资源';
---权限角色对应表
drop table t_role_auth;
create table t_role_auth(
  role_id number(11,0),
  auth_id number(11,0),
  constraint t_role_auth_pk primary key (role_id,auth_id),
  constraint role_resource_resource_fk foreign key (auth_id)
  references t_authority(res_id) on delete cascade enable,
  constraint role_resouce_role_fk foreign key (role_id)
  references t_role(id) on delete cascade enable
);
comment on column t_role_auth.role_id is '资源外键';
comment on column t_role_auth.auth_id is '角色外键';
commit;


drop table t_employee ;
create table t_employee(
  id              number(12) not null,
  name            varchar2(20) not null,
  levels          varchar2(4),
  pid             varchar2(20),
  is_salesman     varchar2(4) not null,
  card_no         varchar2(30),
  card_address    varchar2(300),
  contact_address varchar2(300),
  contact_tel     varchar2(20),
  create_ts       date not null,
  entry_ts        date,
  quit_ts         date,
  states          varchar2(4) not null,
  login_name      varchar2(20) unique ,
  passwd          varchar2(64) not null,
  exp_date        date,
  lastupdates     date,
  pwd_error_count varchar2(20),
  login_ts        date,
  lastloginid     varchar2(32),
  email           varchar2(32) not null,
  update_ts       date,
  role_id         number(11),
  organization_id varchar2(20),
  user_type            varchar2(2),
  merch_id        number(10),
  constraint pk_t_employee primary key (id)
);
-- add comments to the table
comment on table t_employee
is '员工表';
-- add comments to the columns
comment on column t_employee.is_salesman
is '是否为销售人员';
comment on column t_employee.card_no
is '身份证号';
comment on column t_employee.card_address
is '身份证地址';
comment on column t_employee.contact_address
is '联系地址';
comment on column t_employee.contact_tel
is '联系电话';
comment on column t_employee.entry_ts
is '入职时间';
comment on column t_employee.quit_ts
is '离职时间';
comment on column t_employee.states
is '状态 :0 正常，1未激活 3锁定 4 已删除';
comment on column t_employee.login_name
is '登陆名';
comment on column t_employee.passwd
is '密码';
comment on column t_employee.exp_date
is '有效日期';
comment on column t_employee.lastupdates
is '上次修改密码时间';
comment on column t_employee.pwd_error_count
is '密码错误次数';
comment on column t_employee.login_ts
is '登陆时间';
comment on column t_employee.lastloginid
is '上次登陆ip';
comment on column t_employee.email
is '邮箱';
comment on column t_employee.update_ts
is '更新时间';
comment on column t_employee.role_id
is '角色id';
comment on column t_employee.organization_id
is '所属销售组织';
comment on column t_employee.user_type
is '用户类型 1配送商';
comment on column t_employee.merch_id
is '用户对应的客户';
commit;

ALTER TABLE T_EMPLOYEE MODIFY (name varchar2(255));
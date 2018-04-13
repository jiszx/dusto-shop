----字典表
drop table t_dict;
create table  t_dict(
  id number(12) primary key,
  col_name varchar2(100) not null,
  col_description varchar2(200),
  choose_val varchar2(100) not null,
  show_text varchar2(100),
  orders number(6) default 0,
  can_choose char(1) default '0',
  display char(1) default '1',
  create_ts date default sysdate
);
comment on column t_dict.id is '字典编号';
comment on column t_dict.col_name is '字段名';
comment on column t_dict.col_description is '字段名说明';
comment on column t_dict.choose_val is '存储值';
comment on column t_dict.orders is '顺序';
comment on column t_dict.show_text is '显示值';
comment on column t_dict.can_choose is '是否可选';
comment on column t_dict.display is '是否显示';

commit;
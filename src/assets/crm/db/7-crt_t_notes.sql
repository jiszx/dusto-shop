drop table t_notes;
CREATE TABLE T_NOTES(
  id	number(10)	not null,
  title	VARCHAR2(100)	not null,
  desc_txt VARCHAR(200),
  content	clob,
  creator VARCHAR2(50),
  release_area varchar2(100),
  create_ts	date,
  checker	VARCHAR2(50),
  check_ts	date ,
  release_ts	VARCHAR2(10) ,
  top_flag	CHAR(1) not null ,
  stat	CHAR(1) not null,
  area varchar2(100),
  constraint T_NOTES_PK primary key (ID)
);
create index t_notes_i0 on t_notes ( release_ts );
comment on column  T_NOTES.title is '标题';
comment on column  T_NOTES.content is '内容';
comment on column  T_NOTES.desc_txt is '概要';
comment on column  T_NOTES.release_area is '概要';
comment on column  T_NOTES.creator is '创建人';
comment on column  T_NOTES.create_ts is '创建时间';
comment on column  T_NOTES.checker is '审核人';
comment on column  T_NOTES.check_ts is '审核时间';
comment on column  T_NOTES.release_ts is '发布日期';
comment on column  T_NOTES.top_flag is '是否置顶';
comment on column  T_NOTES.stat is '状态 0 录入1 提交审核 2 发布';


commit;
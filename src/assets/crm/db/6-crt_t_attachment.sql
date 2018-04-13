--附件表
drop table t_attachment;
create table t_attachment (
    id     number(12,0)  not null,
    object_key    varchar(30) not null,
		object_name  	varchar2(30)	not null	,
		attachment_name   	VARCHAR2(300)	not null	,
		attachment_type   	varchar2(1)	,
		eff_date   	varchar2(8)	,
		exp_date   	varchar2(8)	,
		issue_org   varchar2(60),
		file_path   varchar2(120),
		file_name   varchar2(120),
		upload_oid    varchar2(30),
		upload_ts	date	not null	,
		stat         varchar2(1),
		constraint "t_attachment_pk" primary key (id)
);
create index t_attachment_i0 on t_attachment ( object_name,object_key );

comment on column t_attachment.id  is '主键';
comment on column t_attachment.object_key  is '对象主键';
comment on column t_attachment.object_name  is '对象名';
comment on column t_attachment.attachment_name  is '附件名称';
comment on column t_attachment.attachment_type  is '附件类型 1 证件复印件 2 电子文档';
comment on column t_attachment.eff_date  is '生效日期';
comment on column t_attachment.exp_date  is '失效日期';
comment on column t_attachment.issue_org  is '发证机关';
comment on column t_attachment.file_path  is '文件目录';
comment on column t_attachment.file_name  is '文件名称';
comment on column t_attachment.upload_oid  is '上传人';
comment on column t_attachment.upload_ts  is '上传时间';
comment on column t_attachment.stat  is '0:激活，1:未激活 ,2 待审批, 3:注销';

commit;
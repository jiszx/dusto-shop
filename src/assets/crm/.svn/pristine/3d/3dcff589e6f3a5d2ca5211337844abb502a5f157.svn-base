-- Create table
drop table  t_factory;
create table T_FACTORY
(
  id              VARCHAR2(20) not null,
  name            VARCHAR2(300),
  zip_code        VARCHAR2(10),
  city            VARCHAR2(200),
  abbr_name       VARCHAR2(100),
  retrieval1      VARCHAR2(100),
  retrieval2      VARCHAR2(200),
  organization_id VARCHAR2(20),
  leader_name     VARCHAR2(20),
  contact_name    VARCHAR2(20),
  contact_tel     VARCHAR2(20),
   constraint PK_T_FACTORY primary key (ID)
);
-- Add comments to the table 
comment on table T_FACTORY
  is '工厂表';
-- Add comments to the columns 
comment on column T_FACTORY.name
  is '名称';
comment on column T_FACTORY.zip_code
  is '邮政编码';
comment on column T_FACTORY.city
  is '城市';
comment on column T_FACTORY.abbr_name
  is '简称';
comment on column T_FACTORY.retrieval1
  is '检索项1';
comment on column T_FACTORY.retrieval2
  is '检索项2';
comment on column T_FACTORY.organization_id
  is '所属销售组织';
comment on column T_FACTORY.leader_name
  is '负责人姓名';
comment on column T_FACTORY.contact_name
  is '联系人姓名';
comment on column T_FACTORY.contact_tel
  is '联系电话';
	commit;
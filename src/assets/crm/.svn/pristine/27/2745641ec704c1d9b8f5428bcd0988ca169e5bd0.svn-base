-- Create table 销售岗位表
drop table CRM_STATION;
create table CRM_STATION
(
    id              NUMBER(10) not null,
  name            VARCHAR2(50),
  organization_id VARCHAR2(20) not null,
  salesrep_id     NUMBER(12) not null,
  states          VARCHAR2(4) not null,
  description     VARCHAR2(300),
  create_ts       DATE not null,
  create_oid      NUMBER(12) not null,
  update_ts       DATE,
  update_oid      NUMBER(12),
  org_area_id     VARCHAR2(50) not null, 
  constraint PK_CRM_STATION primary key (ID)
  
);
-- Add comments to the table 
comment on table CRM_STATION
  is '岗位表';
-- Add comments to the columns 
comment on column CRM_STATION.name
  is '名称';
comment on column CRM_STATION.organization_id
  is '销售组织ID';
comment on column CRM_STATION.salesrep_id
  is '销售人员ID';
comment on column CRM_STATION.states
  is '状态：';
comment on column CRM_STATION.description
  is '隶属团队';
comment on column CRM_STATION.create_ts
  is '创建时间';
comment on column CRM_STATION.create_oid
  is '创建人';
comment on column CRM_STATION.update_ts
  is '更新时间';
comment on column CRM_STATION.org_area_id
  is '区域ID';
commit;
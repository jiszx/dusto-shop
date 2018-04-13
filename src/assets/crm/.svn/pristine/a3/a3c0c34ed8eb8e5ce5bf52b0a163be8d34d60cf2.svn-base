-- Create table
drop table crm_cost_main;
create table CRM_COST_MAIN
(
    id              NUMBER(10) not null,
  amt             NUMBER(20) not null,
  cost_typeid     VARCHAR2(4) not null,
  organization_id VARCHAR2(24) not null,
  region_id       VARCHAR2(24),
  prov_id         VARCHAR2(24),
  constraint CRM_COST_MAIN primary key (ID)
);
-- Add comments to the table 
comment on table CRM_COST_MAIN
  is '费用池可用余额表';
-- Add comments to the columns 
comment on column CRM_COST_MAIN.id
  is '主键ID';
comment on column CRM_COST_MAIN.amt
  is '金额';
comment on column CRM_COST_MAIN.cost_typeid
  is '费用类型ID';
comment on column CRM_COST_MAIN.organization_id
  is '销售组织';
comment on column CRM_COST_MAIN.region_id
  is '大区ID';
  
-- Create sequence 
commit;
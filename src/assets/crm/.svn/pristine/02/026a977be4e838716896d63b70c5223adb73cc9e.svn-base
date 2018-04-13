-- Create table
drop table t_material_factory;
create table T_MATERIAL_FACTORY
(
  facotry_id  VARCHAR2(20) not null,
  material_id VARCHAR2(20) not null,
  states      VARCHAR2(4) default '1',
  inv_num     VARCHAR2(13),
  constraint T_MATERIAL_FACTORY primary key (FACOTRY_ID, MATERIAL_ID)
);
-- Add comments to the table 
comment on table T_MATERIAL_FACTORY
  is '物料工厂对应关系表';
-- Add comments to the columns 
comment on column T_MATERIAL_FACTORY.facotry_id
  is '工厂ID';
comment on column T_MATERIAL_FACTORY.material_id
  is '物料ID';
comment on column T_MATERIAL_FACTORY.states
  is '状态';
comment on column T_MATERIAL_FACTORY.inv_num
  is '库存';

commit;

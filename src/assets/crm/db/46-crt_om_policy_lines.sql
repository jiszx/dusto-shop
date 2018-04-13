-- Create table
drop  table om_policy_lines;
create table OM_POLICY_LINES
(
    id                 NUMBER(10) not null,
  header_id          NUMBER(10) not null,
  material_id        VARCHAR2(30),
  description        VARCHAR2(300),
  primary            VARCHAR2(30) ,
  discount           VARCHAR2(30) not null,
  limit              VARCHAR2(30),
  discount_intensity VARCHAR2(20),
  states             VARCHAR2(4),
  constraint OM_POLICY_LINES primary key (ID)
);
-- Add comments to the table 
comment on table OM_POLICY_LINES
  is '销售政策行表';
-- Add comments to the columns 
comment on column OM_POLICY_LINES.id
  is '主键ID';
comment on column OM_POLICY_LINES.header_id
  is '政策头ID';
comment on column OM_POLICY_LINES.material_id
  is '物料ID';
comment on column OM_POLICY_LINES.description
  is '描述';
comment on column OM_POLICY_LINES.primary
  is '目标数量或者目标金额';
comment on column OM_POLICY_LINES.discount
  is '优惠SKU或者方式';
comment on column OM_POLICY_LINES.limit
  is '限制';
comment on column OM_POLICY_LINES.discount_intensity
  is '优惠力度';
comment on column OM_POLICY_LINES.states
  is '状态';
-- Create sequence 


commit;
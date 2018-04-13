-- Create table
drop table OM_POLICY_CUST;
create table OM_POLICY_CUST
(
  id            NUMBER(10) not null,
  header_id     NUMBER(10) not null,
  merch_cust_id NUMBER(10) not null,
  state         VARCHAR2(4),
  constraint OM_POLICY_AREA primary key (ID)
);
-- Add comments to the table 
comment on table OM_POLICY_CUST
  is '销售政策客户表';
-- Add comments to the columns 
comment on column OM_POLICY_CUST.header_id
  is '销售政策头ID';
comment on column OM_POLICY_CUST.merch_cust_id
  is '销售区域ID';
comment on column OM_POLICY_CUST.state
  is '状态：1有效，2失效';

commit;
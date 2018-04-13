-- Create table 客户所属岗位表
drop table C_MERCH_CUST_STATION;
create table C_MERCH_CUST_STATION
(
  id            NUMBER(10) not null,
  merch_cust_id NUMBER(10) not null,
  station_id    NUMBER(10) not null,
  states        VARCHAR2(4),
  create_ts     DATE,
  create_oid    NUMBER(12),
  update_ts     DATE,
  update_oid    NUMBER(12),
  attribute1    VARCHAR2(300),
  attribute2    VARCHAR2(300),
  attribute3    VARCHAR2(300),
  contract_id   NUMBER(10),
  constraint PK_C_MERCH_CUST_STATION primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_STATION
  is '客户所属岗位表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_STATION.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_STATION.station_id
  is '岗位ID';
comment on column C_MERCH_CUST_STATION.states
  is '状态';
comment on column C_MERCH_CUST_STATION.create_ts
  is '创建时间';
comment on column C_MERCH_CUST_STATION.create_oid
  is '创建人ID';
comment on column C_MERCH_CUST_STATION.update_ts
  is '更新时间';
comment on column C_MERCH_CUST_STATION.update_oid
  is '更新人ID';
comment on column C_MERCH_CUST_STATION.contract_id
  is '合同ID';
-- Create sequence 


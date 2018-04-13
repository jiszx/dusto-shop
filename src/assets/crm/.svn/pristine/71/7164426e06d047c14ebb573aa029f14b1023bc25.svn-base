-- Create table客户送达方信息
drop table C_MERCH_CUST_DISTRIBUTION;
create table C_MERCH_CUST_DISTRIBUTION
(
  id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10) not null,
  name            VARCHAR2(300) not null,
  states          VARCHAR2(4) DEFAULT '1',
  organization_id VARCHAR2(20) not null,
  logistics       VARCHAR2(300) not null,
  site            VARCHAR2(300) not null,
  prov_id         VARCHAR2(4),
  city_id         VARCHAR2(4),
  address         VARCHAR2(300),
  contacter       VARCHAR2(50),
  mobile          VARCHAR2(20),
  create_ts       DATE DEFAULT sysdate,
  create_oid      NUMBER(10) not null,
  update_ts       DATE,
  update_oid      NUMBER(10),
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  constraint PK_C_MERCH_CUST_DISTRIBUTION primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_DISTRIBUTION
  is '客户配送信息表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_DISTRIBUTION.merch_cust_id
  is '客户ID';
comment on column C_MERCH_CUST_DISTRIBUTION.name
  is '送达方名称';
comment on column C_MERCH_CUST_DISTRIBUTION.states
  is '状态：1有效，2失效';
comment on column C_MERCH_CUST_DISTRIBUTION.organization_id
  is '销售组织ID';
comment on column C_MERCH_CUST_DISTRIBUTION.logistics
  is '物流公司';
comment on column C_MERCH_CUST_DISTRIBUTION.site
  is '到站信息';
comment on column C_MERCH_CUST_DISTRIBUTION.prov_id
  is '省';
comment on column C_MERCH_CUST_DISTRIBUTION.city_id
  is '市';
comment on column C_MERCH_CUST_DISTRIBUTION.address
  is '详细地址';
comment on column C_MERCH_CUST_DISTRIBUTION.contacter
  is '联系人';
comment on column C_MERCH_CUST_DISTRIBUTION.mobile
  is '联系电话';
comment on column C_MERCH_CUST_DISTRIBUTION.create_ts
  is '创建人时间';
comment on column C_MERCH_CUST_DISTRIBUTION.create_oid
  is '创建人ID';
comment on column C_MERCH_CUST_DISTRIBUTION.update_ts
  is '更新时间';
comment on column C_MERCH_CUST_DISTRIBUTION.update_oid
  is '更新人';
-- Create sequence 


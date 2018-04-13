-- Create table
drop table C_MERCH_CUST_CONTRACT_LINES;
create table C_MERCH_CUST_CONTRACT_LINES
(
  id          NUMBER(10) not null,
  contract_id NUMBER(10) not null,
  agent_type  VARCHAR2(4) ,
  agent_id varchar2(100) not null,
  name        VARCHAR2(300) ,
  y_amt       NUMBER(12) not null,
  y_ratio     VARCHAR2(10) not null,
  q_amt       NUMBER(12),
  q_ratio     VARCHAR2(10) not null,
  create_ts   DATE,
  create_oid  NUMBER(10),
  update_ts   DATE,
  update_oid  NUMBER(10),
  constraint C_MERCH_CUST_CONTRACT_LINES primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_CONTRACT_LINES
  is '客户合同行表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_CONTRACT_LINES.id
  is '主键ID';
comment on column C_MERCH_CUST_CONTRACT_LINES.contract_id
  is '合同ID';
comment on column C_MERCH_CUST_CONTRACT_LINES.agent_type
  is '代理类型：产品组，品牌，系列，SKU';
comment on column C_MERCH_CUST_CONTRACT_LINES.name
  is '描述';
comment on column C_MERCH_CUST_CONTRACT_LINES.y_amt
  is '年度进货目标';
comment on column C_MERCH_CUST_CONTRACT_LINES.y_ratio
  is '年度返利比例';
comment on column C_MERCH_CUST_CONTRACT_LINES.q_amt
  is '季度进货目标';
comment on column C_MERCH_CUST_CONTRACT_LINES.q_ratio
  is '季度返利比例';
comment on column C_MERCH_CUST_CONTRACT_LINES.create_ts
  is '创建时间';
comment on column C_MERCH_CUST_CONTRACT_LINES.create_oid
  is '创建人';
comment on column C_MERCH_CUST_CONTRACT_LINES.update_ts
  is '更新时间';
comment on column C_MERCH_CUST_CONTRACT_LINES.update_oid
  is '更新人';
-- Create sequence 

-- create table客户合同信息表
drop table c_merch_cust_contract;
create table c_merch_cust_contract
(
  id               number(10) not null,
  merch_cust_id    number(10) not null,
  organization_id  varchar2(20) not null,
  type_id          number(10),
  merch_levels     varchar2(4),
  delivery_address varchar2(300),
  contract_bdate   varchar2(30) not null,
  contract_edate   varchar2(30) not null,
  settle_type      varchar2(4),
  a_period         varchar2(30),
  year_amt         varchar2(30),
  rebate           varchar2(30),
  states		   varchar2(1),
  create_ts        date,
  create_oid       number(10),
  audit_ts         date,
  audit_oid        number(10),
  credit_amt	   number(10),	
  factory_id       VARCHAR2(20),
  agent_area       varchar2(300),
  agent_type	   varchar2(4),
  virtual_warehouse VARCHAR2(10),
  attribute1       varchar2(300),
  attribute2       varchar2(30),
  attribute3       varchar2(30),
  attribute4       varchar2(30),
  attribute5       varchar2(30),
  constraint c_merch_cust_contract primary key (id)
);
-- add comments to the table
comment on table c_merch_cust_contract
  is '客户合同表';
-- add comments to the columns
comment on column c_merch_cust_contract.merch_cust_id
  is '客户id';
comment on column c_merch_cust_contract.organization_id
  is '销售组织id';
comment on column c_merch_cust_contract.type_id
  is '类型（盐，调味品，添加剂）';
comment on column c_merch_cust_contract.merch_levels
  is '客户等级（金银铜铁）';
comment on column c_merch_cust_contract.delivery_address
  is '交货地点';
comment on column c_merch_cust_contract.contract_bdate
  is '合同开始日期';
comment on column c_merch_cust_contract.contract_edate
  is '合同结束日期';
comment on column c_merch_cust_contract.settle_type
  is '结算方式(预付款/账期n天)';
comment on column c_merch_cust_contract.a_period
  is '账期(天)';
comment on column c_merch_cust_contract.year_amt
  is '年度进货总额';
comment on column c_merch_cust_contract.rebate
  is '返利比例：5%';
comment on column c_merch_cust_contract.states
  is '状态';
comment on column c_merch_cust_contract.agent_area
  is '经销区域';
comment on column c_merch_cust_contract.credit_amt
  is '经销区域';
comment on column C_MERCH_CUST_CONTRACT.factory_id
  is '工厂ID';
comment on column C_MERCH_CUST_CONTRACT.agent_type
  is '代理类型：产品组，品牌，系列，SKU';
comment on column C_MERCH_CUST_CONTRACT.virtual_warehouse
  is '虚拟仓代码';
-- create sequence
create sequence seq_merch_contract_id
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;




commit;
-- create table 客户资金调整表
drop table c_merch_cust_adjust;
create table c_merch_cust_adjust
(
  id              number(10) not null,
  merch_cust_id   number(10) not null,
  organization_id varchar2(20) not null,
  create_ts       date not null,
  create_oid      number(10) not null,
  amt             number(12) not null,
  remark          varchar2(50),
  order_id        number(14),
  order_line_id   number(10),
  type            varchar2(4) not null,
  account_type    varchar2(4) not null,
  states          varchar2(4),
  reason          varchar2(4) not null,
  sku             varchar2(300),
  order_num       varchar2(10),
  order_price     varchar2(10),
  attribute1      varchar2(300),
  attribute2      varchar2(300),
  attribute3      varchar2(300),
  attribute4      varchar2(300),
  attribute5      varchar2(300),
  update_ts       date,
  update_oid      number(12),
  bank_account         VARCHAR2(30),
  target_merch_cust_id NUMBER(10),
  adjust_direction     VARCHAR2(4),
  constraint c_merch_cust_adjust primary key (id)
);
-- add comments to the table
comment on table c_merch_cust_adjust
  is '客户资金调整表';
-- add comments to the columns
comment on column c_merch_cust_adjust.id
  is '主键id';
comment on column c_merch_cust_adjust.merch_cust_id
  is '客户id';
comment on column c_merch_cust_adjust.organization_id
  is '所属组织';
comment on column c_merch_cust_adjust.create_ts
  is '创建时间';
comment on column c_merch_cust_adjust.create_oid
  is '创建人';
comment on column c_merch_cust_adjust.amt
  is '调整金额';
comment on column c_merch_cust_adjust.remark
  is '备注';
comment on column c_merch_cust_adjust.order_id
  is '调整来源单号';
comment on column c_merch_cust_adjust.order_line_id
  is '销售订单行id';
comment on column c_merch_cust_adjust.type
  is '调整类型：1手工调整，2订单调整，3销售政策奖励';
comment on column c_merch_cust_adjust.account_type
  is '资金来源：1现金，2货补，3授信，4保证金';
comment on column c_merch_cust_adjust.states
  is '状态';
comment on column c_merch_cust_adjust.reason
  is '调整原因';
comment on column c_merch_cust_adjust.sku
  is 'sku';
comment on column c_merch_cust_adjust.order_num
  is '销售订单数量';
comment on column c_merch_cust_adjust.order_price
  is '销售订单单价';
comment on column c_merch_cust_adjust.update_ts
  is '更新时间';
comment on column c_merch_cust_adjust.update_oid
  is '更新人';
comment on column C_MERCH_CUST_ADJUST.bank_account
  is '银行账户';
comment on column C_MERCH_CUST_ADJUST.target_merch_cust_id
  is '目标客户';
comment on column C_MERCH_CUST_ADJUST.adjust_direction
  is '调整方向：1客户间调整，2调整银行账户';
comment on column C_MERCH_CUST_ADJUST.attribute2
  is 'sap接口返回信息';
commit;

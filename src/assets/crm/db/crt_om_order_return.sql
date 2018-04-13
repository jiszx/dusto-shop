-- Create table退货表
drop table OM_ORDER_RETURN;
create table OM_ORDER_RETURN
(
   id              NUMBER(10) not null,
  merch_cust_id   NUMBER(10) not null,
  organization_id VARCHAR2(24) not null,
  material_id     VARCHAR2(30) not null,
  return_amt      NUMBER(10) not null,
  create_ts       DATE not null,
  create_oid      NUMBER(10) not null,
  audit_ts        DATE,
  audit_oid       NUMBER(10),
  states          VARCHAR2(4) not null,
  return_num      NUMBER(10,2),
  remark          VARCHAR2(1000),
  update_ts       DATE,
  update_oid      NUMBER(10),
  hb_amt          NUMBER(10),
  cash_amt        NUMBER(10),
  credit_amt      NUMBER(10),
  process_id      VARCHAR2(30),
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  is_rebate       VARCHAR2(4) default 0,
  constraint OM_ORDER_RETURN primary key (ID)
);
-- Add comments to the table 
comment on table OM_ORDER_RETURN
  is '客户退货表';
-- Add comments to the columns 
comment on column OM_ORDER_RETURN.id
  is '主键ID';
comment on column OM_ORDER_RETURN.merch_cust_id
  is '客户id';
comment on column OM_ORDER_RETURN.organization_id
  is '销售组织';
comment on column OM_ORDER_RETURN.material_id
  is '物料编码';
comment on column OM_ORDER_RETURN.return_amt
  is '退货金额';
comment on column OM_ORDER_RETURN.create_ts
  is '创建时间';
comment on column OM_ORDER_RETURN.create_oid
  is '创建人';
comment on column OM_ORDER_RETURN.states
  is '状态';
comment on column OM_ORDER_RETURN.return_num
  is '退货数量';
comment on column OM_ORDER_RETURN.remark
  is '备注';
comment on column OM_ORDER_RETURN.hb_amt
  is '货补金额';
comment on column OM_ORDER_RETURN.cash_amt
  is '现金金额';
comment on column OM_ORDER_RETURN.credit_amt
  is '授信金额';
comment on column OM_ORDER_RETURN.process_id
  is '流程ID';

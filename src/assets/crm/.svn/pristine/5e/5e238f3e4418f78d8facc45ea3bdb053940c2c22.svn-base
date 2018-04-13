-- Create table
drop table om_order_headers_all;
create table OM_ORDER_HEADERS_ALL
(
  id                NUMBER(14) not null,
  organization_id   VARCHAR2(20) not null,
  merch_cust_id     NUMBER(10) not null,
  ship_id           NUMBER(10) not null,
  create_ts         DATE not null,
  create_oid        NUMBER(10) not null,
  update_ts         DATE,
  update_oid        NUMBER(10),
  states            VARCHAR2(4) not null,
  invoice_number    VARCHAR2(30),
  region_id         VARCHAR2(20),
  provi_id          VARCHAR2(20),
  order_amt         NUMBER(10),
  discount_amt      NUMBER(10),
  amt               NUMBER(10),
  station_id        NUMBER(10) not null,
  bill_to           NUMBER(10),
  attribute1        VARCHAR2(300),
  attribute2        VARCHAR2(300),
  attribute3        VARCHAR2(300),
  attribute4        VARCHAR2(300),
  attribute5        VARCHAR2(300),
  salesrep_id       NUMBER(10),
  sap_order_id      VARCHAR2(50),
  remark            VARCHAR2(500),
  hb_amt            NUMBER(10),
  cash_amt          NUMBER(10),
  credit_amt        NUMBER(10),
  is_rebate         VARCHAR2(4),
  attribute6        VARCHAR2(300),
  attribute7        VARCHAR2(300),
  attribute8        VARCHAR2(300),
  attribute9        VARCHAR2(300),
  attribute10       VARCHAR2(300),
  attribute11       VARCHAR2(300),
  attribute12       VARCHAR2(300),
  attribute13       VARCHAR2(300),
  return_hb_amt     NUMBER(10),
  return_cash_amt   NUMBER(10),
  return_credit_amt NUMBER(10),
  order_type        VARCHAR2(2),
  is_accrued        VARCHAR2(4),
  rdc_code			VARCHAR2(10),
  constraint OM_ORDER_HEADERS_ALL primary key (ID)
);
-- Add comments to the table 
comment on table OM_ORDER_HEADERS_ALL
  is '销售订单头表';
-- Add comments to the columns 
comment on column OM_ORDER_HEADERS_ALL.id
  is '主键ID--销售订单号';
comment on column OM_ORDER_HEADERS_ALL.organization_id
  is '销售组织ID';
comment on column OM_ORDER_HEADERS_ALL.merch_cust_id
  is '客户ID';
comment on column OM_ORDER_HEADERS_ALL.ship_id
  is '送达方ID';
comment on column OM_ORDER_HEADERS_ALL.create_ts
  is '下单时间';
comment on column OM_ORDER_HEADERS_ALL.create_oid
  is '下单人';
comment on column OM_ORDER_HEADERS_ALL.update_ts
  is '更新时间';
comment on column OM_ORDER_HEADERS_ALL.update_oid
  is '更新人';
comment on column OM_ORDER_HEADERS_ALL.states
  is '订单状态';
comment on column OM_ORDER_HEADERS_ALL.invoice_number
  is '发票号码';
comment on column OM_ORDER_HEADERS_ALL.region_id
  is '业务大区';
comment on column OM_ORDER_HEADERS_ALL.provi_id
  is '业务省';
comment on column OM_ORDER_HEADERS_ALL.order_amt
  is '销售订单金额';
comment on column OM_ORDER_HEADERS_ALL.discount_amt
  is '折扣金额';
comment on column OM_ORDER_HEADERS_ALL.amt
  is '订单净额';
comment on column OM_ORDER_HEADERS_ALL.station_id
  is '岗位ID';
comment on column OM_ORDER_HEADERS_ALL.bill_to
  is '开票方';
comment on column OM_ORDER_HEADERS_ALL.salesrep_id
  is '销售人员ID';
comment on column OM_ORDER_HEADERS_ALL.sap_order_id
  is 'sap订单编号';
comment on column OM_ORDER_HEADERS_ALL.hb_amt
  is '货补扣减金额';
comment on column OM_ORDER_HEADERS_ALL.cash_amt
  is '现金扣减金额';
comment on column OM_ORDER_HEADERS_ALL.credit_amt
  is '授信扣减金额';
comment on column OM_ORDER_HEADERS_ALL.is_rebate
  is '是否返利';
comment on column OM_ORDER_HEADERS_ALL.return_hb_amt
  is '退货货补金额';
comment on column OM_ORDER_HEADERS_ALL.return_cash_amt
  is '退货现金金额';
comment on column OM_ORDER_HEADERS_ALL.return_credit_amt
  is '退货授信金额';
comment on column OM_ORDER_HEADERS_ALL.order_type
  is '订单类型   0默认 1配送商订单 2零售商订单,3退货订单,4特殊订单，5调拨单，6仓储零售商订单，7合资盐业公司订单';
comment on column OM_ORDER_HEADERS_ALL.attribute2
  is 'ka订单 最晚送达时间';
comment on column OM_ORDER_HEADERS_ALL.attribute6
  is '开票类型   0不开票 ，1开普通发票，2开增值税发票';
comment on column OM_ORDER_HEADERS_ALL.attribute7
  is '是否开票   0未开票 ，1开过发票';
comment on column OM_ORDER_HEADERS_ALL.attribute8
  is '批次号';
comment on column OM_ORDER_HEADERS_ALL.attribute9
  is '是否匹配资金   1匹配过';
comment on column OM_ORDER_HEADERS_ALL.is_accrued
  is '是否计提';
comment on column OM_ORDER_HEADERS_ALL.attribute10
  is '销售订单取消/关闭SAP返回数据';
comment on column OM_ORDER_HEADERS_ALL.rdc_code
  is '发货虚拟仓';
commit;
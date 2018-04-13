-- create table
drop table OM_ORDER_SPILTS;
create table OM_ORDER_SPILTS
(
  id               NUMBER(10) not null,
  header_id        NUMBER(14) not null,
  line_id          NUMBER(10) not null,
  price            NUMBER(10) not null,
  num              NUMBER(10,2) not null,
  amt              NUMBER(10) not null,
  merch_cust_id    NUMBER(10) not null,
  material_id      VARCHAR2(30) not null,
  states           VARCHAR2(4) not null,
  sap_header_id    VARCHAR2(30),
  sap_create_ts    DATE,
  organization_id  VARCHAR2(24) not null,
  type             VARCHAR2(4),
  ship_to          NUMBER(10) not null,
  create_ts        DATE not null,
  create_oid       NUMBER(10) not null,
  regin_id         VARCHAR2(24) not null,
  prov_id          VARCHAR2(24) not null,
  station_id       NUMBER(10) not null,
  salesrep_id      NUMBER(10) not null,
  attribute1       VARCHAR2(300),
  attribute2       VARCHAR2(300),
  attribute3       VARCHAR2(300),
  attribute4       VARCHAR2(300),
  attribute5       VARCHAR2(300),
  delivered_num    VARCHAR2(30),
  orderitem_sap_no VARCHAR2(20),
  constraint OM_ORDER_SPILTS primary key (id)
);
-- Add comments to the table 
comment on table OM_ORDER_SPILTS
  is '拆分行表';
-- Add comments to the columns 
comment on column OM_ORDER_SPILTS.id
  is '主键ID';
comment on column OM_ORDER_SPILTS.header_id
  is '订单头ID';
comment on column OM_ORDER_SPILTS.line_id
  is '订单行ID';
comment on column OM_ORDER_SPILTS.price
  is '价格';
comment on column OM_ORDER_SPILTS.num
  is '数量';
comment on column OM_ORDER_SPILTS.amt
  is '总金额';
comment on column OM_ORDER_SPILTS.merch_cust_id
  is '客户ID';
comment on column OM_ORDER_SPILTS.material_id
  is '物料ID';
comment on column OM_ORDER_SPILTS.states
  is '行状态';
comment on column OM_ORDER_SPILTS.sap_header_id
  is 'sap订单编号';
comment on column OM_ORDER_SPILTS.sap_create_ts
  is 'sap创建时间';
comment on column OM_ORDER_SPILTS.organization_id
  is '销售组织ID';
comment on column OM_ORDER_SPILTS.type
  is '1:标准订单，2货补，3销售政策';
comment on column OM_ORDER_SPILTS.ship_to
  is '送达方';
comment on column OM_ORDER_SPILTS.create_ts
  is '创建时间';
comment on column OM_ORDER_SPILTS.create_oid
  is '创建人';
comment on column OM_ORDER_SPILTS.regin_id
  is '大区';
comment on column OM_ORDER_SPILTS.prov_id
  is '省区';
comment on column OM_ORDER_SPILTS.station_id
  is '岗位';
comment on column OM_ORDER_SPILTS.salesrep_id
  is '销售人员';
comment on column OM_ORDER_SPILTS.delivered_num
  is '已发货数量';
comment on column OM_ORDER_SPILTS.orderitem_sap_no
  is 'sap订单行项目';
comment on column OM_ORDER_SPILTS.attribute2
  is '发货工厂';

commit;

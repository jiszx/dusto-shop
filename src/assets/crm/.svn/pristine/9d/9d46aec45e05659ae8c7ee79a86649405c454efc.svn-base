-- Create table
drop table om_order_lines_all;
create table OM_ORDER_LINES_ALL
(
  id                        NUMBER(10) not null,
  header_id                 NUMBER(14) not null,
  material_id               VARCHAR2(30) not null,
  price                     NUMBER(10) not null,
  num                       NUMBER(10,2) not null,
  policy_header_id          NUMBER(10),
  hb_num                    NUMBER(10,2),
  amt                       NUMBER(10) not null,
  discount_amt              NUMBER(10),
  order_amt                 NUMBER(10) not null,
  states                    VARCHAR2(4) not null,
  policy_line_id            NUMBER(10),
  hb_amt                    NUMBER(10),
  high_price                NUMBER(10),
  transport_price           NUMBER(10),
  delivered_num             NUMBER(10,2),
  attribute2                VARCHAR2(300),
  attribute3                VARCHAR2(300),
  attribute4                VARCHAR2(300),
  attribute5                VARCHAR2(300),
  attribute1                VARCHAR2(300),
  create_ts                 DATE,
  create_oid                NUMBER(10),
  update_ts                 DATE,
  update_oid                NUMBER(10),
  policy_discount           VARCHAR2(30),
  policy_discount_intensity VARCHAR2(30),
  policy_verfication        VARCHAR2(4),
  order_price               NUMBER(10),
  unit                      VARCHAR2(30),
  attribute6                VARCHAR2(300),
  attribute7                VARCHAR2(300),
  attribute8                VARCHAR2(300),
  attribute9                VARCHAR2(300),
  attribute10               VARCHAR2(300),
  attribute11               VARCHAR2(300),
  attribute12               VARCHAR2(300) default 0,
  return_num                NUMBER(10,2),
  retrun_hb_num             NUMBER(10,2),
  return_policy_num         NUMBER(10,2),
  constraint OM_ORDER_LINES_ALL primary key (ID)
);
-- Add comments to the table 
comment on table OM_ORDER_LINES_ALL
  is '销售订单行表';
-- Add comments to the columns 
comment on column OM_ORDER_LINES_ALL.id
  is '主键ID';
comment on column OM_ORDER_LINES_ALL.header_id
  is '订单头ID';
comment on column OM_ORDER_LINES_ALL.material_id
  is '物料ID';
comment on column OM_ORDER_LINES_ALL.price
  is '产品标准价格';
comment on column OM_ORDER_LINES_ALL.num
  is '购买数量';
comment on column OM_ORDER_LINES_ALL.policy_header_id
  is '政策ID';
comment on column OM_ORDER_LINES_ALL.hb_num
  is '货补数量';
comment on column OM_ORDER_LINES_ALL.amt
  is '金额';
comment on column OM_ORDER_LINES_ALL.discount_amt
  is '折扣金额';
comment on column OM_ORDER_LINES_ALL.order_amt
  is '订单净额';
comment on column OM_ORDER_LINES_ALL.states
  is '订单行状态';
comment on column OM_ORDER_LINES_ALL.policy_line_id
  is '销售政策行ID';
comment on column OM_ORDER_LINES_ALL.hb_amt
  is '货补金额';
comment on column OM_ORDER_LINES_ALL.high_price
  is '高买价格';
comment on column OM_ORDER_LINES_ALL.transport_price
  is '物流成本';
comment on column OM_ORDER_LINES_ALL.delivered_num
  is '已发货数量';
comment on column OM_ORDER_LINES_ALL.attribute2
  is '搭赠数量';
comment on column OM_ORDER_LINES_ALL.attribute3
  is '确认数量';
comment on column OM_ORDER_LINES_ALL.attribute4
  is '备注';
comment on column OM_ORDER_LINES_ALL.attribute5
  is '收货数量';
comment on column OM_ORDER_LINES_ALL.attribute6
  is '工厂';
comment on column OM_ORDER_LINES_ALL.policy_discount
  is '政策奖励内容';
comment on column OM_ORDER_LINES_ALL.policy_discount_intensity
  is '政策奖励力度';
comment on column OM_ORDER_LINES_ALL.policy_verfication
  is '政策奖励方式：搭赠，折扣，货补。。。。';
comment on column OM_ORDER_LINES_ALL.order_price
  is '订单价';
comment on column OM_ORDER_LINES_ALL.unit
  is '订单单位';
comment on column OM_ORDER_LINES_ALL.return_num
  is '现金退货数量';
comment on column OM_ORDER_LINES_ALL.retrun_hb_num
  is '货补退货数量';
comment on column OM_ORDER_LINES_ALL.return_policy_num
  is '销售政策退货数量';

commit;

-- Create table
drop table OM_ORDER_DELIVERED;
create table OM_ORDER_DELIVERED
(
  delivery_no      VARCHAR2(30),
  sap_order_id     VARCHAR2(30),
  num              VARCHAR2(30),
  post_time        DATE,
  orderitem_sap_no VARCHAR2(30),
  deliveryitem_no  VARCHAR2(30)
);
-- Add comments to the table 
comment on table OM_ORDER_DELIVERED
  is '销售订单SAP交货表';
-- Add comments to the columns 
comment on column OM_ORDER_DELIVERED.delivery_no
  is 'SAP交货单编号';
comment on column OM_ORDER_DELIVERED.sap_order_id
  is 'sap订单编号';
comment on column OM_ORDER_DELIVERED.num
  is '发货数量';
comment on column OM_ORDER_DELIVERED.post_time
  is 'SAP交货时间';
comment on column OM_ORDER_DELIVERED.orderitem_sap_no
  is 'SAP订单行项目编号';
comment on column OM_ORDER_DELIVERED.deliveryitem_no
  is 'SAP交货单行项目号';
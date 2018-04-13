-- drop table
drop table OM_ORDER_DELIVERED_HISTORY;
-- Create table
create table OM_ORDER_DELIVERED_HISTORY
(
  id          NUMBER(10) not null,
  delivery_no      VARCHAR2(30),
  sap_order_id     VARCHAR2(30),
  num              VARCHAR2(30),
  post_time        DATE,
  orderitem_sap_no VARCHAR2(30),
  deliveryitem_no  VARCHAR2(30)
);
-- Add comments to the table 
comment on table OM_ORDER_DELIVERED_HISTORY
  is '销售订单SAP交货历史记录表';
-- Add comments to the columns 
comment on column OM_ORDER_DELIVERED_HISTORY.delivery_no
  is 'SAP交货单编号';
comment on column OM_ORDER_DELIVERED_HISTORY.sap_order_id
  is 'sap订单编号';
comment on column OM_ORDER_DELIVERED_HISTORY.num
  is '发货数量';
comment on column OM_ORDER_DELIVERED_HISTORY.post_time
  is 'SAP交货时间';
comment on column OM_ORDER_DELIVERED_HISTORY.orderitem_sap_no
  is 'SAP订单行项目编号';
comment on column OM_ORDER_DELIVERED_HISTORY.deliveryitem_no
  is 'SAP交货单行项目号';
alter table OM_ORDER_DELIVERED_HISTORY
  add constraint PK_OM_ORDER_DELIVERED_HISTORY primary key (ID);

create sequence SEQ_ORDER_DELIVERED_HISTORY_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;
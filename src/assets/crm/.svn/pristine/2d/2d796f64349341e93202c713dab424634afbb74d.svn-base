drop table OM_ORDER_LOGISTICS;
-- Create table
create table OM_ORDER_LOGISTICS
(
  id              NUMBER(10) not null,
  sap_delivery_no VARCHAR2(30),
  site            VARCHAR2(50),
  logistics_no    VARCHAR2(50),
  remark          VARCHAR2(300),
  create_ts       DATE,
  create_oid      NUMBER(10),
  logistics       VARCHAR2(300),
  delivery_ts     DATE,
  logistics_cost  NUMBER(12,2)
);
-- Add comments to the table 
comment on table OM_ORDER_LOGISTICS
  is '销售订单物流表';
-- Add comments to the columns 
comment on column OM_ORDER_LOGISTICS.id
  is '主键ID';
comment on column OM_ORDER_LOGISTICS.sap_delivery_no
  is 'SAP发货单编号';
comment on column OM_ORDER_LOGISTICS.site
  is '到站';
comment on column OM_ORDER_LOGISTICS.logistics_no
  is '物流单号';
comment on column OM_ORDER_LOGISTICS.remark
  is '备注';
comment on column OM_ORDER_LOGISTICS.logistics
  is '物料公司';
comment on column OM_ORDER_LOGISTICS.delivery_ts
  is '发货时间';
comment on column OM_ORDER_LOGISTICS.logistics_cost
  is '物流费用';
-- Create/Recreate primary, unique and foreign key constraints 
alter table OM_ORDER_LOGISTICS add primary key (ID);
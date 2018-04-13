-- Create table
create table C_MERCH_CUST_INV_WARNING
(
  id                 number(10) not null,
  CUSTOMER_ID           number(10) not null,
  IS_WARNING char(1) not null,
  create_ts DATE not null,
  create_oid NUMBER(10),
  update_ts DATE not null,
  update_oid NUMBER(10)
)
;
-- Add comments to the table 
comment on table C_MERCH_CUST_INV_WARNING
  is '物流商库存预警';
-- Add comments to the columns 
comment on column C_MERCH_CUST_INV_WARNING.CUSTOMER_ID
  is '对应客户ID';
comment on column C_MERCH_CUST_INV_WARNING.IS_WARNING
  is '是否预警';
  
create sequence SEQ_CUST_INV_WARNING_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1;

-- Create table
create table C_MERCH_CUST_INV_WARNING_ITEM
(
  id                 number(10) not null,
  warning_id         number(10) not null,
  cust_Inv_id        number(10) not null,
  warning_type char(1) not null,
  target NUMBER(12,3),
  is_active char(1) not null,
  create_ts DATE not null,
  create_oid NUMBER(10),
  update_ts DATE not null,
  update_oid NUMBER(10)
)
;
-- Add comments to the table 
comment on table C_MERCH_CUST_INV_WARNING_ITEM
  is '物流商库存预警明细';
-- Add comments to the columns 
comment on column C_MERCH_CUST_INV_WARNING_ITEM.warning_id
  is '预警ID';
comment on column C_MERCH_CUST_INV_WARNING_ITEM.cust_Inv_id
  is '客户库存对应ID';
comment on column C_MERCH_CUST_INV_WARNING_ITEM.warning_type
  is '预警类型';
comment on column C_MERCH_CUST_INV_WARNING_ITEM.target
  is '目标比例或数量';
comment on column C_MERCH_CUST_INV_WARNING_ITEM.is_active
  is '启用禁用';
  
  
create sequence SEQ_CUST_INV_WARNING_ITEM_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1;

insert into t_authority (RES_ID, RES_NAME, RES_TYPE, RES_URL, OTHER_RES, ORDERS, PID, ICONCLASS, SCRIPT_TEXT, UPDATE_TS, XTYPE)
values (seq_authority_id.nextval, '物流商库存预警设置', 2, 'customer/config/inv/warning/index.html', 'customer/config/inv/warning*', 5, 1000, 'icon icon-plane', null, null, '0');
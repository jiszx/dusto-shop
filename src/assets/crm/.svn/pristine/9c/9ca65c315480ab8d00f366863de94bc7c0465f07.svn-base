-- Create table
create table T_BUSINESS_MODEL_MOQ
(
  id                 number(10) not null,
  model_id           number(10) not null,
  min_order_quantity number(10,2) not null,
  range              number(10,2) default 0
)
;
-- Add comments to the table 
comment on table T_BUSINESS_MODEL_MOQ
  is '业务模式最小起订量';
-- Add comments to the columns 
comment on column T_BUSINESS_MODEL_MOQ.model_id
  is '业务模式ID，字典表BUSINESS_MODEL';
comment on column T_BUSINESS_MODEL_MOQ.min_order_quantity
  is '最小起订量';
comment on column T_BUSINESS_MODEL_MOQ.range
  is '重量浮动幅度';
  
  
create sequence SEQ_BUSINESS_MODEL_MOQ
minvalue 1
maxvalue 999999999999
start with 1
increment by 1;

insert into t_authority (RES_ID, RES_NAME, RES_TYPE, RES_URL, OTHER_RES, ORDERS, PID, ICONCLASS, SCRIPT_TEXT, UPDATE_TS, XTYPE)
values (seq_authority_id.nextval, '业务模式最小起订量设置', 2, 'config/moq/index.html', 'config/moq/save*', 4, 1000, 'icon icon-th-large', null, null, '0');


-- Add/modify columns 
alter table T_ROLE add org_id VARCHAR2(10);
-- Add comments to the columns 
comment on column T_ROLE.org_id
  is '销售组织ID';
  
UPDATE t_role t SET t.org_id='T';
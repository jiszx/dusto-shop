-- Create table
drop table crm_business_model;
create table CRM_BUSINESS_MODEL
(
  id         NUMBER(10) not null,
  model_id   VARCHAR2(50),
  brand      VARCHAR2(500),
  create_ts  DATE,
  create_oid NUMBER(10),
  constraint CRM_BUSINESS_MODEL primary key (ID)
);
-- Add comments to the table 
comment on table CRM_BUSINESS_MODEL
  is '生意模式';


-- Create sequence 
create sequence SEQ_BUSINESS_MODEL_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;


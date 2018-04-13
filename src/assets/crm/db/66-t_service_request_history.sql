drop table T_SERVICE_REQUEST_HISTORY;
-- Create table
create table T_SERVICE_REQUEST_HISTORY
(
  id             NUMBER(19) not null,
  req_class  VARCHAR2(255),
  req_method VARCHAR2(255),
  req_param  VARCHAR2(2000),
  req_source CHAR(1),
  result         VARCHAR2(2000),
  callback       VARCHAR2(255),
  token          VARCHAR2(512),
  req_date date
);
-- Add comments to the columns 
comment on column T_SERVICE_REQUEST_HISTORY.req_param
  is '请求参数';
comment on column T_SERVICE_REQUEST_HISTORY.req_source
  is '请求来源';
comment on column T_SERVICE_REQUEST_HISTORY.result
  is '请求结果';
comment on column T_SERVICE_REQUEST_HISTORY.callback
  is '回调地址';
comment on column T_SERVICE_REQUEST_HISTORY.token
  is '请求token';
comment on column T_SERVICE_REQUEST_HISTORY.req_class
  is '请求类';
comment on column T_SERVICE_REQUEST_HISTORY.req_method
  is '请求方法';
comment on column T_SERVICE_REQUEST_HISTORY.req_date
  is '请求发起时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SERVICE_REQUEST_HISTORY
  add primary key (ID);
-- Create/Recreate indexes 
create unique index index_request_history_token on T_SERVICE_REQUEST_HISTORY (token);
-- Create sequence 
create sequence SEQ_SERVICE_REQUEST_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20;
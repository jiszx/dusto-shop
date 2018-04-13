-- Create table
DROP TABLE GL_JOURNAL;
create table GL_JOURNAL
(
  company       VARCHAR2(30),
  journal_ts    DATE,
  period        VARCHAR2(10),
  summary       VARCHAR2(300),
  amt           NUMBER(12,2),
  merch_cust_id VARCHAR2(30),
  voucher_id    VARCHAR2(30),
  voucher_item  VARCHAR2(30),
  type          VARCHAR2(4),
  ar_invoice_no VARCHAR2(30),
  id            NUMBER(12) not null,
  constraint GL_JOURNAL primary key (ID)
);
-- Add comments to the table 
comment on table GL_JOURNAL
  is '日记账凭证';
-- Add comments to the columns 
comment on column GL_JOURNAL.company
  is '公司';
comment on column GL_JOURNAL.journal_ts
  is '记账日期';
comment on column GL_JOURNAL.period
  is '期间';
comment on column GL_JOURNAL.summary
  is '摘要';
comment on column GL_JOURNAL.amt
  is '金额';
comment on column GL_JOURNAL.merch_cust_id
  is '客户代码';
comment on column GL_JOURNAL.voucher_id
  is '会计凭证编号';
comment on column GL_JOURNAL.voucher_item
  is '会计凭证行项目';
comment on column GL_JOURNAL.type
  is '类型：1资金调整/资金上账，2销售应收发票，3手工凭证';
comment on column GL_JOURNAL.ar_invoice_no
  is '应收发票编号';
  
-- Create sequence 
create sequence SEQ_GL_JOURNAL_ID
minvalue 1
maxvalue 999999999999
start with 1
increment by 1
cache 20;


-- Add/modify columns 
alter table GL_JOURNAL add merch_cust_name VARCHAR2(225);
-- Add comments to the columns 
comment on column GL_JOURNAL.merch_cust_name
  is '一次性客户名称';

-- Create table
drop table C_MERCH_UPACCOUNT;
create table C_MERCH_UPACCOUNT
(
  id              NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  create_ts       DATE not null,
  create_oid      NUMBER(12) not null,
  pay_name        VARCHAR2(20) ,
  pay_bank        VARCHAR2(50) ,
  pay_city        VARCHAR2(20) ,
  pay_amt         NUMBER(12) ,
  pay_bank_no     VARCHAR2(20) ,
  pay_date        VARCHAR2(20) ,
  states          VARCHAR2(4),
  salesrep_id     NUMBER(10),
  salesrep_date   DATE,
  audit_ts        DATE,
  audit_oid       NUMBER(10),
  attribute1      VARCHAR2(300),
  attribute2      VARCHAR2(300),
  attribute3      VARCHAR2(300),
  attribute4      VARCHAR2(300),
  attribute5      VARCHAR2(300),
  update_ts       DATE,
  update_oid      NUMBER(12),
  pay_type        VARCHAR2(4) not null,
  merch_cus_id    NUMBER(10),
  bank_account    VARCHAR2(20),
  bill_in_date    DATE,
  bill_out_date   DATE,
  bill_no         VARCHAR2(30),
  bill_bank       VARCHAR2(200),
  bill_out_name   VARCHAR2(100),
  bill_in_name    VARCHAR2(100),
  constraint C_MERCH_UPACCOUNT primary key (ID)
);
-- Add comments to the columns 
comment on column C_MERCH_UPACCOUNT.id
  is '主键ID';
comment on column C_MERCH_UPACCOUNT.organization_id
  is '所属销售组织';
comment on column C_MERCH_UPACCOUNT.create_ts
  is '录入时间';
comment on column C_MERCH_UPACCOUNT.create_oid
  is '录入人';
comment on column C_MERCH_UPACCOUNT.pay_name
  is '打款人姓名';
comment on column C_MERCH_UPACCOUNT.pay_bank
  is '打款银行';
comment on column C_MERCH_UPACCOUNT.pay_city
  is '打款城市';
comment on column C_MERCH_UPACCOUNT.pay_amt
  is '打款金额';
comment on column C_MERCH_UPACCOUNT.pay_bank_no
  is '打款银行卡号后四位';
comment on column C_MERCH_UPACCOUNT.pay_date
  is '来款时间';
comment on column C_MERCH_UPACCOUNT.states
  is '状态';
comment on column C_MERCH_UPACCOUNT.salesrep_id
  is '销售人员ID';
comment on column C_MERCH_UPACCOUNT.salesrep_date
  is '销售确认时间';
comment on column C_MERCH_UPACCOUNT.audit_ts
  is '审批时间';
comment on column C_MERCH_UPACCOUNT.audit_oid
  is '审批人ID';
comment on column C_MERCH_UPACCOUNT.update_ts
  is '更新时间';
comment on column C_MERCH_UPACCOUNT.update_oid
  is '更新人ID';
comment on column C_MERCH_UPACCOUNT.pay_type
  is '来款类型';
comment on column C_MERCH_UPACCOUNT.merch_cus_id
  is '客户ID';
comment on column C_MERCH_UPACCOUNT.bank_account
  is '收款银行账户';
comment on column C_MERCH_UPACCOUNT.bill_in_date
  is '到票日期';
comment on column C_MERCH_UPACCOUNT.bill_out_date
  is '出票日期';
comment on column C_MERCH_UPACCOUNT.bill_no
  is '票号';
comment on column C_MERCH_UPACCOUNT.bill_bank
  is '出票银行';
comment on column C_MERCH_UPACCOUNT.bill_out_name
  is '出票人';
comment on column C_MERCH_UPACCOUNT.bill_in_name
  is '收票人';
commit;

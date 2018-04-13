-- Drop table
drop table T_BANK;
-- Create table
create table T_BANK
(
  code VARCHAR2(100) not null,
  name VARCHAR2(100) not null
);
-- Add comments to the table 
comment on table T_BANK
  is '银行信息表';
-- Add comments to the columns 
comment on column T_BANK.code
  is '银行编码';
comment on column T_BANK.name
  is '银行名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_BANK
  add constraint PK_BANK primary key (code);

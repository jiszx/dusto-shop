-- Create table
drop table C_MERCH_ACCOUNT_ALLOCATION;
create table C_MERCH_ACCOUNT_ALLOCATION
(
  id              NUMBER(10) not null,
  upaccount_id    NUMBER(10),
  allocation_id   NUMBER(10),
  allocation_date DATE,
  allocation_oid  NUMBER(10),
  constraint C_MERCH_ACCOUNT_ALLOCATION primary key (ID)
);
-- Add comments to the table 
comment on table C_MERCH_ACCOUNT_ALLOCATION
  is '资金分配表';
-- Add comments to the columns 
comment on column C_MERCH_ACCOUNT_ALLOCATION.id
  is '主键ID';
comment on column C_MERCH_ACCOUNT_ALLOCATION.upaccount_id
  is '上账ID';
comment on column C_MERCH_ACCOUNT_ALLOCATION.allocation_id
  is '分配到零售商对应的ID';
comment on column C_MERCH_ACCOUNT_ALLOCATION.allocation_date
  is '分配时间';
comment on column C_MERCH_ACCOUNT_ALLOCATION.allocation_oid
  is '操作人';

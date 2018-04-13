-- Create table
--促销品库存信息表
drop table CRM_PROMOTION_INV;
create table CRM_PROMOTION_INV
(
  id              NUMBER(10) not null,
  promotion_id    NUMBER(10) not null,
  stores_id       NUMBER(10) not null,
  num             NUMBER(10) not null,
  organization_id VARCHAR2(20) not null,
  amt             NUMBER(12)
);
-- Add comments to the table 
comment on table CRM_PROMOTION_INV
  is '促销品库存信息表';
-- Add comments to the columns 
comment on column CRM_PROMOTION_INV.id
  is '主键ID';
comment on column CRM_PROMOTION_INV.promotion_id
  is '促销品ID';
comment on column CRM_PROMOTION_INV.stores_id
  is '库房ID';
comment on column CRM_PROMOTION_INV.num
  is '数量';
comment on column CRM_PROMOTION_INV.organization_id
  is '所属销售组织';
comment on column CRM_PROMOTION_INV.amt
  is '库存金额';
commit;
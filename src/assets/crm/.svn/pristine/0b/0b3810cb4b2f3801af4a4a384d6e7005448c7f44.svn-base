-- Create table物料价格表
drop  table T_MATERIAL_PRICE;
create table T_MATERIAL_PRICE
(
  material_id     VARCHAR2(20),
  create_ts       DATE,
  organization_id VARCHAR2(20) not null,
  price           NUMBER(10) not null,
  unit            VARCHAR2(20) not null,
  bdate           DATE not null,
  edate           DATE not null,
  channel         VARCHAR2(20)
);
commit;

-- Add/modify columns 
alter table T_MATERIAL_PRICE add sap_record_no VARCHAR2(10) NOT NULL;
alter table T_MATERIAL_PRICE add constraint PK_MATERIAL_PRICE primary key (SAP_RECORD_NO);
alter table T_MATERIAL_PRICE modify material_id not null;
alter table T_MATERIAL_PRICE modify unit null;
alter table T_MATERIAL_PRICE modify channel not null;

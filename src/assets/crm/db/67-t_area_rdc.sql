-- Create table
create table T_AREA_RDC
(
  city_id  NUMBER(12) not null,
  rdc_code VARCHAR2(255) not null
);
-- Add comments to the columns 
comment on column T_AREA_RDC.city_id
  is '城市id';
comment on column T_AREA_RDC.rdc_code
  is '虚拟仓编号';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_AREA_RDC
  add primary key (CITY_ID, RDC_CODE);

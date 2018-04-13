-- Create table 行政地区表
drop table t_area;
create table T_AREA
(
  id         VARCHAR2(4) not null,
  pid        VARCHAR2(4) not null,
  name       VARCHAR2(50) not null,
  area_level VARCHAR2(4) not null
);
-- Add comments to the columns 
comment on column T_AREA.id
  is '主键ID';
comment on column T_AREA.pid
  is '上级ID';
comment on column T_AREA.name
  is '名称';
comment on column T_AREA.area_level
  is '等级';
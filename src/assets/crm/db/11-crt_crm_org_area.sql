drop table crm_org_area;
create table crm_org_area(
  id varchar2(50) PRIMARY key,
  org_id varchar2(20),
  area_id varchar2(4),
  name varchar2(100),
  pid varchar2(50)
);
comment on column crm_org_area.org_id is '组织ID';
comment on column crm_org_area.area_id is '区域ID';
commit;
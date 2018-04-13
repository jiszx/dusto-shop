-- 产品分类
drop table T_PRODUCT_CATEGORY;
create table T_PRODUCT_CATEGORY
(
  id   varchar2(50) primary key,
  NAME varchar2(200) not null,
  pid varchar2(50)
);
comment on column  T_PRODUCT_CATEGORY.id is '编号';
comment on column  T_PRODUCT_CATEGORY.NAME is '名称';
comment on column  T_PRODUCT_CATEGORY.pid is '上级id';

commit;

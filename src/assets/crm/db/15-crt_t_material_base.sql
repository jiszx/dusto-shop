-- Create table 物料基本信息表
drop table T_MATERIAL_BASE;
create table T_MATERIAL_BASE
(
  sap_id             VARCHAR2(20) not null,
  sku                VARCHAR2(300) not null,
  material_name      VARCHAR2(300),
  material_type      VARCHAR2(20),
  material_group     VARCHAR2(20),
  material_groupname VARCHAR2(50),
  unit               VARCHAR2(20),
  order_unit         VARCHAR2(30),
  product_group      VARCHAR2(20),
  product_group_name VARCHAR2(50),
  category           VARCHAR2(50),
  brand              VARCHAR2(50),
  levels             VARCHAR2(50),
  series             VARCHAR2(50),
  p_name             VARCHAR2(50),
  i_package          VARCHAR2(50),
  o_package          VARCHAR2(50),
  specifications     VARCHAR2(50),
  symbol             VARCHAR2(50),
  prov_id            VARCHAR2(20),
  city_id            VARCHAR2(20),
  iodine             VARCHAR2(50),
  unloose            VARCHAR2(50),
  accessories        VARCHAR2(50),
  salt               VARCHAR2(50),
  grain              VARCHAR2(50),
  flavor             VARCHAR2(50),
  produce_type       VARCHAR2(50),
  invlimit           VARCHAR2(1),
  attribute1         VARCHAR2(300),
  attribute2         VARCHAR2(300),
  attribute3         VARCHAR2(300),
  attribute4         VARCHAR2(300),
  attribute5         VARCHAR2(300),
  attribute6         VARCHAR2(300),
  attribute7         VARCHAR2(300),
  attribute8         VARCHAR2(300),
  attribute9         VARCHAR2(300),
  attribute10        VARCHAR2(300),
  constraint PK_T_MATERIAL_BASE primary key (sap_id)
);
-- Add comments to the table 
comment on table T_MATERIAL_BASE
  is '物料基本信息表';
-- Add comments to the columns 
comment on column T_MATERIAL_BASE.sap_id
  is 'SAP_id';
comment on column T_MATERIAL_BASE.sku
  is 'SKU信息';
comment on column T_MATERIAL_BASE.material_name
  is '物料描述';
comment on column T_MATERIAL_BASE.material_type
  is '物料类型';
comment on column T_MATERIAL_BASE.material_group
  is '物料组';
comment on column T_MATERIAL_BASE.material_groupname
  is '物料组名称';
comment on column T_MATERIAL_BASE.unit
  is '基本计量单位';
comment on column T_MATERIAL_BASE.order_unit
  is '订单单位';
comment on column T_MATERIAL_BASE.product_group
  is '产品组';
comment on column T_MATERIAL_BASE.product_group_name
  is '产品组名称';
comment on column T_MATERIAL_BASE.category
  is '品类';
comment on column T_MATERIAL_BASE.brand
  is '品牌';
comment on column T_MATERIAL_BASE.levels
  is '等级';
comment on column T_MATERIAL_BASE.series
  is '系列';
comment on column T_MATERIAL_BASE.p_name
  is '品名';
comment on column T_MATERIAL_BASE.i_package
  is '内包装形式';
comment on column T_MATERIAL_BASE.o_package
  is '外包装形式';
comment on column T_MATERIAL_BASE.specifications
  is '包装规格';
comment on column T_MATERIAL_BASE.symbol
  is '符号';
comment on column T_MATERIAL_BASE.prov_id
  is '省';
comment on column T_MATERIAL_BASE.city_id
  is '地市';
comment on column T_MATERIAL_BASE.iodine
  is '碘添加';
comment on column T_MATERIAL_BASE.unloose
  is '防松剂';
comment on column T_MATERIAL_BASE.accessories
  is '辅料种类';
comment on column T_MATERIAL_BASE.salt
  is '原盐';
comment on column T_MATERIAL_BASE.grain
  is '颗粒';
comment on column T_MATERIAL_BASE.flavor
  is '味型';
comment on column T_MATERIAL_BASE.produce_type
  is '产出形式';
comment on column T_MATERIAL_BASE.invlimit
  is '下单时是否需要库存限制';

commit;

-- Add/modify columns 
alter table T_MATERIAL_BASE add active char(1) default '1' not null;
-- Add comments to the columns 
comment on column T_MATERIAL_BASE.active
  is '是否有效，1：是，0：否';
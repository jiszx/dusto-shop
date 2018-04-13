-- create table 客户基本信息表
drop table c_merch_cust_base;
create table c_merch_cust_base
(
   id                        NUMBER(10) not null,
  name                      VARCHAR2(200) not null,
  abbr_name                 VARCHAR2(50),
  prov_id                   VARCHAR2(4),
  city_id                   VARCHAR2(4),
  county_id                 VARCHAR2(4),
  address                   VARCHAR2(300),
  lp_no                     VARCHAR2(30),
  lp_name                   VARCHAR2(30),
  zip_code                  VARCHAR2(10),
  reg_addr                  VARCHAR2(300),
  tel                       VARCHAR2(50),
  create_ts                 DATE,
  create_oid                NUMBER(12),
  update_ts                 DATE,
  update_oid                NUMBER(10),
  states                    VARCHAR2(4) not null,
  sap_customer_id           VARCHAR2(50),
  channel_id                VARCHAR2(4),
  cust_type                 VARCHAR2(4),
  is_attachment             VARCHAR2(4),
  business_license          VARCHAR2(50),
  contact_name              VARCHAR2(30),
  contact_tel               VARCHAR2(50),
  organization_id           VARCHAR2(20),
  pid                       NUMBER(10),
  invoice_name              VARCHAR2(200),
  invoice_tax_num           VARCHAR2(30),
  invoice_address           VARCHAR2(300),
  invoice_account           VARCHAR2(30),
  invoice_tel               VARCHAR2(30),
  invoice_bank_name         VARCHAR2(300),
  invoice_account_name      VARCHAR2(30),
  opening_type              VARCHAR2(4),
  opening_reason            VARCHAR2(300),
  opening_merchant          VARCHAR2(100),
  opening_close_ts          DATE,
  expand_ka_place           VARCHAR2(30),
  expand_bc_place           VARCHAR2(30),
  expand_circulate_place    VARCHAR2(30),
  expand_factory_place      VARCHAR2(30),
  expand_school_place       VARCHAR2(30),
  expand_retail_place       VARCHAR2(30),
  expand_area_peoples       VARCHAR2(30),
  expand_area_volume        VARCHAR2(30),
  expand_busines_volume     VARCHAR2(30),
  expand_spent_mamt         VARCHAR2(30),
  expand_spent_famt         VARCHAR2(30),
  context_sales_year        VARCHAR2(30),
  context_business_ratio    VARCHAR2(300),
  context_investment        VARCHAR2(30),
  context_operate_capital   VARCHAR2(30),
  context_ka_num            VARCHAR2(30),
  context_bc_num            VARCHAR2(30),
  context_wholesalers_num   VARCHAR2(30),
  context_retail_num        VARCHAR2(30),
  context_farmers_num       VARCHAR2(30),
  CONTEXT_RESTAURANT_NUM VARCHAR2(30),
  context_others_num        VARCHAR2(30),
  context_salesman_num      VARCHAR2(30),
  context_logistics_num     VARCHAR2(30),
  context_truck_num         VARCHAR2(50),
  context_lorry_num         VARCHAR2(50),
  context_depot             VARCHAR2(200),
  prov_name                 VARCHAR2(50),
  city_name                 VARCHAR2(50),
  county_name               VARCHAR2(50),
  PROCESS_ID               VARCHAR2(50),
  is_invoice               VARCHAR2(1),
  constraint pk_c_merch_cust_base primary key (id)
);
-- Add comments to the table 
comment on table C_MERCH_CUST_BASE
  is '客户基本信息表';
-- Add comments to the columns 
comment on column C_MERCH_CUST_BASE.id
  is 'ID';
comment on column C_MERCH_CUST_BASE.name
  is '名称';
comment on column C_MERCH_CUST_BASE.abbr_name
  is '简称';
comment on column C_MERCH_CUST_BASE.prov_id
  is '所在省ID';
comment on column C_MERCH_CUST_BASE.city_id
  is '地市ID';
comment on column C_MERCH_CUST_BASE.county_id
  is '区县ID';
comment on column C_MERCH_CUST_BASE.address
  is '详细地址';
comment on column C_MERCH_CUST_BASE.lp_no
  is '法人证件号';
comment on column C_MERCH_CUST_BASE.lp_name
  is '法人姓名';
comment on column C_MERCH_CUST_BASE.zip_code
  is '邮政编码';
comment on column C_MERCH_CUST_BASE.reg_addr
  is '注册地址';
comment on column C_MERCH_CUST_BASE.tel
  is '公司电话';
comment on column C_MERCH_CUST_BASE.create_ts
  is '创建时间';
comment on column C_MERCH_CUST_BASE.create_oid
  is '创建人ID';
comment on column C_MERCH_CUST_BASE.update_ts
  is '更新时间';
comment on column C_MERCH_CUST_BASE.update_oid
  is '更新人ID';
comment on column C_MERCH_CUST_BASE.states
  is '客户状态：1潜在客户，2正式客户，3休眠客户，4失效客户';
comment on column C_MERCH_CUST_BASE.sap_customer_id
  is 'sap系统中客户ID';
comment on column C_MERCH_CUST_BASE.channel_id
  is '客户渠道';
comment on column C_MERCH_CUST_BASE.cust_type
  is '客户类型';
comment on column C_MERCH_CUST_BASE.is_attachment
  is '是否存在附件';
comment on column C_MERCH_CUST_BASE.business_license
  is '营业执照号';
comment on column C_MERCH_CUST_BASE.contact_name
  is '业务联系人姓名';
comment on column C_MERCH_CUST_BASE.contact_tel
  is '业务联系电话';
comment on column C_MERCH_CUST_BASE.organization_id
  is '所属销售组织';
comment on column C_MERCH_CUST_BASE.pid
  is '上级客户编码';
comment on column C_MERCH_CUST_BASE.invoice_name
  is '开票名称';
comment on column C_MERCH_CUST_BASE.invoice_tax_num
  is '开票税号';
comment on column C_MERCH_CUST_BASE.invoice_address
  is '开票地址';
comment on column C_MERCH_CUST_BASE.invoice_account
  is '开票账户';
comment on column C_MERCH_CUST_BASE.invoice_tel
  is '开票电话';
comment on column C_MERCH_CUST_BASE.invoice_bank_name
  is '开票常用打款银行名称';
comment on column C_MERCH_CUST_BASE.invoice_account_name
  is '开票常用账户名称';
comment on column C_MERCH_CUST_BASE.opening_type
  is '新开类型';
comment on column C_MERCH_CUST_BASE.opening_reason
  is '替开/升级原因';
comment on column C_MERCH_CUST_BASE.opening_merchant
  is '替开/升级经销商名称';
comment on column C_MERCH_CUST_BASE.opening_close_ts
  is '替开/升级，原经销商关闭时间';
comment on column C_MERCH_CUST_BASE.expand_ka_place
  is '计划拓展KA渠道数量';
comment on column C_MERCH_CUST_BASE.expand_bc_place
  is '计划拓展BC渠道数量';
comment on column C_MERCH_CUST_BASE.expand_circulate_place
  is '计划拓展流通渠道数量';
comment on column C_MERCH_CUST_BASE.expand_factory_place
  is '计划拓展工厂数量';
comment on column C_MERCH_CUST_BASE.expand_school_place
  is '计划拓展学校渠道数量';
comment on column C_MERCH_CUST_BASE.expand_retail_place
  is '计划拓展零售网点数';
comment on column C_MERCH_CUST_BASE.expand_area_peoples
  is '计划拓展区域人数';
comment on column C_MERCH_CUST_BASE.expand_area_volume
  is '计划拓展区域体量';
comment on column C_MERCH_CUST_BASE.expand_busines_volume
  is '计划拓展生意体量';
comment on column C_MERCH_CUST_BASE.expand_spent_mamt
  is '计划投入每月运营金额';
comment on column C_MERCH_CUST_BASE.expand_spent_famt
  is '计划投入到我司产品的首单金额';
comment on column C_MERCH_CUST_BASE.context_sales_year
  is '企业年销售额';
comment on column C_MERCH_CUST_BASE.context_business_ratio
  is '企业各厂家/供应商 生意占比';
comment on column C_MERCH_CUST_BASE.context_investment
  is '企业年度总投资额';
comment on column C_MERCH_CUST_BASE.context_operate_capital
  is '企业截止目前，每月营运资金';
comment on column C_MERCH_CUST_BASE.context_ka_num
  is '企业KA数量';
comment on column C_MERCH_CUST_BASE.context_bc_num
  is '企业BC数量';
comment on column C_MERCH_CUST_BASE.context_wholesalers_num
  is '企业批发商数量';
comment on column C_MERCH_CUST_BASE.context_retail_num
  is '企业零售网点数';
comment on column C_MERCH_CUST_BASE.context_farmers_num
  is '企业农贸网点数量';
comment on column C_MERCH_CUST_BASE."CONTEXT_RESTAURANT _NUM"
  is '企业餐饮网点数量';
comment on column C_MERCH_CUST_BASE.context_others_num
  is '企业其他网点数量';
comment on column C_MERCH_CUST_BASE.context_salesman_num
  is '企业销售人员数量';
comment on column C_MERCH_CUST_BASE.context_logistics_num
  is '企业后勤人员数量';
comment on column C_MERCH_CUST_BASE.context_truck_num
  is '企业卡车数量';
comment on column C_MERCH_CUST_BASE.context_lorry_num
  is '企业货车数量';
comment on column C_MERCH_CUST_BASE.context_depot
  is '企业仓库面积';
comment on column C_MERCH_CUST_BASE.prov_name
  is '省名称';
comment on column C_MERCH_CUST_BASE.city_name
  is '地市名称';
comment on column C_MERCH_CUST_BASE.county_name
  is '区县名称';
comment on column C_MERCH_CUST_BASE.is_invoice
  is '是否开票';
comment on column C_MERCH_CUST_BASE.payer
  is '付款方';

-- Add/modify columns 
alter table C_MERCH_CUST_BASE add plan_area VARCHAR2(200);
alter table C_MERCH_CUST_BASE add plan_brand VARCHAR2(200);
alter table C_MERCH_CUST_BASE add plan_category VARCHAR2(200);
ALTER TABLE C_MERCH_CUST_BASE ADD (email VARCHAR2(64));
ALTER TABLE C_MERCH_CUST_BASE ADD (payer number(10));
-- Add comments to the columns 
comment on column C_MERCH_CUST_BASE.plan_area
  is '计划拓展区域';
comment on column C_MERCH_CUST_BASE.plan_brand
  is '计划拓展品牌';
comment on column C_MERCH_CUST_BASE.plan_category
  is '计划拓展品类';
comment on column C_MERCH_CUST_BASE.email
  is '邮箱';

commit;

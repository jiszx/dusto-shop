DROP TABLE crm_export_config;
CREATE TABLE crm_export_config (
id NUMBER(12) NOT NULL ,
export_key VARCHAR2(100 BYTE) NULL ,
mapping VARCHAR2(4000 BYTE) NULL ,
sql VARCHAR2(4000 BYTE) NULL ,
description VARCHAR2(64 BYTE) NULL
);
ALTER TABLE crm_export_config ADD CHECK (id IS NOT NULL);
ALTER TABLE crm_export_config ADD PRIMARY KEY (id);

comment on column crm_export_config.mapping
  is '映射 eg：[{"head":"销售组织","column":"orgname"},{"head":"订单类型","column":"ORDER_TYPE"}]';
comment on column crm_export_config.description
  is '描述，说明此sql的用途';
comment on column crm_export_config.sql
  is '查询sql权限控制语句使用 ## ##包围 用#{station}加入岗位 eg：SELECT t.ORGANIZATION_ID orgname,t.ORDER_TYPE,t.CREATE_TS from OM_ORDER_HEADERS_ALL t ##where station in(#{station})##';
commit;
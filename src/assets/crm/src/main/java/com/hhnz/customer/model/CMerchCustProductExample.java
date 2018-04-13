package com.hhnz.customer.model;

import com.hhnz.util.db.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CMerchCustProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public CMerchCustProductExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdIsNull() {
            addCriterion("MERCH_CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdIsNotNull() {
            addCriterion("MERCH_CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdEqualTo(Long value) {
            addCriterion("MERCH_CUST_ID =", value, "merchCustId");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdNotEqualTo(Long value) {
            addCriterion("MERCH_CUST_ID <>", value, "merchCustId");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdGreaterThan(Long value) {
            addCriterion("MERCH_CUST_ID >", value, "merchCustId");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MERCH_CUST_ID >=", value, "merchCustId");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdLessThan(Long value) {
            addCriterion("MERCH_CUST_ID <", value, "merchCustId");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdLessThanOrEqualTo(Long value) {
            addCriterion("MERCH_CUST_ID <=", value, "merchCustId");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdIn(List<Long> values) {
            addCriterion("MERCH_CUST_ID in", values, "merchCustId");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdNotIn(List<Long> values) {
            addCriterion("MERCH_CUST_ID not in", values, "merchCustId");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdBetween(Long value1, Long value2) {
            addCriterion("MERCH_CUST_ID between", value1, value2, "merchCustId");
            return (Criteria) this;
        }

        public Criteria andMerchCustIdNotBetween(Long value1, Long value2) {
            addCriterion("MERCH_CUST_ID not between", value1, value2, "merchCustId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIsNull() {
            addCriterion("MATERIAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIsNotNull() {
            addCriterion("MATERIAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialIdEqualTo(String value) {
            addCriterion("MATERIAL_ID =", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotEqualTo(String value) {
            addCriterion("MATERIAL_ID <>", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdGreaterThan(String value) {
            addCriterion("MATERIAL_ID >", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdGreaterThanOrEqualTo(String value) {
            addCriterion("MATERIAL_ID >=", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLessThan(String value) {
            addCriterion("MATERIAL_ID <", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLessThanOrEqualTo(String value) {
            addCriterion("MATERIAL_ID <=", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdLike(String value) {
            addCriterion("MATERIAL_ID like", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotLike(String value) {
            addCriterion("MATERIAL_ID not like", value, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdIn(List<String> values) {
            addCriterion("MATERIAL_ID in", values, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotIn(List<String> values) {
            addCriterion("MATERIAL_ID not in", values, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdBetween(String value1, String value2) {
            addCriterion("MATERIAL_ID between", value1, value2, "materialId");
            return (Criteria) this;
        }

        public Criteria andMaterialIdNotBetween(String value1, String value2) {
            addCriterion("MATERIAL_ID not between", value1, value2, "materialId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNull() {
            addCriterion("ORGANIZATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNotNull() {
            addCriterion("ORGANIZATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdEqualTo(String value) {
            addCriterion("ORGANIZATION_ID =", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotEqualTo(String value) {
            addCriterion("ORGANIZATION_ID <>", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThan(String value) {
            addCriterion("ORGANIZATION_ID >", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORGANIZATION_ID >=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThan(String value) {
            addCriterion("ORGANIZATION_ID <", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThanOrEqualTo(String value) {
            addCriterion("ORGANIZATION_ID <=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLike(String value) {
            addCriterion("ORGANIZATION_ID like", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotLike(String value) {
            addCriterion("ORGANIZATION_ID not like", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIn(List<String> values) {
            addCriterion("ORGANIZATION_ID in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotIn(List<String> values) {
            addCriterion("ORGANIZATION_ID not in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdBetween(String value1, String value2) {
            addCriterion("ORGANIZATION_ID between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotBetween(String value1, String value2) {
            addCriterion("ORGANIZATION_ID not between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andContractIdIsNull() {
            addCriterion("CONTRACT_ID is null");
            return (Criteria) this;
        }

        public Criteria andContractIdIsNotNull() {
            addCriterion("CONTRACT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andContractIdEqualTo(Long value) {
            addCriterion("CONTRACT_ID =", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotEqualTo(Long value) {
            addCriterion("CONTRACT_ID <>", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdGreaterThan(Long value) {
            addCriterion("CONTRACT_ID >", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CONTRACT_ID >=", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLessThan(Long value) {
            addCriterion("CONTRACT_ID <", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLessThanOrEqualTo(Long value) {
            addCriterion("CONTRACT_ID <=", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdIn(List<Long> values) {
            addCriterion("CONTRACT_ID in", values, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotIn(List<Long> values) {
            addCriterion("CONTRACT_ID not in", values, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdBetween(Long value1, Long value2) {
            addCriterion("CONTRACT_ID between", value1, value2, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotBetween(Long value1, Long value2) {
            addCriterion("CONTRACT_ID not between", value1, value2, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractLineidIsNull() {
            addCriterion("CONTRACT_LINEID is null");
            return (Criteria) this;
        }

        public Criteria andContractLineidIsNotNull() {
            addCriterion("CONTRACT_LINEID is not null");
            return (Criteria) this;
        }

        public Criteria andContractLineidEqualTo(Long value) {
            addCriterion("CONTRACT_LINEID =", value, "contractLineid");
            return (Criteria) this;
        }

        public Criteria andContractLineidNotEqualTo(Long value) {
            addCriterion("CONTRACT_LINEID <>", value, "contractLineid");
            return (Criteria) this;
        }

        public Criteria andContractLineidGreaterThan(Long value) {
            addCriterion("CONTRACT_LINEID >", value, "contractLineid");
            return (Criteria) this;
        }

        public Criteria andContractLineidGreaterThanOrEqualTo(Long value) {
            addCriterion("CONTRACT_LINEID >=", value, "contractLineid");
            return (Criteria) this;
        }

        public Criteria andContractLineidLessThan(Long value) {
            addCriterion("CONTRACT_LINEID <", value, "contractLineid");
            return (Criteria) this;
        }

        public Criteria andContractLineidLessThanOrEqualTo(Long value) {
            addCriterion("CONTRACT_LINEID <=", value, "contractLineid");
            return (Criteria) this;
        }

        public Criteria andContractLineidIn(List<Long> values) {
            addCriterion("CONTRACT_LINEID in", values, "contractLineid");
            return (Criteria) this;
        }

        public Criteria andContractLineidNotIn(List<Long> values) {
            addCriterion("CONTRACT_LINEID not in", values, "contractLineid");
            return (Criteria) this;
        }

        public Criteria andContractLineidBetween(Long value1, Long value2) {
            addCriterion("CONTRACT_LINEID between", value1, value2, "contractLineid");
            return (Criteria) this;
        }

        public Criteria andContractLineidNotBetween(Long value1, Long value2) {
            addCriterion("CONTRACT_LINEID not between", value1, value2, "contractLineid");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("PRICE is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Long value) {
            addCriterion("PRICE =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Long value) {
            addCriterion("PRICE <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Long value) {
            addCriterion("PRICE >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("PRICE >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Long value) {
            addCriterion("PRICE <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Long value) {
            addCriterion("PRICE <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Long> values) {
            addCriterion("PRICE in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Long> values) {
            addCriterion("PRICE not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Long value1, Long value2) {
            addCriterion("PRICE between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Long value1, Long value2) {
            addCriterion("PRICE not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andCreateTsIsNull() {
            addCriterion("CREATE_TS is null");
            return (Criteria) this;
        }

        public Criteria andCreateTsIsNotNull() {
            addCriterion("CREATE_TS is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTsEqualTo(Date value) {
            addCriterion("CREATE_TS =", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsNotEqualTo(Date value) {
            addCriterion("CREATE_TS <>", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsGreaterThan(Date value) {
            addCriterion("CREATE_TS >", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TS >=", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsLessThan(Date value) {
            addCriterion("CREATE_TS <", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TS <=", value, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsIn(List<Date> values) {
            addCriterion("CREATE_TS in", values, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsNotIn(List<Date> values) {
            addCriterion("CREATE_TS not in", values, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsBetween(Date value1, Date value2) {
            addCriterion("CREATE_TS between", value1, value2, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateTsNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TS not between", value1, value2, "createTs");
            return (Criteria) this;
        }

        public Criteria andCreateOidIsNull() {
            addCriterion("CREATE_OID is null");
            return (Criteria) this;
        }

        public Criteria andCreateOidIsNotNull() {
            addCriterion("CREATE_OID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateOidEqualTo(Long value) {
            addCriterion("CREATE_OID =", value, "createOid");
            return (Criteria) this;
        }

        public Criteria andCreateOidNotEqualTo(Long value) {
            addCriterion("CREATE_OID <>", value, "createOid");
            return (Criteria) this;
        }

        public Criteria andCreateOidGreaterThan(Long value) {
            addCriterion("CREATE_OID >", value, "createOid");
            return (Criteria) this;
        }

        public Criteria andCreateOidGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_OID >=", value, "createOid");
            return (Criteria) this;
        }

        public Criteria andCreateOidLessThan(Long value) {
            addCriterion("CREATE_OID <", value, "createOid");
            return (Criteria) this;
        }

        public Criteria andCreateOidLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_OID <=", value, "createOid");
            return (Criteria) this;
        }

        public Criteria andCreateOidIn(List<Long> values) {
            addCriterion("CREATE_OID in", values, "createOid");
            return (Criteria) this;
        }

        public Criteria andCreateOidNotIn(List<Long> values) {
            addCriterion("CREATE_OID not in", values, "createOid");
            return (Criteria) this;
        }

        public Criteria andCreateOidBetween(Long value1, Long value2) {
            addCriterion("CREATE_OID between", value1, value2, "createOid");
            return (Criteria) this;
        }

        public Criteria andCreateOidNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_OID not between", value1, value2, "createOid");
            return (Criteria) this;
        }

        public Criteria andUpdateTsIsNull() {
            addCriterion("UPDATE_TS is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTsIsNotNull() {
            addCriterion("UPDATE_TS is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTsEqualTo(Date value) {
            addCriterion("UPDATE_TS =", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsNotEqualTo(Date value) {
            addCriterion("UPDATE_TS <>", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsGreaterThan(Date value) {
            addCriterion("UPDATE_TS >", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TS >=", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsLessThan(Date value) {
            addCriterion("UPDATE_TS <", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TS <=", value, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsIn(List<Date> values) {
            addCriterion("UPDATE_TS in", values, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsNotIn(List<Date> values) {
            addCriterion("UPDATE_TS not in", values, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TS between", value1, value2, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateTsNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TS not between", value1, value2, "updateTs");
            return (Criteria) this;
        }

        public Criteria andUpdateOidIsNull() {
            addCriterion("UPDATE_OID is null");
            return (Criteria) this;
        }

        public Criteria andUpdateOidIsNotNull() {
            addCriterion("UPDATE_OID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateOidEqualTo(Long value) {
            addCriterion("UPDATE_OID =", value, "updateOid");
            return (Criteria) this;
        }

        public Criteria andUpdateOidNotEqualTo(Long value) {
            addCriterion("UPDATE_OID <>", value, "updateOid");
            return (Criteria) this;
        }

        public Criteria andUpdateOidGreaterThan(Long value) {
            addCriterion("UPDATE_OID >", value, "updateOid");
            return (Criteria) this;
        }

        public Criteria andUpdateOidGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATE_OID >=", value, "updateOid");
            return (Criteria) this;
        }

        public Criteria andUpdateOidLessThan(Long value) {
            addCriterion("UPDATE_OID <", value, "updateOid");
            return (Criteria) this;
        }

        public Criteria andUpdateOidLessThanOrEqualTo(Long value) {
            addCriterion("UPDATE_OID <=", value, "updateOid");
            return (Criteria) this;
        }

        public Criteria andUpdateOidIn(List<Long> values) {
            addCriterion("UPDATE_OID in", values, "updateOid");
            return (Criteria) this;
        }

        public Criteria andUpdateOidNotIn(List<Long> values) {
            addCriterion("UPDATE_OID not in", values, "updateOid");
            return (Criteria) this;
        }

        public Criteria andUpdateOidBetween(Long value1, Long value2) {
            addCriterion("UPDATE_OID between", value1, value2, "updateOid");
            return (Criteria) this;
        }

        public Criteria andUpdateOidNotBetween(Long value1, Long value2) {
            addCriterion("UPDATE_OID not between", value1, value2, "updateOid");
            return (Criteria) this;
        }

        public Criteria andHPriceIsNull() {
            addCriterion("H_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andHPriceIsNotNull() {
            addCriterion("H_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andHPriceEqualTo(BigDecimal value) {
            addCriterion("H_PRICE =", value, "hPrice");
            return (Criteria) this;
        }

        public Criteria andHPriceNotEqualTo(BigDecimal value) {
            addCriterion("H_PRICE <>", value, "hPrice");
            return (Criteria) this;
        }

        public Criteria andHPriceGreaterThan(BigDecimal value) {
            addCriterion("H_PRICE >", value, "hPrice");
            return (Criteria) this;
        }

        public Criteria andHPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("H_PRICE >=", value, "hPrice");
            return (Criteria) this;
        }

        public Criteria andHPriceLessThan(BigDecimal value) {
            addCriterion("H_PRICE <", value, "hPrice");
            return (Criteria) this;
        }

        public Criteria andHPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("H_PRICE <=", value, "hPrice");
            return (Criteria) this;
        }

        public Criteria andHPriceIn(List<BigDecimal> values) {
            addCriterion("H_PRICE in", values, "hPrice");
            return (Criteria) this;
        }

        public Criteria andHPriceNotIn(List<BigDecimal> values) {
            addCriterion("H_PRICE not in", values, "hPrice");
            return (Criteria) this;
        }

        public Criteria andHPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("H_PRICE between", value1, value2, "hPrice");
            return (Criteria) this;
        }

        public Criteria andHPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("H_PRICE not between", value1, value2, "hPrice");
            return (Criteria) this;
        }

        public Criteria andStatesIsNull() {
            addCriterion("STATES is null");
            return (Criteria) this;
        }

        public Criteria andStatesIsNotNull() {
            addCriterion("STATES is not null");
            return (Criteria) this;
        }

        public Criteria andStatesEqualTo(String value) {
            addCriterion("STATES =", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesNotEqualTo(String value) {
            addCriterion("STATES <>", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesGreaterThan(String value) {
            addCriterion("STATES >", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesGreaterThanOrEqualTo(String value) {
            addCriterion("STATES >=", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesLessThan(String value) {
            addCriterion("STATES <", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesLessThanOrEqualTo(String value) {
            addCriterion("STATES <=", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesLike(String value) {
            addCriterion("STATES like", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesNotLike(String value) {
            addCriterion("STATES not like", value, "states");
            return (Criteria) this;
        }

        public Criteria andStatesIn(List<String> values) {
            addCriterion("STATES in", values, "states");
            return (Criteria) this;
        }

        public Criteria andStatesNotIn(List<String> values) {
            addCriterion("STATES not in", values, "states");
            return (Criteria) this;
        }

        public Criteria andStatesBetween(String value1, String value2) {
            addCriterion("STATES between", value1, value2, "states");
            return (Criteria) this;
        }

        public Criteria andStatesNotBetween(String value1, String value2) {
            addCriterion("STATES not between", value1, value2, "states");
            return (Criteria) this;
        }

        public Criteria andBDateIsNull() {
            addCriterion("B_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBDateIsNotNull() {
            addCriterion("B_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBDateEqualTo(Date value) {
            addCriterion("B_DATE =", value, "bDate");
            return (Criteria) this;
        }

        public Criteria andBDateNotEqualTo(Date value) {
            addCriterion("B_DATE <>", value, "bDate");
            return (Criteria) this;
        }

        public Criteria andBDateGreaterThan(Date value) {
            addCriterion("B_DATE >", value, "bDate");
            return (Criteria) this;
        }

        public Criteria andBDateGreaterThanOrEqualTo(Date value) {
            addCriterion("B_DATE >=", value, "bDate");
            return (Criteria) this;
        }

        public Criteria andBDateLessThan(Date value) {
            addCriterion("B_DATE <", value, "bDate");
            return (Criteria) this;
        }

        public Criteria andBDateLessThanOrEqualTo(Date value) {
            addCriterion("B_DATE <=", value, "bDate");
            return (Criteria) this;
        }

        public Criteria andBDateIn(List<Date> values) {
            addCriterion("B_DATE in", values, "bDate");
            return (Criteria) this;
        }

        public Criteria andBDateNotIn(List<Date> values) {
            addCriterion("B_DATE not in", values, "bDate");
            return (Criteria) this;
        }

        public Criteria andBDateBetween(Date value1, Date value2) {
            addCriterion("B_DATE between", value1, value2, "bDate");
            return (Criteria) this;
        }

        public Criteria andBDateNotBetween(Date value1, Date value2) {
            addCriterion("B_DATE not between", value1, value2, "bDate");
            return (Criteria) this;
        }

        public Criteria andEDateIsNull() {
            addCriterion("E_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEDateIsNotNull() {
            addCriterion("E_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEDateEqualTo(Date value) {
            addCriterion("E_DATE =", value, "eDate");
            return (Criteria) this;
        }

        public Criteria andEDateNotEqualTo(Date value) {
            addCriterion("E_DATE <>", value, "eDate");
            return (Criteria) this;
        }

        public Criteria andEDateGreaterThan(Date value) {
            addCriterion("E_DATE >", value, "eDate");
            return (Criteria) this;
        }

        public Criteria andEDateGreaterThanOrEqualTo(Date value) {
            addCriterion("E_DATE >=", value, "eDate");
            return (Criteria) this;
        }

        public Criteria andEDateLessThan(Date value) {
            addCriterion("E_DATE <", value, "eDate");
            return (Criteria) this;
        }

        public Criteria andEDateLessThanOrEqualTo(Date value) {
            addCriterion("E_DATE <=", value, "eDate");
            return (Criteria) this;
        }

        public Criteria andEDateIn(List<Date> values) {
            addCriterion("E_DATE in", values, "eDate");
            return (Criteria) this;
        }

        public Criteria andEDateNotIn(List<Date> values) {
            addCriterion("E_DATE not in", values, "eDate");
            return (Criteria) this;
        }

        public Criteria andEDateBetween(Date value1, Date value2) {
            addCriterion("E_DATE between", value1, value2, "eDate");
            return (Criteria) this;
        }

        public Criteria andEDateNotBetween(Date value1, Date value2) {
            addCriterion("E_DATE not between", value1, value2, "eDate");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdIsNull() {
            addCriterion("BASE_PRICE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdIsNotNull() {
            addCriterion("BASE_PRICE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdEqualTo(Long value) {
            addCriterion("BASE_PRICE_ID =", value, "basePriceId");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdNotEqualTo(Long value) {
            addCriterion("BASE_PRICE_ID <>", value, "basePriceId");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdGreaterThan(Long value) {
            addCriterion("BASE_PRICE_ID >", value, "basePriceId");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BASE_PRICE_ID >=", value, "basePriceId");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdLessThan(Long value) {
            addCriterion("BASE_PRICE_ID <", value, "basePriceId");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdLessThanOrEqualTo(Long value) {
            addCriterion("BASE_PRICE_ID <=", value, "basePriceId");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdIn(List<Long> values) {
            addCriterion("BASE_PRICE_ID in", values, "basePriceId");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdNotIn(List<Long> values) {
            addCriterion("BASE_PRICE_ID not in", values, "basePriceId");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdBetween(Long value1, Long value2) {
            addCriterion("BASE_PRICE_ID between", value1, value2, "basePriceId");
            return (Criteria) this;
        }

        public Criteria andBasePriceIdNotBetween(Long value1, Long value2) {
            addCriterion("BASE_PRICE_ID not between", value1, value2, "basePriceId");
            return (Criteria) this;
        }

        public Criteria andOldProductIdIsNull() {
            addCriterion("OLD_PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andOldProductIdIsNotNull() {
            addCriterion("OLD_PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOldProductIdEqualTo(Long value) {
            addCriterion("OLD_PRODUCT_ID =", value, "oldProductId");
            return (Criteria) this;
        }

        public Criteria andOldProductIdNotEqualTo(Long value) {
            addCriterion("OLD_PRODUCT_ID <>", value, "oldProductId");
            return (Criteria) this;
        }

        public Criteria andOldProductIdGreaterThan(Long value) {
            addCriterion("OLD_PRODUCT_ID >", value, "oldProductId");
            return (Criteria) this;
        }

        public Criteria andOldProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OLD_PRODUCT_ID >=", value, "oldProductId");
            return (Criteria) this;
        }

        public Criteria andOldProductIdLessThan(Long value) {
            addCriterion("OLD_PRODUCT_ID <", value, "oldProductId");
            return (Criteria) this;
        }

        public Criteria andOldProductIdLessThanOrEqualTo(Long value) {
            addCriterion("OLD_PRODUCT_ID <=", value, "oldProductId");
            return (Criteria) this;
        }

        public Criteria andOldProductIdIn(List<Long> values) {
            addCriterion("OLD_PRODUCT_ID in", values, "oldProductId");
            return (Criteria) this;
        }

        public Criteria andOldProductIdNotIn(List<Long> values) {
            addCriterion("OLD_PRODUCT_ID not in", values, "oldProductId");
            return (Criteria) this;
        }

        public Criteria andOldProductIdBetween(Long value1, Long value2) {
            addCriterion("OLD_PRODUCT_ID between", value1, value2, "oldProductId");
            return (Criteria) this;
        }

        public Criteria andOldProductIdNotBetween(Long value1, Long value2) {
            addCriterion("OLD_PRODUCT_ID not between", value1, value2, "oldProductId");
            return (Criteria) this;
        }

        public Criteria andCifPriceIsNull() {
            addCriterion("CIF_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andCifPriceIsNotNull() {
            addCriterion("CIF_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andCifPriceEqualTo(BigDecimal value) {
            addCriterion("CIF_PRICE =", value, "cifPrice");
            return (Criteria) this;
        }

        public Criteria andCifPriceNotEqualTo(BigDecimal value) {
            addCriterion("CIF_PRICE <>", value, "cifPrice");
            return (Criteria) this;
        }

        public Criteria andCifPriceGreaterThan(BigDecimal value) {
            addCriterion("CIF_PRICE >", value, "cifPrice");
            return (Criteria) this;
        }

        public Criteria andCifPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CIF_PRICE >=", value, "cifPrice");
            return (Criteria) this;
        }

        public Criteria andCifPriceLessThan(BigDecimal value) {
            addCriterion("CIF_PRICE <", value, "cifPrice");
            return (Criteria) this;
        }

        public Criteria andCifPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CIF_PRICE <=", value, "cifPrice");
            return (Criteria) this;
        }

        public Criteria andCifPriceIn(List<BigDecimal> values) {
            addCriterion("CIF_PRICE in", values, "cifPrice");
            return (Criteria) this;
        }

        public Criteria andCifPriceNotIn(List<BigDecimal> values) {
            addCriterion("CIF_PRICE not in", values, "cifPrice");
            return (Criteria) this;
        }

        public Criteria andCifPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CIF_PRICE between", value1, value2, "cifPrice");
            return (Criteria) this;
        }

        public Criteria andCifPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CIF_PRICE not between", value1, value2, "cifPrice");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
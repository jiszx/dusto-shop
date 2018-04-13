package com.hhnz.customerInv.model;

import com.hhnz.util.db.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CMerchCustProudctInvExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public CMerchCustProudctInvExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
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

        public Criteria andInvNumIsNull() {
            addCriterion("INV_NUM is null");
            return (Criteria) this;
        }

        public Criteria andInvNumIsNotNull() {
            addCriterion("INV_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andInvNumEqualTo(BigDecimal value) {
            addCriterion("INV_NUM =", value, "invNum");
            return (Criteria) this;
        }

        public Criteria andInvNumNotEqualTo(BigDecimal value) {
            addCriterion("INV_NUM <>", value, "invNum");
            return (Criteria) this;
        }

        public Criteria andInvNumGreaterThan(BigDecimal value) {
            addCriterion("INV_NUM >", value, "invNum");
            return (Criteria) this;
        }

        public Criteria andInvNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("INV_NUM >=", value, "invNum");
            return (Criteria) this;
        }

        public Criteria andInvNumLessThan(BigDecimal value) {
            addCriterion("INV_NUM <", value, "invNum");
            return (Criteria) this;
        }

        public Criteria andInvNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("INV_NUM <=", value, "invNum");
            return (Criteria) this;
        }

        public Criteria andInvNumIn(List<BigDecimal> values) {
            addCriterion("INV_NUM in", values, "invNum");
            return (Criteria) this;
        }

        public Criteria andInvNumNotIn(List<BigDecimal> values) {
            addCriterion("INV_NUM not in", values, "invNum");
            return (Criteria) this;
        }

        public Criteria andInvNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INV_NUM between", value1, value2, "invNum");
            return (Criteria) this;
        }

        public Criteria andInvNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INV_NUM not between", value1, value2, "invNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumIsNull() {
            addCriterion("FROZEN_NUM is null");
            return (Criteria) this;
        }

        public Criteria andFrozenNumIsNotNull() {
            addCriterion("FROZEN_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenNumEqualTo(BigDecimal value) {
            addCriterion("FROZEN_NUM =", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumNotEqualTo(BigDecimal value) {
            addCriterion("FROZEN_NUM <>", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumGreaterThan(BigDecimal value) {
            addCriterion("FROZEN_NUM >", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FROZEN_NUM >=", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumLessThan(BigDecimal value) {
            addCriterion("FROZEN_NUM <", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FROZEN_NUM <=", value, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumIn(List<BigDecimal> values) {
            addCriterion("FROZEN_NUM in", values, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumNotIn(List<BigDecimal> values) {
            addCriterion("FROZEN_NUM not in", values, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FROZEN_NUM between", value1, value2, "frozenNum");
            return (Criteria) this;
        }

        public Criteria andFrozenNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FROZEN_NUM not between", value1, value2, "frozenNum");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated do_not_delete_during_merge Wed Dec 07 10:09:43 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_CUST_PROUDCT_INV
     *
     * @mbggenerated Wed Dec 07 10:09:43 CST 2016
     */
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
package com.hhnz.account.model;

import com.hhnz.util.db.Page;
import java.util.ArrayList;
import java.util.List;

public class CMerchCustAccountExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public CMerchCustAccountExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
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
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
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

        public Criteria andCashAmtIsNull() {
            addCriterion("CASH_AMT is null");
            return (Criteria) this;
        }

        public Criteria andCashAmtIsNotNull() {
            addCriterion("CASH_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andCashAmtEqualTo(Long value) {
            addCriterion("CASH_AMT =", value, "cashAmt");
            return (Criteria) this;
        }

        public Criteria andCashAmtNotEqualTo(Long value) {
            addCriterion("CASH_AMT <>", value, "cashAmt");
            return (Criteria) this;
        }

        public Criteria andCashAmtGreaterThan(Long value) {
            addCriterion("CASH_AMT >", value, "cashAmt");
            return (Criteria) this;
        }

        public Criteria andCashAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("CASH_AMT >=", value, "cashAmt");
            return (Criteria) this;
        }

        public Criteria andCashAmtLessThan(Long value) {
            addCriterion("CASH_AMT <", value, "cashAmt");
            return (Criteria) this;
        }

        public Criteria andCashAmtLessThanOrEqualTo(Long value) {
            addCriterion("CASH_AMT <=", value, "cashAmt");
            return (Criteria) this;
        }

        public Criteria andCashAmtIn(List<Long> values) {
            addCriterion("CASH_AMT in", values, "cashAmt");
            return (Criteria) this;
        }

        public Criteria andCashAmtNotIn(List<Long> values) {
            addCriterion("CASH_AMT not in", values, "cashAmt");
            return (Criteria) this;
        }

        public Criteria andCashAmtBetween(Long value1, Long value2) {
            addCriterion("CASH_AMT between", value1, value2, "cashAmt");
            return (Criteria) this;
        }

        public Criteria andCashAmtNotBetween(Long value1, Long value2) {
            addCriterion("CASH_AMT not between", value1, value2, "cashAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtIsNull() {
            addCriterion("CREDIT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andCreditAmtIsNotNull() {
            addCriterion("CREDIT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andCreditAmtEqualTo(Long value) {
            addCriterion("CREDIT_AMT =", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtNotEqualTo(Long value) {
            addCriterion("CREDIT_AMT <>", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtGreaterThan(Long value) {
            addCriterion("CREDIT_AMT >", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("CREDIT_AMT >=", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtLessThan(Long value) {
            addCriterion("CREDIT_AMT <", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtLessThanOrEqualTo(Long value) {
            addCriterion("CREDIT_AMT <=", value, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtIn(List<Long> values) {
            addCriterion("CREDIT_AMT in", values, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtNotIn(List<Long> values) {
            addCriterion("CREDIT_AMT not in", values, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtBetween(Long value1, Long value2) {
            addCriterion("CREDIT_AMT between", value1, value2, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andCreditAmtNotBetween(Long value1, Long value2) {
            addCriterion("CREDIT_AMT not between", value1, value2, "creditAmt");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtIsNull() {
            addCriterion("SUBSIDY_AMT is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtIsNotNull() {
            addCriterion("SUBSIDY_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtEqualTo(Long value) {
            addCriterion("SUBSIDY_AMT =", value, "subsidyAmt");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtNotEqualTo(Long value) {
            addCriterion("SUBSIDY_AMT <>", value, "subsidyAmt");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtGreaterThan(Long value) {
            addCriterion("SUBSIDY_AMT >", value, "subsidyAmt");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("SUBSIDY_AMT >=", value, "subsidyAmt");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtLessThan(Long value) {
            addCriterion("SUBSIDY_AMT <", value, "subsidyAmt");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtLessThanOrEqualTo(Long value) {
            addCriterion("SUBSIDY_AMT <=", value, "subsidyAmt");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtIn(List<Long> values) {
            addCriterion("SUBSIDY_AMT in", values, "subsidyAmt");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtNotIn(List<Long> values) {
            addCriterion("SUBSIDY_AMT not in", values, "subsidyAmt");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtBetween(Long value1, Long value2) {
            addCriterion("SUBSIDY_AMT between", value1, value2, "subsidyAmt");
            return (Criteria) this;
        }

        public Criteria andSubsidyAmtNotBetween(Long value1, Long value2) {
            addCriterion("SUBSIDY_AMT not between", value1, value2, "subsidyAmt");
            return (Criteria) this;
        }

        public Criteria andBondAmtIsNull() {
            addCriterion("BOND_AMT is null");
            return (Criteria) this;
        }

        public Criteria andBondAmtIsNotNull() {
            addCriterion("BOND_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andBondAmtEqualTo(Long value) {
            addCriterion("BOND_AMT =", value, "bondAmt");
            return (Criteria) this;
        }

        public Criteria andBondAmtNotEqualTo(Long value) {
            addCriterion("BOND_AMT <>", value, "bondAmt");
            return (Criteria) this;
        }

        public Criteria andBondAmtGreaterThan(Long value) {
            addCriterion("BOND_AMT >", value, "bondAmt");
            return (Criteria) this;
        }

        public Criteria andBondAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("BOND_AMT >=", value, "bondAmt");
            return (Criteria) this;
        }

        public Criteria andBondAmtLessThan(Long value) {
            addCriterion("BOND_AMT <", value, "bondAmt");
            return (Criteria) this;
        }

        public Criteria andBondAmtLessThanOrEqualTo(Long value) {
            addCriterion("BOND_AMT <=", value, "bondAmt");
            return (Criteria) this;
        }

        public Criteria andBondAmtIn(List<Long> values) {
            addCriterion("BOND_AMT in", values, "bondAmt");
            return (Criteria) this;
        }

        public Criteria andBondAmtNotIn(List<Long> values) {
            addCriterion("BOND_AMT not in", values, "bondAmt");
            return (Criteria) this;
        }

        public Criteria andBondAmtBetween(Long value1, Long value2) {
            addCriterion("BOND_AMT between", value1, value2, "bondAmt");
            return (Criteria) this;
        }

        public Criteria andBondAmtNotBetween(Long value1, Long value2) {
            addCriterion("BOND_AMT not between", value1, value2, "bondAmt");
            return (Criteria) this;
        }

        public Criteria andAttribute1IsNull() {
            addCriterion("ATTRIBUTE1 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute1IsNotNull() {
            addCriterion("ATTRIBUTE1 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute1EqualTo(String value) {
            addCriterion("ATTRIBUTE1 =", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotEqualTo(String value) {
            addCriterion("ATTRIBUTE1 <>", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1GreaterThan(String value) {
            addCriterion("ATTRIBUTE1 >", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1GreaterThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE1 >=", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1LessThan(String value) {
            addCriterion("ATTRIBUTE1 <", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1LessThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE1 <=", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1Like(String value) {
            addCriterion("ATTRIBUTE1 like", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotLike(String value) {
            addCriterion("ATTRIBUTE1 not like", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1In(List<String> values) {
            addCriterion("ATTRIBUTE1 in", values, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotIn(List<String> values) {
            addCriterion("ATTRIBUTE1 not in", values, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1Between(String value1, String value2) {
            addCriterion("ATTRIBUTE1 between", value1, value2, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotBetween(String value1, String value2) {
            addCriterion("ATTRIBUTE1 not between", value1, value2, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute2IsNull() {
            addCriterion("ATTRIBUTE2 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute2IsNotNull() {
            addCriterion("ATTRIBUTE2 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute2EqualTo(String value) {
            addCriterion("ATTRIBUTE2 =", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotEqualTo(String value) {
            addCriterion("ATTRIBUTE2 <>", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2GreaterThan(String value) {
            addCriterion("ATTRIBUTE2 >", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2GreaterThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE2 >=", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2LessThan(String value) {
            addCriterion("ATTRIBUTE2 <", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2LessThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE2 <=", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2Like(String value) {
            addCriterion("ATTRIBUTE2 like", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotLike(String value) {
            addCriterion("ATTRIBUTE2 not like", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2In(List<String> values) {
            addCriterion("ATTRIBUTE2 in", values, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotIn(List<String> values) {
            addCriterion("ATTRIBUTE2 not in", values, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2Between(String value1, String value2) {
            addCriterion("ATTRIBUTE2 between", value1, value2, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotBetween(String value1, String value2) {
            addCriterion("ATTRIBUTE2 not between", value1, value2, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute3IsNull() {
            addCriterion("ATTRIBUTE3 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute3IsNotNull() {
            addCriterion("ATTRIBUTE3 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute3EqualTo(String value) {
            addCriterion("ATTRIBUTE3 =", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotEqualTo(String value) {
            addCriterion("ATTRIBUTE3 <>", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3GreaterThan(String value) {
            addCriterion("ATTRIBUTE3 >", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3GreaterThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE3 >=", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3LessThan(String value) {
            addCriterion("ATTRIBUTE3 <", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3LessThanOrEqualTo(String value) {
            addCriterion("ATTRIBUTE3 <=", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3Like(String value) {
            addCriterion("ATTRIBUTE3 like", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotLike(String value) {
            addCriterion("ATTRIBUTE3 not like", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3In(List<String> values) {
            addCriterion("ATTRIBUTE3 in", values, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotIn(List<String> values) {
            addCriterion("ATTRIBUTE3 not in", values, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3Between(String value1, String value2) {
            addCriterion("ATTRIBUTE3 between", value1, value2, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotBetween(String value1, String value2) {
            addCriterion("ATTRIBUTE3 not between", value1, value2, "attribute3");
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

        public Criteria andContractCreditAmtIsNull() {
            addCriterion("CONTRACT_CREDIT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtIsNotNull() {
            addCriterion("CONTRACT_CREDIT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtEqualTo(Long value) {
            addCriterion("CONTRACT_CREDIT_AMT =", value, "contractCreditAmt");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtNotEqualTo(Long value) {
            addCriterion("CONTRACT_CREDIT_AMT <>", value, "contractCreditAmt");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtGreaterThan(Long value) {
            addCriterion("CONTRACT_CREDIT_AMT >", value, "contractCreditAmt");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("CONTRACT_CREDIT_AMT >=", value, "contractCreditAmt");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtLessThan(Long value) {
            addCriterion("CONTRACT_CREDIT_AMT <", value, "contractCreditAmt");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtLessThanOrEqualTo(Long value) {
            addCriterion("CONTRACT_CREDIT_AMT <=", value, "contractCreditAmt");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtIn(List<Long> values) {
            addCriterion("CONTRACT_CREDIT_AMT in", values, "contractCreditAmt");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtNotIn(List<Long> values) {
            addCriterion("CONTRACT_CREDIT_AMT not in", values, "contractCreditAmt");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtBetween(Long value1, Long value2) {
            addCriterion("CONTRACT_CREDIT_AMT between", value1, value2, "contractCreditAmt");
            return (Criteria) this;
        }

        public Criteria andContractCreditAmtNotBetween(Long value1, Long value2) {
            addCriterion("CONTRACT_CREDIT_AMT not between", value1, value2, "contractCreditAmt");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated do_not_delete_during_merge Mon Oct 24 14:39:16 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_CUST_ACCOUNT
     *
     * @mbggenerated Mon Oct 24 14:39:16 CST 2016
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
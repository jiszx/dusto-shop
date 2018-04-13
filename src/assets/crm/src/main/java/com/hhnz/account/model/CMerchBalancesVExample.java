package com.hhnz.account.model;

import com.hhnz.util.db.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CMerchBalancesVExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public CMerchBalancesVExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
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
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
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

        public Criteria andCustnameIsNull() {
            addCriterion("CUSTNAME is null");
            return (Criteria) this;
        }

        public Criteria andCustnameIsNotNull() {
            addCriterion("CUSTNAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustnameEqualTo(String value) {
            addCriterion("CUSTNAME =", value, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameNotEqualTo(String value) {
            addCriterion("CUSTNAME <>", value, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameGreaterThan(String value) {
            addCriterion("CUSTNAME >", value, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTNAME >=", value, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameLessThan(String value) {
            addCriterion("CUSTNAME <", value, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameLessThanOrEqualTo(String value) {
            addCriterion("CUSTNAME <=", value, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameLike(String value) {
            addCriterion("CUSTNAME like", value, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameNotLike(String value) {
            addCriterion("CUSTNAME not like", value, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameIn(List<String> values) {
            addCriterion("CUSTNAME in", values, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameNotIn(List<String> values) {
            addCriterion("CUSTNAME not in", values, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameBetween(String value1, String value2) {
            addCriterion("CUSTNAME between", value1, value2, "custname");
            return (Criteria) this;
        }

        public Criteria andCustnameNotBetween(String value1, String value2) {
            addCriterion("CUSTNAME not between", value1, value2, "custname");
            return (Criteria) this;
        }

        public Criteria andOrgnameIsNull() {
            addCriterion("ORGNAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgnameIsNotNull() {
            addCriterion("ORGNAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgnameEqualTo(String value) {
            addCriterion("ORGNAME =", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotEqualTo(String value) {
            addCriterion("ORGNAME <>", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameGreaterThan(String value) {
            addCriterion("ORGNAME >", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameGreaterThanOrEqualTo(String value) {
            addCriterion("ORGNAME >=", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLessThan(String value) {
            addCriterion("ORGNAME <", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLessThanOrEqualTo(String value) {
            addCriterion("ORGNAME <=", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameLike(String value) {
            addCriterion("ORGNAME like", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotLike(String value) {
            addCriterion("ORGNAME not like", value, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameIn(List<String> values) {
            addCriterion("ORGNAME in", values, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotIn(List<String> values) {
            addCriterion("ORGNAME not in", values, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameBetween(String value1, String value2) {
            addCriterion("ORGNAME between", value1, value2, "orgname");
            return (Criteria) this;
        }

        public Criteria andOrgnameNotBetween(String value1, String value2) {
            addCriterion("ORGNAME not between", value1, value2, "orgname");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdIsNull() {
            addCriterion("SAP_CUSTOMER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdIsNotNull() {
            addCriterion("SAP_CUSTOMER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdEqualTo(String value) {
            addCriterion("SAP_CUSTOMER_ID =", value, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdNotEqualTo(String value) {
            addCriterion("SAP_CUSTOMER_ID <>", value, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdGreaterThan(String value) {
            addCriterion("SAP_CUSTOMER_ID >", value, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("SAP_CUSTOMER_ID >=", value, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdLessThan(String value) {
            addCriterion("SAP_CUSTOMER_ID <", value, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("SAP_CUSTOMER_ID <=", value, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdLike(String value) {
            addCriterion("SAP_CUSTOMER_ID like", value, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdNotLike(String value) {
            addCriterion("SAP_CUSTOMER_ID not like", value, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdIn(List<String> values) {
            addCriterion("SAP_CUSTOMER_ID in", values, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdNotIn(List<String> values) {
            addCriterion("SAP_CUSTOMER_ID not in", values, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdBetween(String value1, String value2) {
            addCriterion("SAP_CUSTOMER_ID between", value1, value2, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andSapCustomerIdNotBetween(String value1, String value2) {
            addCriterion("SAP_CUSTOMER_ID not between", value1, value2, "sapCustomerId");
            return (Criteria) this;
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(BigDecimal value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigDecimal value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigDecimal value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigDecimal value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigDecimal> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigDecimal> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID not between", value1, value2, "id");
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
        public Criteria andMerchPidEqualTo(Long value) {
            addCriterion("MERCH_PID =", value, "merchPid");
            return (Criteria) this;
        }
        public Criteria andAccountTypeIsNull() {
            addCriterion("ACCOUNT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("ACCOUNT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(String value) {
            addCriterion("ACCOUNT_TYPE >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(String value) {
            addCriterion("ACCOUNT_TYPE <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_TYPE <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLike(String value) {
            addCriterion("ACCOUNT_TYPE like", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotLike(String value) {
            addCriterion("ACCOUNT_TYPE not like", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<String> values) {
            addCriterion("ACCOUNT_TYPE in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<String> values) {
            addCriterion("ACCOUNT_TYPE not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(String value1, String value2) {
            addCriterion("ACCOUNT_TYPE between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_TYPE not between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andYtdIsNull() {
            addCriterion("YTD is null");
            return (Criteria) this;
        }

        public Criteria andYtdIsNotNull() {
            addCriterion("YTD is not null");
            return (Criteria) this;
        }

        public Criteria andYtdEqualTo(Long value) {
            addCriterion("YTD =", value, "ytd");
            return (Criteria) this;
        }

        public Criteria andYtdNotEqualTo(Long value) {
            addCriterion("YTD <>", value, "ytd");
            return (Criteria) this;
        }

        public Criteria andYtdGreaterThan(Long value) {
            addCriterion("YTD >", value, "ytd");
            return (Criteria) this;
        }

        public Criteria andYtdGreaterThanOrEqualTo(Long value) {
            addCriterion("YTD >=", value, "ytd");
            return (Criteria) this;
        }

        public Criteria andYtdLessThan(Long value) {
            addCriterion("YTD <", value, "ytd");
            return (Criteria) this;
        }

        public Criteria andYtdLessThanOrEqualTo(Long value) {
            addCriterion("YTD <=", value, "ytd");
            return (Criteria) this;
        }

        public Criteria andYtdIn(List<Long> values) {
            addCriterion("YTD in", values, "ytd");
            return (Criteria) this;
        }

        public Criteria andYtdNotIn(List<Long> values) {
            addCriterion("YTD not in", values, "ytd");
            return (Criteria) this;
        }

        public Criteria andYtdBetween(Long value1, Long value2) {
            addCriterion("YTD between", value1, value2, "ytd");
            return (Criteria) this;
        }

        public Criteria andYtdNotBetween(Long value1, Long value2) {
            addCriterion("YTD not between", value1, value2, "ytd");
            return (Criteria) this;
        }

        public Criteria andDAmtIsNull() {
            addCriterion("D_AMT is null");
            return (Criteria) this;
        }

        public Criteria andDAmtIsNotNull() {
            addCriterion("D_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andDAmtEqualTo(Long value) {
            addCriterion("D_AMT =", value, "dAmt");
            return (Criteria) this;
        }

        public Criteria andDAmtNotEqualTo(Long value) {
            addCriterion("D_AMT <>", value, "dAmt");
            return (Criteria) this;
        }

        public Criteria andDAmtGreaterThan(Long value) {
            addCriterion("D_AMT >", value, "dAmt");
            return (Criteria) this;
        }

        public Criteria andDAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("D_AMT >=", value, "dAmt");
            return (Criteria) this;
        }

        public Criteria andDAmtLessThan(Long value) {
            addCriterion("D_AMT <", value, "dAmt");
            return (Criteria) this;
        }

        public Criteria andDAmtLessThanOrEqualTo(Long value) {
            addCriterion("D_AMT <=", value, "dAmt");
            return (Criteria) this;
        }

        public Criteria andDAmtIn(List<Long> values) {
            addCriterion("D_AMT in", values, "dAmt");
            return (Criteria) this;
        }

        public Criteria andDAmtNotIn(List<Long> values) {
            addCriterion("D_AMT not in", values, "dAmt");
            return (Criteria) this;
        }

        public Criteria andDAmtBetween(Long value1, Long value2) {
            addCriterion("D_AMT between", value1, value2, "dAmt");
            return (Criteria) this;
        }

        public Criteria andDAmtNotBetween(Long value1, Long value2) {
            addCriterion("D_AMT not between", value1, value2, "dAmt");
            return (Criteria) this;
        }

        public Criteria andCAmtIsNull() {
            addCriterion("C_AMT is null");
            return (Criteria) this;
        }

        public Criteria andCAmtIsNotNull() {
            addCriterion("C_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andCAmtEqualTo(Long value) {
            addCriterion("C_AMT =", value, "cAmt");
            return (Criteria) this;
        }

        public Criteria andCAmtNotEqualTo(Long value) {
            addCriterion("C_AMT <>", value, "cAmt");
            return (Criteria) this;
        }

        public Criteria andCAmtGreaterThan(Long value) {
            addCriterion("C_AMT >", value, "cAmt");
            return (Criteria) this;
        }

        public Criteria andCAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("C_AMT >=", value, "cAmt");
            return (Criteria) this;
        }

        public Criteria andCAmtLessThan(Long value) {
            addCriterion("C_AMT <", value, "cAmt");
            return (Criteria) this;
        }

        public Criteria andCAmtLessThanOrEqualTo(Long value) {
            addCriterion("C_AMT <=", value, "cAmt");
            return (Criteria) this;
        }

        public Criteria andCAmtIn(List<Long> values) {
            addCriterion("C_AMT in", values, "cAmt");
            return (Criteria) this;
        }

        public Criteria andCAmtNotIn(List<Long> values) {
            addCriterion("C_AMT not in", values, "cAmt");
            return (Criteria) this;
        }

        public Criteria andCAmtBetween(Long value1, Long value2) {
            addCriterion("C_AMT between", value1, value2, "cAmt");
            return (Criteria) this;
        }

        public Criteria andCAmtNotBetween(Long value1, Long value2) {
            addCriterion("C_AMT not between", value1, value2, "cAmt");
            return (Criteria) this;
        }

        public Criteria andPtdIsNull() {
            addCriterion("PTD is null");
            return (Criteria) this;
        }

        public Criteria andPtdIsNotNull() {
            addCriterion("PTD is not null");
            return (Criteria) this;
        }

        public Criteria andPtdEqualTo(Short value) {
            addCriterion("PTD =", value, "ptd");
            return (Criteria) this;
        }

        public Criteria andPtdNotEqualTo(Short value) {
            addCriterion("PTD <>", value, "ptd");
            return (Criteria) this;
        }

        public Criteria andPtdGreaterThan(Short value) {
            addCriterion("PTD >", value, "ptd");
            return (Criteria) this;
        }

        public Criteria andPtdGreaterThanOrEqualTo(Short value) {
            addCriterion("PTD >=", value, "ptd");
            return (Criteria) this;
        }

        public Criteria andPtdLessThan(Short value) {
            addCriterion("PTD <", value, "ptd");
            return (Criteria) this;
        }

        public Criteria andPtdLessThanOrEqualTo(Short value) {
            addCriterion("PTD <=", value, "ptd");
            return (Criteria) this;
        }

        public Criteria andPtdIn(List<Short> values) {
            addCriterion("PTD in", values, "ptd");
            return (Criteria) this;
        }

        public Criteria andPtdNotIn(List<Short> values) {
            addCriterion("PTD not in", values, "ptd");
            return (Criteria) this;
        }

        public Criteria andPtdBetween(Short value1, Short value2) {
            addCriterion("PTD between", value1, value2, "ptd");
            return (Criteria) this;
        }

        public Criteria andPtdNotBetween(Short value1, Short value2) {
            addCriterion("PTD not between", value1, value2, "ptd");
            return (Criteria) this;
        }

        public Criteria andStationIdIsNull() {
            addCriterion("STATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andStationIdIsNotNull() {
            addCriterion("STATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStationIdEqualTo(Long value) {
            addCriterion("STATION_ID =", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdNotEqualTo(Long value) {
            addCriterion("STATION_ID <>", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdGreaterThan(Long value) {
            addCriterion("STATION_ID >", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STATION_ID >=", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdLessThan(Long value) {
            addCriterion("STATION_ID <", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdLessThanOrEqualTo(Long value) {
            addCriterion("STATION_ID <=", value, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdIn(List<Long> values) {
            addCriterion("STATION_ID in", values, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdNotIn(List<Long> values) {
            addCriterion("STATION_ID not in", values, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdBetween(Long value1, Long value2) {
            addCriterion("STATION_ID between", value1, value2, "stationId");
            return (Criteria) this;
        }

        public Criteria andStationIdNotBetween(Long value1, Long value2) {
            addCriterion("STATION_ID not between", value1, value2, "stationId");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("PERIOD is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("PERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(String value) {
            addCriterion("PERIOD =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(String value) {
            addCriterion("PERIOD <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(String value) {
            addCriterion("PERIOD >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("PERIOD >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(String value) {
            addCriterion("PERIOD <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(String value) {
            addCriterion("PERIOD <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLike(String value) {
            addCriterion("PERIOD like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotLike(String value) {
            addCriterion("PERIOD not like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<String> values) {
            addCriterion("PERIOD in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<String> values) {
            addCriterion("PERIOD not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(String value1, String value2) {
            addCriterion("PERIOD between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(String value1, String value2) {
            addCriterion("PERIOD not between", value1, value2, "period");
            return (Criteria) this;
        }
        public Criteria andCustTypeEqualTo(String value){
        	addCriterion("Cust_type =", value,"custType"); 
        	return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated do_not_delete_during_merge Tue Sep 27 10:13:37 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_BALANCES_V
     *
     * @mbggenerated Tue Sep 27 10:13:37 CST 2016
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
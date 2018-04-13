package com.hhnz.account.model;

import com.hhnz.util.db.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CMerchReceiveVerifieExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public CMerchReceiveVerifieExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
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
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
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

        public Criteria andVerifiedIdIsNull() {
            addCriterion("VERIFIED_ID is null");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdIsNotNull() {
            addCriterion("VERIFIED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdEqualTo(Long value) {
            addCriterion("VERIFIED_ID =", value, "verifiedId");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdNotEqualTo(Long value) {
            addCriterion("VERIFIED_ID <>", value, "verifiedId");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdGreaterThan(Long value) {
            addCriterion("VERIFIED_ID >", value, "verifiedId");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdGreaterThanOrEqualTo(Long value) {
            addCriterion("VERIFIED_ID >=", value, "verifiedId");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdLessThan(Long value) {
            addCriterion("VERIFIED_ID <", value, "verifiedId");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdLessThanOrEqualTo(Long value) {
            addCriterion("VERIFIED_ID <=", value, "verifiedId");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdIn(List<Long> values) {
            addCriterion("VERIFIED_ID in", values, "verifiedId");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdNotIn(List<Long> values) {
            addCriterion("VERIFIED_ID not in", values, "verifiedId");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdBetween(Long value1, Long value2) {
            addCriterion("VERIFIED_ID between", value1, value2, "verifiedId");
            return (Criteria) this;
        }

        public Criteria andVerifiedIdNotBetween(Long value1, Long value2) {
            addCriterion("VERIFIED_ID not between", value1, value2, "verifiedId");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNull() {
            addCriterion("INVOICE_NO is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNotNull() {
            addCriterion("INVOICE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoEqualTo(String value) {
            addCriterion("INVOICE_NO =", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotEqualTo(String value) {
            addCriterion("INVOICE_NO <>", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThan(String value) {
            addCriterion("INVOICE_NO >", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_NO >=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThan(String value) {
            addCriterion("INVOICE_NO <", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_NO <=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLike(String value) {
            addCriterion("INVOICE_NO like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotLike(String value) {
            addCriterion("INVOICE_NO not like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIn(List<String> values) {
            addCriterion("INVOICE_NO in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotIn(List<String> values) {
            addCriterion("INVOICE_NO not in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoBetween(String value1, String value2) {
            addCriterion("INVOICE_NO between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotBetween(String value1, String value2) {
            addCriterion("INVOICE_NO not between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtIsNull() {
            addCriterion("VERIFIE_AMT is null");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtIsNotNull() {
            addCriterion("VERIFIE_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtEqualTo(Long value) {
            addCriterion("VERIFIE_AMT =", value, "verifieAmt");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtNotEqualTo(Long value) {
            addCriterion("VERIFIE_AMT <>", value, "verifieAmt");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtGreaterThan(Long value) {
            addCriterion("VERIFIE_AMT >", value, "verifieAmt");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("VERIFIE_AMT >=", value, "verifieAmt");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtLessThan(Long value) {
            addCriterion("VERIFIE_AMT <", value, "verifieAmt");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtLessThanOrEqualTo(Long value) {
            addCriterion("VERIFIE_AMT <=", value, "verifieAmt");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtIn(List<Long> values) {
            addCriterion("VERIFIE_AMT in", values, "verifieAmt");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtNotIn(List<Long> values) {
            addCriterion("VERIFIE_AMT not in", values, "verifieAmt");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtBetween(Long value1, Long value2) {
            addCriterion("VERIFIE_AMT between", value1, value2, "verifieAmt");
            return (Criteria) this;
        }

        public Criteria andVerifieAmtNotBetween(Long value1, Long value2) {
            addCriterion("VERIFIE_AMT not between", value1, value2, "verifieAmt");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIsNull() {
            addCriterion("INVOICE_ID is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIsNotNull() {
            addCriterion("INVOICE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdEqualTo(Long value) {
            addCriterion("INVOICE_ID =", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotEqualTo(Long value) {
            addCriterion("INVOICE_ID <>", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdGreaterThan(Long value) {
            addCriterion("INVOICE_ID >", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("INVOICE_ID >=", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdLessThan(Long value) {
            addCriterion("INVOICE_ID <", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdLessThanOrEqualTo(Long value) {
            addCriterion("INVOICE_ID <=", value, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdIn(List<Long> values) {
            addCriterion("INVOICE_ID in", values, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotIn(List<Long> values) {
            addCriterion("INVOICE_ID not in", values, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdBetween(Long value1, Long value2) {
            addCriterion("INVOICE_ID between", value1, value2, "invoiceId");
            return (Criteria) this;
        }

        public Criteria andInvoiceIdNotBetween(Long value1, Long value2) {
            addCriterion("INVOICE_ID not between", value1, value2, "invoiceId");
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

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andVerifieDateIsNull() {
            addCriterion("VERIFIE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andVerifieDateIsNotNull() {
            addCriterion("VERIFIE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andVerifieDateEqualTo(Date value) {
            addCriterion("VERIFIE_DATE =", value, "verifieDate");
            return (Criteria) this;
        }

        public Criteria andVerifieDateNotEqualTo(Date value) {
            addCriterion("VERIFIE_DATE <>", value, "verifieDate");
            return (Criteria) this;
        }

        public Criteria andVerifieDateGreaterThan(Date value) {
            addCriterion("VERIFIE_DATE >", value, "verifieDate");
            return (Criteria) this;
        }

        public Criteria andVerifieDateGreaterThanOrEqualTo(Date value) {
            addCriterion("VERIFIE_DATE >=", value, "verifieDate");
            return (Criteria) this;
        }

        public Criteria andVerifieDateLessThan(Date value) {
            addCriterion("VERIFIE_DATE <", value, "verifieDate");
            return (Criteria) this;
        }

        public Criteria andVerifieDateLessThanOrEqualTo(Date value) {
            addCriterion("VERIFIE_DATE <=", value, "verifieDate");
            return (Criteria) this;
        }

        public Criteria andVerifieDateIn(List<Date> values) {
            addCriterion("VERIFIE_DATE in", values, "verifieDate");
            return (Criteria) this;
        }

        public Criteria andVerifieDateNotIn(List<Date> values) {
            addCriterion("VERIFIE_DATE not in", values, "verifieDate");
            return (Criteria) this;
        }

        public Criteria andVerifieDateBetween(Date value1, Date value2) {
            addCriterion("VERIFIE_DATE between", value1, value2, "verifieDate");
            return (Criteria) this;
        }

        public Criteria andVerifieDateNotBetween(Date value1, Date value2) {
            addCriterion("VERIFIE_DATE not between", value1, value2, "verifieDate");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("YEAR is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("YEAR =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("YEAR <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("YEAR >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("YEAR >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("YEAR <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("YEAR <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("YEAR like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("YEAR not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("YEAR in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("YEAR not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("YEAR between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("YEAR not between", value1, value2, "year");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated do_not_delete_during_merge Fri Nov 18 14:31:14 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_RECEIVE_VERIFIE
     *
     * @mbggenerated Fri Nov 18 14:31:14 CST 2016
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
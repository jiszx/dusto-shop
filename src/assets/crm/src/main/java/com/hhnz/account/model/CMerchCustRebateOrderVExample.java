package com.hhnz.account.model;

import com.hhnz.util.db.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CMerchCustRebateOrderVExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public CMerchCustRebateOrderVExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
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
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
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

        public Criteria andHeadidIsNull() {
            addCriterion("HEADID is null");
            return (Criteria) this;
        }

        public Criteria andHeadidIsNotNull() {
            addCriterion("HEADID is not null");
            return (Criteria) this;
        }

        public Criteria andHeadidEqualTo(Long value) {
            addCriterion("HEADID =", value, "headid");
            return (Criteria) this;
        }

        public Criteria andHeadidNotEqualTo(Long value) {
            addCriterion("HEADID <>", value, "headid");
            return (Criteria) this;
        }

        public Criteria andHeadidGreaterThan(Long value) {
            addCriterion("HEADID >", value, "headid");
            return (Criteria) this;
        }

        public Criteria andHeadidGreaterThanOrEqualTo(Long value) {
            addCriterion("HEADID >=", value, "headid");
            return (Criteria) this;
        }

        public Criteria andHeadidLessThan(Long value) {
            addCriterion("HEADID <", value, "headid");
            return (Criteria) this;
        }

        public Criteria andHeadidLessThanOrEqualTo(Long value) {
            addCriterion("HEADID <=", value, "headid");
            return (Criteria) this;
        }

        public Criteria andHeadidIn(List<Long> values) {
            addCriterion("HEADID in", values, "headid");
            return (Criteria) this;
        }

        public Criteria andHeadidNotIn(List<Long> values) {
            addCriterion("HEADID not in", values, "headid");
            return (Criteria) this;
        }

        public Criteria andHeadidBetween(Long value1, Long value2) {
            addCriterion("HEADID between", value1, value2, "headid");
            return (Criteria) this;
        }

        public Criteria andHeadidNotBetween(Long value1, Long value2) {
            addCriterion("HEADID not between", value1, value2, "headid");
            return (Criteria) this;
        }

        public Criteria andRebateIdIsNull() {
            addCriterion("REBATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRebateIdIsNotNull() {
            addCriterion("REBATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRebateIdEqualTo(Long value) {
            addCriterion("REBATE_ID =", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdNotEqualTo(Long value) {
            addCriterion("REBATE_ID <>", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdGreaterThan(Long value) {
            addCriterion("REBATE_ID >", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("REBATE_ID >=", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdLessThan(Long value) {
            addCriterion("REBATE_ID <", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdLessThanOrEqualTo(Long value) {
            addCriterion("REBATE_ID <=", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdIn(List<Long> values) {
            addCriterion("REBATE_ID in", values, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdNotIn(List<Long> values) {
            addCriterion("REBATE_ID not in", values, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdBetween(Long value1, Long value2) {
            addCriterion("REBATE_ID between", value1, value2, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdNotBetween(Long value1, Long value2) {
            addCriterion("REBATE_ID not between", value1, value2, "rebateId");
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

        public Criteria andSkuIsNull() {
            addCriterion("SKU is null");
            return (Criteria) this;
        }

        public Criteria andSkuIsNotNull() {
            addCriterion("SKU is not null");
            return (Criteria) this;
        }

        public Criteria andSkuEqualTo(String value) {
            addCriterion("SKU =", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotEqualTo(String value) {
            addCriterion("SKU <>", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThan(String value) {
            addCriterion("SKU >", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThanOrEqualTo(String value) {
            addCriterion("SKU >=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThan(String value) {
            addCriterion("SKU <", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThanOrEqualTo(String value) {
            addCriterion("SKU <=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLike(String value) {
            addCriterion("SKU like", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotLike(String value) {
            addCriterion("SKU not like", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuIn(List<String> values) {
            addCriterion("SKU in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotIn(List<String> values) {
            addCriterion("SKU not in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuBetween(String value1, String value2) {
            addCriterion("SKU between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotBetween(String value1, String value2) {
            addCriterion("SKU not between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andLineidIsNull() {
            addCriterion("LINEID is null");
            return (Criteria) this;
        }

        public Criteria andLineidIsNotNull() {
            addCriterion("LINEID is not null");
            return (Criteria) this;
        }

        public Criteria andLineidEqualTo(Long value) {
            addCriterion("LINEID =", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidNotEqualTo(Long value) {
            addCriterion("LINEID <>", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidGreaterThan(Long value) {
            addCriterion("LINEID >", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidGreaterThanOrEqualTo(Long value) {
            addCriterion("LINEID >=", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidLessThan(Long value) {
            addCriterion("LINEID <", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidLessThanOrEqualTo(Long value) {
            addCriterion("LINEID <=", value, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidIn(List<Long> values) {
            addCriterion("LINEID in", values, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidNotIn(List<Long> values) {
            addCriterion("LINEID not in", values, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidBetween(Long value1, Long value2) {
            addCriterion("LINEID between", value1, value2, "lineid");
            return (Criteria) this;
        }

        public Criteria andLineidNotBetween(Long value1, Long value2) {
            addCriterion("LINEID not between", value1, value2, "lineid");
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

        public Criteria andNumIsNull() {
            addCriterion("NUM is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("NUM is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Long value) {
            addCriterion("NUM =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Long value) {
            addCriterion("NUM <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Long value) {
            addCriterion("NUM >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Long value) {
            addCriterion("NUM >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Long value) {
            addCriterion("NUM <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Long value) {
            addCriterion("NUM <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Long> values) {
            addCriterion("NUM in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Long> values) {
            addCriterion("NUM not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Long value1, Long value2) {
            addCriterion("NUM between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Long value1, Long value2) {
            addCriterion("NUM not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andHbNumIsNull() {
            addCriterion("HB_NUM is null");
            return (Criteria) this;
        }

        public Criteria andHbNumIsNotNull() {
            addCriterion("HB_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andHbNumEqualTo(Long value) {
            addCriterion("HB_NUM =", value, "hbNum");
            return (Criteria) this;
        }

        public Criteria andHbNumNotEqualTo(Long value) {
            addCriterion("HB_NUM <>", value, "hbNum");
            return (Criteria) this;
        }

        public Criteria andHbNumGreaterThan(Long value) {
            addCriterion("HB_NUM >", value, "hbNum");
            return (Criteria) this;
        }

        public Criteria andHbNumGreaterThanOrEqualTo(Long value) {
            addCriterion("HB_NUM >=", value, "hbNum");
            return (Criteria) this;
        }

        public Criteria andHbNumLessThan(Long value) {
            addCriterion("HB_NUM <", value, "hbNum");
            return (Criteria) this;
        }

        public Criteria andHbNumLessThanOrEqualTo(Long value) {
            addCriterion("HB_NUM <=", value, "hbNum");
            return (Criteria) this;
        }

        public Criteria andHbNumIn(List<Long> values) {
            addCriterion("HB_NUM in", values, "hbNum");
            return (Criteria) this;
        }

        public Criteria andHbNumNotIn(List<Long> values) {
            addCriterion("HB_NUM not in", values, "hbNum");
            return (Criteria) this;
        }

        public Criteria andHbNumBetween(Long value1, Long value2) {
            addCriterion("HB_NUM between", value1, value2, "hbNum");
            return (Criteria) this;
        }

        public Criteria andHbNumNotBetween(Long value1, Long value2) {
            addCriterion("HB_NUM not between", value1, value2, "hbNum");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIsNull() {
            addCriterion("ORDER_AMT is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIsNotNull() {
            addCriterion("ORDER_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmtEqualTo(Long value) {
            addCriterion("ORDER_AMT =", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotEqualTo(Long value) {
            addCriterion("ORDER_AMT <>", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtGreaterThan(Long value) {
            addCriterion("ORDER_AMT >", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_AMT >=", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtLessThan(Long value) {
            addCriterion("ORDER_AMT <", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_AMT <=", value, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtIn(List<Long> values) {
            addCriterion("ORDER_AMT in", values, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotIn(List<Long> values) {
            addCriterion("ORDER_AMT not in", values, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtBetween(Long value1, Long value2) {
            addCriterion("ORDER_AMT between", value1, value2, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andOrderAmtNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_AMT not between", value1, value2, "orderAmt");
            return (Criteria) this;
        }

        public Criteria andHbAmtIsNull() {
            addCriterion("HB_AMT is null");
            return (Criteria) this;
        }

        public Criteria andHbAmtIsNotNull() {
            addCriterion("HB_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andHbAmtEqualTo(Long value) {
            addCriterion("HB_AMT =", value, "hbAmt");
            return (Criteria) this;
        }

        public Criteria andHbAmtNotEqualTo(Long value) {
            addCriterion("HB_AMT <>", value, "hbAmt");
            return (Criteria) this;
        }

        public Criteria andHbAmtGreaterThan(Long value) {
            addCriterion("HB_AMT >", value, "hbAmt");
            return (Criteria) this;
        }

        public Criteria andHbAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("HB_AMT >=", value, "hbAmt");
            return (Criteria) this;
        }

        public Criteria andHbAmtLessThan(Long value) {
            addCriterion("HB_AMT <", value, "hbAmt");
            return (Criteria) this;
        }

        public Criteria andHbAmtLessThanOrEqualTo(Long value) {
            addCriterion("HB_AMT <=", value, "hbAmt");
            return (Criteria) this;
        }

        public Criteria andHbAmtIn(List<Long> values) {
            addCriterion("HB_AMT in", values, "hbAmt");
            return (Criteria) this;
        }

        public Criteria andHbAmtNotIn(List<Long> values) {
            addCriterion("HB_AMT not in", values, "hbAmt");
            return (Criteria) this;
        }

        public Criteria andHbAmtBetween(Long value1, Long value2) {
            addCriterion("HB_AMT between", value1, value2, "hbAmt");
            return (Criteria) this;
        }

        public Criteria andHbAmtNotBetween(Long value1, Long value2) {
            addCriterion("HB_AMT not between", value1, value2, "hbAmt");
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

        public Criteria andBdateIsNull() {
            addCriterion("BDATE is null");
            return (Criteria) this;
        }

        public Criteria andBdateIsNotNull() {
            addCriterion("BDATE is not null");
            return (Criteria) this;
        }

        public Criteria andBdateEqualTo(Date value) {
            addCriterion("BDATE =", value, "bdate");
            return (Criteria) this;
        }

        public Criteria andBdateNotEqualTo(Date value) {
            addCriterion("BDATE <>", value, "bdate");
            return (Criteria) this;
        }

        public Criteria andBdateGreaterThan(Date value) {
            addCriterion("BDATE >", value, "bdate");
            return (Criteria) this;
        }

        public Criteria andBdateGreaterThanOrEqualTo(Date value) {
            addCriterion("BDATE >=", value, "bdate");
            return (Criteria) this;
        }

        public Criteria andBdateLessThan(Date value) {
            addCriterion("BDATE <", value, "bdate");
            return (Criteria) this;
        }

        public Criteria andBdateLessThanOrEqualTo(Date value) {
            addCriterion("BDATE <=", value, "bdate");
            return (Criteria) this;
        }

        public Criteria andBdateIn(List<Date> values) {
            addCriterion("BDATE in", values, "bdate");
            return (Criteria) this;
        }

        public Criteria andBdateNotIn(List<Date> values) {
            addCriterion("BDATE not in", values, "bdate");
            return (Criteria) this;
        }

        public Criteria andBdateBetween(Date value1, Date value2) {
            addCriterion("BDATE between", value1, value2, "bdate");
            return (Criteria) this;
        }

        public Criteria andBdateNotBetween(Date value1, Date value2) {
            addCriterion("BDATE not between", value1, value2, "bdate");
            return (Criteria) this;
        }

        public Criteria andEdateIsNull() {
            addCriterion("EDATE is null");
            return (Criteria) this;
        }

        public Criteria andEdateIsNotNull() {
            addCriterion("EDATE is not null");
            return (Criteria) this;
        }

        public Criteria andEdateEqualTo(Date value) {
            addCriterion("EDATE =", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateNotEqualTo(Date value) {
            addCriterion("EDATE <>", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateGreaterThan(Date value) {
            addCriterion("EDATE >", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateGreaterThanOrEqualTo(Date value) {
            addCriterion("EDATE >=", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateLessThan(Date value) {
            addCriterion("EDATE <", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateLessThanOrEqualTo(Date value) {
            addCriterion("EDATE <=", value, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateIn(List<Date> values) {
            addCriterion("EDATE in", values, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateNotIn(List<Date> values) {
            addCriterion("EDATE not in", values, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateBetween(Date value1, Date value2) {
            addCriterion("EDATE between", value1, value2, "edate");
            return (Criteria) this;
        }

        public Criteria andEdateNotBetween(Date value1, Date value2) {
            addCriterion("EDATE not between", value1, value2, "edate");
            return (Criteria) this;
        }

        public Criteria andSalerepIsNull() {
            addCriterion("SALEREP is null");
            return (Criteria) this;
        }

        public Criteria andSalerepIsNotNull() {
            addCriterion("SALEREP is not null");
            return (Criteria) this;
        }

        public Criteria andSalerepEqualTo(String value) {
            addCriterion("SALEREP =", value, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepNotEqualTo(String value) {
            addCriterion("SALEREP <>", value, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepGreaterThan(String value) {
            addCriterion("SALEREP >", value, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepGreaterThanOrEqualTo(String value) {
            addCriterion("SALEREP >=", value, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepLessThan(String value) {
            addCriterion("SALEREP <", value, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepLessThanOrEqualTo(String value) {
            addCriterion("SALEREP <=", value, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepLike(String value) {
            addCriterion("SALEREP like", value, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepNotLike(String value) {
            addCriterion("SALEREP not like", value, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepIn(List<String> values) {
            addCriterion("SALEREP in", values, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepNotIn(List<String> values) {
            addCriterion("SALEREP not in", values, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepBetween(String value1, String value2) {
            addCriterion("SALEREP between", value1, value2, "salerep");
            return (Criteria) this;
        }

        public Criteria andSalerepNotBetween(String value1, String value2) {
            addCriterion("SALEREP not between", value1, value2, "salerep");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated do_not_delete_during_merge Mon Sep 12 14:12:31 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table C_MERCH_CUST_REBATE_ORDER_V
     *
     * @mbggenerated Mon Sep 12 14:12:31 CST 2016
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
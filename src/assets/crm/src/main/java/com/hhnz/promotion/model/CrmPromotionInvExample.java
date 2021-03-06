package com.hhnz.promotion.model;

import com.hhnz.util.db.Page;
import java.util.ArrayList;
import java.util.List;

public class CrmPromotionInvExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public CrmPromotionInvExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
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
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
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

        public Criteria andPromotionIdIsNull() {
            addCriterion("PROMOTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andPromotionIdIsNotNull() {
            addCriterion("PROMOTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPromotionIdEqualTo(Long value) {
            addCriterion("PROMOTION_ID =", value, "promotionId");
            return (Criteria) this;
        }

        public Criteria andPromotionIdNotEqualTo(Long value) {
            addCriterion("PROMOTION_ID <>", value, "promotionId");
            return (Criteria) this;
        }

        public Criteria andPromotionIdGreaterThan(Long value) {
            addCriterion("PROMOTION_ID >", value, "promotionId");
            return (Criteria) this;
        }

        public Criteria andPromotionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROMOTION_ID >=", value, "promotionId");
            return (Criteria) this;
        }

        public Criteria andPromotionIdLessThan(Long value) {
            addCriterion("PROMOTION_ID <", value, "promotionId");
            return (Criteria) this;
        }

        public Criteria andPromotionIdLessThanOrEqualTo(Long value) {
            addCriterion("PROMOTION_ID <=", value, "promotionId");
            return (Criteria) this;
        }

        public Criteria andPromotionIdIn(List<Long> values) {
            addCriterion("PROMOTION_ID in", values, "promotionId");
            return (Criteria) this;
        }

        public Criteria andPromotionIdNotIn(List<Long> values) {
            addCriterion("PROMOTION_ID not in", values, "promotionId");
            return (Criteria) this;
        }

        public Criteria andPromotionIdBetween(Long value1, Long value2) {
            addCriterion("PROMOTION_ID between", value1, value2, "promotionId");
            return (Criteria) this;
        }

        public Criteria andPromotionIdNotBetween(Long value1, Long value2) {
            addCriterion("PROMOTION_ID not between", value1, value2, "promotionId");
            return (Criteria) this;
        }

        public Criteria andStoresIdIsNull() {
            addCriterion("STORES_ID is null");
            return (Criteria) this;
        }

        public Criteria andStoresIdIsNotNull() {
            addCriterion("STORES_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStoresIdEqualTo(Long value) {
            addCriterion("STORES_ID =", value, "storesId");
            return (Criteria) this;
        }

        public Criteria andStoresIdNotEqualTo(Long value) {
            addCriterion("STORES_ID <>", value, "storesId");
            return (Criteria) this;
        }

        public Criteria andStoresIdGreaterThan(Long value) {
            addCriterion("STORES_ID >", value, "storesId");
            return (Criteria) this;
        }

        public Criteria andStoresIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STORES_ID >=", value, "storesId");
            return (Criteria) this;
        }

        public Criteria andStoresIdLessThan(Long value) {
            addCriterion("STORES_ID <", value, "storesId");
            return (Criteria) this;
        }

        public Criteria andStoresIdLessThanOrEqualTo(Long value) {
            addCriterion("STORES_ID <=", value, "storesId");
            return (Criteria) this;
        }

        public Criteria andStoresIdIn(List<Long> values) {
            addCriterion("STORES_ID in", values, "storesId");
            return (Criteria) this;
        }

        public Criteria andStoresIdNotIn(List<Long> values) {
            addCriterion("STORES_ID not in", values, "storesId");
            return (Criteria) this;
        }

        public Criteria andStoresIdBetween(Long value1, Long value2) {
            addCriterion("STORES_ID between", value1, value2, "storesId");
            return (Criteria) this;
        }

        public Criteria andStoresIdNotBetween(Long value1, Long value2) {
            addCriterion("STORES_ID not between", value1, value2, "storesId");
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

        public Criteria andAmtIsNull() {
            addCriterion("AMT is null");
            return (Criteria) this;
        }

        public Criteria andAmtIsNotNull() {
            addCriterion("AMT is not null");
            return (Criteria) this;
        }

        public Criteria andAmtEqualTo(Long value) {
            addCriterion("AMT =", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotEqualTo(Long value) {
            addCriterion("AMT <>", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtGreaterThan(Long value) {
            addCriterion("AMT >", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("AMT >=", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtLessThan(Long value) {
            addCriterion("AMT <", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtLessThanOrEqualTo(Long value) {
            addCriterion("AMT <=", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtIn(List<Long> values) {
            addCriterion("AMT in", values, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotIn(List<Long> values) {
            addCriterion("AMT not in", values, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtBetween(Long value1, Long value2) {
            addCriterion("AMT between", value1, value2, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotBetween(Long value1, Long value2) {
            addCriterion("AMT not between", value1, value2, "amt");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated do_not_delete_during_merge Fri Sep 02 09:55:27 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table CRM_PROMOTION_INV
     *
     * @mbggenerated Fri Sep 02 09:55:27 CST 2016
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
package com.hhnz.logistics.model;

import com.hhnz.util.db.Page;
import java.util.ArrayList;
import java.util.List;

public class TLogisticsRdcExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public TLogisticsRdcExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
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
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
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

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRdcCodeIsNull() {
            addCriterion("RDC_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRdcCodeIsNotNull() {
            addCriterion("RDC_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRdcCodeEqualTo(String value) {
            addCriterion("RDC_CODE =", value, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeNotEqualTo(String value) {
            addCriterion("RDC_CODE <>", value, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeGreaterThan(String value) {
            addCriterion("RDC_CODE >", value, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeGreaterThanOrEqualTo(String value) {
            addCriterion("RDC_CODE >=", value, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeLessThan(String value) {
            addCriterion("RDC_CODE <", value, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeLessThanOrEqualTo(String value) {
            addCriterion("RDC_CODE <=", value, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeLike(String value) {
            addCriterion("RDC_CODE like", value, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeNotLike(String value) {
            addCriterion("RDC_CODE not like", value, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeIn(List<String> values) {
            addCriterion("RDC_CODE in", values, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeNotIn(List<String> values) {
            addCriterion("RDC_CODE not in", values, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeBetween(String value1, String value2) {
            addCriterion("RDC_CODE between", value1, value2, "rdcCode");
            return (Criteria) this;
        }

        public Criteria andRdcCodeNotBetween(String value1, String value2) {
            addCriterion("RDC_CODE not between", value1, value2, "rdcCode");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated do_not_delete_during_merge Fri Sep 08 16:00:23 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_LOGISTICS_RDC
     *
     * @mbggenerated Fri Sep 08 16:00:23 CST 2017
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
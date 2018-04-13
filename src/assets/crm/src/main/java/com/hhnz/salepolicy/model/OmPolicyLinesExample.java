package com.hhnz.salepolicy.model;

import com.hhnz.util.db.Page;
import java.util.ArrayList;
import java.util.List;

public class OmPolicyLinesExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public OmPolicyLinesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
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
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
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

        public Criteria andHeaderIdIsNull() {
            addCriterion("HEADER_ID is null");
            return (Criteria) this;
        }

        public Criteria andHeaderIdIsNotNull() {
            addCriterion("HEADER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderIdEqualTo(Long value) {
            addCriterion("HEADER_ID =", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdNotEqualTo(Long value) {
            addCriterion("HEADER_ID <>", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdGreaterThan(Long value) {
            addCriterion("HEADER_ID >", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("HEADER_ID >=", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdLessThan(Long value) {
            addCriterion("HEADER_ID <", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdLessThanOrEqualTo(Long value) {
            addCriterion("HEADER_ID <=", value, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdIn(List<Long> values) {
            addCriterion("HEADER_ID in", values, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdNotIn(List<Long> values) {
            addCriterion("HEADER_ID not in", values, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdBetween(Long value1, Long value2) {
            addCriterion("HEADER_ID between", value1, value2, "headerId");
            return (Criteria) this;
        }

        public Criteria andHeaderIdNotBetween(Long value1, Long value2) {
            addCriterion("HEADER_ID not between", value1, value2, "headerId");
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

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andPrimaryIsNull() {
            addCriterion("PRIMARY is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryIsNotNull() {
            addCriterion("PRIMARY is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryEqualTo(String value) {
            addCriterion("PRIMARY =", value, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryNotEqualTo(String value) {
            addCriterion("PRIMARY <>", value, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryGreaterThan(String value) {
            addCriterion("PRIMARY >", value, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryGreaterThanOrEqualTo(String value) {
            addCriterion("PRIMARY >=", value, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryLessThan(String value) {
            addCriterion("PRIMARY <", value, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryLessThanOrEqualTo(String value) {
            addCriterion("PRIMARY <=", value, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryLike(String value) {
            addCriterion("PRIMARY like", value, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryNotLike(String value) {
            addCriterion("PRIMARY not like", value, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryIn(List<String> values) {
            addCriterion("PRIMARY in", values, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryNotIn(List<String> values) {
            addCriterion("PRIMARY not in", values, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryBetween(String value1, String value2) {
            addCriterion("PRIMARY between", value1, value2, "primary");
            return (Criteria) this;
        }

        public Criteria andPrimaryNotBetween(String value1, String value2) {
            addCriterion("PRIMARY not between", value1, value2, "primary");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("DISCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("DISCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(String value) {
            addCriterion("DISCOUNT =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(String value) {
            addCriterion("DISCOUNT <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(String value) {
            addCriterion("DISCOUNT >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(String value) {
            addCriterion("DISCOUNT >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(String value) {
            addCriterion("DISCOUNT <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(String value) {
            addCriterion("DISCOUNT <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLike(String value) {
            addCriterion("DISCOUNT like", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotLike(String value) {
            addCriterion("DISCOUNT not like", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<String> values) {
            addCriterion("DISCOUNT in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<String> values) {
            addCriterion("DISCOUNT not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(String value1, String value2) {
            addCriterion("DISCOUNT between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(String value1, String value2) {
            addCriterion("DISCOUNT not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andLimitIsNull() {
            addCriterion("LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andLimitIsNotNull() {
            addCriterion("LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andLimitEqualTo(String value) {
            addCriterion("LIMIT =", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotEqualTo(String value) {
            addCriterion("LIMIT <>", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitGreaterThan(String value) {
            addCriterion("LIMIT >", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitGreaterThanOrEqualTo(String value) {
            addCriterion("LIMIT >=", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitLessThan(String value) {
            addCriterion("LIMIT <", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitLessThanOrEqualTo(String value) {
            addCriterion("LIMIT <=", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitLike(String value) {
            addCriterion("LIMIT like", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotLike(String value) {
            addCriterion("LIMIT not like", value, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitIn(List<String> values) {
            addCriterion("LIMIT in", values, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotIn(List<String> values) {
            addCriterion("LIMIT not in", values, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitBetween(String value1, String value2) {
            addCriterion("LIMIT between", value1, value2, "limit");
            return (Criteria) this;
        }

        public Criteria andLimitNotBetween(String value1, String value2) {
            addCriterion("LIMIT not between", value1, value2, "limit");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityIsNull() {
            addCriterion("DISCOUNT_INTENSITY is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityIsNotNull() {
            addCriterion("DISCOUNT_INTENSITY is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityEqualTo(String value) {
            addCriterion("DISCOUNT_INTENSITY =", value, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityNotEqualTo(String value) {
            addCriterion("DISCOUNT_INTENSITY <>", value, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityGreaterThan(String value) {
            addCriterion("DISCOUNT_INTENSITY >", value, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityGreaterThanOrEqualTo(String value) {
            addCriterion("DISCOUNT_INTENSITY >=", value, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityLessThan(String value) {
            addCriterion("DISCOUNT_INTENSITY <", value, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityLessThanOrEqualTo(String value) {
            addCriterion("DISCOUNT_INTENSITY <=", value, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityLike(String value) {
            addCriterion("DISCOUNT_INTENSITY like", value, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityNotLike(String value) {
            addCriterion("DISCOUNT_INTENSITY not like", value, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityIn(List<String> values) {
            addCriterion("DISCOUNT_INTENSITY in", values, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityNotIn(List<String> values) {
            addCriterion("DISCOUNT_INTENSITY not in", values, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityBetween(String value1, String value2) {
            addCriterion("DISCOUNT_INTENSITY between", value1, value2, "discountIntensity");
            return (Criteria) this;
        }

        public Criteria andDiscountIntensityNotBetween(String value1, String value2) {
            addCriterion("DISCOUNT_INTENSITY not between", value1, value2, "discountIntensity");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated do_not_delete_during_merge Fri Aug 19 11:14:32 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OM_POLICY_LINES
     *
     * @mbggenerated Fri Aug 19 11:14:32 CST 2016
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
package com.hhnz.crm.model;

import com.hhnz.util.db.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TDictExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    protected Page page;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public TDictExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
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
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public void setPage(Page page) {
        this.page=page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public Page getPage() {
        return page;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
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

        public Criteria andColNameIsNull() {
            addCriterion("COL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andColNameIsNotNull() {
            addCriterion("COL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andColNameEqualTo(String value) {
            addCriterion("COL_NAME =", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotEqualTo(String value) {
            addCriterion("COL_NAME <>", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameGreaterThan(String value) {
            addCriterion("COL_NAME >", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameGreaterThanOrEqualTo(String value) {
            addCriterion("COL_NAME >=", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameLessThan(String value) {
            addCriterion("COL_NAME <", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameLessThanOrEqualTo(String value) {
            addCriterion("COL_NAME <=", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameLike(String value) {
            addCriterion("COL_NAME like", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotLike(String value) {
            addCriterion("COL_NAME not like", value, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameIn(List<String> values) {
            addCriterion("COL_NAME in", values, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotIn(List<String> values) {
            addCriterion("COL_NAME not in", values, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameBetween(String value1, String value2) {
            addCriterion("COL_NAME between", value1, value2, "colName");
            return (Criteria) this;
        }

        public Criteria andColNameNotBetween(String value1, String value2) {
            addCriterion("COL_NAME not between", value1, value2, "colName");
            return (Criteria) this;
        }

        public Criteria andChooseValIsNull() {
            addCriterion("CHOOSE_VAL is null");
            return (Criteria) this;
        }

        public Criteria andChooseValIsNotNull() {
            addCriterion("CHOOSE_VAL is not null");
            return (Criteria) this;
        }

        public Criteria andChooseValEqualTo(String value) {
            addCriterion("CHOOSE_VAL =", value, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValNotEqualTo(String value) {
            addCriterion("CHOOSE_VAL <>", value, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValGreaterThan(String value) {
            addCriterion("CHOOSE_VAL >", value, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValGreaterThanOrEqualTo(String value) {
            addCriterion("CHOOSE_VAL >=", value, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValLessThan(String value) {
            addCriterion("CHOOSE_VAL <", value, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValLessThanOrEqualTo(String value) {
            addCriterion("CHOOSE_VAL <=", value, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValLike(String value) {
            addCriterion("CHOOSE_VAL like", value, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValNotLike(String value) {
            addCriterion("CHOOSE_VAL not like", value, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValIn(List<String> values) {
            addCriterion("CHOOSE_VAL in", values, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValNotIn(List<String> values) {
            addCriterion("CHOOSE_VAL not in", values, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValBetween(String value1, String value2) {
            addCriterion("CHOOSE_VAL between", value1, value2, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andChooseValNotBetween(String value1, String value2) {
            addCriterion("CHOOSE_VAL not between", value1, value2, "chooseVal");
            return (Criteria) this;
        }

        public Criteria andShowTextIsNull() {
            addCriterion("SHOW_TEXT is null");
            return (Criteria) this;
        }

        public Criteria andShowTextIsNotNull() {
            addCriterion("SHOW_TEXT is not null");
            return (Criteria) this;
        }

        public Criteria andShowTextEqualTo(String value) {
            addCriterion("SHOW_TEXT =", value, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextNotEqualTo(String value) {
            addCriterion("SHOW_TEXT <>", value, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextGreaterThan(String value) {
            addCriterion("SHOW_TEXT >", value, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextGreaterThanOrEqualTo(String value) {
            addCriterion("SHOW_TEXT >=", value, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextLessThan(String value) {
            addCriterion("SHOW_TEXT <", value, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextLessThanOrEqualTo(String value) {
            addCriterion("SHOW_TEXT <=", value, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextLike(String value) {
            addCriterion("SHOW_TEXT like", value, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextNotLike(String value) {
            addCriterion("SHOW_TEXT not like", value, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextIn(List<String> values) {
            addCriterion("SHOW_TEXT in", values, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextNotIn(List<String> values) {
            addCriterion("SHOW_TEXT not in", values, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextBetween(String value1, String value2) {
            addCriterion("SHOW_TEXT between", value1, value2, "showText");
            return (Criteria) this;
        }

        public Criteria andShowTextNotBetween(String value1, String value2) {
            addCriterion("SHOW_TEXT not between", value1, value2, "showText");
            return (Criteria) this;
        }

        public Criteria andOrdersIsNull() {
            addCriterion("ORDERS is null");
            return (Criteria) this;
        }

        public Criteria andOrdersIsNotNull() {
            addCriterion("ORDERS is not null");
            return (Criteria) this;
        }

        public Criteria andOrdersEqualTo(Integer value) {
            addCriterion("ORDERS =", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersNotEqualTo(Integer value) {
            addCriterion("ORDERS <>", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersGreaterThan(Integer value) {
            addCriterion("ORDERS >", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDERS >=", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersLessThan(Integer value) {
            addCriterion("ORDERS <", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersLessThanOrEqualTo(Integer value) {
            addCriterion("ORDERS <=", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersIn(List<Integer> values) {
            addCriterion("ORDERS in", values, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersNotIn(List<Integer> values) {
            addCriterion("ORDERS not in", values, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersBetween(Integer value1, Integer value2) {
            addCriterion("ORDERS between", value1, value2, "orders");
            return (Criteria) this;
        }

        public Criteria andOrdersNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDERS not between", value1, value2, "orders");
            return (Criteria) this;
        }

        public Criteria andCanChooseIsNull() {
            addCriterion("CAN_CHOOSE is null");
            return (Criteria) this;
        }

        public Criteria andCanChooseIsNotNull() {
            addCriterion("CAN_CHOOSE is not null");
            return (Criteria) this;
        }

        public Criteria andCanChooseEqualTo(String value) {
            addCriterion("CAN_CHOOSE =", value, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseNotEqualTo(String value) {
            addCriterion("CAN_CHOOSE <>", value, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseGreaterThan(String value) {
            addCriterion("CAN_CHOOSE >", value, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseGreaterThanOrEqualTo(String value) {
            addCriterion("CAN_CHOOSE >=", value, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseLessThan(String value) {
            addCriterion("CAN_CHOOSE <", value, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseLessThanOrEqualTo(String value) {
            addCriterion("CAN_CHOOSE <=", value, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseLike(String value) {
            addCriterion("CAN_CHOOSE like", value, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseNotLike(String value) {
            addCriterion("CAN_CHOOSE not like", value, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseIn(List<String> values) {
            addCriterion("CAN_CHOOSE in", values, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseNotIn(List<String> values) {
            addCriterion("CAN_CHOOSE not in", values, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseBetween(String value1, String value2) {
            addCriterion("CAN_CHOOSE between", value1, value2, "canChoose");
            return (Criteria) this;
        }

        public Criteria andCanChooseNotBetween(String value1, String value2) {
            addCriterion("CAN_CHOOSE not between", value1, value2, "canChoose");
            return (Criteria) this;
        }

        public Criteria andDisplayIsNull() {
            addCriterion("DISPLAY is null");
            return (Criteria) this;
        }

        public Criteria andDisplayIsNotNull() {
            addCriterion("DISPLAY is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayEqualTo(String value) {
            addCriterion("DISPLAY =", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotEqualTo(String value) {
            addCriterion("DISPLAY <>", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayGreaterThan(String value) {
            addCriterion("DISPLAY >", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayGreaterThanOrEqualTo(String value) {
            addCriterion("DISPLAY >=", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayLessThan(String value) {
            addCriterion("DISPLAY <", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayLessThanOrEqualTo(String value) {
            addCriterion("DISPLAY <=", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayLike(String value) {
            addCriterion("DISPLAY like", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotLike(String value) {
            addCriterion("DISPLAY not like", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayIn(List<String> values) {
            addCriterion("DISPLAY in", values, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotIn(List<String> values) {
            addCriterion("DISPLAY not in", values, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayBetween(String value1, String value2) {
            addCriterion("DISPLAY between", value1, value2, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotBetween(String value1, String value2) {
            addCriterion("DISPLAY not between", value1, value2, "display");
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

        public Criteria andColDescriptionIsNull() {
            addCriterion("COL_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andColDescriptionIsNotNull() {
            addCriterion("COL_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andColDescriptionEqualTo(String value) {
            addCriterion("COL_DESCRIPTION =", value, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionNotEqualTo(String value) {
            addCriterion("COL_DESCRIPTION <>", value, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionGreaterThan(String value) {
            addCriterion("COL_DESCRIPTION >", value, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("COL_DESCRIPTION >=", value, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionLessThan(String value) {
            addCriterion("COL_DESCRIPTION <", value, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionLessThanOrEqualTo(String value) {
            addCriterion("COL_DESCRIPTION <=", value, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionLike(String value) {
            addCriterion("COL_DESCRIPTION like", value, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionNotLike(String value) {
            addCriterion("COL_DESCRIPTION not like", value, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionIn(List<String> values) {
            addCriterion("COL_DESCRIPTION in", values, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionNotIn(List<String> values) {
            addCriterion("COL_DESCRIPTION not in", values, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionBetween(String value1, String value2) {
            addCriterion("COL_DESCRIPTION between", value1, value2, "colDescription");
            return (Criteria) this;
        }

        public Criteria andColDescriptionNotBetween(String value1, String value2) {
            addCriterion("COL_DESCRIPTION not between", value1, value2, "colDescription");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_DICT
     *
     * @mbggenerated do_not_delete_during_merge Mon Jul 25 14:48:11 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_DICT
     *
     * @mbggenerated Mon Jul 25 14:48:11 CST 2016
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

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

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value) {
            super();
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }
}
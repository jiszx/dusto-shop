package com.hhnz.jco.model;

import com.hhnz.util.db.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TRfcExecuteHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public TRfcExecuteHistoryExample() {
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

        public Criteria andSerialNoIsNull() {
            addCriterion("SERIAL_NO is null");
            return (Criteria) this;
        }

        public Criteria andSerialNoIsNotNull() {
            addCriterion("SERIAL_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNoEqualTo(String value) {
            addCriterion("SERIAL_NO =", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotEqualTo(String value) {
            addCriterion("SERIAL_NO <>", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoGreaterThan(String value) {
            addCriterion("SERIAL_NO >", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoGreaterThanOrEqualTo(String value) {
            addCriterion("SERIAL_NO >=", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLessThan(String value) {
            addCriterion("SERIAL_NO <", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLessThanOrEqualTo(String value) {
            addCriterion("SERIAL_NO <=", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLike(String value) {
            addCriterion("SERIAL_NO like", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotLike(String value) {
            addCriterion("SERIAL_NO not like", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoIn(List<String> values) {
            addCriterion("SERIAL_NO in", values, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotIn(List<String> values) {
            addCriterion("SERIAL_NO not in", values, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoBetween(String value1, String value2) {
            addCriterion("SERIAL_NO between", value1, value2, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotBetween(String value1, String value2) {
            addCriterion("SERIAL_NO not between", value1, value2, "serialNo");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeIsNull() {
            addCriterion("EXECUTE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeIsNotNull() {
            addCriterion("EXECUTE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeEqualTo(String value) {
            addCriterion("EXECUTE_TYPE =", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotEqualTo(String value) {
            addCriterion("EXECUTE_TYPE <>", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeGreaterThan(String value) {
            addCriterion("EXECUTE_TYPE >", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeGreaterThanOrEqualTo(String value) {
            addCriterion("EXECUTE_TYPE >=", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeLessThan(String value) {
            addCriterion("EXECUTE_TYPE <", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeLessThanOrEqualTo(String value) {
            addCriterion("EXECUTE_TYPE <=", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeLike(String value) {
            addCriterion("EXECUTE_TYPE like", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotLike(String value) {
            addCriterion("EXECUTE_TYPE not like", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeIn(List<String> values) {
            addCriterion("EXECUTE_TYPE in", values, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotIn(List<String> values) {
            addCriterion("EXECUTE_TYPE not in", values, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeBetween(String value1, String value2) {
            addCriterion("EXECUTE_TYPE between", value1, value2, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotBetween(String value1, String value2) {
            addCriterion("EXECUTE_TYPE not between", value1, value2, "executeType");
            return (Criteria) this;
        }

        public Criteria andParametersIsNull() {
            addCriterion("PARAMETERS is null");
            return (Criteria) this;
        }

        public Criteria andParametersIsNotNull() {
            addCriterion("PARAMETERS is not null");
            return (Criteria) this;
        }

        public Criteria andParametersEqualTo(String value) {
            addCriterion("PARAMETERS =", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersNotEqualTo(String value) {
            addCriterion("PARAMETERS <>", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersGreaterThan(String value) {
            addCriterion("PARAMETERS >", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersGreaterThanOrEqualTo(String value) {
            addCriterion("PARAMETERS >=", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersLessThan(String value) {
            addCriterion("PARAMETERS <", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersLessThanOrEqualTo(String value) {
            addCriterion("PARAMETERS <=", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersLike(String value) {
            addCriterion("PARAMETERS like", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersNotLike(String value) {
            addCriterion("PARAMETERS not like", value, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersIn(List<String> values) {
            addCriterion("PARAMETERS in", values, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersNotIn(List<String> values) {
            addCriterion("PARAMETERS not in", values, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersBetween(String value1, String value2) {
            addCriterion("PARAMETERS between", value1, value2, "parameters");
            return (Criteria) this;
        }

        public Criteria andParametersNotBetween(String value1, String value2) {
            addCriterion("PARAMETERS not between", value1, value2, "parameters");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andFailureTimesIsNull() {
            addCriterion("FAILURE_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andFailureTimesIsNotNull() {
            addCriterion("FAILURE_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andFailureTimesEqualTo(Integer value) {
            addCriterion("FAILURE_TIMES =", value, "failureTimes");
            return (Criteria) this;
        }

        public Criteria andFailureTimesNotEqualTo(Integer value) {
            addCriterion("FAILURE_TIMES <>", value, "failureTimes");
            return (Criteria) this;
        }

        public Criteria andFailureTimesGreaterThan(Integer value) {
            addCriterion("FAILURE_TIMES >", value, "failureTimes");
            return (Criteria) this;
        }

        public Criteria andFailureTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("FAILURE_TIMES >=", value, "failureTimes");
            return (Criteria) this;
        }

        public Criteria andFailureTimesLessThan(Integer value) {
            addCriterion("FAILURE_TIMES <", value, "failureTimes");
            return (Criteria) this;
        }

        public Criteria andFailureTimesLessThanOrEqualTo(Integer value) {
            addCriterion("FAILURE_TIMES <=", value, "failureTimes");
            return (Criteria) this;
        }

        public Criteria andFailureTimesIn(List<Integer> values) {
            addCriterion("FAILURE_TIMES in", values, "failureTimes");
            return (Criteria) this;
        }

        public Criteria andFailureTimesNotIn(List<Integer> values) {
            addCriterion("FAILURE_TIMES not in", values, "failureTimes");
            return (Criteria) this;
        }

        public Criteria andFailureTimesBetween(Integer value1, Integer value2) {
            addCriterion("FAILURE_TIMES between", value1, value2, "failureTimes");
            return (Criteria) this;
        }

        public Criteria andFailureTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("FAILURE_TIMES not between", value1, value2, "failureTimes");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("RESULT is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("RESULT =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("RESULT <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("RESULT >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("RESULT <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("RESULT <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("RESULT like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("RESULT not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("RESULT in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("RESULT not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("RESULT between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("RESULT not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCallbackNameIsNull() {
            addCriterion("CALLBACK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCallbackNameIsNotNull() {
            addCriterion("CALLBACK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCallbackNameEqualTo(String value) {
            addCriterion("CALLBACK_NAME =", value, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameNotEqualTo(String value) {
            addCriterion("CALLBACK_NAME <>", value, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameGreaterThan(String value) {
            addCriterion("CALLBACK_NAME >", value, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameGreaterThanOrEqualTo(String value) {
            addCriterion("CALLBACK_NAME >=", value, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameLessThan(String value) {
            addCriterion("CALLBACK_NAME <", value, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameLessThanOrEqualTo(String value) {
            addCriterion("CALLBACK_NAME <=", value, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameLike(String value) {
            addCriterion("CALLBACK_NAME like", value, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameNotLike(String value) {
            addCriterion("CALLBACK_NAME not like", value, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameIn(List<String> values) {
            addCriterion("CALLBACK_NAME in", values, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameNotIn(List<String> values) {
            addCriterion("CALLBACK_NAME not in", values, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameBetween(String value1, String value2) {
            addCriterion("CALLBACK_NAME between", value1, value2, "callbackName");
            return (Criteria) this;
        }

        public Criteria andCallbackNameNotBetween(String value1, String value2) {
            addCriterion("CALLBACK_NAME not between", value1, value2, "callbackName");
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
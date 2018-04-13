package com.hhnz.monitor.model;

import com.hhnz.util.db.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public TLogExample() {
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

        public Criteria andOperIdIsNull() {
            addCriterion("OPER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperIdIsNotNull() {
            addCriterion("OPER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperIdEqualTo(String value) {
            addCriterion("OPER_ID =", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotEqualTo(String value) {
            addCriterion("OPER_ID <>", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThan(String value) {
            addCriterion("OPER_ID >", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_ID >=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThan(String value) {
            addCriterion("OPER_ID <", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_ID <=", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdLike(String value) {
            addCriterion("OPER_ID like", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotLike(String value) {
            addCriterion("OPER_ID not like", value, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdIn(List<String> values) {
            addCriterion("OPER_ID in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotIn(List<String> values) {
            addCriterion("OPER_ID not in", values, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdBetween(String value1, String value2) {
            addCriterion("OPER_ID between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andOperIdNotBetween(String value1, String value2) {
            addCriterion("OPER_ID not between", value1, value2, "operId");
            return (Criteria) this;
        }

        public Criteria andAccessIpIsNull() {
            addCriterion("ACCESS_IP is null");
            return (Criteria) this;
        }

        public Criteria andAccessIpIsNotNull() {
            addCriterion("ACCESS_IP is not null");
            return (Criteria) this;
        }

        public Criteria andAccessIpEqualTo(String value) {
            addCriterion("ACCESS_IP =", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpNotEqualTo(String value) {
            addCriterion("ACCESS_IP <>", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpGreaterThan(String value) {
            addCriterion("ACCESS_IP >", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESS_IP >=", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpLessThan(String value) {
            addCriterion("ACCESS_IP <", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpLessThanOrEqualTo(String value) {
            addCriterion("ACCESS_IP <=", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpLike(String value) {
            addCriterion("ACCESS_IP like", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpNotLike(String value) {
            addCriterion("ACCESS_IP not like", value, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpIn(List<String> values) {
            addCriterion("ACCESS_IP in", values, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpNotIn(List<String> values) {
            addCriterion("ACCESS_IP not in", values, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpBetween(String value1, String value2) {
            addCriterion("ACCESS_IP between", value1, value2, "accessIp");
            return (Criteria) this;
        }

        public Criteria andAccessIpNotBetween(String value1, String value2) {
            addCriterion("ACCESS_IP not between", value1, value2, "accessIp");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("CLASS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("CLASS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("CLASS_NAME =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("CLASS_NAME <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("CLASS_NAME >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_NAME >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("CLASS_NAME <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("CLASS_NAME <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("CLASS_NAME like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("CLASS_NAME not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("CLASS_NAME in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("CLASS_NAME not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("CLASS_NAME between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("CLASS_NAME not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNull() {
            addCriterion("METHOD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNotNull() {
            addCriterion("METHOD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMethodNameEqualTo(String value) {
            addCriterion("METHOD_NAME =", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotEqualTo(String value) {
            addCriterion("METHOD_NAME <>", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThan(String value) {
            addCriterion("METHOD_NAME >", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThanOrEqualTo(String value) {
            addCriterion("METHOD_NAME >=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThan(String value) {
            addCriterion("METHOD_NAME <", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThanOrEqualTo(String value) {
            addCriterion("METHOD_NAME <=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLike(String value) {
            addCriterion("METHOD_NAME like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotLike(String value) {
            addCriterion("METHOD_NAME not like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIn(List<String> values) {
            addCriterion("METHOD_NAME in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotIn(List<String> values) {
            addCriterion("METHOD_NAME not in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameBetween(String value1, String value2) {
            addCriterion("METHOD_NAME between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotBetween(String value1, String value2) {
            addCriterion("METHOD_NAME not between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andOpParamsIsNull() {
            addCriterion("OP_PARAMS is null");
            return (Criteria) this;
        }

        public Criteria andOpParamsIsNotNull() {
            addCriterion("OP_PARAMS is not null");
            return (Criteria) this;
        }

        public Criteria andOpParamsEqualTo(String value) {
            addCriterion("OP_PARAMS =", value, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsNotEqualTo(String value) {
            addCriterion("OP_PARAMS <>", value, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsGreaterThan(String value) {
            addCriterion("OP_PARAMS >", value, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsGreaterThanOrEqualTo(String value) {
            addCriterion("OP_PARAMS >=", value, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsLessThan(String value) {
            addCriterion("OP_PARAMS <", value, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsLessThanOrEqualTo(String value) {
            addCriterion("OP_PARAMS <=", value, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsLike(String value) {
            addCriterion("OP_PARAMS like", value, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsNotLike(String value) {
            addCriterion("OP_PARAMS not like", value, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsIn(List<String> values) {
            addCriterion("OP_PARAMS in", values, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsNotIn(List<String> values) {
            addCriterion("OP_PARAMS not in", values, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsBetween(String value1, String value2) {
            addCriterion("OP_PARAMS between", value1, value2, "opParams");
            return (Criteria) this;
        }

        public Criteria andOpParamsNotBetween(String value1, String value2) {
            addCriterion("OP_PARAMS not between", value1, value2, "opParams");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoIsNull() {
            addCriterion("EXCEPTION_INFO is null");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoIsNotNull() {
            addCriterion("EXCEPTION_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoEqualTo(String value) {
            addCriterion("EXCEPTION_INFO =", value, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoNotEqualTo(String value) {
            addCriterion("EXCEPTION_INFO <>", value, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoGreaterThan(String value) {
            addCriterion("EXCEPTION_INFO >", value, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoGreaterThanOrEqualTo(String value) {
            addCriterion("EXCEPTION_INFO >=", value, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoLessThan(String value) {
            addCriterion("EXCEPTION_INFO <", value, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoLessThanOrEqualTo(String value) {
            addCriterion("EXCEPTION_INFO <=", value, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoLike(String value) {
            addCriterion("EXCEPTION_INFO like", value, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoNotLike(String value) {
            addCriterion("EXCEPTION_INFO not like", value, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoIn(List<String> values) {
            addCriterion("EXCEPTION_INFO in", values, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoNotIn(List<String> values) {
            addCriterion("EXCEPTION_INFO not in", values, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoBetween(String value1, String value2) {
            addCriterion("EXCEPTION_INFO between", value1, value2, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andExceptionInfoNotBetween(String value1, String value2) {
            addCriterion("EXCEPTION_INFO not between", value1, value2, "exceptionInfo");
            return (Criteria) this;
        }

        public Criteria andOpTsIsNull() {
            addCriterion("OP_TS is null");
            return (Criteria) this;
        }

        public Criteria andOpTsIsNotNull() {
            addCriterion("OP_TS is not null");
            return (Criteria) this;
        }

        public Criteria andOpTsEqualTo(Date value) {
            addCriterion("OP_TS =", value, "opTs");
            return (Criteria) this;
        }

        public Criteria andOpTsNotEqualTo(Date value) {
            addCriterion("OP_TS <>", value, "opTs");
            return (Criteria) this;
        }

        public Criteria andOpTsGreaterThan(Date value) {
            addCriterion("OP_TS >", value, "opTs");
            return (Criteria) this;
        }

        public Criteria andOpTsGreaterThanOrEqualTo(Date value) {
            addCriterion("OP_TS >=", value, "opTs");
            return (Criteria) this;
        }

        public Criteria andOpTsLessThan(Date value) {
            addCriterion("OP_TS <", value, "opTs");
            return (Criteria) this;
        }

        public Criteria andOpTsLessThanOrEqualTo(Date value) {
            addCriterion("OP_TS <=", value, "opTs");
            return (Criteria) this;
        }

        public Criteria andOpTsIn(List<Date> values) {
            addCriterion("OP_TS in", values, "opTs");
            return (Criteria) this;
        }

        public Criteria andOpTsNotIn(List<Date> values) {
            addCriterion("OP_TS not in", values, "opTs");
            return (Criteria) this;
        }

        public Criteria andOpTsBetween(Date value1, Date value2) {
            addCriterion("OP_TS between", value1, value2, "opTs");
            return (Criteria) this;
        }

        public Criteria andOpTsNotBetween(Date value1, Date value2) {
            addCriterion("OP_TS not between", value1, value2, "opTs");
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
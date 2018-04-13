package com.hhnz.config.model;

import com.hhnz.util.db.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProcessDeployExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public ProcessDeployExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDeployIdIsNull() {
            addCriterion("DEPLOY_ID is null");
            return (Criteria) this;
        }

        public Criteria andDeployIdIsNotNull() {
            addCriterion("DEPLOY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDeployIdEqualTo(String value) {
            addCriterion("DEPLOY_ID =", value, "deployId");
            return (Criteria) this;
        }

        public Criteria andDeployIdNotEqualTo(String value) {
            addCriterion("DEPLOY_ID <>", value, "deployId");
            return (Criteria) this;
        }

        public Criteria andDeployIdGreaterThan(String value) {
            addCriterion("DEPLOY_ID >", value, "deployId");
            return (Criteria) this;
        }

        public Criteria andDeployIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEPLOY_ID >=", value, "deployId");
            return (Criteria) this;
        }

        public Criteria andDeployIdLessThan(String value) {
            addCriterion("DEPLOY_ID <", value, "deployId");
            return (Criteria) this;
        }

        public Criteria andDeployIdLessThanOrEqualTo(String value) {
            addCriterion("DEPLOY_ID <=", value, "deployId");
            return (Criteria) this;
        }

        public Criteria andDeployIdIn(List<String> values) {
            addCriterion("DEPLOY_ID in", values, "deployId");
            return (Criteria) this;
        }

        public Criteria andDeployIdNotIn(List<String> values) {
            addCriterion("DEPLOY_ID not in", values, "deployId");
            return (Criteria) this;
        }

        public Criteria andDeployIdBetween(String value1, String value2) {
            addCriterion("DEPLOY_ID between", value1, value2, "deployId");
            return (Criteria) this;
        }

        public Criteria andDeployIdNotBetween(String value1, String value2) {
            addCriterion("DEPLOY_ID not between", value1, value2, "deployId");
            return (Criteria) this;
        }

        public Criteria andPkeyIsNull() {
            addCriterion("PKEY is null");
            return (Criteria) this;
        }

        public Criteria andPkeyIsNotNull() {
            addCriterion("PKEY is not null");
            return (Criteria) this;
        }

        public Criteria andPkeyEqualTo(String value) {
            addCriterion("PKEY =", value, "pkey");
            return (Criteria) this;
        }

        public Criteria andPkeyNotEqualTo(String value) {
            addCriterion("PKEY <>", value, "pkey");
            return (Criteria) this;
        }

        public Criteria andPkeyGreaterThan(String value) {
            addCriterion("PKEY >", value, "pkey");
            return (Criteria) this;
        }

        public Criteria andPkeyGreaterThanOrEqualTo(String value) {
            addCriterion("PKEY >=", value, "pkey");
            return (Criteria) this;
        }

        public Criteria andPkeyLessThan(String value) {
            addCriterion("PKEY <", value, "pkey");
            return (Criteria) this;
        }

        public Criteria andPkeyLessThanOrEqualTo(String value) {
            addCriterion("PKEY <=", value, "pkey");
            return (Criteria) this;
        }

        public Criteria andPkeyIn(List<String> values) {
            addCriterion("PKEY in", values, "pkey");
            return (Criteria) this;
        }

        public Criteria andPkeyNotIn(List<String> values) {
            addCriterion("PKEY not in", values, "pkey");
            return (Criteria) this;
        }

        public Criteria andPkeyBetween(String value1, String value2) {
            addCriterion("PKEY between", value1, value2, "pkey");
            return (Criteria) this;
        }

        public Criteria andPkeyNotBetween(String value1, String value2) {
            addCriterion("PKEY not between", value1, value2, "pkey");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(BigDecimal value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(BigDecimal value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(BigDecimal value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(BigDecimal value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<BigDecimal> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<BigDecimal> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andDeployDesIsNull() {
            addCriterion("DEPLOY_DES is null");
            return (Criteria) this;
        }

        public Criteria andDeployDesIsNotNull() {
            addCriterion("DEPLOY_DES is not null");
            return (Criteria) this;
        }

        public Criteria andDeployDesEqualTo(String value) {
            addCriterion("DEPLOY_DES =", value, "deployDes");
            return (Criteria) this;
        }

        public Criteria andDeployDesNotEqualTo(String value) {
            addCriterion("DEPLOY_DES <>", value, "deployDes");
            return (Criteria) this;
        }

        public Criteria andDeployDesGreaterThan(String value) {
            addCriterion("DEPLOY_DES >", value, "deployDes");
            return (Criteria) this;
        }

        public Criteria andDeployDesGreaterThanOrEqualTo(String value) {
            addCriterion("DEPLOY_DES >=", value, "deployDes");
            return (Criteria) this;
        }

        public Criteria andDeployDesLessThan(String value) {
            addCriterion("DEPLOY_DES <", value, "deployDes");
            return (Criteria) this;
        }

        public Criteria andDeployDesLessThanOrEqualTo(String value) {
            addCriterion("DEPLOY_DES <=", value, "deployDes");
            return (Criteria) this;
        }

        public Criteria andDeployDesIn(List<String> values) {
            addCriterion("DEPLOY_DES in", values, "deployDes");
            return (Criteria) this;
        }

        public Criteria andDeployDesNotIn(List<String> values) {
            addCriterion("DEPLOY_DES not in", values, "deployDes");
            return (Criteria) this;
        }

        public Criteria andDeployDesBetween(String value1, String value2) {
            addCriterion("DEPLOY_DES between", value1, value2, "deployDes");
            return (Criteria) this;
        }

        public Criteria andDeployDesNotBetween(String value1, String value2) {
            addCriterion("DEPLOY_DES not between", value1, value2, "deployDes");
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
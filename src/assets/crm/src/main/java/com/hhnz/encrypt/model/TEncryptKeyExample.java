package com.hhnz.encrypt.model;

import com.hhnz.util.db.Page;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TEncryptKeyExample {

//    private Page page;

//    public Page getPage() {
//        return page;
//    }
//
//    public void setPage(Page page) {
//        this.page = page;
//    }

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TEncryptKeyExample() {
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

        public Criteria andKeyNameIsNull() {
            addCriterion("KEY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andKeyNameIsNotNull() {
            addCriterion("KEY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andKeyNameEqualTo(String value) {
            addCriterion("KEY_NAME =", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameNotEqualTo(String value) {
            addCriterion("KEY_NAME <>", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameGreaterThan(String value) {
            addCriterion("KEY_NAME >", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_NAME >=", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameLessThan(String value) {
            addCriterion("KEY_NAME <", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameLessThanOrEqualTo(String value) {
            addCriterion("KEY_NAME <=", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameLike(String value) {
            addCriterion("KEY_NAME like", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameNotLike(String value) {
            addCriterion("KEY_NAME not like", value, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameIn(List<String> values) {
            addCriterion("KEY_NAME in", values, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameNotIn(List<String> values) {
            addCriterion("KEY_NAME not in", values, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameBetween(String value1, String value2) {
            addCriterion("KEY_NAME between", value1, value2, "keyName");
            return (Criteria) this;
        }

        public Criteria andKeyNameNotBetween(String value1, String value2) {
            addCriterion("KEY_NAME not between", value1, value2, "keyName");
            return (Criteria) this;
        }

        public Criteria andSystemNameIsNull() {
            addCriterion("SYSTEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSystemNameIsNotNull() {
            addCriterion("SYSTEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSystemNameEqualTo(String value) {
            addCriterion("SYSTEM_NAME =", value, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameNotEqualTo(String value) {
            addCriterion("SYSTEM_NAME <>", value, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameGreaterThan(String value) {
            addCriterion("SYSTEM_NAME >", value, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameGreaterThanOrEqualTo(String value) {
            addCriterion("SYSTEM_NAME >=", value, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameLessThan(String value) {
            addCriterion("SYSTEM_NAME <", value, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameLessThanOrEqualTo(String value) {
            addCriterion("SYSTEM_NAME <=", value, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameLike(String value) {
            addCriterion("SYSTEM_NAME like", value, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameNotLike(String value) {
            addCriterion("SYSTEM_NAME not like", value, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameIn(List<String> values) {
            addCriterion("SYSTEM_NAME in", values, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameNotIn(List<String> values) {
            addCriterion("SYSTEM_NAME not in", values, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameBetween(String value1, String value2) {
            addCriterion("SYSTEM_NAME between", value1, value2, "systemName");
            return (Criteria) this;
        }

        public Criteria andSystemNameNotBetween(String value1, String value2) {
            addCriterion("SYSTEM_NAME not between", value1, value2, "systemName");
            return (Criteria) this;
        }

        public Criteria andKeyDescIsNull() {
            addCriterion("KEY_DESC is null");
            return (Criteria) this;
        }

        public Criteria andKeyDescIsNotNull() {
            addCriterion("KEY_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andKeyDescEqualTo(String value) {
            addCriterion("KEY_DESC =", value, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescNotEqualTo(String value) {
            addCriterion("KEY_DESC <>", value, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescGreaterThan(String value) {
            addCriterion("KEY_DESC >", value, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_DESC >=", value, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescLessThan(String value) {
            addCriterion("KEY_DESC <", value, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescLessThanOrEqualTo(String value) {
            addCriterion("KEY_DESC <=", value, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescLike(String value) {
            addCriterion("KEY_DESC like", value, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescNotLike(String value) {
            addCriterion("KEY_DESC not like", value, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescIn(List<String> values) {
            addCriterion("KEY_DESC in", values, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescNotIn(List<String> values) {
            addCriterion("KEY_DESC not in", values, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescBetween(String value1, String value2) {
            addCriterion("KEY_DESC between", value1, value2, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyDescNotBetween(String value1, String value2) {
            addCriterion("KEY_DESC not between", value1, value2, "keyDesc");
            return (Criteria) this;
        }

        public Criteria andKeyVersionIsNull() {
            addCriterion("KEY_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andKeyVersionIsNotNull() {
            addCriterion("KEY_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andKeyVersionEqualTo(BigDecimal value) {
            addCriterion("KEY_VERSION =", value, "keyVersion");
            return (Criteria) this;
        }

        public Criteria andKeyVersionNotEqualTo(BigDecimal value) {
            addCriterion("KEY_VERSION <>", value, "keyVersion");
            return (Criteria) this;
        }

        public Criteria andKeyVersionGreaterThan(BigDecimal value) {
            addCriterion("KEY_VERSION >", value, "keyVersion");
            return (Criteria) this;
        }

        public Criteria andKeyVersionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("KEY_VERSION >=", value, "keyVersion");
            return (Criteria) this;
        }

        public Criteria andKeyVersionLessThan(BigDecimal value) {
            addCriterion("KEY_VERSION <", value, "keyVersion");
            return (Criteria) this;
        }

        public Criteria andKeyVersionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("KEY_VERSION <=", value, "keyVersion");
            return (Criteria) this;
        }

        public Criteria andKeyVersionIn(List<BigDecimal> values) {
            addCriterion("KEY_VERSION in", values, "keyVersion");
            return (Criteria) this;
        }

        public Criteria andKeyVersionNotIn(List<BigDecimal> values) {
            addCriterion("KEY_VERSION not in", values, "keyVersion");
            return (Criteria) this;
        }

        public Criteria andKeyVersionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("KEY_VERSION between", value1, value2, "keyVersion");
            return (Criteria) this;
        }

        public Criteria andKeyVersionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("KEY_VERSION not between", value1, value2, "keyVersion");
            return (Criteria) this;
        }

        public Criteria andKeyStateIsNull() {
            addCriterion("KEY_STATE is null");
            return (Criteria) this;
        }

        public Criteria andKeyStateIsNotNull() {
            addCriterion("KEY_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andKeyStateEqualTo(String value) {
            addCriterion("KEY_STATE =", value, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateNotEqualTo(String value) {
            addCriterion("KEY_STATE <>", value, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateGreaterThan(String value) {
            addCriterion("KEY_STATE >", value, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_STATE >=", value, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateLessThan(String value) {
            addCriterion("KEY_STATE <", value, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateLessThanOrEqualTo(String value) {
            addCriterion("KEY_STATE <=", value, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateLike(String value) {
            addCriterion("KEY_STATE like", value, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateNotLike(String value) {
            addCriterion("KEY_STATE not like", value, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateIn(List<String> values) {
            addCriterion("KEY_STATE in", values, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateNotIn(List<String> values) {
            addCriterion("KEY_STATE not in", values, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateBetween(String value1, String value2) {
            addCriterion("KEY_STATE between", value1, value2, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyStateNotBetween(String value1, String value2) {
            addCriterion("KEY_STATE not between", value1, value2, "keyState");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsIsNull() {
            addCriterion("KEY_UPDATE_TS is null");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsIsNotNull() {
            addCriterion("KEY_UPDATE_TS is not null");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsEqualTo(Date value) {
            addCriterion("KEY_UPDATE_TS =", value, "keyUpdateTs");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsNotEqualTo(Date value) {
            addCriterion("KEY_UPDATE_TS <>", value, "keyUpdateTs");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsGreaterThan(Date value) {
            addCriterion("KEY_UPDATE_TS >", value, "keyUpdateTs");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsGreaterThanOrEqualTo(Date value) {
            addCriterion("KEY_UPDATE_TS >=", value, "keyUpdateTs");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsLessThan(Date value) {
            addCriterion("KEY_UPDATE_TS <", value, "keyUpdateTs");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsLessThanOrEqualTo(Date value) {
            addCriterion("KEY_UPDATE_TS <=", value, "keyUpdateTs");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsIn(List<Date> values) {
            addCriterion("KEY_UPDATE_TS in", values, "keyUpdateTs");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsNotIn(List<Date> values) {
            addCriterion("KEY_UPDATE_TS not in", values, "keyUpdateTs");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsBetween(Date value1, Date value2) {
            addCriterion("KEY_UPDATE_TS between", value1, value2, "keyUpdateTs");
            return (Criteria) this;
        }

        public Criteria andKeyUpdateTsNotBetween(Date value1, Date value2) {
            addCriterion("KEY_UPDATE_TS not between", value1, value2, "keyUpdateTs");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeIsNull() {
            addCriterion("KEY_SENDTYPE is null");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeIsNotNull() {
            addCriterion("KEY_SENDTYPE is not null");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeEqualTo(String value) {
            addCriterion("KEY_SENDTYPE =", value, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeNotEqualTo(String value) {
            addCriterion("KEY_SENDTYPE <>", value, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeGreaterThan(String value) {
            addCriterion("KEY_SENDTYPE >", value, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_SENDTYPE >=", value, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeLessThan(String value) {
            addCriterion("KEY_SENDTYPE <", value, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeLessThanOrEqualTo(String value) {
            addCriterion("KEY_SENDTYPE <=", value, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeLike(String value) {
            addCriterion("KEY_SENDTYPE like", value, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeNotLike(String value) {
            addCriterion("KEY_SENDTYPE not like", value, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeIn(List<String> values) {
            addCriterion("KEY_SENDTYPE in", values, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeNotIn(List<String> values) {
            addCriterion("KEY_SENDTYPE not in", values, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeBetween(String value1, String value2) {
            addCriterion("KEY_SENDTYPE between", value1, value2, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySendtypeNotBetween(String value1, String value2) {
            addCriterion("KEY_SENDTYPE not between", value1, value2, "keySendtype");
            return (Criteria) this;
        }

        public Criteria andKeySenduriIsNull() {
            addCriterion("KEY_SENDURI is null");
            return (Criteria) this;
        }

        public Criteria andKeySenduriIsNotNull() {
            addCriterion("KEY_SENDURI is not null");
            return (Criteria) this;
        }

        public Criteria andKeySenduriEqualTo(String value) {
            addCriterion("KEY_SENDURI =", value, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriNotEqualTo(String value) {
            addCriterion("KEY_SENDURI <>", value, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriGreaterThan(String value) {
            addCriterion("KEY_SENDURI >", value, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_SENDURI >=", value, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriLessThan(String value) {
            addCriterion("KEY_SENDURI <", value, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriLessThanOrEqualTo(String value) {
            addCriterion("KEY_SENDURI <=", value, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriLike(String value) {
            addCriterion("KEY_SENDURI like", value, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriNotLike(String value) {
            addCriterion("KEY_SENDURI not like", value, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriIn(List<String> values) {
            addCriterion("KEY_SENDURI in", values, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriNotIn(List<String> values) {
            addCriterion("KEY_SENDURI not in", values, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriBetween(String value1, String value2) {
            addCriterion("KEY_SENDURI between", value1, value2, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeySenduriNotBetween(String value1, String value2) {
            addCriterion("KEY_SENDURI not between", value1, value2, "keySenduri");
            return (Criteria) this;
        }

        public Criteria andKeyTypeIsNull() {
            addCriterion("KEY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andKeyTypeIsNotNull() {
            addCriterion("KEY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andKeyTypeEqualTo(String value) {
            addCriterion("KEY_TYPE =", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotEqualTo(String value) {
            addCriterion("KEY_TYPE <>", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeGreaterThan(String value) {
            addCriterion("KEY_TYPE >", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_TYPE >=", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeLessThan(String value) {
            addCriterion("KEY_TYPE <", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeLessThanOrEqualTo(String value) {
            addCriterion("KEY_TYPE <=", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeLike(String value) {
            addCriterion("KEY_TYPE like", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotLike(String value) {
            addCriterion("KEY_TYPE not like", value, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeIn(List<String> values) {
            addCriterion("KEY_TYPE in", values, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotIn(List<String> values) {
            addCriterion("KEY_TYPE not in", values, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeBetween(String value1, String value2) {
            addCriterion("KEY_TYPE between", value1, value2, "keyType");
            return (Criteria) this;
        }

        public Criteria andKeyTypeNotBetween(String value1, String value2) {
            addCriterion("KEY_TYPE not between", value1, value2, "keyType");
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
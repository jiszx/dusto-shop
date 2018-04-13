package com.hhnz.crm.model;

import com.hhnz.util.db.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TMaterialPriceAdjustExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;
    
    protected boolean withMaterial;

    public TMaterialPriceAdjustExample() {
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

    public boolean isWithMaterial() {
		return withMaterial;
	}

	public void setWithMaterial(boolean withMaterial) {
		this.withMaterial = withMaterial;
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

        public Criteria andAdjustTypeIsNull() {
            addCriterion("ADJUST_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeIsNotNull() {
            addCriterion("ADJUST_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeEqualTo(String value) {
            addCriterion("ADJUST_TYPE =", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotEqualTo(String value) {
            addCriterion("ADJUST_TYPE <>", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeGreaterThan(String value) {
            addCriterion("ADJUST_TYPE >", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ADJUST_TYPE >=", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeLessThan(String value) {
            addCriterion("ADJUST_TYPE <", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeLessThanOrEqualTo(String value) {
            addCriterion("ADJUST_TYPE <=", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeLike(String value) {
            addCriterion("ADJUST_TYPE like", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotLike(String value) {
            addCriterion("ADJUST_TYPE not like", value, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeIn(List<String> values) {
            addCriterion("ADJUST_TYPE in", values, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotIn(List<String> values) {
            addCriterion("ADJUST_TYPE not in", values, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeBetween(String value1, String value2) {
            addCriterion("ADJUST_TYPE between", value1, value2, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustTypeNotBetween(String value1, String value2) {
            addCriterion("ADJUST_TYPE not between", value1, value2, "adjustType");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIsNull() {
            addCriterion("ADJUST_PARAM is null");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIsNotNull() {
            addCriterion("ADJUST_PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustParamEqualTo(String value) {
            addCriterion("ADJUST_PARAM =", value, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamNotEqualTo(String value) {
            addCriterion("ADJUST_PARAM <>", value, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamGreaterThan(String value) {
            addCriterion("ADJUST_PARAM >", value, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamGreaterThanOrEqualTo(String value) {
            addCriterion("ADJUST_PARAM >=", value, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamLessThan(String value) {
            addCriterion("ADJUST_PARAM <", value, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamLessThanOrEqualTo(String value) {
            addCriterion("ADJUST_PARAM <=", value, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamLike(String value) {
            addCriterion("ADJUST_PARAM like", value, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamNotLike(String value) {
            addCriterion("ADJUST_PARAM not like", value, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamIn(List<String> values) {
            addCriterion("ADJUST_PARAM in", values, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamNotIn(List<String> values) {
            addCriterion("ADJUST_PARAM not in", values, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamBetween(String value1, String value2) {
            addCriterion("ADJUST_PARAM between", value1, value2, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustParamNotBetween(String value1, String value2) {
            addCriterion("ADJUST_PARAM not between", value1, value2, "adjustParam");
            return (Criteria) this;
        }

        public Criteria andAdjustValIsNull() {
            addCriterion("ADJUST_VAL is null");
            return (Criteria) this;
        }

        public Criteria andAdjustValIsNotNull() {
            addCriterion("ADJUST_VAL is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustValEqualTo(BigDecimal value) {
            addCriterion("ADJUST_VAL =", value, "adjustVal");
            return (Criteria) this;
        }

        public Criteria andAdjustValNotEqualTo(BigDecimal value) {
            addCriterion("ADJUST_VAL <>", value, "adjustVal");
            return (Criteria) this;
        }

        public Criteria andAdjustValGreaterThan(BigDecimal value) {
            addCriterion("ADJUST_VAL >", value, "adjustVal");
            return (Criteria) this;
        }

        public Criteria andAdjustValGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ADJUST_VAL >=", value, "adjustVal");
            return (Criteria) this;
        }

        public Criteria andAdjustValLessThan(BigDecimal value) {
            addCriterion("ADJUST_VAL <", value, "adjustVal");
            return (Criteria) this;
        }

        public Criteria andAdjustValLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ADJUST_VAL <=", value, "adjustVal");
            return (Criteria) this;
        }

        public Criteria andAdjustValIn(List<BigDecimal> values) {
            addCriterion("ADJUST_VAL in", values, "adjustVal");
            return (Criteria) this;
        }

        public Criteria andAdjustValNotIn(List<BigDecimal> values) {
            addCriterion("ADJUST_VAL not in", values, "adjustVal");
            return (Criteria) this;
        }

        public Criteria andAdjustValBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ADJUST_VAL between", value1, value2, "adjustVal");
            return (Criteria) this;
        }

        public Criteria andAdjustValNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ADJUST_VAL not between", value1, value2, "adjustVal");
            return (Criteria) this;
        }

        public Criteria andAdjustOptIsNull() {
            addCriterion("ADJUST_OPT is null");
            return (Criteria) this;
        }

        public Criteria andAdjustOptIsNotNull() {
            addCriterion("ADJUST_OPT is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustOptEqualTo(String value) {
            addCriterion("ADJUST_OPT =", value, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptNotEqualTo(String value) {
            addCriterion("ADJUST_OPT <>", value, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptGreaterThan(String value) {
            addCriterion("ADJUST_OPT >", value, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptGreaterThanOrEqualTo(String value) {
            addCriterion("ADJUST_OPT >=", value, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptLessThan(String value) {
            addCriterion("ADJUST_OPT <", value, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptLessThanOrEqualTo(String value) {
            addCriterion("ADJUST_OPT <=", value, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptLike(String value) {
            addCriterion("ADJUST_OPT like", value, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptNotLike(String value) {
            addCriterion("ADJUST_OPT not like", value, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptIn(List<String> values) {
            addCriterion("ADJUST_OPT in", values, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptNotIn(List<String> values) {
            addCriterion("ADJUST_OPT not in", values, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptBetween(String value1, String value2) {
            addCriterion("ADJUST_OPT between", value1, value2, "adjustOpt");
            return (Criteria) this;
        }

        public Criteria andAdjustOptNotBetween(String value1, String value2) {
            addCriterion("ADJUST_OPT not between", value1, value2, "adjustOpt");
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

        public Criteria andAdjustDescIsNull() {
            addCriterion("ADJUST_DESC is null");
            return (Criteria) this;
        }

        public Criteria andAdjustDescIsNotNull() {
            addCriterion("ADJUST_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustDescEqualTo(String value) {
            addCriterion("ADJUST_DESC =", value, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescNotEqualTo(String value) {
            addCriterion("ADJUST_DESC <>", value, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescGreaterThan(String value) {
            addCriterion("ADJUST_DESC >", value, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescGreaterThanOrEqualTo(String value) {
            addCriterion("ADJUST_DESC >=", value, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescLessThan(String value) {
            addCriterion("ADJUST_DESC <", value, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescLessThanOrEqualTo(String value) {
            addCriterion("ADJUST_DESC <=", value, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescLike(String value) {
            addCriterion("ADJUST_DESC like", value, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescNotLike(String value) {
            addCriterion("ADJUST_DESC not like", value, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescIn(List<String> values) {
            addCriterion("ADJUST_DESC in", values, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescNotIn(List<String> values) {
            addCriterion("ADJUST_DESC not in", values, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescBetween(String value1, String value2) {
            addCriterion("ADJUST_DESC between", value1, value2, "adjustDesc");
            return (Criteria) this;
        }

        public Criteria andAdjustDescNotBetween(String value1, String value2) {
            addCriterion("ADJUST_DESC not between", value1, value2, "adjustDesc");
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

        public Criteria andAdjustCategoryIsNull() {
            addCriterion("ADJUST_CATEGORY is null");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryIsNotNull() {
            addCriterion("ADJUST_CATEGORY is not null");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryEqualTo(String value) {
            addCriterion("ADJUST_CATEGORY =", value, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryNotEqualTo(String value) {
            addCriterion("ADJUST_CATEGORY <>", value, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryGreaterThan(String value) {
            addCriterion("ADJUST_CATEGORY >", value, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("ADJUST_CATEGORY >=", value, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryLessThan(String value) {
            addCriterion("ADJUST_CATEGORY <", value, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryLessThanOrEqualTo(String value) {
            addCriterion("ADJUST_CATEGORY <=", value, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryLike(String value) {
            addCriterion("ADJUST_CATEGORY like", value, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryNotLike(String value) {
            addCriterion("ADJUST_CATEGORY not like", value, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryIn(List<String> values) {
            addCriterion("ADJUST_CATEGORY in", values, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryNotIn(List<String> values) {
            addCriterion("ADJUST_CATEGORY not in", values, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryBetween(String value1, String value2) {
            addCriterion("ADJUST_CATEGORY between", value1, value2, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andAdjustCategoryNotBetween(String value1, String value2) {
            addCriterion("ADJUST_CATEGORY not between", value1, value2, "adjustCategory");
            return (Criteria) this;
        }

        public Criteria andProcessIdIsNull() {
            addCriterion("PROCESS_ID is null");
            return (Criteria) this;
        }

        public Criteria andProcessIdIsNotNull() {
            addCriterion("PROCESS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProcessIdEqualTo(String value) {
            addCriterion("PROCESS_ID =", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotEqualTo(String value) {
            addCriterion("PROCESS_ID <>", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThan(String value) {
            addCriterion("PROCESS_ID >", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_ID >=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThan(String value) {
            addCriterion("PROCESS_ID <", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_ID <=", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdLike(String value) {
            addCriterion("PROCESS_ID like", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotLike(String value) {
            addCriterion("PROCESS_ID not like", value, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdIn(List<String> values) {
            addCriterion("PROCESS_ID in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotIn(List<String> values) {
            addCriterion("PROCESS_ID not in", values, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdBetween(String value1, String value2) {
            addCriterion("PROCESS_ID between", value1, value2, "processId");
            return (Criteria) this;
        }

        public Criteria andProcessIdNotBetween(String value1, String value2) {
            addCriterion("PROCESS_ID not between", value1, value2, "processId");
            return (Criteria) this;
        }
        
        public Criteria andRelatedMaterialIdEqualTo(String value) {
            addCriterion("R.MATERIAL_ID =", value, "R.MATERIAL_ID");
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
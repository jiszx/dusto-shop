package com.hhnz.crm.model;

import java.util.Date;

public class UserStations {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_STATIONS_V.ID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    private Long stationid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_STATIONS_V.STATIONNAME
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    private String stationname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_STATIONS_V.STATIONORGID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    private String stationorgid;
    
    private String provName;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_STATIONS_V.ORGID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    private String orgid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_STATIONS_V.ORGNAME
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    private String orgname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_STATIONS_V.STATES
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    private String states;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_STATIONS_V.SALESREP_ID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    private Long salesrepId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_STATIONS_V.USERNAME
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_STATIONS_V.DESCRIPTION
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER_STATIONS_V.CREATE_TS
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    private Date createTs;
    
    private String hbratio;
    
    

    public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_STATIONS_V.ID
     *
     * @return the value of USER_STATIONS_V.ID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
   

    public String getHbratio() {
		return hbratio;
	}

	public void setHbratio(String hbratio) {
		this.hbratio = hbratio;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_STATIONS_V.STATIONNAME
     *
     * @return the value of USER_STATIONS_V.STATIONNAME
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public String getStationname() {
        return stationname;
    }

    public Long getStationid() {
		return stationid;
	}

	public void setStationid(Long stationid) {
		this.stationid = stationid;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_STATIONS_V.STATIONNAME
     *
     * @param stationname the value for USER_STATIONS_V.STATIONNAME
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_STATIONS_V.STATIONORGID
     *
     * @return the value of USER_STATIONS_V.STATIONORGID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public String getStationorgid() {
        return stationorgid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_STATIONS_V.STATIONORGID
     *
     * @param stationorgid the value for USER_STATIONS_V.STATIONORGID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public void setStationorgid(String stationorgid) {
        this.stationorgid = stationorgid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_STATIONS_V.ORGID
     *
     * @return the value of USER_STATIONS_V.ORGID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public String getOrgid() {
        return orgid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_STATIONS_V.ORGID
     *
     * @param orgid the value for USER_STATIONS_V.ORGID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_STATIONS_V.ORGNAME
     *
     * @return the value of USER_STATIONS_V.ORGNAME
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public String getOrgname() {
        return orgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_STATIONS_V.ORGNAME
     *
     * @param orgname the value for USER_STATIONS_V.ORGNAME
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_STATIONS_V.STATES
     *
     * @return the value of USER_STATIONS_V.STATES
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public String getStates() {
        return states;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_STATIONS_V.STATES
     *
     * @param states the value for USER_STATIONS_V.STATES
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public void setStates(String states) {
        this.states = states;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_STATIONS_V.SALESREP_ID
     *
     * @return the value of USER_STATIONS_V.SALESREP_ID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public Long getSalesrepId() {
        return salesrepId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_STATIONS_V.SALESREP_ID
     *
     * @param salesrepId the value for USER_STATIONS_V.SALESREP_ID
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public void setSalesrepId(Long salesrepId) {
        this.salesrepId = salesrepId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_STATIONS_V.USERNAME
     *
     * @return the value of USER_STATIONS_V.USERNAME
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_STATIONS_V.USERNAME
     *
     * @param username the value for USER_STATIONS_V.USERNAME
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_STATIONS_V.DESCRIPTION
     *
     * @return the value of USER_STATIONS_V.DESCRIPTION
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_STATIONS_V.DESCRIPTION
     *
     * @param description the value for USER_STATIONS_V.DESCRIPTION
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER_STATIONS_V.CREATE_TS
     *
     * @return the value of USER_STATIONS_V.CREATE_TS
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public Date getCreateTs() {
        return createTs;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER_STATIONS_V.CREATE_TS
     *
     * @param createTs the value for USER_STATIONS_V.CREATE_TS
     *
     * @mbggenerated Thu Aug 04 15:26:43 CST 2016
     */
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }
}
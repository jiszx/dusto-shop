package com.hhnz.account.model;

import java.util.Date;

public class CMerchAccountAllocation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_ACCOUNT_ALLOCATION.ID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_ACCOUNT_ALLOCATION.UPACCOUNT_ID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    private Long upaccountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_ID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    private Long allocationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_DATE
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    private Date allocationDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_OID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    private Long allocationOid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_ACCOUNT_ALLOCATION.ID
     *
     * @return the value of C_MERCH_ACCOUNT_ALLOCATION.ID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_ACCOUNT_ALLOCATION.ID
     *
     * @param id the value for C_MERCH_ACCOUNT_ALLOCATION.ID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_ACCOUNT_ALLOCATION.UPACCOUNT_ID
     *
     * @return the value of C_MERCH_ACCOUNT_ALLOCATION.UPACCOUNT_ID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    public Long getUpaccountId() {
        return upaccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_ACCOUNT_ALLOCATION.UPACCOUNT_ID
     *
     * @param upaccountId the value for C_MERCH_ACCOUNT_ALLOCATION.UPACCOUNT_ID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    public void setUpaccountId(Long upaccountId) {
        this.upaccountId = upaccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_ID
     *
     * @return the value of C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_ID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    public Long getAllocationId() {
        return allocationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_ID
     *
     * @param allocationId the value for C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_ID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    public void setAllocationId(Long allocationId) {
        this.allocationId = allocationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_DATE
     *
     * @return the value of C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_DATE
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    public Date getAllocationDate() {
        return allocationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_DATE
     *
     * @param allocationDate the value for C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_DATE
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_OID
     *
     * @return the value of C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_OID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    public Long getAllocationOid() {
        return allocationOid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_OID
     *
     * @param allocationOid the value for C_MERCH_ACCOUNT_ALLOCATION.ALLOCATION_OID
     *
     * @mbggenerated Thu Nov 24 09:38:22 CST 2016
     */
    public void setAllocationOid(Long allocationOid) {
        this.allocationOid = allocationOid;
    }
}
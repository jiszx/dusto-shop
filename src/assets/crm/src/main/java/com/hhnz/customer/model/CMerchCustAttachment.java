package com.hhnz.customer.model;

import java.util.Date;

public class CMerchCustAttachment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ATTACHMENT.ID
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ATTACHMENT.MERCH_CUST_ID
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    private String merchCustId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ATTACHMENT.FILE_PATH
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    private String filePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ATTACHMENT.FILE_NAME
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    private String fileName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column C_MERCH_CUST_ATTACHMENT.UPLOAD_DATE
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    private Date uploadDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ATTACHMENT.ID
     *
     * @return the value of C_MERCH_CUST_ATTACHMENT.ID
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ATTACHMENT.ID
     *
     * @param id the value for C_MERCH_CUST_ATTACHMENT.ID
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ATTACHMENT.MERCH_CUST_ID
     *
     * @return the value of C_MERCH_CUST_ATTACHMENT.MERCH_CUST_ID
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    public String getMerchCustId() {
        return merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ATTACHMENT.MERCH_CUST_ID
     *
     * @param merchCustId the value for C_MERCH_CUST_ATTACHMENT.MERCH_CUST_ID
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    public void setMerchCustId(String merchCustId) {
        this.merchCustId = merchCustId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ATTACHMENT.FILE_PATH
     *
     * @return the value of C_MERCH_CUST_ATTACHMENT.FILE_PATH
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ATTACHMENT.FILE_PATH
     *
     * @param filePath the value for C_MERCH_CUST_ATTACHMENT.FILE_PATH
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ATTACHMENT.FILE_NAME
     *
     * @return the value of C_MERCH_CUST_ATTACHMENT.FILE_NAME
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ATTACHMENT.FILE_NAME
     *
     * @param fileName the value for C_MERCH_CUST_ATTACHMENT.FILE_NAME
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column C_MERCH_CUST_ATTACHMENT.UPLOAD_DATE
     *
     * @return the value of C_MERCH_CUST_ATTACHMENT.UPLOAD_DATE
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column C_MERCH_CUST_ATTACHMENT.UPLOAD_DATE
     *
     * @param uploadDate the value for C_MERCH_CUST_ATTACHMENT.UPLOAD_DATE
     *
     * @mbggenerated Mon Aug 15 16:58:22 CST 2016
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
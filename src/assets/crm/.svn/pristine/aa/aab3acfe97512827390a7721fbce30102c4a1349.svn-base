package com.hhnz.pub.model;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.google.common.base.Objects;
public class ChangeVar implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String branchId;
	
	private Long changeId;
	
	private String objectKey;
	
	private String objectName;
	
	private String columnName;
	
	private String changeType;
	
	private String changeValue;
	
	private String oldValue;
	
	private String changeOid;
	
	private Date changeTs;
	
	private String stat;

	public ChangeVar() {
	}

	public ChangeVar(Integer id) {
		this.id = id;
	}

	public static List<ChangeVar> constuct(Change c, Object osrc, Object odest) {
		List<ChangeVar> cvl = new ArrayList<ChangeVar>();
		final BeanWrapper src = new BeanWrapperImpl(osrc);
		final BeanWrapper dest = new BeanWrapperImpl(odest);
		PropertyDescriptor[] propertyDescriptors = dest.getPropertyDescriptors();
		for (PropertyDescriptor pd : propertyDescriptors) {
			String propertyName = pd.getName();
			Object spv = src.getPropertyValue(propertyName);
			Object dpv = dest.getPropertyValue(propertyName);
			//仅保存不为null且与原值不同的变更数据
			if (dpv != null && !Objects.equal(spv, dpv)) {
				if(StringUtils.isBlank(dpv.toString()) && spv == null){
					continue;
				}
				ChangeVar cv = new ChangeVar();
				cv.init(c);
				cv.setColumnName(propertyName);
				cv.setOldValue(spv == null?null:spv.toString());
				cv.setChangeValue(dpv.toString());
				cvl.add(cv);
			}
		}
		return cvl;
	}

	public void init(Change c) {
		this.setChangeId(c.getId());
		this.setBranchId(c.getBranchId());
		this.setChangeOid(c.getChangeOid());
		this.setChangeType(c.getChangeType());
		this.setObjectKey(c.getObjectKey());
		this.setObjectName(c.getObjectName());
		this.setStat(c.getStat());
		this.setChangeTs(new Date());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Long getChangeId() {
		return changeId;
	}

	public void setChangeId(Long changeId) {
		this.changeId = changeId;
	}

	public String getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getChangeValue() {
		return changeValue;
	}

	public void setChangeValue(String changeValue) {
		this.changeValue = changeValue;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getChangeOid() {
		return changeOid;
	}

	public void setChangeOid(String changeOid) {
		this.changeOid = changeOid;
	}

	public Date getChangeTs() {
		return changeTs;
	}

	public void setChangeTs(Date changeTs) {
		this.changeTs = changeTs;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}
}

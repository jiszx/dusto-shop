package com.hhnz.pub.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hhnz.pub.enu.ChangeStatus;
import com.hhnz.util.ApplicationContextUtil;
public class Change implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String branchId;
	
	private String changeTitle;
	
	private String changeType;;
	
	private String changeMemo;
	
	private String hasAttachment;
	
	private String changeOid;
	
	private Date changeTs;
	
	private Date checkTs;
	
	private String checkOid;
	
	private String stat;
	
	private String objectKey;
	
	private String objectName;
	
	private String processId;
	
	private List<ChangeVar> changeVars;
	
	public Change(){}
	public Change(Long id){this.id=id;}
	
	public void init(){
		this.setChangeTs(new Date());
		this.setStat(ChangeStatus.DRAFT);
		this.setChangeOid(ApplicationContextUtil.getCurrentUser()==null?"":ApplicationContextUtil.getCurrentUser().getId().toString());
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getChangeTitle() {
		return changeTitle;
	}
	public void setChangeTitle(String changeTitle) {
		this.changeTitle = changeTitle;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	public String getChangeMemo() {
		return changeMemo;
	}
	public void setChangeMemo(String changeMemo) {
		this.changeMemo = changeMemo;
	}
	public String getHasAttachment() {
		return hasAttachment;
	}
	public void setHasAttachment(String hasAttachment) {
		this.hasAttachment = hasAttachment;
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
	public Date getCheckTs() {
		return checkTs;
	}
	public void setCheckTs(Date checkTs) {
		this.checkTs = checkTs;
	}
	public String getCheckOid() {
		return checkOid;
	}
	public void setCheckOid(String checkOid) {
		this.checkOid = checkOid;
	}
	public ChangeStatus getStatEnu() {
		return ChangeStatus.toEnum(stat);
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public void setStat(ChangeStatus stat) {
		this.stat = stat.getCode();
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
	public List<ChangeVar> getChangeVars() {
		return changeVars;
	}
	public void setChangeVars(List<ChangeVar> changeVars) {
		this.changeVars = changeVars;
	}
}

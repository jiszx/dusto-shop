package com.hhnz.combination.dto;

import java.util.List;

import com.hhnz.combination.model.CrmMaterialPackageHeader;
import com.hhnz.combination.model.CrmMaterialPackageLines;
import com.hhnz.combination.model.CrmMaterialPackageRebate;

public class CombinationDTO {
	private CrmMaterialPackageHeader header;
	private List<CrmMaterialPackageLines> lines;
	private List<CrmMaterialPackageRebate> rebates;
	public CrmMaterialPackageHeader getHeader() {
		return header;
	}
	public void setHeader(CrmMaterialPackageHeader header) {
		this.header = header;
	}
	public List<CrmMaterialPackageLines> getLines() {
		return lines;
	}
	public void setLines(List<CrmMaterialPackageLines> lines) {
		this.lines = lines;
	}
	public List<CrmMaterialPackageRebate> getRebates() {
		return rebates;
	}
	public void setRebates(List<CrmMaterialPackageRebate> rebates) {
		this.rebates = rebates;
	}
	
	
}

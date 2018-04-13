package com.hhnz.crm.dto;

import java.util.List;

public class ProductPriceQueryDTO {

	private String attribute1;
	
	private String series;

	private List<String> sapIds;
	
	public List<String> getSapIds() {
		return sapIds;
	}

	public void setSapIds(List<String> sapIds) {
		this.sapIds = sapIds;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

}
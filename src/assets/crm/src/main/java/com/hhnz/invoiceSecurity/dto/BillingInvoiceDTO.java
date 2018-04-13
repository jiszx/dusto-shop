package com.hhnz.invoiceSecurity.dto;

import java.util.List;

public class BillingInvoiceDTO {
	private  HeadersDTO  header;
	
	private List<ItemsDTO> items;
	
	public HeadersDTO getHeader() {
		return header;
	}

	public void setHeader(HeadersDTO header) {
		this.header = header;
	}
	
	public List<ItemsDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemsDTO> items) {
		this.items = items;
	}

	
	
}

package com.hhnz.account.uti;

public enum AccountLogTypeEnum {
	accountUp("1", "现金上账"),
	contractRebate("2", "合同返利"),
	adjust("3", "资金调整"),
	order("4", "订单取消"),
	orderBack("5", "订单取消"),
	orderClose("6","订单关闭"),
	orderRebut("7","订单审批驳回"),
	allocationOrder("8","调拨单"),
	backOrder("9","退货订单"),
	allocationOrderAdjust("11","调拨单手工调整"),
	allocationOrderClose("12","调拨单关闭");
	private String context;
	private String code;

	private AccountLogTypeEnum(String code, String context) {
		this.context = context;
		this.code = code;
	}
	// 普通方法  
    public static String getName(String  code) {  
        for (AccountLogTypeEnum c : AccountLogTypeEnum.values()) {  
            if (c.getCode().equals(code)) {  
                return c.getContext();  
            }  
        }  
        return null;  
    } 
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

package com.hhnz.account.uti;


public enum AccountTypeEnum {
	cash("1", "现金"),
	subsidy("2", "货补"),
	credit("3", "授信"),
	bond("4", "保证金");
	private String context;
	private String code;

	private AccountTypeEnum(String code, String context) {
		this.context = context;
		this.code = code;
	}
	// 普通方法  
    public static String getName(String  code) {  
        for (AccountTypeEnum c : AccountTypeEnum.values()) {  
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

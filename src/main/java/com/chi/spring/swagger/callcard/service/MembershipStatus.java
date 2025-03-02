package com.chi.spring.swagger.callcard.service;

public enum MembershipStatus {

	VALID		(1, "有效"  ),
	INVALID		(2, "無效"  ),
	CANCELLED	(3, "已註銷"),
	DISABLED	(4, "已核准"),
	APPROVED	(6, "未核准"),
	UNDER_REVIEW(7, "審核中"),
	NOT_APPROVED(8, "已停用");
	
	private int statusCode;
	private String statusValue;
	
	MembershipStatus(int statusCode, String statusValue) {
		this.statusCode = statusCode;
		this.statusValue = statusValue;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public String getStatusValue() {
		return statusValue;
	}
}

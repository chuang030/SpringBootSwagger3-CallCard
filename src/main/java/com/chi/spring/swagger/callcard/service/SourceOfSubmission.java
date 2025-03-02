package com.chi.spring.swagger.callcard.service;

public enum SourceOfSubmission {
	PAPER(1, "紙本");
	
	private int sourceNumber;
	private String sourceValue;
	
	SourceOfSubmission(int sourceNumber, String sourceValue) {
		this.sourceNumber = sourceNumber;
		this.sourceValue = sourceValue;
	}
	
	public int getSourceNumber() {
		return sourceNumber;
	}
	
	public String getSourceValue() {
		return sourceValue;
	}
}

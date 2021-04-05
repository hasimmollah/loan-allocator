package com.hasim.loanallocation.data;

import com.google.gson.JsonObject;

public class InvestorVO {
	
	String name;
	double initialAmount;
	
	JsonObject rule;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getInitialAmount() {
		return initialAmount;
	}
	public void setInitialAmount(double initialAmount) {
		this.initialAmount = initialAmount;
	}
	public JsonObject getRule() {
		return rule;
	}
	public void setRule(JsonObject rule) {
		this.rule = rule;
	}
	
}

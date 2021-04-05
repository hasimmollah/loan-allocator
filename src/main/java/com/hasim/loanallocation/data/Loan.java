package com.hasim.loanallocation.data;

public class Loan {
	String id;
	String category;
	String riskBand;
	double amount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRiskBand() {
		return riskBand;
	}
	public void setRiskBand(String riskBand) {
		this.riskBand = riskBand;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String toString() {
		return "id: "+id+" category: " + category+" riskBand: "+ riskBand+" amount: "+amount;
	}

}

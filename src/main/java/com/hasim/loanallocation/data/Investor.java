package com.hasim.loanallocation.data;

public class Investor {
	
	String name;
	double initialAmount;
	
	Rule rule;
	double availableAmount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(double availableAmount) {
		this.availableAmount = availableAmount;
	}
	public boolean equals(Object obj) {
		if(obj==this) {
			return true;
		}
		if(obj==null) {
			return false;
		}
		
		if(obj instanceof Investor) {
			Investor soure =(Investor) obj;
			if(soure.getName().equalsIgnoreCase(this.getName())) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public int hashCode() {
		return this.getName().hashCode();
	}

	public String toString() {
		return "Name: "+name + " available amount: "+availableAmount;
	}
	public double getInitialAmount() {
		return initialAmount;
	}
	public void setInitialAmount(double initialAmount) {
		this.initialAmount = initialAmount;
	}
	public Rule getRule() {
		return rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
}

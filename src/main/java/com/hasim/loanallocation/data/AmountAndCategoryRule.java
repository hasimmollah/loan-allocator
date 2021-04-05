package com.hasim.loanallocation.data;

import java.util.List;

public class AmountAndCategoryRule extends Rule{
	
	List<String> categories;
	double amount;
	
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	

}

package com.hasim.loanallocation.data;

import java.util.List;

public class CategoryAndRiskBandRule extends Rule{
	
	List<String> categories;
	List<String> riskBand;
	
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public List<String> getRiskBand() {
		return riskBand;
	}
	public void setRiskBand(List<String> riskBand) {
		this.riskBand = riskBand;
	}

}

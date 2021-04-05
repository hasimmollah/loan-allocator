package com.hasim.loanallocation.data;

import java.util.List;

public class RiskBandRule extends Rule{
	
	List<String> riskBand;
	
	
	public List<String> getRiskBand() {
		return riskBand;
	}
	public void setRiskBand(List<String> riskBand) {
		this.riskBand = riskBand;
	}

}

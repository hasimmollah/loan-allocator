package com.hasim.loanallocation.rule;

import com.hasim.loanallocation.data.CategoryAndRiskBandRule;
import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.data.Loan;

public class CategoryAndRiskRuleProcessor implements RuleProcessor{
	public boolean isEligible(Investor investor, Loan loan) {
		CategoryAndRiskBandRule categoryAndRiskBandRule = (CategoryAndRiskBandRule) investor.getRule();
		boolean isEligible = categoryAndRiskBandRule.getCategories().contains(loan.getCategory())
				&& categoryAndRiskBandRule.getRiskBand().contains(loan.getRiskBand())
				&& investor.getAvailableAmount() >= loan.getAmount();
		return isEligible;
	}

}

package com.hasim.loanallocation.rule;

import com.hasim.loanallocation.data.CategoryRule;
import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.data.Loan;

public class CategoryRuleProcessor implements RuleProcessor{
	public boolean isEligible(Investor investor, Loan loan) {
		CategoryRule categoryRule = (CategoryRule) investor.getRule();
		boolean isEligible = categoryRule.getCategories().contains(loan.getCategory())
				&& investor.getAvailableAmount() >= loan.getAmount();
		return isEligible;
	}

}

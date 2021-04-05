package com.hasim.loanallocation.rule;

import com.hasim.loanallocation.data.AmountAndCategoryRule;
import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.data.Loan;

public class AmountAndCategoryRuleProcessor implements RuleProcessor{
	public boolean isEligible(Investor investor, Loan loan) {
		AmountAndCategoryRule amountAndCategoryRule = (AmountAndCategoryRule) investor.getRule();
		boolean isEligible = amountAndCategoryRule.getCategories().contains(loan.getCategory())
				&& investor.getAvailableAmount() >= loan.getAmount()
				&& (investor.getInitialAmount() - investor.getAvailableAmount()) <= amountAndCategoryRule.getAmount()
				;
		return isEligible;
	}

}

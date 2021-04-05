package com.hasim.loanallocation.rule;

import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.data.Loan;
import com.hasim.loanallocation.data.RiskBandRule;

public class RiskBandRuleProcessor implements RuleProcessor {
	public boolean isEligible(Investor investor, Loan loan) {
		RiskBandRule riskBandRule = (RiskBandRule) investor.getRule();
		boolean isEligible = riskBandRule.getRiskBand().contains(loan.getRiskBand())
				&& investor.getAvailableAmount() >= loan.getAmount();
		return isEligible;
	}

}

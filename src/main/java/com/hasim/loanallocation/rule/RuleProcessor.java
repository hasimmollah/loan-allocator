package com.hasim.loanallocation.rule;

import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.data.Loan;

public interface RuleProcessor {
	public boolean isEligible(Investor investor, Loan loan);

}

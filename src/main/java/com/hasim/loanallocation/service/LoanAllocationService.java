package com.hasim.loanallocation.service;

import java.util.List;
import java.util.Map;

import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.data.Loan;

public interface LoanAllocationService {

	public Map<Investor, List<Loan>> allocate(List<Investor> investors, List<Loan> loans);

}

package com.hasim.loanallocation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.data.Loan;
import com.hasim.loanallocation.rule.RuleProcessorFactory;

public class LoanAllocationServiceImpl implements LoanAllocationService{
	private static final Logger logger = LogManager.getLogger(LoanAllocationServiceImpl.class);

	public Map<Investor, List<Loan>> allocate(List<Investor> investors, List<Loan> loans) {
		logger.info("allocate started");
		 Map<Investor, List<Loan>> finalMap = new HashMap();
		 Map<String, Investor> investorMap = investors.stream().collect(Collectors.toMap(Investor::getName, Function.identity()));
		 Map<String, Investor> availableInvestorMap = new ConcurrentHashMap();
		 
		 loans.stream().forEach(loan->{
			 logger.debug(" processing :: " + loan);
			 if(availableInvestorMap.isEmpty()) {
				 availableInvestorMap.putAll(investorMap);
			 }
			
			 Optional<Investor> optionalInvestor = investors.stream().filter(investor->isEligible(investor, loan, availableInvestorMap)).findFirst();
			 
			 if(optionalInvestor.isPresent()) {
				 updateAvailableMap(optionalInvestor.get(), loan, finalMap, availableInvestorMap);
			 } else {
				 availableInvestorMap.clear();
				 availableInvestorMap.putAll(investorMap);
				 Optional<Investor> optionalInvestorUpdated = investors.stream().filter(investor->isEligible(investor, loan, availableInvestorMap)).findFirst();
				 if(optionalInvestorUpdated.isPresent()) {
					 updateAvailableMap(optionalInvestorUpdated.get(), loan, finalMap, availableInvestorMap);
				 }
			 }
		 });
		 logger.debug("finalMap:::::::"+ finalMap);
		 logger.info("allocate finished");
		return finalMap;
	}
	
	private void updateAvailableMap(Investor selectedInvestor, Loan loan, Map<Investor, List<Loan>> finalMap, Map<String, Investor> availableInvestorMap) {
		logger.info("updateAvailableMap started");
		logger.debug(" selectedInvestor :: " + selectedInvestor);
		List<Loan> allocatedLoan = finalMap.get(selectedInvestor);
		 allocatedLoan = allocatedLoan!=null?allocatedLoan:new ArrayList();
		 allocatedLoan.add(loan);
		 finalMap.put(selectedInvestor,allocatedLoan);
		 selectedInvestor.setAvailableAmount(selectedInvestor.getAvailableAmount()-loan.getAmount());
		 availableInvestorMap.remove(selectedInvestor.getName());
		 logger.info("updateAvailableMap finished");
	}
	
	private boolean isEligible(Investor investor, Loan loan,  Map<String, Investor> availableInvestorMap) {
		boolean isEligible = RuleProcessorFactory.getRuleProcessor(investor.getRule().getClass()).isEligible(investor, loan)
				&& availableInvestorMap.containsKey(investor.getName())
				;
		logger.debug(" availableInvestorMap :: " + availableInvestorMap);
		logger.debug(" isEligible :: " + isEligible);
		return isEligible;
	}

}

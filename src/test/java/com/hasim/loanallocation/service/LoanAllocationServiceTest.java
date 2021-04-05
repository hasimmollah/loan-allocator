package com.hasim.loanallocation.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hasim.loanallocation.data.CategoryRule;
import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.data.Loan;
import com.hasim.loanallocation.data.RiskBandRule;
public class LoanAllocationServiceTest {
	
	LoanAllocationService loanAllocationService;
	
	@BeforeEach
	public void setup() {
		loanAllocationService = new LoanAllocationServiceImpl();
	}
	
	
	@Test
	public void shouldAllocateLoanToInvestor() {
		List<Investor> investors = new ArrayList();
		Investor investorBob = new Investor();
		investorBob.setAvailableAmount(3000);
		CategoryRule rule = new CategoryRule();
		rule.setCategories(Arrays.asList("property"));
		investorBob.setRule(rule);
		investorBob.setName("Bob");
		investors.add(investorBob);
		
		
		Investor investorSusan = new Investor();
		investorSusan.setAvailableAmount(1000);
		CategoryRule ruleSusan = new CategoryRule();
		ruleSusan.setCategories(Arrays.asList("property", "retail"));
		investorSusan.setRule(ruleSusan);
		investorSusan.setName("Susan");
		investors.add(investorSusan);
		
		Investor investorGeorge = new Investor();
		investorGeorge.setAvailableAmount(1000);
		RiskBandRule riskBandRule = new RiskBandRule();
		
		riskBandRule.setRiskBand(Arrays.asList("A"));
		investorGeorge.setRule(riskBandRule);
		investorGeorge.setName("George");
		investors.add(investorGeorge);
		
		List<Loan> loans = new ArrayList();
		Loan loan = new Loan();
		loan.setAmount(1000);
		loan.setCategory("property");
		loan.setRiskBand("A");
		loan.setId("1");
		loans.add(loan);
		
		Loan loan1 = new Loan();
		loan1.setAmount(1000);
		loan1.setCategory("retail");
		loan1.setRiskBand("A");
		loan1.setId("2");
		loans.add(loan1);
		
		
		Loan loan2 = new Loan();
		loan2.setAmount(1000);
		loan2.setCategory("retail");
		loan2.setRiskBand("A");
		loan2.setId("3");
		loans.add(loan2);
		
		Loan loan3 = new Loan();
		loan3.setAmount(1000);
		loan3.setCategory("property");
		loan3.setRiskBand("A");
		loan3.setId("4");
		loans.add(loan3);
		
		Loan loan4 = new Loan();
		loan4.setAmount(1000);
		loan4.setCategory("property");
		loan4.setRiskBand("B");
		loan4.setId("5");
		loans.add(loan4);
		
		Map<Investor,List<Loan>> investorToLoanMap = loanAllocationService.allocate(investors,loans);
		System.out.println(investorToLoanMap);
		
		//ObjectMapper om = new ObjectMapper();
		assertTrue(investorToLoanMap.keySet().size()==investors.size());

	}
}

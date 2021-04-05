package com.hasim.loanallocation.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.data.Loan;
import com.hasim.loanallocation.data.LoanVO;
import com.hasim.loanallocation.service.LoanAllocationService;
import com.hasim.loanallocation.service.LoanAllocationServiceImpl;

public class LoanProcessor implements Processor{
	private static final Logger logger = LogManager.getLogger(LoanProcessor.class);

	Map<Investor, List<Loan>>  investorLoadMap = new ConcurrentHashMap();
	@Override
	public void process(Path fileDetails, List<Investor> investors) {
		logger.info("process method started");
		
		try {
		BufferedReader br = new BufferedReader(new FileReader(fileDetails.toAbsolutePath().toString()));
		LoanVO[] loanVOArr = new Gson().fromJson(br, LoanVO[].class);  
		List<Loan> loans = Arrays.asList(loanVOArr).stream().map((loanVO)->{
			Loan loandata = new Loan();
			loandata.setAmount(loanVO.getAmount());
			loandata.setCategory(loanVO.getCategory());
			loandata.setId(loanVO.getId());
			loandata.setRiskBand(loanVO.getRiskBand());
			return loandata;
		}).collect(Collectors.toList());
		
		LoanAllocationService loanAllocationService = new LoanAllocationServiceImpl();
		Map<Investor, List<Loan>>  investorLoadMapUpdated = loanAllocationService.allocate(investors, loans);
		investorLoadMap.entrySet().forEach(investorLoadMapData->{
			Investor currInvestor = investorLoadMapData.getKey();
			List<Loan>  newLoans = investorLoadMapUpdated.get(currInvestor);
			if(newLoans!=null) {
				investorLoadMapData.getValue().addAll(newLoans);
			}
			
		});
		
		investorLoadMapUpdated.entrySet().forEach(investorLoadMapUpdatedData->{
			Investor currInvestor = investorLoadMapUpdatedData.getKey();
			List<Loan>  newLoans =investorLoadMap.get(currInvestor);
			if(newLoans==null) {
				investorLoadMap.put(currInvestor, investorLoadMapUpdatedData.getValue());
			}
		});
		
		} catch(Exception e) {
			logger.error("Error occurred", e);
			
		}
		logger.info("process method finished");
		
	}

}

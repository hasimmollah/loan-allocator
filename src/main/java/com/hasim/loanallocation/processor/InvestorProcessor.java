package com.hasim.loanallocation.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hasim.loanallocation.data.AmountAndCategoryRule;
import com.hasim.loanallocation.data.CategoryAndRiskBandRule;
import com.hasim.loanallocation.data.CategoryRule;
import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.data.InvestorVO;
import com.hasim.loanallocation.data.RiskBandRule;
import com.hasim.loanallocation.data.Rule;

public class InvestorProcessor implements Processor{
	private static final Logger logger = LogManager.getLogger(InvestorProcessor.class);

	
	Map<String, Class> ruleMap ;
	
	public InvestorProcessor(){
		ruleMap =  new HashMap();
		ruleMap.put("CategoryRule", CategoryRule.class);
		ruleMap.put("RiskBandRule", RiskBandRule.class);
		ruleMap.put("CategoryAndRiskBandRule", CategoryAndRiskBandRule.class);
		ruleMap.put("AmountAndCategoryRule", AmountAndCategoryRule.class);
	}
	
	@Override
	public void process(Path fileDetails, List<Investor> investors) {
		logger.info("process method started");
		try {
			Map<String, Investor> investorMap = investors.stream().collect(Collectors.toMap(Investor::getName,Function.identity()));
			
			
			BufferedReader br = new BufferedReader(new FileReader(fileDetails.toAbsolutePath().toString()));
			InvestorVO[] investorVOArr = new Gson().fromJson(br,InvestorVO[].class);  
			List<Investor> investorDataList = Arrays.asList(investorVOArr).stream().map((investorVO)->{
				Investor investordata = new Investor();
				investordata.setAvailableAmount(investorVO.getInitialAmount());
				investordata.setInitialAmount(investorVO.getInitialAmount());
				investordata.setName(investorVO.getName());
				investordata.setRule(prepareRule(investorVO.getRule()));
				return investordata;
			}).collect(Collectors.toList());
			
			Map<String, Investor> investorDataListMap = investorDataList.stream().collect(Collectors.toMap(Investor::getName,Function.identity()));
			
			investorDataListMap.entrySet().forEach(investorDataListMapData->{
				String name = investorDataListMapData.getKey();
				
				Investor investortoUpdate = investorMap.get(name);
				Investor investortoNewData = investorDataListMapData.getValue();
				if(investortoUpdate!=null) {
					
					
					investortoUpdate.setInitialAmount(investortoNewData.getInitialAmount());
					investortoUpdate.setName(investortoNewData.getName());
					investortoUpdate.setRule(investortoNewData.getRule());
					
					
				} else {
					investors.add(investortoNewData);
				}
			});
			
			
			
			} catch(Exception e) {
				logger.error("Error occurred" , e);
			}
		logger.info("investors:::::"+ investors);
		logger.info("Process method finished");
	}
	
	private Rule prepareRule(JsonObject jsonRule) {
		String ruleName = jsonRule.get("name").getAsString();
		return  (Rule) new Gson().fromJson(jsonRule.get("details"), ruleMap.get(ruleName));
		
		
		
	}
}

package com.hasim.loanallocation.rule;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.hasim.loanallocation.data.AmountAndCategoryRule;
import com.hasim.loanallocation.data.CategoryAndRiskBandRule;
import com.hasim.loanallocation.data.CategoryRule;
import com.hasim.loanallocation.data.RiskBandRule;

public class RuleProcessorFactory {
	static Map<Class, RuleProcessor> ruleProcessorMap = new HashMap();
	static {
		ruleProcessorMap.put(CategoryRule.class, new CategoryRuleProcessor());
		ruleProcessorMap.put(RiskBandRule.class, new RiskBandRuleProcessor());
		ruleProcessorMap.put(CategoryAndRiskBandRule.class, new CategoryAndRiskRuleProcessor());
		ruleProcessorMap.put(AmountAndCategoryRule.class, new AmountAndCategoryRuleProcessor());
	}
	
	
	public static RuleProcessor getRuleProcessor(Class clas) {
		return Optional.ofNullable(ruleProcessorMap.get(clas)).orElseThrow(()->new RuntimeException());
	}

	
}



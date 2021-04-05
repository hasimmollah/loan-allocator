package com.hasim.loanallocation.processor;

import java.util.HashMap;
import java.util.Map;

import com.hasim.loanallocation.exception.ApplicationException;


public class ProcessorFactory {
	private static Map<String,Processor> fileProcessorMap = new HashMap();
	static {
		fileProcessorMap.put("LOAN",new LoanProcessor());
		fileProcessorMap.put("INVESTOR",new InvestorProcessor());
	}
	
	public static Processor getProcessor(String fileName) {
		Processor processor = fileProcessorMap.get(fileName);
		if(processor==null) {
			throw new ApplicationException("Extension Not supported");
		}
		return processor;
	}

}

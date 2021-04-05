package com.hasim.loanallocation.processor;

import java.nio.file.Path;
import java.util.List;

import com.hasim.loanallocation.data.Investor;

public interface Processor {
	public void process(Path fileDetails, List<Investor> investors);

}

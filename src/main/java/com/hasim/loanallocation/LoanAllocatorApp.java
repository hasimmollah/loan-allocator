package com.hasim.loanallocation;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class LoanAllocatorApp {
	 final static Logger LOGGER = Logger.getLogger(LoanAllocatorApp.class);

	public static void main(String[] args) {

		DirectoryManager directoryManager = new DirectoryManager();
		Scanner in = new Scanner(System.in);
		LOGGER.info("Please enter the directory path to watch");
		String directoryPath = in.nextLine();

		directoryManager.watchDirectory(directoryPath);
	}

}

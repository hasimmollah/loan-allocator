package com.hasim.loanallocation;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.hasim.loanallocation.data.Investor;
import com.hasim.loanallocation.processor.Processor;
import com.hasim.loanallocation.processor.ProcessorFactory;

public class DirectoryManager {
	final static Logger LOGGER = Logger.getLogger(DirectoryManager.class);

	private WatchService registerPathAndPrepareWatchService(String directoryPath) {
		WatchService watchService = null;
		try {
			watchService = FileSystems.getDefault().newWatchService();

			Path path = Paths.get(directoryPath);

			path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		} catch (Exception e) {

		}

		return watchService;

	}

	public void watchDirectory(String directoryPath) {
		processDirectory(registerPathAndPrepareWatchService(directoryPath));
	}
	private void processDirectory(WatchService watchService) {
		LOGGER.info("Started watching");
		try {
			
			WatchKey key;
			List<Investor> investors = new CopyOnWriteArrayList();
			while ((key = watchService.take()) != null) {
				LOGGER.info("Searching for inserted file in the directory");
						Path path = (Path) key.watchable();
				for (WatchEvent<?> event : key.pollEvents()) {
					try {
						LOGGER.info("Event kind:" + event.kind() + ". File found : " + event.context() + ".");
						Path fileDetails = path.resolve(((WatchEvent<Path>) event).context());
						String fileFullPath = fileDetails.getFileName().toAbsolutePath().toString();
						LOGGER.info("Processing file : " + fileFullPath.toString());
						
						String extension = (event.context().toString().startsWith("LOAN")||event.context().toString().startsWith("loan"))?"LOAN":"INVESTOR";

						Processor processor = ProcessorFactory.getProcessor(extension.toUpperCase());
						processor.process(fileDetails,investors);
						

					} catch (Exception e) {
						LOGGER.error("Exception occurred while processing", e);
					}

				}
				key.reset();
			}
			
		} catch (Exception e) {
			LOGGER.error("Exception occurred while watching the directory", e);
		}
		LOGGER.info("Finished watching");
	}
}

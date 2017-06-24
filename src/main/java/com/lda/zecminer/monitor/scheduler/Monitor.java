package com.lda.zecminer.monitor.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Monitor {

	private static final Logger log = LoggerFactory.getLogger(Monitor.class);
	
	@Scheduled(initialDelay = 3000, fixedRate = 5000)
	public void checkMiners()
	{
		log.info("Scheduled work");
	}
}

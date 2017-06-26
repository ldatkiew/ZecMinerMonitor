package com.lda.zecminer.monitor.restarter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevRestarter implements RestarterI{

	private final static Logger log = LoggerFactory.getLogger(DevRestarter.class);
	
	@Override
	public void restart()
	{
		log.info("It is empty dev restarter - only log");
	}
}

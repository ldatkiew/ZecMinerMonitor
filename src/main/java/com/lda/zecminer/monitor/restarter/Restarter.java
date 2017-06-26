package com.lda.zecminer.monitor.restarter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class Restarter implements RestarterI {

	private final static Logger log = LoggerFactory.getLogger(Restarter.class);
	
	private static final String RESTART_COMMAND = "shutdown.exe -r -t 0";

	@Override
	public void restart()
	{
		    try {
				Runtime.getRuntime().exec(RESTART_COMMAND);
			} catch (IOException e) {
				log.error(e.toString());
			}
		    System.exit(0);
	}
}

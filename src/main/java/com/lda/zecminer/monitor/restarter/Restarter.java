package com.lda.zecminer.monitor.restarter;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class Restarter {

	private static final String RESTART_COMMAND = "shutdown.exe -r -t 0";

	public void restart()
	{
		    try {
				Runtime.getRuntime().exec(RESTART_COMMAND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		    System.exit(0);
	}
}

package com.lda.zecminer.monitor.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lda.zecminer.monitor.domain.MinerStat;
import com.lda.zecminer.monitor.restarter.RestarterI;
import com.lda.zecminer.monitor.web.ZecMinerApiReader;

@Component
public class Monitor {

	private static final Logger log = LoggerFactory.getLogger(Monitor.class);
	
	@Autowired
	private ZecMinerApiReader apiReader;
	
	@Autowired
	private RestarterI restarter;
	
	private static int notWorkingCounter = 0;
	
	@Scheduled(initialDelay = 3000, fixedRate = 5000)
	public void checkMiners()
	{
		MinerStat minerStat = apiReader.readApi();
		boolean allWorking = minerStat.isAllWorking();
		
		if(allWorking)
		{
			log.info("All GPU's works");
			log.info(minerStat.forLog());
		}
		else
		{
			log.error("Some GPU is not working. Restart work station");
			log.error(minerStat.forLog());
			notWorkingCounter++;
		}
		
		if(notWorkingCounter > 1)
		{
			log.error("Restart work station");
			restarter.restart();
		}
	}
}

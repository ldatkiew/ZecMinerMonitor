package com.lda.zecminer.monitor.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lda.zecminer.monitor.domain.MinerStat;
import com.lda.zecminer.monitor.restarter.Restarter;
import com.lda.zecminer.monitor.web.ZecMinerApiReader;

@Component
public class Monitor {

	private static final Logger log = LoggerFactory.getLogger(Monitor.class);
	
	@Autowired
	private ZecMinerApiReader apiReader;
	
	@Autowired
	private Restarter restarter;
	
	@Scheduled(initialDelay = 3000, fixedRate = 5000)
	public void checkMiners()
	{
		MinerStat minerStat = apiReader.readApi();
		boolean allWorking = minerStat.isAllWorking();
		
		if(allWorking)
		{
			log.info("All GPU's works"); //TODO:add good logs
		}
		else
		{
			log.info("Some GPU is not working. Restart work station"); //TODO:add good logs
			restarter.restart();
		}
	}
}

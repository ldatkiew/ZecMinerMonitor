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

	private static final int SAME_RESULT_IN_ROW_LIMIT = 4;
	private static final int NOT_WORKING_IN_ROW_LIMIT = 1;
	private static final Logger log = LoggerFactory.getLogger(Monitor.class);

	@Autowired
	private ZecMinerApiReader apiReader;

	@Autowired
	private RestarterI restarter;
	
	private int notWorkingCounter = 0;
	private int sameResultCounter = 0;
	private MinerStat previousMinerStat = null;

	@Scheduled(initialDelayString = "${lda.monitor.initialDelay}", fixedRateString = "${lda.monitor.fixedRate}")  //TODO:LDA profile it
	public void checkMiners() {
		
		MinerStat minerStat = apiReader.readApi();
		
		updateSameStatistics(minerStat);
		updateAndLogWorkingStatistics(minerStat);

		if (notWorkingCounter > NOT_WORKING_IN_ROW_LIMIT || sameResultCounter > SAME_RESULT_IN_ROW_LIMIT) {
			log.error("Restart work station");
			restarter.restart();
		}
	}

	private void updateAndLogWorkingStatistics(MinerStat minerStat) {
		if (minerStat.isAllWorking()) {
			log.info("All GPU's works");
			log.info(minerStat.forLog());
			notWorkingCounter = 0;
		} else {
			log.error("Some GPU is not working. Restart work station");
			log.error(minerStat.forLog());
			notWorkingCounter++;
		}
	}

	private void updateSameStatistics(MinerStat minerStat) {
		if (minerStat.equals(previousMinerStat)) {
			sameResultCounter++;
		} else {
			sameResultCounter = 0;
		}

		previousMinerStat = minerStat;
	}

	public void setApiReader(ZecMinerApiReader apiReader) {
		this.apiReader = apiReader;
	}

	public void setRestarter(RestarterI restarter) {
		this.restarter = restarter;
	}
}

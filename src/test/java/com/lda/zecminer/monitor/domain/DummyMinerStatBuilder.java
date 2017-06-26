package com.lda.zecminer.monitor.domain;

import java.util.ArrayList;
import java.util.List;

public class DummyMinerStatBuilder {
	public static MinerStat createExpectedMinerStat2Workers() {
		MinerStat expectedMinerStat = new MinerStat();

		expectedMinerStat.setMethod("getstat");
		expectedMinerStat.setError(null);
		expectedMinerStat.setStart_time(1497973764L);
		expectedMinerStat.setCurrent_server("eu1-zcash.flypool.org:3333");
		expectedMinerStat.setAvailable_servers(1L);
		expectedMinerStat.setServer_status(2L);

		List<GpuStat> gpusStat = new ArrayList<>();

		GpuStat gpu0 = new GpuStat();

		gpu0.setGpuid(0L);
		gpu0.setCudaid(0L);
		gpu0.setBusid("0000:01:00.0");
		gpu0.setName("GeForce GTX 1060 6GB");
		gpu0.setGpu_status(2L);
		gpu0.setSolver(0L);
		gpu0.setTemperature(67L);
		gpu0.setGpu_power_usage(88L);
		gpu0.setSpeed_sps(288L);
		gpu0.setAccepted_shares(1L);
		gpu0.setRejected_shares(0L);
		gpu0.setStart_time(1497973765L);
		gpusStat.add(gpu0);

		GpuStat gpu1 = new GpuStat();

		gpu1.setGpuid(1L);
		gpu1.setCudaid(1L);
		gpu1.setBusid("0000:03:00.0");
		gpu1.setName("GeForce GTX 1060 6GB");
		gpu1.setGpu_status(2L);
		gpu1.setSolver(0L);
		gpu1.setTemperature(62L);
		gpu1.setGpu_power_usage(104L);
		gpu1.setSpeed_sps(310L);
		gpu1.setAccepted_shares(4L);
		gpu1.setRejected_shares(0L);
		gpu1.setStart_time(1497973765L);
		gpusStat.add(gpu1);

		expectedMinerStat.setGpusStat(gpusStat);
		return expectedMinerStat;
	}
}

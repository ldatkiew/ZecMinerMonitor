package com.lda.zecminer.monitor.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

public class GpuStatTest {

	@Test
	public void should_result_true_for_high_speed_gpu() {
		// Given
		GpuStat gpuStat = new GpuStat();
		gpuStat.setSpeed_sps(300L);

		// When
		boolean working = gpuStat.isWorking();

		// Then
		assertThat(working).isTrue();
	}
	
	@Test
	public void should_result_true_for_low_speed_gpu() {
		// Given
		GpuStat gpuStat = new GpuStat();
		gpuStat.setSpeed_sps(40L);

		// When
		boolean working = gpuStat.isWorking();

		// Then
		assertThat(working).isFalse();
	}
	
	@Test
	public void should_change_linux_timestamp_to_date() {
		// Given
		GpuStat gpuStat = new GpuStat();
		gpuStat.setStart_time(1497973765L);
	
		// When
		Date extractedStartDate = gpuStat.extractedStartDate();
	
		// Then
		assertThat(extractedStartDate).isEqualTo("2017-06-20T17:49:25");
	}
	
	@Test
	public void should_set_speed_min_limit_on_gpu() {
		// Given
		GpuStat gpuStat = new GpuStat();

		int speedMinLimit = 77;
		// When
		gpuStat.setSpeedMinLimit(speedMinLimit );
		
		// Then
		
			// When
		gpuStat.setSpeed_sps(77L);
			// Then
		assertThat(gpuStat.isWorking()).isFalse();
			// 	When
		gpuStat.setSpeed_sps(78L);
			// Then
		assertThat(gpuStat.isWorking()).isTrue();


	}
	
//	@Test
//	public void should_print_miner_log_info() {
//		System.out.println(DummyMinerStatBuilder.createExpectedMinerStat2Workers().forLog());
//	}
}

package com.lda.zecminer.monitor.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GpuStatTest {

	@Test
	public void should_result_true_for_high_speed_gpu() {
		// Given
		GpuStat result = new GpuStat();
		result.setSpeed_sps(300L);

		// When
		boolean working = result.isWorking();

		// Then
		assertThat(working).isTrue();
	}
	
	@Test
	public void should_result_true_for_low_speed_gpu() {
		// Given
		GpuStat result = new GpuStat();
		result.setSpeed_sps(40L);

		// When
		boolean working = result.isWorking();

		// Then
		assertThat(working).isFalse();
	}
}

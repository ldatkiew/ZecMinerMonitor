package com.lda.zecminer.monitor.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class MinerStatTest {

	@Test
	public void should_return_false_for_empty_gpus_list() {
		// Given
		MinerStat minerStat = MinerStat.createEmptyStat();

		// When
		boolean allWorking = minerStat.isAllWorking();

		// Then
		assertThat(allWorking).isFalse();
	}

	@Test
	public void should_retur_true_if_all_gpus_works() {
		// Given

		MinerStat minerStat = new MinerStat();
		List<GpuStat> gpus = Lists.newArrayList(mockGpuWorking(true), mockGpuWorking(true));
		minerStat.setGpusStat(gpus);
		
		// When
		boolean allWorking = minerStat.isAllWorking();

		// Then
		assertThat(allWorking).isTrue();
	}
	
	@Test
	public void should_retur_false_if_all_gpus_not_working() {
		// Given
		
		MinerStat minerStat = new MinerStat();
		List<GpuStat> gpus = Lists.newArrayList(mockGpuWorking(false), mockGpuWorking(false));
		minerStat.setGpusStat(gpus);
		
		// When
		boolean allWorking = minerStat.isAllWorking();
		
		// Then
		assertThat(allWorking).isFalse();
	}

	@Test
	public void should_retur_false_if_any_gpu_not_working() {
		// Given

		MinerStat minerStat = new MinerStat();
		List<GpuStat> gpus = Lists.newArrayList(mockGpuWorking(true), mockGpuWorking(false));
		minerStat.setGpusStat(gpus);
		
		// When
		boolean allWorking = minerStat.isAllWorking();

		// Then
		assertThat(allWorking).isFalse();
	}

	private GpuStat mockGpuWorking(boolean isWorking) {
		GpuStat gpu0 = mock(GpuStat.class);
		given(gpu0.isWorking()).willReturn(isWorking);
		return gpu0;
	}
}

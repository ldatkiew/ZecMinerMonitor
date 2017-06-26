package com.lda.zecminer.monitor.scheduler;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.lda.zecminer.monitor.domain.MinerStat;
import com.lda.zecminer.monitor.restarter.Restarter;
import com.lda.zecminer.monitor.web.ZecMinerApiReader;

@RunWith(MockitoJUnitRunner.class)
public class MonitorTest {
	
	@Mock
	private ZecMinerApiReader apiReader;
	
	@Mock
	private Restarter restarter;
	
	@InjectMocks
	private Monitor monitor = new Monitor();
	
	@Test
	public void should_read_api_check_is_working_and_log_when_all_is_working() {
		// Given
		MinerStat minerStat = mock(MinerStat.class);
		given(minerStat.isAllWorking()).willReturn(true);
		given(apiReader.readApi()).willReturn(minerStat);

		// When
		monitor.checkMiners();

		// Then
		verify(apiReader).readApi();
		verify(minerStat).isAllWorking();
	}
	
	@Test
	public void should_read_api_check_is_working_and_log_restart_when_not_working() {
		// Given
		MinerStat minerStat = mock(MinerStat.class);
		given(minerStat.isAllWorking()).willReturn(false);
		given(apiReader.readApi()).willReturn(minerStat);

		// When
		monitor.checkMiners();

		// Then
		verify(apiReader, times(1)).readApi();
		verify(minerStat, times(1)).isAllWorking();
		verify(restarter, never()).restart();
		
		// When 2
		monitor.checkMiners();

		// Then 2
		verify(apiReader, times(2)).readApi();
		verify(minerStat, times(2)).isAllWorking();
		verify(restarter, times(1)).restart();
	}
	
	
}

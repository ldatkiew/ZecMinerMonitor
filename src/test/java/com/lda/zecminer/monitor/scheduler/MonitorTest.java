package com.lda.zecminer.monitor.scheduler;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.lda.zecminer.monitor.domain.MinerStat;
import com.lda.zecminer.monitor.restarter.Restarter;
import com.lda.zecminer.monitor.web.ZecMinerApiReader;

@RunWith(MockitoJUnitRunner.class)
public class MonitorTest {
	
	private static final boolean WORKING = true;
	private static final boolean NOT_WORKING = false;
	private Monitor monitor;
	private ZecMinerApiReader apiReader;
	private Restarter restarter;
	
	@Mock
	private MinerStat minerStat;
	
	@Before
	public void createNewMonitor() {
		monitor = new Monitor();
		apiReader = mock(ZecMinerApiReader.class);
		monitor.setApiReader(apiReader);
		restarter = mock(Restarter.class);
		monitor.setRestarter(restarter);
	}
	
	@Test
	public void should_read_api_check_is_working_and_log_when_all_is_working() {
		// Given
		given(minerStat.isAllWorking()).willReturn(WORKING);
		given(apiReader.readApi()).willReturn(minerStat);

		// When
		monitor.checkMiners();

		// Then
		verify(apiReader).readApi();
		verify(minerStat).isAllWorking();
	}

	
	@Test
	public void should_read_api_check_is_working_and_log_restart_when_not_working_twice_in_row() {
		// Given
		given(minerStat.isAllWorking()).willReturn(NOT_WORKING);
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
	
	@Test
	public void should_read_api_check_is_working_and_log_reset_not_working_counter() {
		// Given
		given(minerStat.isAllWorking()).willReturn(NOT_WORKING);
		given(apiReader.readApi()).willReturn(minerStat);

		// When
		monitor.checkMiners();

		// Then
		verify(apiReader, times(1)).readApi();
		verify(minerStat, times(1)).isAllWorking();
		verify(restarter, never()).restart();
		
		// When 2
		given(minerStat.isAllWorking()).willReturn(WORKING);
		monitor.checkMiners();

		// Then 2
		verify(apiReader, times(2)).readApi();
		verify(minerStat, times(2)).isAllWorking();
		verify(restarter, never()).restart();
		
		// When 3
		given(minerStat.isAllWorking()).willReturn(NOT_WORKING);
		monitor.checkMiners();

		// Then 3
		verify(apiReader, times(3)).readApi();
		verify(minerStat, times(3)).isAllWorking();
		verify(restarter, never()).restart();
	}
	
	@Test
	public void should_not_restart_when_same_result_less_then_5_times_in_row() {
		// Given
		given(minerStat.isAllWorking()).willReturn(WORKING);
		given(apiReader.readApi()).willReturn(minerStat);

		// When
		monitor.checkMiners();
		monitor.checkMiners();
		monitor.checkMiners();
		monitor.checkMiners();

		// Then
		verify(restarter, never()).restart();

	}
	
	@Test
	public void should_restart_when_same_result_eq_or_more_then_5_times_in_row() {
		// Given
		given(minerStat.isAllWorking()).willReturn(WORKING);
		given(apiReader.readApi()).willReturn(minerStat);

		// When
		monitor.checkMiners(); //init check
		monitor.checkMiners();
		monitor.checkMiners();
		monitor.checkMiners();
		monitor.checkMiners();
		monitor.checkMiners();

		// Then
		verify(restarter, times(1)).restart();
	}
	
	@Test
	public void should_reset_same_stat_counter() {
		// Given
		given(minerStat.isAllWorking()).willReturn(WORKING);
		given(apiReader.readApi()).willReturn(minerStat);

		// When
		monitor.checkMiners(); //init check
		monitor.checkMiners();
		monitor.checkMiners();
		monitor.checkMiners();
		monitor.checkMiners();
		MinerStat minerStat = mock(MinerStat.class);
		given(minerStat.isAllWorking()).willReturn(WORKING);
		given(apiReader.readApi()).willReturn(minerStat);
		monitor.checkMiners();
		monitor.checkMiners();

		// Then
		verify(restarter, never()).restart();
	}
	
	
	
	
}

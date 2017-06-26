package com.lda.zecminer.monitor.restarter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class DevRestarterTest {
	@Autowired
	private RestarterI restarter;

	@Test
	public void should_use_dev_restarter() {
		// Given
		// When
		// Then
		assertThat(restarter).isNotNull();
		assertThat(restarter).isInstanceOf(DevRestarter.class);
	}
}

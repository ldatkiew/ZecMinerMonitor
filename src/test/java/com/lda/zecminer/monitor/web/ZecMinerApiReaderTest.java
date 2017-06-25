package com.lda.zecminer.monitor.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.lda.zecminer.monitor.domain.GpuStat;
import com.lda.zecminer.monitor.domain.MinerStat;

@RunWith(SpringRunner.class)
@RestClientTest(ZecMinerApiReader.class)
public class ZecMinerApiReaderTest {

	private static final String SERVER_ADDRESS = "http://127.0.0.1:42000/getstat";

	@Autowired
	private ZecMinerApiReader apiReader;

	@Autowired
	private MockRestServiceServer server;

	@Test
	public void should_execute_get_request_to_local_api_success() throws IOException {
		// Given
		Resource response = getResource("ZecMinerResponse_2workers.txt");

		this.server.expect(requestTo(SERVER_ADDRESS))
				.andRespond(withSuccess(response, MediaType.APPLICATION_OCTET_STREAM));

		// When
		MinerStat actualMinerStat = this.apiReader.readApi();

		// Then
		MinerStat expectedMinerStat = createExpectedMinerStat2Workers();
		assertThat(actualMinerStat).isEqualTo(expectedMinerStat);

	}
	
	@Test
	public void should_execute_get_request_to_local_api_bad_request() throws IOException {
		// Given
		this.server.expect(requestTo(SERVER_ADDRESS))
				.andRespond(withBadRequest());

		// When
		MinerStat actualMinerStat = this.apiReader.readApi();

		// Then
		MinerStat expectedMinerStat = MinerStat.createEmptyStat();
		assertThat(actualMinerStat).isEqualTo(expectedMinerStat);
	}
	
	@Test
	public void should_execute_get_request_to_local_api_server_error() throws IOException {
		// Given
		this.server.expect(requestTo(SERVER_ADDRESS))
				.andRespond(withServerError());

		// When
		MinerStat actualMinerStat = this.apiReader.readApi();

		// Then
		MinerStat expectedMinerStat = MinerStat.createEmptyStat();
		assertThat(actualMinerStat).isEqualTo(expectedMinerStat);
	}

	private MinerStat createExpectedMinerStat2Workers() {
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

	private Resource getResource(String fileName) {

		Resource resource = new ClassPathResource("com/lda/zecminer/monitor/web/" + fileName);
		return resource;
	}

}

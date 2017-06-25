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

import com.lda.zecminer.monitor.domain.MinerStat;
import com.lda.zecminer.monitor.domain.Result;

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

		List<Result> results = new ArrayList<>();

		Result result01 = new Result();

		result01.setGpuid(0L);
		result01.setCudaid(0L);
		result01.setBusid("0000:01:00.0");
		result01.setName("GeForce GTX 1060 6GB");
		result01.setGpu_status(2L);
		result01.setSolver(0L);
		result01.setTemperature(67L);
		result01.setGpu_power_usage(88L);
		result01.setSpeed_sps(288L);
		result01.setAccepted_shares(1L);
		result01.setRejected_shares(0L);
		result01.setStart_time(1497973765L);
		results.add(result01);

		Result result02 = new Result();

		result02.setGpuid(1L);
		result02.setCudaid(1L);
		result02.setBusid("0000:03:00.0");
		result02.setName("GeForce GTX 1060 6GB");
		result02.setGpu_status(2L);
		result02.setSolver(0L);
		result02.setTemperature(62L);
		result02.setGpu_power_usage(104L);
		result02.setSpeed_sps(310L);
		result02.setAccepted_shares(4L);
		result02.setRejected_shares(0L);
		result02.setStart_time(1497973765L);
		results.add(result02);

		expectedMinerStat.setResults(results);
		return expectedMinerStat;
	}

	private Resource getResource(String fileName) {

		Resource resource = new ClassPathResource("com/lda/zecminer/monitor/web/" + fileName);
		return resource;
	}

}

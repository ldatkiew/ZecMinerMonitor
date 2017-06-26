package com.lda.zecminer.monitor.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.lda.zecminer.monitor.domain.DummyMinerStatBuilder;
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
		MinerStat expectedMinerStat = DummyMinerStatBuilder.createExpectedMinerStat2Workers();
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

	private Resource getResource(String fileName) {

		Resource resource = new ClassPathResource("com/lda/zecminer/monitor/web/" + fileName);
		return resource;
	}

}

package com.lda.zecminer.monitor.web;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lda.zecminer.monitor.domain.MinerStat;


@Component
public class ZecMinerApiReader {

	private static final String API_URL = "http://127.0.0.1:42000/getstat";
	private static final Logger LOG = LoggerFactory.getLogger(ZecMinerApiReader.class);

	@Autowired
	private RestTemplate restTemplate;

	public MinerStat readApi() {
		MinerStat stat = MinerStat.createEmptyStat();

		try {
			HttpEntity<String> entity = prepareHttpEntity();
			ResponseEntity<MinerStat> res = restTemplate.exchange(API_URL, HttpMethod.GET, entity, MinerStat.class);
			stat = res.getBody();

		} catch (Exception e) {
			LOG.error(e.toString());
		}
		return stat;
	}

	private HttpEntity<String> prepareHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
}

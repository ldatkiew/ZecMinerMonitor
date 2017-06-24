package com.lda.zecminer.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ZecMinerMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZecMinerMonitorApplication.class, args);
	}
}

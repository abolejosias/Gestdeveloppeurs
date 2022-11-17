package com.mmenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.mmenu")
public class MMenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(MMenuApplication.class, args);
	}

}

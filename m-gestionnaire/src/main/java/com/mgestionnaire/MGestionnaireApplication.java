package com.mgestionnaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.mgestionnaire")

public class MGestionnaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(MGestionnaireApplication.class, args);
	}

}

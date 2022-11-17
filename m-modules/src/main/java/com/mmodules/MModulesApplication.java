package com.mmodules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MModulesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MModulesApplication.class, args);
	}

}

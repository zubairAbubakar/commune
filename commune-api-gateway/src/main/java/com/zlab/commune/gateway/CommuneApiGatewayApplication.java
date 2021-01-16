package com.zlab.commune.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class CommuneApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommuneApiGatewayApplication.class, args);
	}

}

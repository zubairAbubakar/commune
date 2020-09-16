package com.zlab.commune.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CommuneDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommuneDiscoveryServiceApplication.class, args);
	}

}



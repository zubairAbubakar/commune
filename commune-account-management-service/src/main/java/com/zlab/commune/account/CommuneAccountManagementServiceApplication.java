package com.zlab.commune.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommuneAccountManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommuneAccountManagementServiceApplication.class, args);
	}

}

package com.zlab.commune.api.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommuneMemberServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommuneMemberServiceApplication.class, args);
	}

}

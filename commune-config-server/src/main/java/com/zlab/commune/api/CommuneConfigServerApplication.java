package com.zlab.commune.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CommuneConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommuneConfigServerApplication.class, args);
	}

}

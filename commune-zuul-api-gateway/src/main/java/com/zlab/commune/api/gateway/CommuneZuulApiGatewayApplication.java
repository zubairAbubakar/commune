package com.zlab.commune.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class CommuneZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommuneZuulApiGatewayApplication.class, args);
	}

}

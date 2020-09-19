package com.zlab.commune.api.user;

import com.zlab.commune.api.user.shared.FeignErrorDecoder;
import feign.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class UserServiceApplication {

	@Autowired
	Environment environment;


	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){

		return new BCryptPasswordEncoder();
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){

		return new RestTemplate();
	}

	@Bean
	@Profile("production")
	Logger.Level feignLoggerLevel(){

		return Logger.Level.NONE;
	}

	@Bean
	@Profile("!production")
	Logger.Level feignDefaultLoggerLevel(){

		return Logger.Level.FULL;
	}

	@Bean
	@Profile("production")
	public String createProductionBean(){
		System.out.println("Production bean created. myapplication.environment = "+environment.getProperty("myapplication.environment"));
		return "Production Bean";
	}

	@Bean
	@Profile("!production")
	public String createNotProductionBean(){
		System.out.println("Not production bean created. myapplication.environment = "+environment.getProperty("myapplication.environment"));
		return "Not production Bean";
	}

	@Bean
	@Profile("default")
	public String createDefaultBean(){
		System.out.println("Default bean created. myapplication.environment = "+environment.getProperty("myapplication.environment"));
		return "Default Bean";
	}

//	@Bean
//	public FeignErrorDecoder getFeignErrorDecoder(){
//
//		return new FeignErrorDecoder();
//	}
}

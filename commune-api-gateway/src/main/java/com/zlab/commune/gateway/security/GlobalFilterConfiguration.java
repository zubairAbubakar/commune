package com.zlab.commune.gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;


@Configuration
public class GlobalFilterConfiguration {

    final Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Order(1)
    @Bean
    public GlobalFilter secondPreFilter(){

        return ((exchange, chain) -> {

            logger.info("2nd Global pre-filter executed...");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("2nd Global post-filter executed...");
            }));
        });
    }

    @Order(2)
    @Bean
    public GlobalFilter thirdPreFilter(){

        return ((exchange, chain) -> {

            logger.info("3rd Global pre-filter executed...");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("3rd Global post-filter executed...");
            }));
        });
    }

}

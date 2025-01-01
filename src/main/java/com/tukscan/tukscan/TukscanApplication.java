package com.tukscan.tukscan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableJpaRepositories(basePackages = "com.tukscan.tukscan.*.repository.mysql")
@EnableRedisRepositories(basePackages = "com.tukscan.tukscan.*.repository.redis")
@SpringBootApplication
public class TukscanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TukscanApplication.class, args);
	}

}

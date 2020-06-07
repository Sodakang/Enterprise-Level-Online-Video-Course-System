package com.project;

import freemarker.core.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(EurekaApplication.class);
		ConfigurableEnvironment environment = springApplication.run(args).getEnvironment();
		LOG.info("Start Successfully!");
		LOG.info("Eureka Address \thttp://127.0.0.1:{}", environment.getProperty("server.port"));
	}

}

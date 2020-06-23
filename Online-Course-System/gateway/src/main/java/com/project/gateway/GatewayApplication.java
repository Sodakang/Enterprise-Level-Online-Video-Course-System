package com.project.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.util.pattern.PathPatternParser;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication {

	private static final Logger LOG = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(GatewayApplication.class);
		ConfigurableEnvironment environment = springApplication.run(args).getEnvironment();
		LOG.info("Start Successfully!");
		LOG.info("Gateway Address \thttp://127.0.0.1:{}", environment.getProperty("server.port"));
	}

	@Bean
	public CorsWebFilter corsFilter() {

		CorsConfiguration configuration = new CorsConfiguration();

		configuration.setAllowCredentials(Boolean.TRUE);
		configuration.addAllowedMethod("*");
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
		source.registerCorsConfiguration("/**", configuration);

		return new CorsWebFilter(source);
	}

}

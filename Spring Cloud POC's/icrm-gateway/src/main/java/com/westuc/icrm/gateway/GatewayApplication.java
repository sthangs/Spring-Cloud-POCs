package com.westuc.icrm.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.westuc.icrm.gateway.filters.MyRedirectionGatewayFilterFactory;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
@Configuration
@ConfigurationProperties
public class GatewayApplication {

	@Value("${vcap.services.icrm-cups-service.credentials.icrmSubEnv}")
	private String icrmSubEnvValue;

	public static void main(String[] args) {

		SpringApplication.run(GatewayApplication.class, args);

	}

	@Autowired
	MyRedirectionGatewayFilterFactory redirectionStrategy;

	@Value("${fallBackMessage}")
	private String fallBackMessage;

	@RequestMapping("/incaseoffailureusethis")
	public String hystrixfallback() {
		System.out.println(">>>>>>>>>>>>>>>>>>" + icrmSubEnvValue);
	
		return fallBackMessage;
	}

	@Bean
	@Profile({ "local", "dev" })
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
		return http.httpBasic().and().csrf().disable().authorizeExchange().pathMatchers("/anything/**").authenticated()
				.anyExchange().permitAll().and().build();
	}
}

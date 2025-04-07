package com.tutore.centre_interet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CentreInteretApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentreInteretApplication.class, args);
	}

	@LoadBalanced  // Important pour la résolution des services via Eureka
	@Bean
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}
}

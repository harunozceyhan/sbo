package com.smart.sbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SboApplication {

	public static void main(String[] args) {
		SpringApplication.run(SboApplication.class, args);
	}

}


@Configuration
class RestTemplateConfig {
	@Bean
	@LoadBalanced		// Load balance between service instances running at different ports.
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
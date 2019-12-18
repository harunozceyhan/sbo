package com.smart.sbo;

import com.smart.config.SmartHibernateInterceptor;
import com.smart.controller.SecurityErrorController;
import com.smart.config.JpaAuditingConfiguration;
import com.smart.config.ResourceSecurityConfigurer;
import com.smart.config.RestConfiguration;
import com.smart.handler.SmartExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Import({ ResourceSecurityConfigurer.class, SmartExceptionHandler.class, SmartHibernateInterceptor.class, JpaAuditingConfiguration.class, RestConfiguration.class, SecurityErrorController.class })
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class })
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
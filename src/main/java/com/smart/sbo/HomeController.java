package com.smart.sbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/")
public class HomeController {
	@Autowired
	private Environment env;

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/")
	public String homeAdmin() {
		return "SBO service running at port: " + env.getProperty("local.server.port");
	}
}
package com.kenneth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
public class SleuthServerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(SleuthServerController.class);
	
	@GetMapping(value = "/hi")
	public String Home() {
		log.info("hi is being called");
		return "hi i'm kenneth";
	}
	
	@GetMapping("/kenneth")
	public String info() {
		log.info("info is being called");
		return restTemplate.getForObject("http://localhost:8774/info",String.class);
	}
}

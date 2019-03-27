package com.kenneth.controller;

import org.apache.logging.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String callHome() {
		log.info("calling trace sleuth-hi");
		return restTemplate.getForObject("http://localhost:8775/kenneth", String.class);
	}
	
	@GetMapping("/info")
	public String info() {
		log.info("calling trace sleuth-hi");
		return "i'm sleuth-hi";
	}
}

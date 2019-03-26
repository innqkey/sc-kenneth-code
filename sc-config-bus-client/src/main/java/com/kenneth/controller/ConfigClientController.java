package com.kenneth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author qinkai
 * @date 2019年2月20日
 */
@RestController
@RefreshScope
public class ConfigClientController {
	
	@Value("${name}")
	private String name;
	
	@GetMapping(value = "/hi")
	public String hi() {
		return name;
	}
}

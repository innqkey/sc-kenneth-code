package com.kenneth.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @auther qinkai
 * @data 2019/5/7 9:33
 */
@SpringBootApplication
public class WebFluxApplication {
    public static void main(String[] args) {
        new SpringApplication(WebFluxApplication.class).run(args);
    }
}

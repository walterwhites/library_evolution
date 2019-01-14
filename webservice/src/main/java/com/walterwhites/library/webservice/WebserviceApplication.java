package com.walterwhites.library.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.walterwhites.library"})
public class WebserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebserviceApplication.class, args);
    }
}
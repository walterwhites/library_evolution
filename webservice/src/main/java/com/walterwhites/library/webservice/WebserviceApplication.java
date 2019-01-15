package com.walterwhites.library.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.walterwhites.library.consumer.repository")
@EntityScan("com.walterwhites.library.model.entity")
@ComponentScan(basePackages = {"com.walterwhites.library"})
public class WebserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebserviceApplication.class, args);
    }
}
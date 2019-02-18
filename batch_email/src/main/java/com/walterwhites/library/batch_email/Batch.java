package com.walterwhites.library.batch_email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.walterwhites.library"})
public class Batch {
    public static void main(String[] args) throws Exception {
        System.out.println("Batch is running");
        SpringApplication.run(Batch.class, args);
    }
}
package com.walterwhites.library.batch_notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.walterwhites.library"})
public class Batch {
    public static void main(String[] args) throws Exception {
        System.out.println("Batch notification is running");
        SpringApplication.run(Batch.class, args);
    }
}
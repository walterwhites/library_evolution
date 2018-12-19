package com.walterwhites.library.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Batch {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World");
        SpringApplication.run(Batch.class, args);
    }
}
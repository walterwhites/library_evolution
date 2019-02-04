package com.walterwhites.library.webapp;

import com.walterwhites.library.business.client.SentryJClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EntityScan("com.walterwhites.library.model.entity")
@ComponentScan(basePackages = {"com.walterwhites.library"})
@Configuration
public class LibraryApplication extends SpringBootServletInitializer {

    static SentryJClient sentryJClient;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LibraryApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LibraryApplication.class, args);
        sentryJClient = SentryJClient.init();
        sentryJClient.setUser("walterwhites");
        sentryJClient.recordBreadcrumb("start application");
        sentryJClient.addTag("application", "webapp");
    }
}
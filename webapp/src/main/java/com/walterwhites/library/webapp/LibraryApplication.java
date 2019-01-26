package com.walterwhites.library.webapp;

import com.walterwhites.library.business.client.SentryJClient;
import io.sentry.Sentry;
import io.sentry.SentryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication()
@ComponentScan(basePackages = {"com.walterwhites.library"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.walterwhites.library.consumer.repository.entity.*")})
@Configuration
public class LibraryApplication extends SpringBootServletInitializer {

    static SentryClient sentryClient;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LibraryApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(LibraryApplication.class, args);
        SentryJClient.setUser("walterwhites");
        SentryJClient.recordBreadcrumb("start application");
        sentryClient = SentryJClient.init();
        try {
            SentryJClient.exempleException("I run a new Exception");
        } catch (Exception e) {
            Sentry.capture(e);
        }
    }
}
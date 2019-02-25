package com.walterwhites.library.webservice;

import com.walterwhites.library.business.client.SentryJClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("com.walterwhites.library.model.entity")
@EnableJpaRepositories("com.walterwhites.library.consumer.repository")
@ComponentScan(basePackages = {"com.walterwhites.library"})
public class WebserviceApplication  extends SpringBootServletInitializer {

    public static SentryJClient sentryJClient;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebserviceApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebserviceApplication.class, args);
        sentryJClient = SentryJClient.init();
        sentryJClient.setUser("walterwhites");
        sentryJClient.recordBreadcrumb("start application");
        sentryJClient.addTag("application", "webservice");
    }
}
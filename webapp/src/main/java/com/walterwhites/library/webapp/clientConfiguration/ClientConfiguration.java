package com.walterwhites.library.webapp.clientConfiguration;

import com.walterwhites.library.webapp.apiClient.BookClient;
import com.walterwhites.library.webapp.apiClient.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientConfiguration {

    @Bean
    public Jaxb2Marshaller marshallerClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("library.io.github.walterwhites.client");

        return marshaller;
    }

    @Bean
    public UserClient userClient(Jaxb2Marshaller marshallerClient) {
        UserClient userClient = new UserClient();
        userClient.setDefaultUri("http://localhost:8081/ws/client");
        userClient.setMarshaller(marshallerClient);
        userClient.setUnmarshaller(marshallerClient);
        return userClient;
    }
}
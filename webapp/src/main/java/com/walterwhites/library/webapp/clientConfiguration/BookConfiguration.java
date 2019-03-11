package com.walterwhites.library.webapp.clientConfiguration;

import com.walterwhites.library.webapp.apiClient.BookClient;
import com.walterwhites.library.webapp.apiClient.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class BookConfiguration {

    @Bean
    public Jaxb2Marshaller marshallerBooks() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("library.io.github.walterwhites");

        return marshaller;
    }

    @Bean
    public BookClient bookClient(Jaxb2Marshaller marshallerBooks) {
        BookClient bookClient = new BookClient();
        bookClient.setDefaultUri("http://localhost:8081/ws/books");
        bookClient.setMarshaller(marshallerBooks);
        bookClient.setUnmarshaller(marshallerBooks);
        return bookClient;
    }
}
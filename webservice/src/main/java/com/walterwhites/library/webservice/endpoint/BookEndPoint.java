package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.jaxb.impl.BookRepositoryImpl;
import library.io.github.walterwhites.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
@SpringBootApplication
@ComponentScan(basePackages = {"com.walterwhites.library"})
@EnableJpaRepositories(basePackages = {"com.walterwhites.library.consumer.repository"})
public class BookEndPoint {
    private static final String NAMESPACE_URI = "library.io.github.walterwhites";

    private BookRepositoryImpl bookRepository;

    public BookEndPoint(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBookFromTitle(@RequestPayload GetBookRequest request) {
        GetBookResponse response = new GetBookResponse();
        response.getBook().addAll(bookRepository.findByTitle(request.getTitle()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookFromIdRequest")
    @ResponsePayload
    public GetBookFromIdResponse getBookFromId(@RequestPayload GetBookFromIdRequest request) {
        GetBookFromIdResponse response = new GetBookFromIdResponse();
        response.setBook(bookRepository.findById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBookRequest")
    @ResponsePayload
    public GetAllBookResponse getAllBook(@RequestPayload GetAllBookRequest request) {
        GetAllBookResponse response = new GetAllBookResponse();
        response.getBook().addAll(bookRepository.findAllBooks());
        return response;
    }
}
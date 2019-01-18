package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.impl.BookRepositoryWebserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import library.io.github.walterwhites.*;


@Endpoint
public class BookEndPoint {
    private static final String NAMESPACE_URI = "library.io.github.walterwhites";

    private BookRepositoryWebserviceImpl bookRepository;

    @Autowired
    public BookEndPoint(BookRepositoryWebserviceImpl bookRepository) {
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
}
package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.jaxb.java.GetBookRequest;
import com.walterwhites.library.consumer.jaxb.java.GetBookResponse;
import com.walterwhites.library.consumer.repository.impl.BookRepositoryWebserviceImpl;
import jaxb.java.GetBookIdRequest;
import jaxb.java.GetBookIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class BookEndPoint {
    private static final String NAMESPACE_URI = "java.jaxb";

    private BookRepositoryWebserviceImpl bookRepository;

    @Autowired
    public BookEndPoint(BookRepositoryWebserviceImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBookFromTitle(@RequestPayload GetBookRequest request) {
        GetBookResponse response = new GetBookResponse();
        response.setBook(bookRepository.findByTitle(request.getName()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookFromIdRequest")
    @ResponsePayload
    public GetBookIdResponse getBookFromId(@RequestPayload GetBookIdRequest request) {
        GetBookIdResponse response = new GetBookIdResponse();
        response.setBook(bookRepository.findById(request.getId()));
        return response;
    }
}
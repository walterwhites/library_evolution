package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.impl.BookRepositoryWebserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.walterwhites.library.consumer.jaxb.java.GetBookRequest;
import com.walterwhites.library.consumer.jaxb.java.GetBookResponse;


@Endpoint
public class BookEndPoint {
    private static final String NAMESPACE_URI = "http://walterwhites.io/webservice";

    private BookRepositoryWebserviceImpl bookRepository;

    @Autowired
    public BookEndPoint(BookRepositoryWebserviceImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBook(@RequestPayload GetBookRequest request) {
        GetBookResponse response = new GetBookResponse();
        response.setBook(bookRepository.findByTitle(request.getName()));

        return response;
    }
}
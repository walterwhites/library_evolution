package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.impl.BookRepositoryWebserviceImpl;
import com.walterwhites.library.consumer.jaxb.GetBookFromIdRequest;
import com.walterwhites.library.consumer.jaxb.GetBookFromIdResponse;
import com.walterwhites.library.consumer.jaxb.GetBookRequest;
import com.walterwhites.library.consumer.jaxb.GetBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class BookEndPoint {
    private static final String NAMESPACE_URI = "com.walterwhites.library.consumer.jaxb";

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
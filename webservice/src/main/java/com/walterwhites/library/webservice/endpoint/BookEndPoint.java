package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.impl.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.walterwhites.library.webservice.jaxb.java.GetCountryRequest;


@Endpoint
public class BookEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private BookRepositoryImpl bookRepository;

    @Autowired
    public BookEndpoint(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getCountry(@RequestPayload GetBookRequest request) {
        GetBookResponse response = new GetBookResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
package com.walterwhites.library.webapp.apiClient;

import library.io.github.walterwhites.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Service
@Configuration
public class BookClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(BookClient.class);

    public GetBookResponse getBooksFromTitle(String book) {
        GetBookRequest request = new GetBookRequest();
        request.setTitle(book);
        GetBookResponse response = (GetBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws/books", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
        return response;
    }

    public GetBookFromIdResponse getBookFromId(int id) {
        GetBookFromIdRequest request = new GetBookFromIdRequest();
        request.setId(id);
        GetBookFromIdResponse response = (GetBookFromIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws/books", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
        return response;
    }

    public GetAllBookResponse getAllBooks() {
        GetAllBookRequest request = new GetAllBookRequest();
        GetAllBookResponse response = (GetAllBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws/books", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
        return response;
    }
}
package com.walterwhites.library.webapp.apiClient;

import library.io.github.walterwhites.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class BookClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(BookClient.class);

    public PostBookBorrowedResponse postBookBorrowed(long id, long client_id) {
        PostBookBorrowedRequest request = new PostBookBorrowedRequest();
        request.setId(id);
        request.setClientId(client_id);
        PostBookBorrowedResponse response = (PostBookBorrowedResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
        return response;
    }

    public GetBookResponse getBooksFromTitle(String book) {
        GetBookRequest request = new GetBookRequest();
        request.setTitle(book);
        GetBookResponse response = (GetBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
        return response;
    }

    public GetBookFromIdResponse getBookFromId(long id) {
        GetBookFromIdRequest request = new GetBookFromIdRequest();
        request.setId(id);
        GetBookFromIdResponse response = (GetBookFromIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
        return response;
    }

    public GetAllBookResponse getAllBooks() {
        GetAllBookRequest request = new GetAllBookRequest();
        GetAllBookResponse response = (GetAllBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
        return response;
    }

    public GetAllBookFromClientResponse getAllBooksFromClient(String username) {
        GetAllBookFromClientRequest request = new GetAllBookFromClientRequest();
        request.setUsername(username);
        GetAllBookFromClientResponse response = (GetAllBookFromClientResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
        return response;
    }
}
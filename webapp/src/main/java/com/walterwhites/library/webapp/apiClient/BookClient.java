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
        return (PostBookBorrowedResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public PostBookReturnedResponse postBookReturned(long loan_id) {
        PostBookReturnedRequest request = new PostBookReturnedRequest();
        request.setLoanId(loan_id);
        return (PostBookReturnedResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public GetBookResponse getBooksFromTitle(String book) {
        GetBookRequest request = new GetBookRequest();
        request.setTitle(book);
        return (GetBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public GetBookFromIdResponse getBookFromId(long id) {
        GetBookFromIdRequest request = new GetBookFromIdRequest();
        request.setId(id);
        return (GetBookFromIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public GetAllBookResponse getAllBooks() {
        GetAllBookRequest request = new GetAllBookRequest();
        return (GetAllBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public GetAllBookFromClientResponse getAllBooksFromClient(String username) {
        GetAllBookFromClientRequest request = new GetAllBookFromClientRequest();
        request.setUsername(username);
        return (GetAllBookFromClientResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }
}
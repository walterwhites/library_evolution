package com.walterwhites.library.webapp.apiClient;

import library.io.github.walterwhites.*;
import library.io.github.walterwhites.loans.GetAllReservationFromClientRequest;
import library.io.github.walterwhites.loans.GetAllReservationFromClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.math.BigInteger;

public class BookClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(BookClient.class);

    private static final String ENDPOINT = "http://localhost:8081/ws/books";

    public CountAllPendingReservationsOfBookResponse countAllPendingReservationsOfBook(BigInteger id) {
        CountAllPendingReservationsOfBookRequest request = new CountAllPendingReservationsOfBookRequest();
        request.setId(id);
        return (CountAllPendingReservationsOfBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public PostBookRenewedResponse postBookRenewed(long loan_id) {
        PostBookRenewedRequest request = new PostBookRenewedRequest();
        request.setLoanId(loan_id);
        return (PostBookRenewedResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public PostBookBorrowedResponse postBookBorrowed(long id, long client_id) {
        PostBookBorrowedRequest request = new PostBookBorrowedRequest();
        request.setId(id);
        request.setClientId(client_id);
        return (PostBookBorrowedResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public PostBookReturnedResponse postBookReturned(long loan_id) {
        PostBookReturnedRequest request = new PostBookReturnedRequest();
        request.setLoanId(loan_id);
        return (PostBookReturnedResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public GetBookResponse getBooksFromTitle(String book) {
        GetBookRequest request = new GetBookRequest();
        request.setTitle(book);
        return (GetBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public GetBookFromIdResponse getBookFromId(long id) {
        GetBookFromIdRequest request = new GetBookFromIdRequest();
        request.setId(id);
        return (GetBookFromIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public GetAllBookResponse getAllBooks() {
        GetAllBookRequest request = new GetAllBookRequest();
        return (GetAllBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public GetAllBookFromClientResponse getAllBooksFromClient(String username) {
        GetAllBookFromClientRequest request = new GetAllBookFromClientRequest();
        request.setUsername(username);
        request.setState("all_book");
        return (GetAllBookFromClientResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public GetAllBookFromClientResponse getAllBorrowedBooksFromClient(String username) {
        GetAllBookFromClientRequest request = new GetAllBookFromClientRequest();
        request.setUsername(username);
        request.setState("borrowed");
        return (GetAllBookFromClientResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }
}
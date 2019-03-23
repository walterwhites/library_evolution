package com.walterwhites.library.webapp.apiClient;

import library.io.github.walterwhites.loans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class LoanClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(UserClient.class);

    private static final String ENDPOINT = "http://localhost:8081/ws/loans";

    public GetLastReservationResponse getLastReservationResponse(String title) {
        GetLastReservationRequest request = new GetLastReservationRequest();
        request.setBookTitle(title);
        return (GetLastReservationResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public GetAllReservationFromClientResponse getAllReservationFromClientResponse(String username) {
        GetAllReservationFromClientRequest request = new GetAllReservationFromClientRequest();
        request.setUsername(username);
        return (GetAllReservationFromClientResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public PostCancelReservationResponse postCancelReservation(long id) {
        PostCancelReservationRequest request = new PostCancelReservationRequest();
        request.setId(id);
        return (PostCancelReservationResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }

    public PostCreateReservationResponse postCreateReservation(long id, long client_id) {
        PostCreateReservationRequest request = new PostCreateReservationRequest();
        request.setId(id);
        request.setClientId(client_id);
        return (PostCreateReservationResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }
}

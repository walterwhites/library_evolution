package com.walterwhites.library.webapp.apiClient;

import library.io.github.walterwhites.loans.GetAllReservationFromClientRequest;
import library.io.github.walterwhites.loans.GetAllReservationFromClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class LoanClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(UserClient.class);

    private static final String ENDPOINT = "http://localhost:8081/ws/loans";

    public GetAllReservationFromClientResponse getAllReservationFromClientResponse(String username) {
        GetAllReservationFromClientRequest request = new GetAllReservationFromClientRequest();
        request.setUsername(username);
        return (GetAllReservationFromClientResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }
}

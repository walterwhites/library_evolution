package com.walterwhites.library.batch_notification.apiClient;

import library.io.github.walterwhites.loans.GetAllNotReturnedBookRequest;
import library.io.github.walterwhites.loans.GetAllNotReturnedBookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class LoanClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(LoanClient.class);

    public GetAllNotReturnedBookResponse getAllNotReturnedBook() {
        GetAllNotReturnedBookRequest request = new GetAllNotReturnedBookRequest();
        return (GetAllNotReturnedBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws/loans", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }
}
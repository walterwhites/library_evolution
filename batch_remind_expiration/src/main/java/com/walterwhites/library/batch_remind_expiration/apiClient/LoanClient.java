package com.walterwhites.library.batch_remind_expiration.apiClient;

import library.io.github.walterwhites.loans.GetAllSoonExpiredLoanRequest;
import library.io.github.walterwhites.loans.GetAllSoonExpiredLoanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class LoanClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(LoanClient.class);

    public GetAllSoonExpiredLoanResponse getAllSoonExpiredLoanResponse() {
        GetAllSoonExpiredLoanRequest request = new GetAllSoonExpiredLoanRequest();
        return (GetAllSoonExpiredLoanResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws/loans", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }
}
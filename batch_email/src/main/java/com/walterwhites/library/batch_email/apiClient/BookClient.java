package com.walterwhites.library.batch_email.apiClient;

import library.io.github.walterwhites.GetAllNotReturnedBookRequest;
import library.io.github.walterwhites.GetAllNotReturnedBookResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class BookClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(BookClient.class);

    public GetAllNotReturnedBookResponse getAllNotReturnedBook() {
        GetAllNotReturnedBookRequest request = new GetAllNotReturnedBookRequest();
        return (GetAllNotReturnedBookResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }
}
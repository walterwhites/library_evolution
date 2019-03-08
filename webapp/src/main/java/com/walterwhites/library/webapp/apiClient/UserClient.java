package com.walterwhites.library.webapp.apiClient;

import library.io.github.walterwhites.client.GetClientFromUsernameRequest;
import library.io.github.walterwhites.client.GetClientFromUsernameResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class UserClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(UserClient.class);

    private static final String ENDPOINT = "http://localhost:8081/ws/client";

    public GetClientFromUsernameResponse getClientFromUsername(String username) {
        GetClientFromUsernameRequest request = new GetClientFromUsernameRequest();
        request.setUsername(username);
        return (GetClientFromUsernameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(ENDPOINT, request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }
}
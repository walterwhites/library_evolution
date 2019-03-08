package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.jaxb.impl.ClientsRepositoryImpl;
import library.io.github.walterwhites.client.GetClientFromIdRequest;
import library.io.github.walterwhites.client.GetClientFromIdResponse;
import library.io.github.walterwhites.client.GetClientFromUsernameRequest;
import library.io.github.walterwhites.client.GetClientFromUsernameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Configuration
public class ClientEndPoint {
    private static final String NAMESPACE_URI = "library.io.github.walterwhites.client";

    private final ClientsRepositoryImpl clientRepository;

    @Autowired
    public ClientEndPoint(ClientsRepositoryImpl clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientFromUsernameRequest")
    @ResponsePayload
    public GetClientFromUsernameResponse getClientFromId(@RequestPayload GetClientFromUsernameRequest request) {
        GetClientFromUsernameResponse response = new GetClientFromUsernameResponse();
        response.setClient(clientRepository.findByUsername(request.getUsername()));

        return response;
    }
}

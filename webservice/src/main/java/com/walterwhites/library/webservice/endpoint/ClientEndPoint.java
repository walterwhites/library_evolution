package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.jaxb.impl.ClientsRepositoryImpl;
import library.io.github.walterwhites.client.GetClientFromIdRequest;
import library.io.github.walterwhites.client.GetClientFromIdResponse;
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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientFromIdRequest")
    @ResponsePayload
    public GetClientFromIdResponse getClientFromId(@RequestPayload GetClientFromIdRequest request) {
        GetClientFromIdResponse response = new GetClientFromIdResponse();
        response.getClient().add(clientRepository.findById(request.getId()));

        return response;
    }
}

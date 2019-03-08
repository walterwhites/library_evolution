package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.entity.ClientRepositoryImpl;
import com.walterwhites.library.consumer.repository.jaxb.impl.ClientsRepositoryImpl;
import library.io.github.walterwhites.client.*;
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
    private final ClientRepositoryImpl clientRepositoryEntity;

    @Autowired
    public ClientEndPoint(ClientsRepositoryImpl clientRepository, ClientRepositoryImpl clientRepositoryEntity) {
        this.clientRepository = clientRepository;
        this.clientRepositoryEntity = clientRepositoryEntity;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientFromUsernameRequest")
    @ResponsePayload
    public GetClientFromUsernameResponse getClientFromId(@RequestPayload GetClientFromUsernameRequest request) {
        GetClientFromUsernameResponse response = new GetClientFromUsernameResponse();
        response.setClient(clientRepository.findByUsername(request.getUsername()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postAlertEmailRequest")
    @ResponsePayload
    public PostAlertEmailResponse postAlertEmail(@RequestPayload PostAlertEmailRequest request) {
        PostAlertEmailResponse response = new PostAlertEmailResponse();
        com.walterwhites.library.model.entity.Client client = clientRepositoryEntity.findByUsername(request.getUsername());
        response.setAlertEmail(clientRepositoryEntity.updateAlertEmail(client, request.isAlertEmail()));

        return response;
    }
}

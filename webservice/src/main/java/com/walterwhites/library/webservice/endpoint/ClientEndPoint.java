package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.entity.ClientRepositoryEntityImpl;
import com.walterwhites.library.consumer.repository.jaxb.impl.ClientRepositoryImpl;
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

    private final ClientRepositoryImpl clientRepository;
    private final ClientRepositoryEntityImpl clientRepositoryEntity;

    @Autowired
    public ClientEndPoint(ClientRepositoryImpl clientRepository, ClientRepositoryEntityImpl clientRepositoryEntity) {
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
        Boolean value = clientRepositoryEntity.updateAlertEmail(client, request.isAlertEmail());
        System.out.println("testhgfsdjhf");
        System.out.println(value.toString());
        response.setAlertEmail(value);

        return response;
    }
}

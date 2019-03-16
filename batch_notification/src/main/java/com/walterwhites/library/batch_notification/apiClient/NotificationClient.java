package com.walterwhites.library.batch_notification.apiClient;

import library.io.github.walterwhites.loans.UpdateAllNotificationRequest;
import library.io.github.walterwhites.loans.UpdateAllNotificationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class NotificationClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(NotificationClient.class);

    public UpdateAllNotificationResponse updateOutdatedReservations() {
        UpdateAllNotificationRequest request = new UpdateAllNotificationRequest();
        return (UpdateAllNotificationResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws/loans", request,
                        new SoapActionCallback(
                                "http://localhost:8080/dashboard"));
    }
}
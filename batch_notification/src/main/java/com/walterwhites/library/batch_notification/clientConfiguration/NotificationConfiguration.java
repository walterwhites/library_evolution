package com.walterwhites.library.batch_notification.clientConfiguration;

import com.walterwhites.library.batch_notification.apiClient.NotificationClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class NotificationConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("library.io.github.walterwhites.loans");

        return marshaller;
    }

    @Bean
    public NotificationClient loanClient(Jaxb2Marshaller marshaller) {
        NotificationClient notificationClient = new NotificationClient();
        notificationClient.setDefaultUri("http://localhost:8081/ws/loans");
        notificationClient.setMarshaller(marshaller);
        notificationClient.setUnmarshaller(marshaller);
        return notificationClient;
    }
}

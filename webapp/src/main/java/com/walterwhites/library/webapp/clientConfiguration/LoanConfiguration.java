package com.walterwhites.library.webapp.clientConfiguration;

import com.walterwhites.library.webapp.apiClient.LoanClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class LoanConfiguration {

    @Bean
    public Jaxb2Marshaller marshallerClient() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("library.io.github.walterwhites.loans");

        return marshaller;
    }

    @Bean
    public LoanClient loanClient(Jaxb2Marshaller marshallerClient) {
        LoanClient loanClient = new LoanClient();
        loanClient.setDefaultUri("http://localhost:8081/ws/loans");
        loanClient.setMarshaller(marshallerClient);
        loanClient.setUnmarshaller(marshallerClient);
        return loanClient;
    }
}
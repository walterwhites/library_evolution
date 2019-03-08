package com.walterwhites.library.batch_email.clientConfiguration;

import com.walterwhites.library.batch_email.apiClient.LoanClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class LoanConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("library.io.github.walterwhites.loans");

        return marshaller;
    }

    @Bean
    public LoanClient loanClient(Jaxb2Marshaller marshaller) {
        LoanClient loanClient = new LoanClient();
        loanClient.setDefaultUri("http://localhost:8081/ws/loans");
        loanClient.setMarshaller(marshaller);
        loanClient.setUnmarshaller(marshaller);
        return loanClient;
    }
}

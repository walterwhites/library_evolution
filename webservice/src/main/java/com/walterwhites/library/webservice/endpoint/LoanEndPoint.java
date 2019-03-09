package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.jaxb.impl.LoanRepositoryImpl;
import library.io.github.walterwhites.loans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Configuration
public class LoanEndPoint {
    private static final String NAMESPACE_URI = "library.io.github.walterwhites.loans";

    private final LoanRepositoryImpl loanRepository;

    @Autowired
    public LoanEndPoint(LoanRepositoryImpl loanRepository) {
        this.loanRepository = loanRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllNotReturnedBookRequest")
    @ResponsePayload
    public GetAllNotReturnedBookResponse getAllNotReturnedBookRequest(@RequestPayload GetAllNotReturnedBookRequest request) {
        GetAllNotReturnedBookResponse response = new GetAllNotReturnedBookResponse();
        response.getLoan().addAll(loanRepository.findAllNotReturnedBook());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllSoonExpiredLoanRequest")
    @ResponsePayload
    public GetAllSoonExpiredLoanResponse getAllSoonExpiredLoanRequest(@RequestPayload GetAllSoonExpiredLoanRequest request) {
        GetAllSoonExpiredLoanResponse response = new GetAllSoonExpiredLoanResponse();
        response.getLoan().addAll(loanRepository.findAllSoonLoanExpired());
        return response;
    }
}
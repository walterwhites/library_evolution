package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.jaxb.impl.LoanRepositoryImpl;
import library.io.github.walterwhites.loans.GetAllNotReturnedBookRequest;
import library.io.github.walterwhites.loans.GetAllNotReturnedBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Configuration
public class BookEndPointLoans {
    private static final String NAMESPACE_URI = "library.io.github.walterwhites.loans";

    private final LoanRepositoryImpl loanRepository;

    @Autowired
    public BookEndPointLoans(LoanRepositoryImpl loanRepository) {
        this.loanRepository = loanRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllNotReturnedBookRequest")
    @ResponsePayload
    public GetAllNotReturnedBookResponse getAllNotReturnedBookRequest(@RequestPayload GetAllNotReturnedBookRequest request) {
        GetAllNotReturnedBookResponse response = new GetAllNotReturnedBookResponse();
        response.getBooksNotReturned().addAll(loanRepository.findAllNotReturnedBook());
        return response;
    }
}
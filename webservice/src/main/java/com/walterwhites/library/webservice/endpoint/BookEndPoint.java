package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.entity.LoanRepositoryEntityImpl;
import com.walterwhites.library.consumer.repository.jaxb.impl.BookRepositoryImpl;
import library.io.github.walterwhites.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Configuration
public class BookEndPoint {
    private static final String NAMESPACE_URI = "library.io.github.walterwhites";

    private final BookRepositoryImpl bookRepository;
    private final LoanRepositoryEntityImpl loanRepositoryEntity;

    @Autowired
    public BookEndPoint(BookRepositoryImpl bookRepository, LoanRepositoryEntityImpl loanRepositoryEntity) {
        this.bookRepository = bookRepository;
        this.loanRepositoryEntity = loanRepositoryEntity;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBookFromTitle(@RequestPayload GetBookRequest request) {
        GetBookResponse response = new GetBookResponse();
        response.getBook().addAll(bookRepository.findByTitle(request.getTitle()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postBookBorrowedRequest")
    @ResponsePayload
    public PostBookBorrowedResponse postBookBorrowed(@RequestPayload PostBookBorrowedRequest request) {
        PostBookBorrowedResponse response = new PostBookBorrowedResponse();
        Book book = bookRepository.findById(request.getId());
        Long loan_id = loanRepositoryEntity.saveBookBorrowed(book, request.getClientId());
        response.setClientId(loan_id);
        response.setId(loan_id);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookFromIdRequest")
    @ResponsePayload
    public GetBookFromIdResponse getBookFromId(@RequestPayload GetBookFromIdRequest request) {
        GetBookFromIdResponse response = new GetBookFromIdResponse();
        response.setBook(bookRepository.findById(request.getId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBookRequest")
    @ResponsePayload
    public GetAllBookResponse getAllBook(@RequestPayload GetAllBookRequest request) {
        GetAllBookResponse response = new GetAllBookResponse();
        response.getBook().addAll(bookRepository.findAllBooks());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBookFromClientRequest")
    @ResponsePayload
    public GetAllBookFromClientResponse getAllBookFromClient(@RequestPayload GetAllBookFromClientRequest request) {
        GetAllBookFromClientResponse response = new GetAllBookFromClientResponse();
        response.getBook().addAll(bookRepository.findAllBooksFromClient(request.getUsername()));
        return response;
    }
}
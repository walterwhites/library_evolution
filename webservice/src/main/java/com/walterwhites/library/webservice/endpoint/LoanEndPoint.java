package com.walterwhites.library.webservice.endpoint;

import com.walterwhites.library.consumer.repository.entity.LoanRepositoryEntityImpl;
import com.walterwhites.library.consumer.repository.entity.ReservationRepositoryEntityImpl;
import com.walterwhites.library.consumer.repository.jaxb.impl.BookRepositoryImpl;
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
    private final BookRepositoryImpl bookRepository;
    private final ReservationRepositoryEntityImpl reservationRepositoryEntity;


    @Autowired
    public LoanEndPoint(LoanRepositoryImpl loanRepository, BookRepositoryImpl bookRepository, ReservationRepositoryEntityImpl reservationRepositoryEntity) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.reservationRepositoryEntity = reservationRepositoryEntity;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateArchivedReservationRequest")
    @ResponsePayload
    public UpdateArchivedReservationResponse updateArchivedReservationResponse(@RequestPayload UpdateArchivedReservationRequest request) {
        UpdateArchivedReservationResponse response = new UpdateArchivedReservationResponse();
        response.setHasBeenArchived(loanRepository.updateArchivedReservation(request.getUsername(), request.getBookTitle()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLastReservationRequest")
    @ResponsePayload
    public GetLastReservationResponse getLastReservationRequest(@RequestPayload GetLastReservationRequest request) {
        GetLastReservationResponse response = new GetLastReservationResponse();
        Reservation reservation = loanRepository.findLastReservation(request.getBookTitle());
        if (reservation != null) {
            this.reservationRepositoryEntity.saveNewNotification(reservation);
        }
        response.setReservation(reservation);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllNotReturnedBookRequest")
    @ResponsePayload
    public GetAllNotReturnedBookResponse getAllNotReturnedBookRequest(@RequestPayload GetAllNotReturnedBookRequest request) {
        GetAllNotReturnedBookResponse response = new GetAllNotReturnedBookResponse();
        response.getLoan().addAll(loanRepository.findAllNotReturnedBook());
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAllNotificationRequest")
    @ResponsePayload
    public UpdateAllNotificationResponse updateAllNotificationRequest(@RequestPayload UpdateAllNotificationRequest request) {
        UpdateAllNotificationResponse response = new UpdateAllNotificationResponse();
        response.getNotification().addAll(loanRepository.updateAllNotification());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllSoonExpiredLoanRequest")
    @ResponsePayload
    public GetAllSoonExpiredLoanResponse getAllSoonExpiredLoanRequest(@RequestPayload GetAllSoonExpiredLoanRequest request) {
        GetAllSoonExpiredLoanResponse response = new GetAllSoonExpiredLoanResponse();
        response.getLoan().addAll(loanRepository.findAllSoonLoanExpired());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllReservationFromClientRequest")
    @ResponsePayload
    public GetAllReservationFromClientResponse getAllReservationFromClientRequest(@RequestPayload GetAllReservationFromClientRequest request) {
        GetAllReservationFromClientResponse response = new GetAllReservationFromClientResponse();
        response.getReservation().addAll(loanRepository.findAllReservationFromClient(request.getUsername()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postCancelReservationRequest")
    @ResponsePayload
    public PostCancelReservationResponse postCancelReservationResponse(@RequestPayload PostCancelReservationRequest request) {
        PostCancelReservationResponse response = new PostCancelReservationResponse();
        com.walterwhites.library.model.entity.Reservation reservation = reservationRepositoryEntity.findById(request.getId()).get();
        response.setId(reservationRepositoryEntity.cancelReservation(reservation));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postCreateReservationRequest")
    @ResponsePayload
    public PostCreateReservationResponse postCreateReservationResponse(@RequestPayload PostCreateReservationRequest request) {
        PostCreateReservationResponse response = new PostCreateReservationResponse();
        library.io.github.walterwhites.Book book = bookRepository.findById(request.getId());
        Long reservation_id = reservationRepositoryEntity.saveNewReservation(book, request.getClientId());
        response.setClientId(request.getClientId());
        response.setId(reservation_id);
        return response;
    }
}
package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepositoryEntity extends CrudRepository<Reservation, Long>, PagingAndSortingRepository<Reservation, Long> {
    void refresh(Reservation reservation);
}
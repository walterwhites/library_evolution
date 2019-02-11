package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepositoryEntity extends CrudRepository<Loan, Long> {
    void refresh(Loan loan);
}
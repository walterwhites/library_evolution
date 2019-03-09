package com.walterwhites.library.consumer.repository.jaxb.impl;

import library.io.github.walterwhites.loans.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository {
    public List<Loans> findAllNotReturnedBook();
    public List<Notification> updateAllNotification();
}

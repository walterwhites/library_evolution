package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByUsername(String username);
}
package com.walterwhites.library.consumer.repository.jaxb.impl;

import library.io.github.walterwhites.client.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository {
    public Client findById(Long id);
    public Client findByUsername(String username);
}

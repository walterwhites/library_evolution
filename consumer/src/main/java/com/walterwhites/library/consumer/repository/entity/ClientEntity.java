package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientEntity extends CrudRepository<Client, Long> {
}
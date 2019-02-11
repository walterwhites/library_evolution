package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminEntity extends CrudRepository<Admin, Long> {
}
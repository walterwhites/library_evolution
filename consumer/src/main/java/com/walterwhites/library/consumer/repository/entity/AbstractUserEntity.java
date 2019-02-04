package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.AbstractUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractUserEntity extends CrudRepository<AbstractUser, Long> {
}
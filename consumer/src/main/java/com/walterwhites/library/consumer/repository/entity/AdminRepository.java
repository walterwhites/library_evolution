package com.walterwhites.library.consumer.repository.entity;

import com.walterwhites.library.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
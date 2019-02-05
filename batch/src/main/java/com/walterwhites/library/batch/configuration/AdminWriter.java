package com.walterwhites.library.batch.configuration;

import com.walterwhites.library.consumer.repository.entity.AdminRepositoryImpl;
import com.walterwhites.library.model.entity.Admin;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class AdminWriter implements ItemWriter<Admin> {
    private final AdminRepositoryImpl adminRepository;

    @Autowired
    public AdminWriter(AdminRepositoryImpl adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    @Transactional
    public void write(List<? extends Admin> admins) throws Exception {
        adminRepository.saveAll(admins);
    }
}

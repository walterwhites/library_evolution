package com.walterwhites.library.batch.processor;

import com.walterwhites.library.business.utils.RoleEnum;
import com.walterwhites.library.model.entity.Admin;
import com.walterwhites.library.model.entity.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdminItemProcessor implements ItemProcessor<Admin, Admin> {

    private static final Logger log = LoggerFactory.getLogger(AdminItemProcessor.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Admin process(Admin admin) throws Exception {
        Admin transformedAdmin = new Admin();
        Library library = new Library();
        library.setName("Paris Francois Mitterand");
        transformedAdmin.setLibrary(library);
        transformedAdmin.setEnabled(true);
        transformedAdmin.setCreated_at(new Date());
        transformedAdmin.setUsername(admin.getEmail());
        transformedAdmin.setEmail("admin@gmail.com");
        transformedAdmin.setPassword(passwordEncoder().encode("password"));

        List<RoleEnum> adminRole = Arrays.asList(RoleEnum.ADMINISTRATOR);
        transformedAdmin.setRoles(adminRole);

        return transformedAdmin;
    }
}

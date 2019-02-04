package com.walterwhites.library.batch.processor;

import com.walterwhites.library.model.entity.Admin;
import com.walterwhites.library.model.entity.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Date;

public class AdminItemProcessor implements ItemProcessor<Admin, Admin> {

    private static final Logger log = LoggerFactory.getLogger(AdminItemProcessor.class);

    @Override
    public Admin process(Admin admin) throws Exception {
        admin.setPassword("password");
        Library library = new Library();
        library.setName("Paris Francois Mitterand");
        admin.setLibrary(library);
        admin.setEnabled(true);
        admin.setCreated_at(new Date());
        admin.setUsername(admin.getEmail());

        return admin;
    }
}

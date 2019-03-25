package com.walterwhites.library.webapp;

import com.walterwhites.library.business.utils.EmailService;
import com.walterwhites.library.webapp.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan("com.walterwhites.library.model.entity")
@ComponentScan(basePackages = {"com.walterwhites.library"})
public class LibraryApplicationTests {

    @Autowired
    private MainController controller;

    @Test
    public void contextLoads() throws Exception {
    }
}

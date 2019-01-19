package com.walterwhites.library.webapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void index() throws Exception {
        this.mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk()).andExpect(content().string(containsString("Dashboard")))
                .andExpect(status().isOk()).andExpect(content().string(containsString("French books")))
                .andExpect(status().isOk()).andExpect(content().string(containsString("English books")))
                .andExpect(status().isOk()).andExpect(content().string(containsString("Number of users")));

    }
}

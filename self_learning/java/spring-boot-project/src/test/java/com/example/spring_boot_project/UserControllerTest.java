package com.example.spring_boot_project;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import com.example.spring_boot_project.controller.UserController;
import com.example.spring_boot_project.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/users"))
               .andExpect(status().isOk())
               .andExpect(header().string("Content-Type", "text/plain;charset=UTF-8"));
    }

    @Test
    public void testAddNewUser() throws Exception {
        mockMvc.perform(get("/users"))
               .andExpect(status().isOk())
               .andExpect(header().string("Content-Type", "text/plain;charset=UTF-8"));
    }

    @Test
    public void testGetUsersEndpointValidationIdNotSpecified() throws Exception {
        mockMvc.perform(get("/users"))
               .andExpect(status().isBadRequest());
    }
}

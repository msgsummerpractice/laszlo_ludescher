package com.example.spring_boot_project;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import com.example.spring_boot_project.controller.UserController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUsersEndpoint() throws Exception {
        mockMvc.perform(get("/users?id=1000"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello there! Your id is: 1000 (This app is running on port 8081 and ip 127.0.0.1)"))
               .andExpect(header().string("Content-Type", "text/plain;charset=UTF-8"));
    }

    @Test
    public void testGetUsersEndpointValidationIdNotSpecified() throws Exception {
        mockMvc.perform(get("/users"))
               .andExpect(status().isBadRequest());
    }

}

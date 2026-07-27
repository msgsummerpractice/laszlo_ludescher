package com.example.spring_boot_project;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


import com.example.spring_boot_project.repository.UserRepo;
import com.example.spring_boot_project.service.UserService;

@SpringBootTest
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;

    @Autowired
    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        try{
                this.userService.createUser("name", "pwd", 0);
                assertThat(true).isFalse();
        }
        catch(IllegalArgumentException e) {
            assertThat(e.getMessage().equals("User password must be at least 4 characters!")).isTrue();
        }

        this.userService.createUser("name", "pwd1234", 0);
        assertThat(this.userService.getUserRepo().getNrOfUsers()==1).isTrue();

        try{
                this.userService.createUser("name", "pwd1234", 0);
                assertThat(true).isFalse();
        }
        catch(IllegalArgumentException e) {
            assertThat(e.getMessage().equals("User id must be unique")).isTrue();
        }
    }
    
}

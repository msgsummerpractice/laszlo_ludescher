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
                this.userService.create("name", "name@test.com", "pwd", "Bob", "Bane");
                assertThat(true).isFalse();
        }
        catch(IllegalArgumentException e) {
            assertThat(e.getMessage().equals("User password must be at least 4 characters!")).isTrue();
        }
        
        this.userService.create("name", "name@test.com", "pwd1234", "Bob", "Bane");
        assertThat(this.userService.count()==1).isTrue();
        assertThat(this.userService.findByEmail("name@test.com").getId() == 0L).isTrue();

        try{
                this.userService.create("name", "name@test.com", "pwd1234", "Bob", "Bane");
                assertThat(true).isFalse();
        }
        catch(IllegalArgumentException e) {
            assertThat(e.getMessage().equals("User with this email already exists!")).isTrue();
        }
    }

    @Test
    public void testDeleteById() {
        try {
            this.userService.deleteById(1L);
        }
        catch (Exception e) {
            assertThat(e.getMessage().equals("There is no user with id 1")).isTrue();
        }

        this.userService.deleteById(0L);
        assertThat(this.userService.count()==0).isTrue();
    }
}

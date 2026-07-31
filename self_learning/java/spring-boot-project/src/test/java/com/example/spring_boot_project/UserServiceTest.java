package com.example.spring_boot_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.spring_boot_project.repository.UserRepo;
import com.example.spring_boot_project.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        //TODO
        when(userRepo.findByEmail("name@test.com")).thenReturn(Optional.empty());
        this.userService.create("name", "name@test.com", "pwd1234", "Bob", "Bane");
        assertThat(this.userService.count() == 1).isTrue();
        assertThat(this.userService.findByEmail("name@test.com").getId() == 0L).isTrue();

        assertThatThrownBy(() -> this.userService.create("name", "name@test.com", "pwd1234", "Bob", "Bane"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("User with this email already exists!");
    }

    @Test
    public void testDeleteById() {
        //TODO
        when(userRepo.existsById(1L)).thenReturn(false);
       assertThatThrownBy(() -> this.userService.deleteById(1L))
            .isInstanceOf(Exception.class)
            .hasMessage("There is no user with id 1");

        this.userService.deleteById(0L);
        assertThat(this.userService.count() == 0).isTrue();
    }
}

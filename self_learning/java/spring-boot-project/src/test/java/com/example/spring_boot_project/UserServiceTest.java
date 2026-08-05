package com.example.spring_boot_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
        when(userRepo.findByEmail("name@test.com")).thenReturn(Optional.empty());
        this.userService.create("name", "name@test.com", "pwd1234", "Bob", "Bane");
        assertThat(this.userService.count() == 1).isTrue();
        assertThat(this.userService.findByEmail("name@test.com").getId() == 0L).isTrue();

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> this.userService.create("name", "name@test.com", "pwd1234", "Bob", "Bane"))
            .withMessage("User with this email already exists!");
    }

    @Test
    public void testDeleteById() {
        when(userRepo.existsById(1L)).thenReturn(false);
       assertThatExceptionOfType(Exception.class)
            .isThrownBy(() -> this.userService.deleteById(1L))
            .withMessage("There is no user with id 1");

        this.userService.deleteById(0L);
        assertThat(this.userService.count() == 0).isTrue();
    }
}

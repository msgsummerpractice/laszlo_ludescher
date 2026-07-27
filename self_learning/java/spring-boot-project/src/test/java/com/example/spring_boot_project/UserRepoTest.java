package com.example.spring_boot_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.spring_boot_project.model.User;
import com.example.spring_boot_project.repository.UserRepo;

@SpringBootTest
public class UserRepoTest {
    @Autowired
    private UserRepo userRepo;

    @Test
    public void testAddUser() {
        User user1 = new User("name", "pwd", 0);
        userRepo.addUser(user1);
        assertThat( userRepo.getNrOfUsers() == 1).isTrue();
        assertThat( userRepo.getUsers().get(0).getName().equals("name")).isTrue();
    }

    @Test void testDeleteUserWithId() {
        assertThat( userRepo.deleteUserWithId(1)).isFalse();
        assertThat( userRepo.deleteUserWithId(0)).isTrue();
        assertThat( userRepo.getNrOfUsers() == 0).isTrue();
        assertThat( userRepo.deleteUserWithId(0)).isFalse();
    }

    @Test
    public void testExistsUserWithId() {
        User user1 = new User("name", "pwd", 0);
        userRepo.addUser(user1);

        assertThat( userRepo.existsUserWithId(1)).isFalse();
        assertThat( userRepo.existsUserWithId(0)).isTrue();
    }


    
}

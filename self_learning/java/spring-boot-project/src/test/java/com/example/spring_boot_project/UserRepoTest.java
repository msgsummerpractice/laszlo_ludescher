package com.example.spring_boot_project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.spring_boot_project.model.User;
import com.example.spring_boot_project.repository.UserRepo;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class UserRepoTest {
    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    public void cleanDatabase() {
        userRepo.deleteAll();
    }

    @Test
    public void testFindTopTenUsersByUsername() {
        User user1 = new User("name1", "name1@test.com", "pwd", "firstName", "lastName");
        User user2 = new User("name2", "name1@test.com", "pwd", "firstName", "lastName");
        User user3 = new User("name3", "name1@test.com", "pwd", "firstName", "lastName");
        User user4 = new User("name4", "name1@test.com", "pwd", "firstName", "lastName");
        User user5 = new User("name5", "name1@test.com", "pwd", "firstName", "lastName");
        User user6 = new User("name6", "name1@test.com", "pwd", "firstName", "lastName");
        User user7 = new User("name7", "name1@test.com", "pwd", "firstName", "lastName");
        User user8 = new User("name8", "name1@test.com", "pwd", "firstName", "lastName");
        User user9 = new User("name9", "name1@test.com", "pwd", "firstName", "lastName");
        User user10 = new User("name10", "name1@test.com", "pwd", "firstName", "lastName");
        User user11 = new User("name11", "name1@test.com", "pwd", "firstName", "lastName");
        User user12 = new User("name12", "name1@test.com", "pwd", "firstName", "lastName");
        User user13 = new User("name13", "name1@test.com", "pwd", "firstName", "lastName");
        User user14 = new User("name14", "name1@test.com", "pwd", "firstName", "lastName");

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
        userRepo.save(user5);
        userRepo.save(user6);
        userRepo.save(user7);
        userRepo.save(user8);
        userRepo.save(user9);
        userRepo.save(user10);
        userRepo.save(user11);
        userRepo.save(user12);
        userRepo.save(user13);
        userRepo.save(user14);

        List<User> topTenUsers= this.userRepo.findTop10ByOrderByUsernameAsc();
        assertThat(topTenUsers.size() == 10).isTrue();
        assertThat(topTenUsers.get(0).getUsername().equals("name1")).isTrue();        
    }

    @Test
    public void testCountUsers() {

        User user1 = new User("name1", "name1@test.com", "pwd", "firstName", "lastName");
        User user2 = new User("name2", "name1@test.com", "pwd", "firstName", "lastName");
        User user3 = new User("name3", "name1@test.com", "pwd", "firstName", "lastName");
        User user4 = new User("name4", "name1@test.com", "pwd", "firstName", "lastName");
        User user5 = new User("name5", "name1@test.com", "pwd", "firstName", "lastName");
        User user6 = new User("name6", "name1@test.com", "pwd", "firstName", "lastName");
        User user7 = new User("name7", "name1@test.com", "pwd", "firstName", "lastName");

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
        userRepo.save(user5);
        userRepo.save(user6);
        userRepo.save(user7);
        assertThat(this.userRepo.countUsers() == 7L).isTrue();
    }
}

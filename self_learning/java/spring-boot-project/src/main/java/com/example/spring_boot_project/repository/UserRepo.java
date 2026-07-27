package com.example.spring_boot_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spring_boot_project.model.User;

public interface UserRepo extends JpaRepository<User, Long >{

    public User findByEmail(String email);

    public List<User> findTop10ByOrderByUsernameAsc();

    @Query("SELECT COUNT(u) FROM User u")
    public Long countUsers();
}

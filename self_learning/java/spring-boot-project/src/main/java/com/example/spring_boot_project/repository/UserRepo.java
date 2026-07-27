package com.example.spring_boot_project.repository;

import java.util.List;

import com.example.spring_boot_project.model.User;

public interface UserRepo {
    public List<User> getUsers();
    public void addUser(User user);
    public Boolean deleteUserWithId(int id);
    public Boolean existsUserWithId(int id);
    public int getNrOfUsers();
}

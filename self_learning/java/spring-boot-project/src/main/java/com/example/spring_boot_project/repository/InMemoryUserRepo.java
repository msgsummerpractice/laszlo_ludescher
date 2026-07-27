package com.example.spring_boot_project.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.spring_boot_project.model.User;

@Component
public class InMemoryUserRepo implements UserRepo{
    private List<User> users = new ArrayList<>();
    private int nrOfUsers;

    public InMemoryUserRepo() {
        this.nrOfUsers=0;
    }

    public int getNrOfUsers() {
        return this. nrOfUsers;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        this.users.add(user);
        this.nrOfUsers++;
    }

    public Boolean deleteUserWithId(int id) {
        for (int i=0; i < this.nrOfUsers; i++ ) {
            if (users.get(i).getId() == id) {
                this.users.remove(i);
                nrOfUsers--;
                return true;
            }
        }
        return false;
    }

    public Boolean existsUserWithId(int id) {
        for (int i=0; i < this.nrOfUsers; i++ ) {
            if (users.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }
}

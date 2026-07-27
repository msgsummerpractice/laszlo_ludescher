package com.example.spring_boot_project.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class User {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 4)
    private String pwd;

    @NotBlank(message = "Id cannot be blank")
    private int id;

    public User(String name, String pwd, int id) {
       this.name = name;
       this.pwd = pwd; 
       this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }
}

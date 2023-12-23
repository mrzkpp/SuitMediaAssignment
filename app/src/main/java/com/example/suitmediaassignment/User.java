package com.example.suitmediaassignment;

// User.java
public class User {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

    public User(int id, String email, String firstName, String lastName, String avatar) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

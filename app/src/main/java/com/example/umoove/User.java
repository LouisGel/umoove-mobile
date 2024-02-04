package com.example.umoove;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private int points;
    private String password;
    private int movementTempId;

    public User() {
        firstname = "John";
        lastname = "Doe";
        email = "abd123@gmail.com";
        points = 500;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMovementTempId() {
        return movementTempId;
    }

    public void setMovementTempId(int movementTempId) {
        this.movementTempId = movementTempId;
    }
}

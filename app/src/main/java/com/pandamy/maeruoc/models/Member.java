package com.pandamy.maeruoc.models;

public class Member {

    //variable:
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    //Constructor:
    public Member(int id, String firstname, String lastName, String email) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

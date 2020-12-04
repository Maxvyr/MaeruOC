package com.pandamy.maeruoc.models;

public class Member {

    //variable:
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Construtor
     * @param id an integer that corresponds to the id
     * @param firstName a{@link String} that contains the first name
     * @param lastName a{@link String} that contains the last name
     * @param email a{@link String} that contains the email
     */
    public Member(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //GET AND SET
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

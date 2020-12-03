package com.pandamy.maeruoc.models;

public class Room {

    //variable
    private int id;
    private String name;

    //Constructor
    public Room(int id, String name){
        this.id = id;
        this.name = name;
    }


    //GET AND SET
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

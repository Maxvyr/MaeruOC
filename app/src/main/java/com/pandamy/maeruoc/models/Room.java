package com.pandamy.maeruoc.models;


public class Room {

    //variable
    private int id;
    private String name;
    private RoomColor color;

    //Constructor
    public Room(int id, String name, RoomColor color){
        this.id = id;
        this.name = name;
        this.color = color;
    }


    //GET AND SET
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RoomColor getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(RoomColor color) {
        this.color = color;
    }
}

package com.pandamy.maeruoc.models;


public class Room {

    //variable
    private int id;
    private String name;
    private RoomColor color;

    /**
     * Construtor
     * @param id an integer that corresponds to the id
     * @param name a{@link String} that contains the name
     * @param color a{@link RoomColor} that contains the color of the room
     */
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

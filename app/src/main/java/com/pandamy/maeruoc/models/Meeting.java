package com.pandamy.maeruoc.models;

public class Meeting {
    //variable:
    private int id;
    private String title;
    private String date;
    private Room room;
    private Member member;


    public Meeting(int id, String title, String date, Room room, Member member) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.room = room;
        this.member = member;
    }

    //GET AND SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}

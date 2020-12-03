package com.pandamy.maeruoc.models;

public class Meeting {
    //variable:
    private int id;
    private String title;
    private String hour;
    private Room room;
    private Member member;


    public Meeting(int id, String title, String hour, Room room, Member member) {
        this.id = id;
        this.title = title;
        this.hour = hour;
        this.room = room;
        this.member = member;
    }

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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
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

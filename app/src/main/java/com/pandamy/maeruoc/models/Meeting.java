package com.pandamy.maeruoc.models;

/*
    Models return Meeting.
    @param id => id of meeting
    @param title => title of the meeting
    @param date => date of  the meeting
    @param room => room use for the meeting
    @param member => member call for the meeting
 */

public class Meeting {
    //variable:
    private int id;
    private String title;
    private String date;
    private Room room;
    private String member;


    public Meeting(int id, String title, String date, Room room, String member) {
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

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}

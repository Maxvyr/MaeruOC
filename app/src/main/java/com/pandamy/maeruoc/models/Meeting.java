package com.pandamy.maeruoc.models;

/*
    Models return Meeting.
    @param id => id of meeting
    @param title => title of the meeting
    @param date => date of  the meeting
    @param room => room use for the meeting
    @param member => member call for the meeting
 */


import android.os.Parcel;
import android.os.Parcelable;

public class Meeting implements Parcelable {
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

    protected Meeting(Parcel in) {
        id = in.readInt();
        title = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

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

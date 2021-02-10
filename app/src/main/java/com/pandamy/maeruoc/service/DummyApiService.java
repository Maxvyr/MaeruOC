package com.pandamy.maeruoc.service;

import android.util.Log;

import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.models.Member;
import com.pandamy.maeruoc.models.Room;

import java.util.List;

public class DummyApiService implements  ApiService {

    //variables:
    private List<Meeting> meetings = DummyGenerator.generatorOfDummyMeetings();
    private List<Room> rooms = DummyGenerator.generatorOfDummyRooms();
    private List<String> roomsNames = DummyGenerator.getRoomsNameOfDummyRooms();
    private List<Member> members = DummyGenerator.generatorOfDummyMembers();

    //methods:
    @Override
    public List<Meeting> getMeetings() {
        return this.meetings;
    }

    @Override
    public List<Room> getRooms() {
        return this.rooms;
    }

    @Override
    public List<String> getRoomsName() {
        return this.roomsNames;
    }

    @Override
    public List<Member> getMembers() {
        return this.members;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        this.meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        this.meetings.remove(meeting);
    }

    @Override
    public Meeting createMeeting(int id, String title, String date, Room room, String membersEmail) {
        return new Meeting(
                id,
                title,
                date,
                room,
                membersEmail
        );
    }

    @Override
    public void filterMeetingByName(Meeting meeting, List<Meeting> filteredList, String filter) {
        if(meeting.getTitle().toLowerCase().contains(filter)){
            filteredList.add(meeting);
        }
    }

    @Override
    public void filterMeetingByRoom(Meeting meeting, List<Meeting> filteredList, String filter) {
        if(meeting.getRoom().getName().toLowerCase().contains(filter)){
            filteredList.add(meeting);
        }
    }

    @Override
    public void filterMeetingByDate(Meeting meeting, List<Meeting> filteredList, String filter) {
        if(meeting.getDate().contains(filter)){
            filteredList.add(meeting);
        }
    }

}

package com.pandamy.maeruoc.service;

import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.models.Member;
import com.pandamy.maeruoc.models.Room;

import java.util.List;

public class DummyApiService implements  ApiService {

    //variables:
    private List<Meeting> meetings = DummyGenerator.generatorOfDummyMeetings();
    private List<Room> rooms = DummyGenerator.generatorOfDummyRooms();
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
}

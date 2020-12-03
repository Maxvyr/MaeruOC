package com.pandamy.maeruoc.service;

import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.models.Member;
import com.pandamy.maeruoc.models.Room;

import java.util.List;

public interface ApiService {

    List<Meeting> getMeetings();
    List<Room> getRooms();
    List<Member> getMembers();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);


}

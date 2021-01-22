package com.pandamy.maeruoc;


import com.pandamy.maeruoc.di.DI;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.models.Member;
import com.pandamy.maeruoc.models.Room;
import com.pandamy.maeruoc.models.RoomColor;
import com.pandamy.maeruoc.service.ApiService;
import com.pandamy.maeruoc.service.DummyGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private ApiService service;

    @Before
    public void setup() {
        service = DI.getApiService();
    }

    /**
     * Delete Meeting with success
     */
    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        List<Meeting> meetingList = service.getMeetings();
        service.deleteMeeting(meetingToDelete);
        assertFalse(meetingList.contains(meetingToDelete));
    }

    /**
     * Add Meeting with success
     */
    @Test
    public void addMeetingWithSuccess() {
        Meeting meetingToAdd = new Meeting(9999, "Meeting test", "23:23", new Room(5, "Bowser",RoomColor.COLOR_BROWN), "fa@lamzone.com");
        List<Meeting> meetingList = service.getMeetings();
        service.addMeeting(meetingToAdd);
        assertTrue(meetingList.contains(meetingToAdd));
    }

    /**
     * Create Meeting with success
     */
    @Test
    public void createNewMeetingWithSuccess() {
        List<Meeting> meetingList = service.getMeetings();
        Meeting meetingCreated = service.createMeeting(100,"Created","00:23",new Room(5, "Bowser",RoomColor.COLOR_BROWN),"kaori@lamzone.com");
        service.addMeeting(meetingCreated);
        assertTrue(meetingList.contains(meetingCreated));
    }

    /**
     * Get Meetings
     */
    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetingListBase = DummyGenerator.generatorOfDummyMeetings();
        List<Meeting> meetingListRecover = service.getMeetings();
        assertEquals(meetingListBase.size(), meetingListRecover.size());
    }

    /**
     * Get Rooms
     */
    @Test
    public void getRoomsWithSuccess() {
        List<Room> roomsListBase = DummyGenerator.generatorOfDummyRooms();
        List<Room> roomsListRecover = service.getRooms();
        assertEquals(roomsListBase.get(0), roomsListRecover.get(0));
    }

    /**
     * Get RoomsName
     */
    @Test
    public void getRoomsNameWithSuccess() {
        List<String> roomsNameListBase = DummyGenerator.getRoomsNameOfDummyRooms();
        List<String> roomsNameListRecover = service.getRoomsName();
        assertEquals(roomsNameListBase.get(1), roomsNameListRecover.get(1));
    }

    /**
     * Get Members
     */
    @Test
    public void getMembersWithSuccess() {
        List<Member> membersListBase = DummyGenerator.generatorOfDummyMembers();
        List<Member> membersListRecover = service.getMembers();
        assertEquals(membersListBase.get(0), membersListRecover.get(0));
    }
}

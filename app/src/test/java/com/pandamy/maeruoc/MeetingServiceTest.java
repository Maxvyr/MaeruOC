package com.pandamy.maeruoc;


import com.pandamy.maeruoc.di.DI;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.models.Member;
import com.pandamy.maeruoc.models.Room;
import com.pandamy.maeruoc.models.RoomColor;
import com.pandamy.maeruoc.service.ApiService;
import com.pandamy.maeruoc.service.DummyGenerator;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Meeting service
 */
@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MeetingServiceTest {

    private ApiService service;

    @Before
    public void setup() {
        service = DI.getApiService();
    }

    /**
     * Get Meetings
     */
    @Test
    public void A_getMeetingsWithSuccess() {
        List<Meeting> meetingListBase = DummyGenerator.generatorOfDummyMeetings();
        List<Meeting> meetingListRecover = service.getMeetings();
        assertEquals(meetingListBase.get(0), meetingListRecover.get(0));
        assertEquals(meetingListBase.size(), meetingListRecover.size());
    }

    /**
     * Get Rooms
     */
    @Test
    public void B_getRoomsWithSuccess() {
        List<Room> roomsListBase = DummyGenerator.generatorOfDummyRooms();
        List<Room> roomsListRecover = service.getRooms();
        assertEquals(roomsListBase.get(2), roomsListRecover.get(2));
        assertEquals(roomsListBase.size(), roomsListRecover.size());
    }

    /**
     * Get RoomsName
     */
    @Test
    public void C_getRoomsNameWithSuccess() {
        List<String> roomsNameListBase = DummyGenerator.getRoomsNameOfDummyRooms();
        List<String> roomsNameListRecover = service.getRoomsName();
        assertEquals(roomsNameListBase.get(1), roomsNameListRecover.get(1));
        assertEquals(roomsNameListBase.size(), roomsNameListRecover.size());
    }

    /**
     * Get Members
     */
    @Test
    public void D_getMembersWithSuccess() {
        List<Member> membersListBase = DummyGenerator.generatorOfDummyMembers();
        List<Member> membersListRecover = service.getMembers();
        assertEquals(membersListBase.get(0), membersListRecover.get(0));
        assertEquals(membersListBase.size(), membersListRecover.size());
    }

    /**
     * Delete Meeting with success
     */
    @Test
    public void E_deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        List<Meeting> meetingList = service.getMeetings();
        service.deleteMeeting(meetingToDelete);
        assertFalse(meetingList.contains(meetingToDelete));
    }

    /**
     * Add Meeting with success
     */
    @Test
    public void F_addMeetingWithSuccess() {
        Meeting meetingToAdd = new Meeting(
                9999,
                "Meeting test",
                "23:23",
                new Room(5, "Bowser",RoomColor.COLOR_BROWN),
                "fa@lamzone.com"
        );
        List<Meeting> meetingList = service.getMeetings();
        service.addMeeting(meetingToAdd);
        assertTrue(meetingList.contains(meetingToAdd));
    }

    /**
     * Create Meeting with success
     */
    @Test
    public void G_createNewMeetingWithSuccess() {
        List<Meeting> meetingList = service.getMeetings();
        Meeting meetingCreated = service.createMeeting(
                100,
                "Created",
                "00:23",
                new Room(5, "Bowser",RoomColor.COLOR_BROWN),
                "kaori@lamzone.com"
        );
        service.addMeeting(meetingCreated);
        assertTrue(meetingList.contains(meetingCreated));
    }

    /**
     * Filter meeting by name
     */
    @Test
    public void H_filterMeetingByNameWithSuccess() {
        String name = "Meeting D";
        List<Meeting> filterList = new ArrayList<>();
        List<Meeting> meetingsList = service.getMeetings();
        for(Meeting meeting : meetingsList){
            service.filterMeetingByName(meeting,filterList,name.toLowerCase());
        }
        assertEquals(1, filterList.size());
    }



    /**
     * Filter meeting by date
     */
    @Test
    public void I_filterMeetingByDateWithSuccess() {
        String name = "20:30";
        List<Meeting> filterList = new ArrayList<>();
        List<Meeting> meetingsList = service.getMeetings();
        for(Meeting meeting : meetingsList){
            service.filterMeetingByDate(meeting,filterList,name.toLowerCase());
        }
        assertEquals(1, filterList.size());
    }

    /**
     * Filter meeting by room
     */
    @Test
    public void J_filterMeetingByRoomWithSuccess() {
        String name = "Mario";
        List<Meeting> filterList = new ArrayList<>();
        List<Meeting> meetingsList = service.getMeetings();
        for(Meeting meeting : meetingsList){
            service.filterMeetingByRoom(meeting,filterList,name.toLowerCase());
        }
        assertEquals(1, filterList.size());
    }
}

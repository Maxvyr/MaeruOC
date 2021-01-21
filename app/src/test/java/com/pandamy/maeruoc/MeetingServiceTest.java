package com.pandamy.maeruoc;


import com.pandamy.maeruoc.di.DI;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.service.ApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

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
        Meeting MeetingToDelete = service.getMeetings().get(0);
        List<Meeting> MeetingList = service.getMeetings();
        service.deleteMeeting(MeetingToDelete);
        assertFalse(MeetingList.contains(MeetingToDelete));
    }


    /**
     * Add Meeting with success
     */
    @Test
    public void addMeetingWithSuccess() {
        Meeting MeetingToAdd = service.getMeetings().get(0);
        service.addMeeting(MeetingToAdd);
        List<Meeting> MeetingList = service.getMeetings();
        assertTrue(MeetingList.contains(MeetingToAdd));
    }
}

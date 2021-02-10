package com.pandamy.maeruoc.service;

import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.models.Member;
import com.pandamy.maeruoc.models.Room;

import java.util.List;

public interface ApiService {

    /**
     * Returns a {@link List} of {@link Meeting}
     * @return a {@link List} of {@link Meeting}
     */
    List<Meeting> getMeetings();

    /**
     * Returns a {@link List} of {@link Room}
     * @return a {@link List} of {@link Room}
     */
    List<Room> getRooms();

    /**
     * Returns a {@link List} of {@link String}
     * @return a {@link List} of {@link String}
     */
    List<String> getRoomsName();

    /**
     * Returns a {@link List} of {@link Member}
     * @return a {@link List} of {@link Member}
     */
    List<Member> getMembers();

    /**
     * Adds the {@link Meeting} in argument
     * @param meeting a {@link Meeting}
     */
    void addMeeting(Meeting meeting);


    /**
     * Deletes the {@link Meeting} in argument
     * @param meeting a {@link Meeting}
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Deletes the {@link Meeting} in argument
     * @param title
     * @param date
     * @param room
     * @param members
     */
    Meeting createMeeting(int id,String title, String date, Room room, String members);

    /**
     * Filer by Meeting Name
     */
    void filterMeetingByName(Meeting meeting, List<Meeting> filteredList, String filter);

    /**
     * Filer by Room Name
     */
    void filterMeetingByRoom(Meeting meeting, List<Meeting> filteredList, String filter);

    /**
     * Filer by Date
     */
    void filterMeetingByDate(Meeting meeting, List<Meeting> filteredList, String filter);
}

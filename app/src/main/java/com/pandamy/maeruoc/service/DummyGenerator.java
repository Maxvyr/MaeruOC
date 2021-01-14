package com.pandamy.maeruoc.service;

import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.models.Member;
import com.pandamy.maeruoc.models.Room;
import com.pandamy.maeruoc.models.RoomColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyGenerator {

    //List
    private static List<Meeting> dummyMeetings = Arrays.asList(
            new Meeting(1, "Meeting A", "14:00", new Room(1, "Peach", RoomColor.COLOR_YELLOW), new Member(1, "Lea", "Chang", "lea@lamzone.com")),
            new Meeting(2, "Meeting B", "16:00", new Room(2, "Mario", RoomColor.COLOR_RED),new Member(3, "Haneul", "Zhang", "haneul@lamzone.com")),
            new Meeting(3, "Meeting C", "19:00", new Room(3, "Luigi",RoomColor.COLOR_GREEN),  new Member(7, "Hideki", "An", "hideki@lamzone.com")),
            new Meeting(4, "Meeting D", "20:30", new Room(4, "Yoshi",RoomColor.COLOR_BLUE), new Member(10, "Fuhao", "Barde", "fuhai@lamzone.com"))
    );

    private static List<Room> dummyRooms = Arrays.asList(
            new Room(1, "Peach", RoomColor.COLOR_YELLOW),
            new Room(2, "Mario", RoomColor.COLOR_RED),
            new Room(3, "Luigi",RoomColor.COLOR_GREEN),
            new Room(4, "Yoshi",RoomColor.COLOR_BLUE),
            new Room(5, "Bowser",RoomColor.COLOR_BROWN)
    );

    private static List<Member> dummyMembers = Arrays.asList(
            new Member(1, "Lea", "Chang", "lea@lamzone.com"),
            new Member(2, "Jun", "Zarty", "jun@lamzone.com"),
            new Member(3, "Haneul", "Zhang", "haneul@lamzone.com"),
            new Member(4, "Jin", "Hua", "jin@lamzone.com"),
            new Member(5, "Manchu", "Zhen", "manchu@lamzone.com"),
            new Member(6, "Sakura", "Tanaka", "sakura@lamzone.com"),
            new Member(7, "Hideki", "An", "hideki@lamzone.com"),
            new Member(8, "Maxime", "Vy", "maxime@lamzone.com"),
            new Member(9, "Wang", "Lei", "wang@lamzone.com"),
            new Member(10, "Fuhao", "Barde", "fuhai@lamzone.com")
    );

    //methods:

    /**
     * Generates the dummy meetings
     * @return a {@link List} of {@link Meeting}
     */
    public static List<Meeting> generatorOfDummyMeetings() {
        return new ArrayList<>(dummyMeetings);
    }

    /**
     * Generates the dummy rooms
     * @return a {@link List} of {@link Room}
     */
    public static List<Room> generatorOfDummyRooms() {
        return new ArrayList<>(dummyRooms);
    }

    public static List<String> getRoomsNameOfDummyRooms() {
        List<String> nameOfRooms = new ArrayList<>();
        for (Room room : dummyRooms) {
            nameOfRooms.add(room.getName());
        }
        return nameOfRooms;
    }

    /**
     * Generates the dummy members
     * @return a {@link List} of {@link Member}
     */
    public static List<Member> generatorOfDummyMembers() {
        return new ArrayList<>(dummyMembers);
    }
}

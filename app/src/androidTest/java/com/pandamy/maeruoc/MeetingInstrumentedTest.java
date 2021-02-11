package com.pandamy.maeruoc;

import android.content.Context;

import androidx.test.espresso.action.ViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.service.DummyGenerator;
import com.pandamy.maeruoc.ui.ListActivity;
import com.pandamy.maeruoc.utils.AddMemberViewAction;
import com.pandamy.maeruoc.utils.DeleteViewAction;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.pandamy.maeruoc.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MeetingInstrumentedTest {


    //Variable
    private ListActivity mActivity;
    private final int memberListSize = DummyGenerator.generatorOfDummyMembers().size();
    private int meetingListSize = DummyGenerator.generatorOfDummyMeetings().size();

    @Rule
    public ActivityTestRule<ListActivity> mActivityRule =
            new ActivityTestRule<>(ListActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void A_meetingList_shouldNotBeEmpty() {
        onView(withId(R.id.list_meetings))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we click on button add meetings, got to page add meeting
     * then add new meeting
     */
    @Test
    public void B_meetingList_clickAddMeetingButton_shouldGotoPageAddMeeting_thenAddMeeting() {
        //Given

        //Click FAB to go to page add meeting
        onView(withId(R.id.fab_add_meeting)).perform(click());

        //show page add meeting
        onView(withId(R.id.scroll_view_add_meeting_activity))
                .check(matches(isDisplayed()));

        // TEXT INPUT EDIT TEXT: Writes something
        onView(withId(R.id.add_meeting_txt_topic))
                .perform(replaceText("new Meeting"),
                closeSoftKeyboard());

        //HOURS
        onView(withId(R.id.add_meeting_b_hour))
                .perform(click());
        onView(withText("OK"))
                .perform(click());

        //DATE
        onView(withId(R.id.add_meeting_b_date))
                .perform(click());
        onView(withText("OK"))
                .perform(click());

        //Members
        onView(withId(R.id.member_recycler_view)).check(matches(isDisplayed()))
                .check(withItemCount(memberListSize))
                .perform(actionOnItemAtPosition(0,
                        new AddMemberViewAction(R.id.item_member_check)));

        //FAB button for add
        onView(withId(R.id.add_meeting_fab)).perform(click());

        //RV have a new meetings
        onView(withId(R.id.list_meetings))
                .check(matches(isDisplayed()))
                .check(withItemCount(meetingListSize + 1));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void C_meetingList_deleteAction_shouldRemoveItem() {
        //Given
        final int itemsCount = 5;
        final int positionItem = itemsCount - 1;
        onView(withId(R.id.list_meetings)).check(withItemCount(itemsCount));
        onView(withId(R.id.list_meetings))
                .perform(actionOnItemAtPosition(positionItem, new DeleteViewAction()));
        onView(withId(R.id.list_meetings)).check(withItemCount(itemsCount - 1));
    }



    /**
     * Filter meeting by name of Meetings
     */
    @Test
    public void D_meetingList_filterAction_byMeetingName() {

        //click on button search
        onView(withId(R.id.filter_title))
                .perform(click());

        //search meeting by name
        onView(withId(R.id.search_src_text))
                .perform(typeText("Meeting A"));

        //check result
        onView(withId(R.id.list_meetings))
                .check(matches(isDisplayed()))
                .check(withItemCount(1));

        //delete name
        onView(withId(R.id.search_src_text))
                .perform(replaceText(""));
    }

    /**
     * Filter meeting by date
     */
    @Test
    public void E_meetingList_filterAction_byDate() {

        //click on button search
        onView(withId(R.id.filter_date))
                .perform(click());

        //search meeting by name
        onView(withId(R.id.search_src_text))
                .perform(typeText("20:30"));

        //check result
        onView(withId(R.id.list_meetings))
                .check(matches(isDisplayed()))
                .check(withItemCount(1));

        //delete name
        onView(withId(R.id.search_src_text))
                .perform(replaceText(""));
    }

    /**
     * Filter meeting by room
     */
    @Test
    public void F_meetingList_filterAction_byRoomName() {

        //click on button search
        onView(withId(R.id.filter_room))
                .perform(click());

        //search meeting by name
        onView(withId(R.id.search_src_text))
                .perform(typeText("Mario"));

        //check result
        onView(withId(R.id.list_meetings))
                .check(matches(isDisplayed()))
                .check(withItemCount(1));

        //delete name
        onView(withId(R.id.search_src_text))
                .perform(replaceText(""));
    }
}
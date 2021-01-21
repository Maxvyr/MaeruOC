package com.pandamy.maeruoc;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.service.DummyGenerator;
import com.pandamy.maeruoc.ui.ListActivity;
import com.pandamy.maeruoc.utils.AddMemberViewAction;
import com.pandamy.maeruoc.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
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

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    //Variable
    private ListActivity mActivity;
    private final List<Meeting> meetingList = DummyGenerator.generatorOfDummyMeetings();
    private final int memberListSize = DummyGenerator.generatorOfDummyMembers().size();

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.pandamy.maeruoc", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<ListActivity> mActivityRule =
            new ActivityTestRule<>(ListActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }



    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void meetingList_deleteAction_shouldRemoveItem() {
        //Given
        final int itemsCount = meetingList.size();
        final int positionItem = itemsCount - 1;
        onView(withId(R.id.list_meetings)).check(withItemCount(itemsCount));
        onView(withId(R.id.list_meetings))
                .perform(actionOnItemAtPosition(positionItem, new DeleteViewAction()));
        onView(withId(R.id.list_meetings)).check(withItemCount(itemsCount - 1));
    }



    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void meetingList_shouldNotBeEmpty() {
        onView(withId(R.id.list_meetings))
                .check(matches(hasMinimumChildCount(1)));
    }


    /**
     * When we click on button add meetings, got to page add meeting
     * then add new meeting
     */
    @Test
    public void meetingList_clickAddMeetingButtin_shouldGotoPageAddMeetinh_thenAddMeeting() {
        //Given
        final int itemsBegins = meetingList.size();

        //Click FAB to go to page add meeting
        onView(withId(R.id.fab_add_meeting)).perform(click());

        // TEXT INPUT EDIT TEXT: Writes something
        onView(withId(R.id.add_meeting_txt_topic)).perform(replaceText("new Meeting"),
                closeSoftKeyboard());

        //HOURS
        onView(withId(R.id.add_meeting_b_hour)).perform(click());
        onView(withText("OK")).perform(click());

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
                .check(withItemCount(itemsBegins + 1));
    }


}
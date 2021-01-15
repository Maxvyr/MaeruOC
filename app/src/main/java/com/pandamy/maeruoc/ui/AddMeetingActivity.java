package com.pandamy.maeruoc.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.pandamy.maeruoc.R;
import com.pandamy.maeruoc.controller.CallbackMeeting;
import com.pandamy.maeruoc.controller.CallbackMember;
import com.pandamy.maeruoc.di.DI;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.models.Member;
import com.pandamy.maeruoc.models.Room;
import com.pandamy.maeruoc.service.ApiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity implements CallbackMember {

    private ApiService apiService = DI.getApiService();
    private List<Member> members = apiService.getMembers();
    private List<String> membersEmail;
    private ListMemberRecyclerViewAdapter adapter = new ListMemberRecyclerViewAdapter(members, this);
    private RecyclerView recyclerViewMember;
    private EditText titleMeetingEdit;
    private FloatingActionButton fabAddToList;
    private Spinner spinnerRooms;
    private Button addMeetingHour;
    private int hoursPick, minutesPick;
    private static final String TAG = "AddMeetingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        configRV(adapter);
        addMeetingHour = findViewById(R.id.add_meeting_b_hour);
        titleMeetingEdit = findViewById(R.id.add_meeting_txt_topic);
        fabAddToList = findViewById(R.id.add_meeting_fab);
        spinnerRooms = findViewById(R.id.add_meeting_spinner_room);



        configureRoomSpinner();
        choiceHourMeeting();
        clickAddNewMeeting();


    }


    private void configRV(ListMemberRecyclerViewAdapter adapter){
        recyclerViewMember = findViewById(R.id.member_recycler_view);
        recyclerViewMember.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMember.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewMember.setAdapter(adapter);
    }

    /*
        Callback when user click on add member to meeting
     */
    @Override
    public void onClickCheckBox(int position) {
        Member member = apiService.getMembers().get(position);
        Log.d(TAG, "onClickCheckBox: member selected " + member.getEmail());
        if(membersEmail == null){
            membersEmail = new ArrayList<>();
        }
        membersEmail.add(member.getEmail());
    }

    /*
        Configures the spinner list room
     */
    private void configureRoomSpinner() {
        // Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                apiService.getRoomsName());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Spinner
        spinnerRooms.setAdapter(adapter);
    }

    /*
        Choice hour meeting
     */
    private void choiceHourMeeting(){
        addMeetingHour.setOnClickListener(v -> {
            //TimePickerDialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddMeetingActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            hoursPick = hourOfDay;
                            minutesPick = minute;
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(0,0,0,hoursPick,minutesPick);
                            addMeetingHour.setText(DateFormat.format("HH:mm", calendar));
                        }
                    }, 12,0,true
            );
            timePickerDialog.updateTime(hoursPick,minutesPick);
            timePickerDialog.show();
        });
    }

    /*
            click button add new meeting
         */
    private void clickAddNewMeeting(){
        fabAddToList.setOnClickListener(v -> {
            Meeting meeting = newAddMeeting();
            //add to list
            apiService.addMeeting(meeting);
            //return list Activity
            finish();
        });
    }

    private Meeting newAddMeeting(){
        //title meeting
        String titleSelected = titleMeetingEdit.getText().toString().equals("") ? "NO TITLE" : titleMeetingEdit.getText().toString();
        //date
        String dateSelected = hoursPick + ":" + minutesPick;
        //recover Room selected
        Room roomSelected = apiService.getRooms().get(spinnerRooms.getSelectedItemPosition());
        //Member selected
        String memberSelectedEmail = membersEmail != null ? concatListEmail(membersEmail) : "pas de membres";

        return new Meeting(
                apiService.getMeetings().size() + 1,
                titleSelected,
                dateSelected,
                roomSelected,
                memberSelectedEmail
        );
    }

    private String concatListEmail(List<String> emailList){
        String concatenatedString = "";
        String delimiter = " , ";
        for (String word : emailList) {
            concatenatedString += concatenatedString.equals("") ? word : delimiter + word;
        }
        return concatenatedString;
    }
}
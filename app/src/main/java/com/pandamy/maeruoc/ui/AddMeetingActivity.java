package com.pandamy.maeruoc.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pandamy.maeruoc.R;
import com.pandamy.maeruoc.controller.CallbackMember;
import com.pandamy.maeruoc.di.DI;
import com.pandamy.maeruoc.models.Member;
import com.pandamy.maeruoc.service.ApiService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity implements CallbackMember {

    private Toolbar toolbarAdd;
    private ApiService apiService = DI.getApiService();
    private List<Member> members = apiService.getMembers();
    private ListMemberRecyclerViewAdapter adapter = new ListMemberRecyclerViewAdapter(members, this);
    private RecyclerView recyclerViewMember;
    private FloatingActionButton fabAddToList;
    private static final String TAG = "AddMeetingActivity";
    private Button addMeetingHour;
    private int hoursPick, minutesPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        configRV(adapter);
        addMeetingHour = findViewById(R.id.add_meeting_b_hour);

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

    }
}
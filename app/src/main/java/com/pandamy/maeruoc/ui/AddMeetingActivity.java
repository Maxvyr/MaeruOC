package com.pandamy.maeruoc.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pandamy.maeruoc.R;
import com.pandamy.maeruoc.controller.CallbackMember;
import com.pandamy.maeruoc.di.DI;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.models.Member;
import com.pandamy.maeruoc.models.Room;
import com.pandamy.maeruoc.service.ApiService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity implements CallbackMember {

    private ApiService apiService = DI.getApiService();
    private List<Member> members = apiService.getMembers();
    private List<String> membersEmail;
    private ListMemberRecyclerViewAdapter adapter = new ListMemberRecyclerViewAdapter(members, this);
    private RecyclerView recyclerViewMember;
    private CoordinatorLayout coordinatorLayout;
    private EditText titleMeetingEdit;
    private FloatingActionButton fabAddToList;
    private Spinner spinnerRooms;
    private Button addMeetingHour, addMeetingDate;
    private int hoursPick, minutesPick, dayPick, monthPick, yearPick;
    private final Calendar c = Calendar.getInstance();
    private final int yearNow = c.get(Calendar.YEAR);
    private final int monthNow = c.get(Calendar.MONTH);
    private final int dayNow = c.get(Calendar.DAY_OF_MONTH);
    private boolean isTimePick = false;
    private boolean isDatePick = false;
    private static final String TAG = "AddMeetingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        configRV(adapter);
        coordinatorLayout = findViewById(R.id.add_meeting_coordinator_layout);
        addMeetingHour = findViewById(R.id.add_meeting_b_hour);
        addMeetingDate = findViewById(R.id.add_meeting_b_date);
        titleMeetingEdit = findViewById(R.id.add_meeting_txt_topic);
        fabAddToList = findViewById(R.id.add_meeting_fab);
        spinnerRooms = findViewById(R.id.add_meeting_spinner_room);

        configureRoomSpinner();
        choiceHourMeeting();
        choiceDateMeeting();
        setClickAddNewMeeting();
    }


    private void configRV(ListMemberRecyclerViewAdapter adapter){
        recyclerViewMember = findViewById(R.id.member_recycler_view);
        recyclerViewMember.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMember.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewMember.setAdapter(adapter);
    }

    /*
     * Configures the spinner list room
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
     * Configures Snackbar
     */
    private void showSnackbar(String text){
        Snackbar snackbar = Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /*
     * Callback when user click on add member to meeting
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
     * Choice hour meeting
     */
    private void choiceHourMeeting(){
        addMeetingHour.setOnClickListener(v -> {
            //TimePickerDialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    AddMeetingActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            //check if time selected
                            isTimePick = true;
                            hoursPick = hourOfDay;
                            minutesPick = minute;
                            c.set(0,0,0,hoursPick,minutesPick);
                            addMeetingHour.setText(DateFormat.format("HH:mm", c));
                        }
                    }, 12,0,true
            );
            timePickerDialog.updateTime(hoursPick,minutesPick);
            timePickerDialog.show();
        });
    }

    /*
     * Choice date meeting
     */
    private void choiceDateMeeting(){
        addMeetingDate.setOnClickListener(v -> {
            //DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddMeetingActivity.this,
                    (DatePickerDialog.OnDateSetListener) (view, year, month, dayOfMonth) -> {
                        //check if date is selected
                        isDatePick = true;
                        yearPick = year;
                        monthPick = month;
                        dayPick = dayOfMonth;
                        c.set(yearPick,monthPick,dayPick);
                        addMeetingDate.setText(DateFormat.format("dd-MM-yyyy",c));
                    },yearNow,monthNow,dayNow
                    );
            datePickerDialog.updateDate(yearNow,monthNow,dayNow);
            datePickerDialog.show();
        });
    }

    /*
     * Click button add new meeting
     */
    private void setClickAddNewMeeting(){
        fabAddToList.setOnClickListener(v -> {
            verifDataBeforeAdd();
        });
    }

    /*
     * Verif Data before add meeting
     */
    private void verifDataBeforeAdd(){
        //to avoid user create meeting with only spacebar
        String title = titleMeetingEdit.getText().toString().trim();

        if(title.equals("")){
            showSnackbar("Il manque le titre");
        } else if (!isTimePick){
            showSnackbar("Il manque l'heure");
        } else if (!isDatePick){
            showSnackbar("Il manque la date");
        } else if (membersEmail == null){
            showSnackbar("Aucun membre sélectionner");
        } else {
            Meeting meeting = createNewMeeting();
            addMeetingToListAndReturn(meeting);
        }
    }

    /*
     * Create a new meeting
     */
    private Meeting createNewMeeting(){
        //id
        int id = apiService.getMeetings().size() + 1;
        //title meeting
        String titleSelected = titleMeetingEdit.getText().toString();
        //date
        c.set(yearPick,monthPick,dayPick,hoursPick,minutesPick);
        String dateSelected = DateFormat.format("dd-MM-yyyy hh:mm",c).toString();
        //recover Room selected
        Room roomSelected = apiService.getRooms().get(spinnerRooms.getSelectedItemPosition());
        //Member selected
        String memberSelectedEmail = concatListEmail(membersEmail);

        return apiService.createMeeting(id,titleSelected,dateSelected,roomSelected,memberSelectedEmail);
    }

    private String concatListEmail(List<String> emailList){
        String concatenatedString = "";
        String delimiter = " , ";
        for (String word : emailList) {
            concatenatedString += concatenatedString.equals("") ? word : delimiter + word;
        }
        return concatenatedString;
    }

    private void addMeetingToListAndReturn(Meeting meeting){
        //add to list
        apiService.addMeeting(meeting);
        //return list Activity
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
package com.pandamy.maeruoc.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pandamy.maeruoc.R;
import com.pandamy.maeruoc.controller.CallbackMeeting;
import com.pandamy.maeruoc.di.DI;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.service.ApiService;

import java.util.List;

enum FilterChoose{
    TITLE,DATE,ROOM
}

public class ListActivity extends AppCompatActivity implements CallbackMeeting {

    //Variable
    private ApiService apiService = DI.getApiService();
    private List<Meeting> meetings = apiService.getMeetings();
    private ListMeetingRecyclerViewAdapter adapter;
    private RecyclerView recyclerViewMeeting;
    private FloatingActionButton fabAddMeeting;
    public static FilterChoose filterChoose;
    private static final String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ListMeetingRecyclerViewAdapter(meetings, this);
        configRV(adapter);
        fabAddMeeting = findViewById(R.id.fab_add_meeting);
        //Open Add Meeting Activity
        fabAddMeeting.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddMeetingActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        searchView(menu, R.id.filter_title);
        searchView(menu, R.id.filter_date);
        searchView(menu,R.id.filter_room);
        return true;
    }

    /**
     * Show search view selected by user
     * @param menu
     * @param id
     */
    private void searchView(Menu menu, int id){
        MenuItem searchItemName = menu.findItem(id);
        SearchView searchView = (SearchView) searchItemName.getActionView();
        setFilterChoose(id);
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        Log.d(TAG, "onQueryTextChange: filter choose " + filterChoose.toString());
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                }
        );
    }

    private void configRV(ListMeetingRecyclerViewAdapter adapter){
        recyclerViewMeeting = findViewById(R.id.list_meetings);
        recyclerViewMeeting.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMeeting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewMeeting.setAdapter(adapter);
    }

    /**
     * Filter selection depending on click menu
     * @param id
     */
    private void setFilterChoose(int id){
        if(id == R.id.filter_title){
            filterChoose = FilterChoose.TITLE;
        } else if (id == R.id.filter_date){
            filterChoose = FilterChoose.DATE;
        } else if (id == R.id.filter_room) {
            filterChoose = FilterChoose.ROOM;
        }
    }

    /*
     * Callback when user click on delete button
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        Log.d(TAG, "deleteMeeting: ");
        apiService.deleteMeeting(meeting);
        adapter.notifyDataSetChanged();
    }
}
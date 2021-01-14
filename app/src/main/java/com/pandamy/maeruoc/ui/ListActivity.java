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
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pandamy.maeruoc.R;
import com.pandamy.maeruoc.controller.CallbackMeeting;
import com.pandamy.maeruoc.di.DI;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.service.ApiService;

import java.util.List;

public class ListActivity extends AppCompatActivity implements CallbackMeeting {

    //Variable
    private ApiService apiService = DI.getApiService();
    private List<Meeting> meetings = apiService.getMeetings();
    private ListMeetingRecyclerViewAdapter adapter = new ListMeetingRecyclerViewAdapter(meetings, this);
    private RecyclerView recyclerViewMeeting;
    private FloatingActionButton fabAddMeeting;
    private static final String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configRV(adapter);
        View view;
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

        MenuItem searchItem = menu.findItem(R.id.search_filter);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                                              @Override
                                              public boolean onQueryTextSubmit(String query) {
                                                  return false;
                                              }

                                              @Override
                                              public boolean onQueryTextChange(String newText) {
                                                  adapter.getFilter().filter(newText);
                                                  return false;
                                              }
                                          }

        );
        return true;
    }



    private void filterByRoomButton(){
    }

    private void filterByDateButton(){
    }



    public void displayMainMeetingsList(){
    }

    private void configRV(ListMeetingRecyclerViewAdapter adapter){
        recyclerViewMeeting = findViewById(R.id.list_meetings);
        recyclerViewMeeting.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMeeting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewMeeting.setAdapter(adapter);
    }

    /*
                Callback when user clikc on delete button
            */
    @Override
    public void deleteMeeting(Meeting meeting) {
        Log.d(TAG, "deleteMeeting: ");
        meetings.remove(meeting);
        adapter.notifyDataSetChanged();
        //replace notifyDataSetChanged with notifyItemRemove
    }
}
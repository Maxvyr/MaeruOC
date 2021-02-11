package com.pandamy.maeruoc.ui;

import androidx.annotation.NonNull;
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
import com.pandamy.maeruoc.models.FilterChoose;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.service.ApiService;

import java.util.List;

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter_title:
                searchView(item);
            case R.id.filter_date:
                searchView(item);
            case R.id.filter_room:
                searchView(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Show search view selected by user
     * @param item => MenuItem choose
     */
    private void searchView(MenuItem item){
//        MenuItem searchItemName = menu.findItem(id);

        SearchView searchView = (SearchView) item.getActionView();
        setFilterChoose(item.getItemId());
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

    private void configRV(ListMeetingRecyclerViewAdapter adapter){
        recyclerViewMeeting = findViewById(R.id.list_meetings);
        recyclerViewMeeting.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMeeting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewMeeting.setAdapter(adapter);
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
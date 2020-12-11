package com.pandamy.maeruoc.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.pandamy.maeruoc.R;
import com.pandamy.maeruoc.di.DI;
import com.pandamy.maeruoc.models.Meeting;
import com.pandamy.maeruoc.service.ApiService;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    //Variable
    private ApiService apiService = DI.getApiService();
    private List<Meeting> meetings = apiService.getMeetings();
    private ListMeetingRecyclerViewAdapter adapter = new ListMeetingRecyclerViewAdapter(meetings);
    private RecyclerView recyclerViewMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configRV(adapter);
    }

    private void configRV(ListMeetingRecyclerViewAdapter adapter){
        recyclerViewMeeting = findViewById(R.id.list_meetings);
        recyclerViewMeeting.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMeeting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewMeeting.setAdapter(adapter);
    }
}
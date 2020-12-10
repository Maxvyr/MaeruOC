package com.pandamy.maeruoc.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.pandamy.maeruoc.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancie le fragment
        Fragment listMeetingFragment = new ListMeetingFragment();

        //affiche le fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_main, listMeetingFragment);

    }
}
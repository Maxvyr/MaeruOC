package com.pandamy.maeruoc.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.pandamy.maeruoc.R;

public class AddMeetingActivity extends AppCompatActivity {

    private Toolbar toolbarAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

    }

    /*
     * Used to navigate to this activity
     * @param activity
     */

    public static void navigateTo(FragmentActivity activity){
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity,intent,null);
    }
}
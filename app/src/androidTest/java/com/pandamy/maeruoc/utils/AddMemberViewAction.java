package com.pandamy.maeruoc.utils;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.pandamy.maeruoc.R;

import org.hamcrest.Matcher;

public class AddMemberViewAction implements ViewAction {

    private final int id;


    public AddMemberViewAction(int id){
        this.id = id;
    }

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(id);
        // Maybe check for null
        button.performClick();
    }
}
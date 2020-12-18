package com.pandamy.maeruoc.controller;

import com.pandamy.maeruoc.models.Meeting;

public interface CallbackMeeting {
    void deleteMeeting(Meeting meeting);

    void addMeeting(Meeting meeting);
}

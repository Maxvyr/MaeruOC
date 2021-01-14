package com.pandamy.maeruoc.controller;

import com.pandamy.maeruoc.models.Member;

import java.util.List;

public interface CallbackMember {
    /**
     * Retrieves the position of the list and if is checked
     * @param position an integer that contains the position value
     */
    void onClickCheckBox(int position);
}

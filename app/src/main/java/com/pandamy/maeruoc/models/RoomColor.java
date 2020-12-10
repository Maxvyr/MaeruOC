package com.pandamy.maeruoc.models;

import android.graphics.Color;

/*
    Enum Color of each Room
 */
public enum RoomColor {

    COLOR_BLUE(0xFF0366FF), COLOR_BROWN(0xFF82190A), COLOR_GREEN(0xFF1AFF03), COLOR_RED(0xFFFF0303), COLOR_YELLOW(0xFFF7FF03);


    private final int color;

    RoomColor(int color) {
        this.color = color;
    }

    public int getIntColor() {
        return color;
    }
}

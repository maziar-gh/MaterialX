package com.material.components.model;

import android.graphics.drawable.Drawable;

public class Social {

    public int image;
    public Drawable imageDrw;
    public String name;
    public boolean expanded = false;
    public boolean parent = false;

    // flag when item swiped
    public boolean swiped = false;

    public Social() {
    }

    public Social(int image, String name) {
        this.image = image;
        this.name = name;
    }
}

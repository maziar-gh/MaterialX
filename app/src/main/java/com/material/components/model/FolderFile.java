package com.material.components.model;

import android.graphics.drawable.Drawable;

public class FolderFile {

    public int image;
    public Drawable imageDrw;
    public String name;
    public String date;
    public boolean section = false;
    public boolean folder = true;

    public FolderFile() {
    }

    public FolderFile(String name, String date, int image, boolean folder) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.folder = folder;
    }

    public FolderFile(String name, boolean section) {
        this.name = name;
        this.section = section;
    }

}

package com.material.components.model;

public class SectionImage {
    public int image;
    public String title;
    public boolean section = false;

    public SectionImage() {
    }

    public SectionImage(int image, String title, boolean section) {
        this.image = image;
        this.title = title;
        this.section = section;
    }
}

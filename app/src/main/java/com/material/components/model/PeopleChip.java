package com.material.components.model;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.pchmn.materialchips.model.ChipInterface;

public class PeopleChip implements ChipInterface {

    private String id;
    private Drawable image;
    private String name;
    private String info;

    public PeopleChip(String id, Drawable image, String name, String info) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.info = info;
    }

    @Override
    public Object getId() {
        return id;
    }

    @Override
    public Uri getAvatarUri() {
        return null;
    }

    @Override
    public Drawable getAvatarDrawable() {
        return image;
    }

    @Override
    public String getLabel() {
        return name;
    }

    @Override
    public String getInfo() {
        return info;
    }
}
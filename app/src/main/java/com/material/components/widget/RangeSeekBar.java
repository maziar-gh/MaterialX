package com.material.components.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

public class RangeSeekBar extends CrystalRangeSeekbar {

    private final int THUMB_SIZE = 40;

    public RangeSeekBar(Context context) {
        super(context);
    }

    public RangeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RangeSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected float getBarHeight() {
        return 8;
    }

    @Override
    protected float getThumbWidth() {
        return THUMB_SIZE;
    }

    @Override
    protected float getThumbHeight() {
        return THUMB_SIZE;
    }

}

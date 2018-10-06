package com.material.components.widget;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Use this view with width = height or width > height
 * EXAMPLE :
 * android:layout_width="50dp"
 * android:layout_height="30dp"
 *
 * To change dot color you can use :
 * android:background="@color/exampleColor"
 */

public class ViewLoadingDotsBounce extends LinearLayout {

    private Context context;
    private ImageView[] img;
    private GradientDrawable circle = new GradientDrawable();
    private static final int OBJECT_SIZE = 3;
    private static final int POST_DIV = 6;
    private static final int DURATION = 500;
    private ObjectAnimator animator[];

    public ViewLoadingDotsBounce(Context context) {
        super(context);
    }

    public ViewLoadingDotsBounce(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);

        initView();
    }

    public ViewLoadingDotsBounce(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        int color = Color.GRAY;
        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            color = ((ColorDrawable) background).getColor();
        }
        setBackgroundColor(Color.TRANSPARENT);

        removeAllViews();
        img = new ImageView[OBJECT_SIZE];
        circle.setShape(GradientDrawable.OVAL);
        circle.setColor(color);
        circle.setSize(200, 200);

        LayoutParams layoutParams2 = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams2.weight = 1;

        LinearLayout[] rel = new LinearLayout[OBJECT_SIZE];
        for (int i = 0; i < OBJECT_SIZE; i++) {
            rel[i] = new LinearLayout(context);
            rel[i].setGravity(Gravity.CENTER);
            rel[i].setLayoutParams(layoutParams2);
            img[i] = new ImageView(context);
            img[i].setBackgroundDrawable(circle);
            rel[i].addView(img[i]);
            addView(rel[i]);
        }
    }

    boolean onLayoutReach = false;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!onLayoutReach) {
            onLayoutReach = true;
            LayoutParams lp = new LayoutParams(getWidth() / 5, getWidth() / 5);
            for (int i = 0; i < OBJECT_SIZE; i++) {
                img[i].setLayoutParams(lp);
            }
            animateView();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        for (int i = 0; i < OBJECT_SIZE; i++) {
            if (animator[i].isRunning()) {
                animator[i].removeAllListeners();
                animator[i].end();
                animator[i].cancel();
            }
        }
    }

    private void animateView() {
        animator = new ObjectAnimator[OBJECT_SIZE];
        for (int i = 0; i < OBJECT_SIZE; i++) {
            img[i].setTranslationY(getHeight() / POST_DIV);
            PropertyValuesHolder Y = PropertyValuesHolder.ofFloat(img[i].TRANSLATION_Y, -getHeight() / POST_DIV);
            PropertyValuesHolder X = PropertyValuesHolder.ofFloat(img[i].TRANSLATION_X, 0);
            animator[i] = ObjectAnimator.ofPropertyValuesHolder(img[i], X, Y);
            animator[i].setRepeatCount(-1);
            animator[i].setRepeatMode(ValueAnimator.REVERSE);
            animator[i].setDuration(DURATION);
            animator[i].setStartDelay((DURATION / 3) * i);
            animator[i].start();
        }

    }


}

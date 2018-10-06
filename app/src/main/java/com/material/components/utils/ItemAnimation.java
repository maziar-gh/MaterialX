package com.material.components.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ItemAnimation {

    /* animation type */
    public static final int BOTTOM_UP = 1;
    public static final int FADE_IN = 2;
    public static final int LEFT_RIGHT = 3;
    public static final int RIGHT_LEFT = 4;
    public static final int NONE = 0;

    /* animation duration */
    private static final long DURATION_IN_BOTTOM_UP = 150;
    private static final long DURATION_IN_FADE_ID = 500;
    private static final long DURATION_IN_LEFT_RIGHT = 150;
    private static final long DURATION_IN_RIGHT_LEFT = 150;

    public static void animate(View view, int position, int type) {
        switch (type) {
            case BOTTOM_UP:
                animateBottomUp(view, position);
                break;

            case FADE_IN:
                animateFadeIn(view, position);
                break;

            case LEFT_RIGHT:
                animateLeftRight(view, position);
                break;

            case RIGHT_LEFT:
                animateRightLeft(view, position);
                break;
        }
    }

    private static void animateBottomUp(View view, int position) {
        boolean not_first_item = position == -1;
        position = position + 1;
        view.setTranslationY(not_first_item ? 800 : 500);
        view.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", not_first_item ? 800 : 500, 0);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 1.f);
        animatorTranslateY.setStartDelay(not_first_item ? 0 : (position * DURATION_IN_BOTTOM_UP));
        animatorTranslateY.setDuration((not_first_item ? 3 : 1) * DURATION_IN_BOTTOM_UP);
        animatorSet.playTogether(animatorTranslateY, animatorAlpha);
        animatorSet.start();
    }

    private static void animateFadeIn(View view, int position) {
        boolean not_first_item = position == -1;
        position = position + 1;
        view.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 0.f, 0.5f, 1.f);
        ObjectAnimator.ofFloat(view, "alpha", 0.f).start();
        animatorAlpha.setStartDelay(not_first_item ? DURATION_IN_FADE_ID / 2 : (position * DURATION_IN_FADE_ID / 3));
        animatorAlpha.setDuration(DURATION_IN_FADE_ID);
        animatorSet.play(animatorAlpha);
        animatorSet.start();
    }

    private static void animateLeftRight(View view, int position) {
        boolean not_first_item = position == -1;
        position = position + 1;
        view.setTranslationX(-400f);
        view.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(view, "translationX", -400f, 0);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 1.f);
        ObjectAnimator.ofFloat(view, "alpha", 0.f).start();
        animatorTranslateY.setStartDelay(not_first_item ? DURATION_IN_LEFT_RIGHT : (position * DURATION_IN_LEFT_RIGHT));
        animatorTranslateY.setDuration((not_first_item ? 2 : 1) * DURATION_IN_LEFT_RIGHT);
        animatorSet.playTogether(animatorTranslateY, animatorAlpha);
        animatorSet.start();
    }

    private static void animateRightLeft(View view, int position) {
        boolean not_first_item = position == -1;
        position = position + 1;
        view.setTranslationX(view.getX() + 400);
        view.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(view, "translationX", view.getX() + 400, 0);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 1.f);
        ObjectAnimator.ofFloat(view, "alpha", 0.f).start();
        animatorTranslateY.setStartDelay(not_first_item ? DURATION_IN_RIGHT_LEFT : (position * DURATION_IN_RIGHT_LEFT));
        animatorTranslateY.setDuration((not_first_item ? 2 : 1) * DURATION_IN_RIGHT_LEFT);
        animatorSet.playTogether(animatorTranslateY, animatorAlpha);
        animatorSet.start();
    }

}

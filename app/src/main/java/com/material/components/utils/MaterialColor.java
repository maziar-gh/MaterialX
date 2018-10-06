package com.material.components.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import java.util.Random;

public class MaterialColor {

    private static Random r = new Random();

    public static int randInt(int max) {
        return r.nextInt(max);
    }

    public static int getColor(Context ctx) {
        int returnColor = Color.WHITE;
        int arrayId = ctx.getResources().getIdentifier("mdcolor_random", "array", ctx.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = ctx.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

    public static int getColor(Context ctx, String str, int index) {
        int returnColor = Color.WHITE;
        int arrayId = ctx.getResources().getIdentifier("mdcolor_random", "array", ctx.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = ctx.getResources().obtainTypedArray(arrayId);
            int idx = index;
            while (idx >= colors.length()) {
                idx = idx - 5;
            }
            while (idx < 0) {
                idx = idx + 2;
            }
            returnColor = colors.getColor(idx, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }

}

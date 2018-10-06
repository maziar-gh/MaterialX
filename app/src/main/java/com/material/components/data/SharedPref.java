package com.material.components.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private Context context;
    private SharedPreferences sharedPreferences;

    private static final String FIRST_LAUNCH = "_.FIRST_LAUNCH";
    private static final String CLICK_OFFER = "_.MAX_CLICK_OFFER";

    private static final int MAX_CLICK_OFFER = 10;

    public SharedPref(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MAIN_PREF", Context.MODE_PRIVATE);
    }

    public void setFirstLaunch(boolean flag) {
        sharedPreferences.edit().putBoolean(FIRST_LAUNCH, flag).apply();
    }

    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }

    public boolean actionClickOffer() {
        int current = sharedPreferences.getInt(CLICK_OFFER, 1);
        boolean is_reset = false;
        if (current < MAX_CLICK_OFFER) {
            current++;
        } else {
            current = 1;
            is_reset = true;
        }
        sharedPreferences.edit().putInt(CLICK_OFFER, current).apply();
        return is_reset;
    }

}

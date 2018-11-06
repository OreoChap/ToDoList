package com.example.oreooo.todoforstudy;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.prefs.Preferences;

public class SharedPreference {

    private static final String PREF_PLAN_TIME = "addPlanTime";
    private static final String PREF_PLAN_DESCRIPTION = "addPlanTime";

    public static String getPrefPlanTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_PLAN_TIME, null);
    }

    public static void setPrefPlanTime(Context context, String time) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_PLAN_TIME, time)
                .apply();
    }

    public static String getPrefPlanDescription(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_PLAN_DESCRIPTION, null);
    }

    public static void setPrefPlanDescription(Context context, String descriptino) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_PLAN_DESCRIPTION, descriptino)
                .apply();
    }

}

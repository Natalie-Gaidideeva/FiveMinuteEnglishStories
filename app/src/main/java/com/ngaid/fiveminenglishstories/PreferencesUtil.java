package com.ngaid.fiveminenglishstories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesUtil {

    public static void setPrefSize(final Context context, final Float s) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().putFloat(context.getString(R.string.font_size), s).commit();
    }

    public static float getPrefSize(final Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getFloat(context.getString(R.string.font_size), 18);
    }

    public static void setPrefFont(final Context context, final String f) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().putString(context.getString(R.string.font_name), f).commit();
    }

    public static String getPrefFont(final Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(context.getString(R.string.font_name), "open_sans.ttf");
    }

    public static void setPrefColour(final Context context, final boolean mode) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().putBoolean(context.getString(R.string.booleanSwitch), mode).commit();
    }

    public static boolean getPrefColour(final Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getBoolean(context.getString(R.string.booleanSwitch), false);
    }

    public static void setPrefColCoordinator(final Context context, final String cc) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().putString(context.getString(R.string.coordinatorColour), cc).commit();
    }

    public static String getPrefColCoordinator(final Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(context.getString(R.string.coordinatorColour), "#FCFCE9");
    }

    public static void setPrefColText(final Context context, final String ct) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPref.edit().putString(context.getString(R.string.textColour), ct).commit();
    }

    public static String getPrefColText(final Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getString(context.getString(R.string.textColour), "#000000");
    }
}

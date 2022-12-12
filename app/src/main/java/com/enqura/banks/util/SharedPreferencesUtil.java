package com.enqura.banks.util;

import static android.content.Context.MODE_PRIVATE;

import static com.enqura.banks.constants.Generic.FIRST_OPEN;
import static com.enqura.banks.constants.Generic.SPLASH;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SharedPreferencesUtil {
    private static SharedPreferences sharedPref;


    private static SharedPreferences getPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }


    public static boolean isFirstOpen(Context context) {
        sharedPref = context.getSharedPreferences(SPLASH, MODE_PRIVATE);
        return sharedPref.getBoolean(FIRST_OPEN, true);
    }

    //Save SharedPreferences
    public static void saveFirstOpen(Context context, boolean is_Save) {
        try {
            sharedPref = context.getSharedPreferences(
                    SPLASH,
                    MODE_PRIVATE
            );
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(FIRST_OPEN, is_Save);
            editor.apply();
        } catch (Exception exception) {
            Log.e("Error", exception.toString());
        }
    }


}

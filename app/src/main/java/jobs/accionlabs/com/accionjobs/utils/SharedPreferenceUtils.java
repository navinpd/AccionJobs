package jobs.accionlabs.com.accionjobs.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtils {
    private static final String PREF_NAME = "SacombankPrefs";

    public static final String EMAIL_ID = "EMAIL_ID";

    //Persistent memory for login
    public static final String USER_MOBILE_NUMBER = "USER_MOBILE_NUMBER";
    public static final String USER_NAME = "USER_NAME";
    public static final String EMP_ID = "EMP_ID";

    public static String getStringPreference(Context activity, String key) {
        if (activity == null)
            return "";

        SharedPreferences sp = activity.getSharedPreferences(SharedPreferenceUtils.PREF_NAME, 0);
        return sp.getString(key, "");
    }

    public static void setStringPreference(Context activity, String key, String value) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferenceUtils.PREF_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void removeStringPreference(Context activity, String key) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferenceUtils.PREF_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public static long getLongPreference(Context activity, String key) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferenceUtils.PREF_NAME, 0);
        return sp.getLong(key, 0);
    }

    public static void setLongPreference(Context activity, String key, long value) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferenceUtils.PREF_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static boolean getBooleanPreference(Context activity, String key) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferenceUtils.PREF_NAME, 0);
        return sp.getBoolean(key, false);
    }

    public static void setBooleanPreference(Context activity, String key, boolean value) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferenceUtils.PREF_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static int getIntegerPreference(Context activity, String key) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferenceUtils.PREF_NAME, 0);
        return sp.getInt(key, 0);
    }

    public static void setIntegerPreference(Context activity, String key, int value) {
        SharedPreferences sp = activity.getSharedPreferences(SharedPreferenceUtils.PREF_NAME, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }
}

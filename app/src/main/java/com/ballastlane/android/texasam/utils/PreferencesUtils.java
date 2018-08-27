package com.ballastlane.android.texasam.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * @author Daniela Perez danielaperez@kogimobile.com on 11/21/17.
 */

public class PreferencesUtils {

    private static final String USER_INVITED_STATUS = "USER_INVITED";
    private static final String USER_LOGGED = "USER_LOGGED";

    public static final int INVITED_NOT_REGISTERED = 1;
    public static final int INVITED_REGISTERED = 2;
    private static final String PREF_NAME = "PREFERENCES";

    // Fields
    private static PreferencesUtils instance;
    private SharedPreferences preferences;

    public static PreferencesUtils getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new PreferencesUtils(context);
    }

    private PreferencesUtils(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor edit() {
        return preferences.edit();
    }

    //---------------- Getters -----------------//

    public  int getUserInvitedStatus() {
        return preferences.getInt(USER_INVITED_STATUS, 0);
    }

    public  boolean isUserLogged() {
        return preferences.getBoolean(USER_LOGGED, false);
    }

    //---------------- Setters -----------------//

    public  void setUserInvitedStatus(int invited) {
        SharedPreferences.Editor preferencesEditor = edit();
        preferencesEditor.putInt(USER_INVITED_STATUS, invited);
        preferencesEditor.commit();
    }

    public  void setUserLogged(boolean logged) {
        SharedPreferences.Editor preferencesEditor = edit();
        preferencesEditor.putBoolean(USER_LOGGED, logged);
        preferencesEditor.commit();
    }

    public static String getUserToken() {
        return null;
    }
}

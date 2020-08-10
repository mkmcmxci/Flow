package com.mkmcmxci.flow.ListContainer;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    public void saveLoginSession(Context context, String username, String password, int userID)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userID", userID);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("Key", String.valueOf(userID) + "xyz" + username + "xyz" + password);
        editor.apply();



    }


    public String loadLoginSession(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Shared Pref", Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString("userID", null);
        String username = sharedPreferences.getString("username", null);
        String password = sharedPreferences.getString("password", null);
        String key = sharedPreferences.getString("key", null);


        return key;



    }




}

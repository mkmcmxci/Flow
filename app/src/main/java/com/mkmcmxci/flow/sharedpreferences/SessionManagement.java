package com.mkmcmxci.flow.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.mkmcmxci.flow.entities.User;

public class SessionManagement {

    Context context;

    public SessionManagement(Context context) {
        this.context = context;

    }

    public void saveSession(User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Token", user.getId());
        editor.putString("Username", user.getName());
        editor.putString("Mail", user.getMail());
        editor.putString("Password", user.getPassword());
        editor.putInt("UserID", user.getId());
        editor.commit();

    }

    public int loadSession() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);

        return sharedPreferences.getInt("Token", -1);
    }

    public int loadUserID() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);


        return sharedPreferences.getInt("UserID", 0);
    }

    public String loadUsername() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);


        return sharedPreferences.getString("Username", "notFound");
    }

    public String loadMail() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);


        return sharedPreferences.getString("Mail", "notFound");
    }

    public String loadPassword() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);


        return sharedPreferences.getString("Password", "notFound");
    }

    public int removeSession() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove("Token").commit();

        return sharedPreferences.getInt("Token", -1);
    }

}

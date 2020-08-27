package com.mkmcmxci.flow.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.mkmcmxci.flow.entities.User;

public class SessionManagement {

    private static Context context;

    public SessionManagement(Context context) {
        this.context = context;

    }

    public static void saveSession(User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Token", user.getId());
        editor.putString("Username", user.getName());
        editor.putString("Mail", user.getMail());
        editor.putString("Password", user.getPassword());
        editor.putInt("UserID", user.getId());
        editor.commit();

    }

    public static void savePassword(String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Password", password);
        editor.commit();


    }

    public static void saveUsername(String username){
        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", username);
        editor.commit();


    }

    public static void saveMail(String mail){
        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Mail", mail);
        editor.commit();


    }

    public static int loadSession() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);

        return sharedPreferences.getInt("Token", -1);
    }

    public static int loadUserID() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);


        return sharedPreferences.getInt("UserID", 0);
    }

    public static String loadUsername() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);


        return sharedPreferences.getString("Username", "notFound");
    }

    public static String loadMail() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);


        return sharedPreferences.getString("Mail", "notFound");
    }

    public static String loadPassword() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);


        return sharedPreferences.getString("Password", "notFound");
    }

    public static int removeSession() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("session", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove("Token").commit();

        return sharedPreferences.getInt("Token", -1);
    }

}

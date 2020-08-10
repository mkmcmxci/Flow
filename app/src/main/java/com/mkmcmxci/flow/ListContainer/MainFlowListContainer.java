package com.mkmcmxci.flow.ListContainer;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mkmcmxci.flow.entities.Question;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainFlowListContainer {




    public void saveMainList(Context context, List<Question> list) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Shared Pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("Shared Main List", json);
        editor.apply();
    }

    public ArrayList<Question> loadMainList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Shared Pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Shared Main List", null);
        Type type = new TypeToken<ArrayList<Question>>() {}.getType();
        ArrayList<Question> loadList = gson.fromJson(json, type);

        return loadList;

    }
}

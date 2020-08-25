package com.mkmcmxci.flow.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.activity.MainActivity;
import com.mkmcmxci.flow.entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserSignInTask extends AsyncTask<String, Void, String> {

    List<User> users = new ArrayList<>();
    Context context;

    public UserSignInTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        String urlString = strings[0];

        StringBuilder buffer = new StringBuilder();

        try {
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";

            while ((line = bf.readLine()) != null) {

                buffer.append(line);

            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return buffer.toString();
    }

    @Override
    protected void onPostExecute(String s) {

        try {
            JSONObject jObject = new JSONObject(s);

            JSONArray jArray = jObject.getJSONArray("Users");

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject obj = (JSONObject) jArray.get(i);

                users.add(new User(obj.getInt("UserID"),
                        obj.getString("Username"),
                        obj.getString("Mail"),
                        obj.getString("Password"), obj.getInt("UserAnswerSize"), obj.getInt("UserQuestionSize")));
            }


        } catch (JSONException e) {

            e.printStackTrace();

        }

        if (users.size() != 0) {

            Intent i = new Intent(context, MainActivity.class);
            i.putExtra("UserID", users.get(0).getId());
            i.putExtra("Username", users.get(0).getName());
            i.putExtra("Mail", users.get(0).getMail());
            i.putExtra("Password", users.get(0).getPassword());

            SessionManagement sm = new SessionManagement(context);

            sm.saveSession(new User(users.get(0).getId(),
                    users.get(0).getName(),
                    users.get(0).getMail(),
                    users.get(0).getPassword(),
                    users.get(0).getNumberOfAnswers(),
                    users.get(0).getNumberOfQuestions()));




            context.startActivity(i);

        } else {

            Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        }

    }
}

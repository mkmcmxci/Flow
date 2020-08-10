package com.mkmcmxci.flow.tasks;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

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

    ProgressDialog dialog;
    List<User> users = new ArrayList<>();
    Context context;

    public UserSignInTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        dialog = new ProgressDialog(context);
        dialog.setTitle("Please Wait");
        dialog.setMessage("Loading..");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

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

                users.add(new User(obj.getInt("user_id"),
                        obj.getString("name"),
                        obj.getString("mail"),
                        obj.getString("password")));
            }


        } catch (JSONException e) {

            e.printStackTrace();

        }


        dialog.dismiss();

        if (users.size() != 0) {

            Intent i = new Intent(context, MainActivity.class);

            i.putExtra("user_id", users.get(0).getId());
            i.putExtra("name", users.get(0).getName());
            i.putExtra("mail", users.get(0).getMail());
            i.putExtra("password", users.get(0).getPassword());

            context.startActivity(i);

        } else {

            Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        }

    }
}

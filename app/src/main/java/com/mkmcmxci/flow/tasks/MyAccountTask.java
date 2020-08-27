package com.mkmcmxci.flow.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

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

public class MyAccountTask extends AsyncTask<String, Void, String> {

    Context mContext;
    ArrayList<User> mList;
    TextView mQuestionCount, mAnswerCount, mUsername;
    String name, mail;


    public MyAccountTask(Context context, ArrayList<User> list, TextView questionCount, TextView answerCount, TextView username, String name, String mail) {
        this.mList = list;
        this.mContext = context;
        this.name = name;
        this.mail = mail;
        this.mQuestionCount = questionCount;
        this.mAnswerCount = answerCount;
        this.mUsername = username;
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

                mList.add(new User(obj.getInt("UserID"),
                        obj.getString("Username"),
                        obj.getString("Mail"),
                        obj.getString("Password"), obj.getInt("UserAnswerSize"), obj.getInt("UserQuestionSize")));
            }


        } catch (JSONException e) {

            e.printStackTrace();

        }

        name = String.valueOf(mList.get(0).getName());

        mail = String.valueOf(mList.get(0).getMail());

        mQuestionCount.setText(String.valueOf(mList.get(0).getNumberOfQuestions()));

        mAnswerCount.setText(String.valueOf(mList.get(0).getNumberOfAnswers()));

        mUsername.setText(name);



    }
}

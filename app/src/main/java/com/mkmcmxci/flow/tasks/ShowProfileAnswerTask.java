package com.mkmcmxci.flow.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.mkmcmxci.flow.ui.showprofile.ShowProfileAnswerFragmentAdapter;
import com.mkmcmxci.flow.entities.Answer;

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

public class ShowProfileAnswerTask extends AsyncTask<String, Void, String> {

    ShowProfileAnswerFragmentAdapter showProfileAnswerAdapter;
    ArrayList<Answer> showProfileAnswerAnswerList;
    Context context;

    public ShowProfileAnswerTask(Context context, ShowProfileAnswerFragmentAdapter showProfileAnswerAdapter, ArrayList<Answer> showProfileAnswerAnswerList) {
        this.showProfileAnswerAdapter = showProfileAnswerAdapter;
        this.showProfileAnswerAnswerList = showProfileAnswerAnswerList;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

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

            JSONArray jArray = jObject.getJSONArray("Answers");

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject obj = (JSONObject) jArray.get(i);

                showProfileAnswerAnswerList.add(new Answer(obj.getString("Username"),
                        obj.getString("AnswerContent"),
                        obj.getString("QuestionTitle"),
                        obj.getInt("AnswerCount"),
                        obj.getInt("AnswerUserID"),
                        obj.getInt("UserQuestionSize"),
                        obj.getInt("UserAnswerSize"),
                        obj.getInt("QuestionID"),
                        obj.getInt("QuestionUserID"),
                        obj.getInt("AnswerID"),
                        obj.getString("QuestionContent"),
                        obj.getString("QuestionUsername")));
            }


            showProfileAnswerAdapter.notifyDataSetChanged();


        } catch (JSONException e) {

            e.printStackTrace();
        }


    }

}


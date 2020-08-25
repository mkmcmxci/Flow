package com.mkmcmxci.flow.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.mkmcmxci.flow.entities.Question;
import com.mkmcmxci.flow.ui.flow.LastAnsweredAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LastAnsweredTask extends AsyncTask<String, Void, String> {

    LastAnsweredAdapter lastAnsweredAdapter;
    List<Question> lastAnsweredQuestionList;
    Context context;

    public LastAnsweredTask(Context context,
                            LastAnsweredAdapter lastAnsweredAdapter, List<Question> lastAnsweredQuestionList) {
        this.lastAnsweredAdapter = lastAnsweredAdapter;
        this.lastAnsweredQuestionList = lastAnsweredQuestionList;
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

            JSONArray jArray = jObject.getJSONArray("Questions");

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject obj = (JSONObject) jArray.get(i);

                lastAnsweredQuestionList.add(new Question(obj.getInt("QuestionID"),
                        obj.getString("QuestionTitle"),
                        obj.getString("QuestionContent"),
                        obj.getString("Username"),
                        obj.getInt("AnswerSize"),
                        obj.getInt("UserID"),
                        obj.getInt("UserQuestionSize"),
                        obj.getInt("UserAnswerSize")));
            }
            lastAnsweredAdapter.notifyDataSetChanged();

        } catch (JSONException e) {

            e.printStackTrace();
        }

    }
}

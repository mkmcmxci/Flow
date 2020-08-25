package com.mkmcmxci.flow.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.mkmcmxci.flow.entities.Answer;
import com.mkmcmxci.flow.ui.flow.AnswerAdapter;

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

public class AnsweredTask extends AsyncTask<String, Void, String> {
    AnswerAdapter answeredAdapter;
    List<Answer> answeredItemList;

    public AnsweredTask(AnswerAdapter answeredAdapter, List<Answer> answeredItemList) {
        this.answeredAdapter = answeredAdapter;
        this.answeredItemList = answeredItemList;
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

                answeredItemList.add(new Answer(obj.getString("Username"),
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
            answeredAdapter.notifyDataSetChanged();

        } catch (JSONException e) {

            e.printStackTrace();
        }

        Log.i("Dev",answeredItemList.toString());

    }

}

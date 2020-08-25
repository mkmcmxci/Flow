package com.mkmcmxci.flow.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.mkmcmxci.flow.activity.SendMessageAdapter;
import com.mkmcmxci.flow.entities.Message;

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

public class SendMessageTask extends AsyncTask<String, Void, String> {

    ArrayList<Message> mList;
    Context context;
    SendMessageAdapter mAdapter;

    public SendMessageTask(Context context, ArrayList<Message> list, SendMessageAdapter adapter) {
        this.mList = list;
        this.context = context;
        this.mAdapter = adapter;
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

            JSONArray jArray = jObject.getJSONArray("Messages");

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject obj = (JSONObject) jArray.get(i);

                mList.add(new Message(obj.getString("date"),
                        obj.getString("replies"),
                        obj.getString("user_name")));
            }


            mAdapter.notifyDataSetChanged();




        } catch (JSONException e) {

            e.printStackTrace();
        }


    }
}

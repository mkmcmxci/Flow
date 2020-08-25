package com.mkmcmxci.flow.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.mkmcmxci.flow.entities.Conversation;
import com.mkmcmxci.flow.ui.message.MessageAdapter;

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

public class MessageTask extends AsyncTask<String, Void, String> {

    MessageAdapter mAdapter;
    ArrayList<Conversation> mMessageList;
    Context mContext;


    public MessageTask(Context context, MessageAdapter adapter, ArrayList<Conversation> list) {
        this.mAdapter = adapter;
        this.mMessageList = list;
        this.mContext = context;
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

            JSONArray jArray = jObject.getJSONArray("Conversation");

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject obj = (JSONObject) jArray.get(i);

                    mMessageList.add(new Conversation(obj.getString("user1Name"),
                            obj.getString("user2Name"),
                            obj.getString("lastMessage"),
                            obj.getInt("conversationID"),
                            obj.getInt("user1ID"),
                            obj.getInt("user2ID"),
                            obj.getInt("lastMessageID")));
            }

            mAdapter.notifyDataSetChanged();


        } catch (JSONException e) {

            e.printStackTrace();
        }






    }
}

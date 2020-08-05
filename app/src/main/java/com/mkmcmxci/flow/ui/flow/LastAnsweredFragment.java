package com.mkmcmxci.flow.ui.flow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.PostQuestionActivity;
import com.mkmcmxci.flow.entities.Question;

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

public class LastAnsweredFragment extends Fragment {

    RecyclerView lastAnsweredRecView;
    LastAnsweredAdapter lastAnsweredAdapter;
    List<Question> lastAnsweredQuestionList;
    LastAnsweredTask lastAnsweredTask;
    ProgressDialog lastAnsweredDialog;
    FloatingActionButton lastAnsweredFloatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_last_answered, container, false);

        lastAnsweredRecView = v.findViewById(R.id.fragment_last_answered_recycler_view);

        lastAnsweredFloatingActionButton = v.findViewById(R.id.fragment_last_answered_floating_button);

        lastAnsweredQuestionList = new ArrayList<>();

        lastAnsweredAdapter = new LastAnsweredAdapter(getContext(), lastAnsweredQuestionList);

        lastAnsweredRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        lastAnsweredRecView.setAdapter(lastAnsweredAdapter);

        lastAnsweredTask = new LastAnsweredTask();

        lastAnsweredTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/getquestionsbyanswer/");

        lastAnsweredFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PostQuestionActivity.class);
                startActivity(i);
            }
        });


        return v;
    }

    public class LastAnsweredTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            lastAnsweredDialog = new ProgressDialog(getContext());
            lastAnsweredDialog.setTitle("Please Wait");
            lastAnsweredDialog.setMessage("Loading..");
            lastAnsweredDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            lastAnsweredDialog.show();

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

                    lastAnsweredQuestionList.add(new Question(obj.getInt("question_id"),
                            obj.getString("title"),
                            obj.getString("content"),
                            obj.getString("username"),
                            obj.getInt("answer_size")));
                }
                lastAnsweredAdapter.notifyDataSetChanged();

            } catch (JSONException e) {

                e.printStackTrace();
            }

            lastAnsweredDialog.dismiss();
        }
    }
}

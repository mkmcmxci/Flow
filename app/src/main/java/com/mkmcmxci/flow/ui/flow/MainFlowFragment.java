package com.mkmcmxci.flow.ui.flow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

public class MainFlowFragment extends Fragment {

    RecyclerView mainFlowRecView;
    MainFlowAdapter mainFlowAdapter;
    List<Question> mainFlowQuestionList;
    MainFlowTask mainFlowTask;
    ProgressDialog mainFlowDialog;
    FloatingActionButton mainFlowFloatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_flow, container, false);

        mainFlowRecView = v.findViewById(R.id.fragment_main_flow_recycler_view);

        mainFlowFloatingActionButton = v.findViewById(R.id.fragment_main_flow_floating_button);

        mainFlowQuestionList = new ArrayList<>();

        mainFlowAdapter = new MainFlowAdapter(getContext(), mainFlowQuestionList);

        mainFlowRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mainFlowRecView.setAdapter(mainFlowAdapter);

        mainFlowTask = new MainFlowTask();

        mainFlowTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/getallquestions");

        mainFlowFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), PostQuestionActivity.class);
                startActivity(i);

            }
        });

        return v;
    }

    public class MainFlowTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            mainFlowDialog = new ProgressDialog(getContext());
            mainFlowDialog.setTitle("Please Wait");
            mainFlowDialog.setMessage("Loading..");
            mainFlowDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mainFlowDialog.show();

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

                    mainFlowQuestionList.add(new Question(obj.getInt("question_id"),
                            obj.getString("title"),
                            obj.getString("content"),
                            obj.getString("username"),
                            obj.getInt("answer_size")));
                }
                mainFlowAdapter.notifyDataSetChanged();

            } catch (JSONException e) {

                e.printStackTrace();
            }

            mainFlowDialog.dismiss();
        }
    }
}

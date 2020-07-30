package com.mkmcmxci.flow.ui.flow;

import android.app.ProgressDialog;
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

import com.mkmcmxci.flow.R;
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

    RecyclerView recView;
    MainFlowAdapter mainFlowAdapter;
    List<Question> questionList;
    //MainFlowTask task;
    ProgressDialog dialog;
    Question q = new Question();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main_flow, container, false);

        recView = v.findViewById(R.id.fragment_main_flow_recycler_view);

        questionList = new ArrayList<>();

        mainFlowAdapter = new MainFlowAdapter(getContext(), questionList);

        recView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recView.setAdapter(mainFlowAdapter);

        //task = new MainFlowTask();

        //task.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/getallquestions/");

        return v;
    }

    /*
    public class MainFlowTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(getContext());
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
                JSONArray arr = new JSONArray(s);

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject obj = (JSONObject) arr.get(i);

                    questionList.add(new Question(Integer.parseInt(obj.getString("user_id")),
                            obj.getString("title"), obj.getString("content"),
                            obj.getString("username"),
                            Integer.parseInt(obj.getString("answer_size"))));
                }
                mainFlowAdapter.notifyDataSetChanged();

            } catch (JSONException e) {

                e.printStackTrace();
            }
            dialog.dismiss();

        }
    }

    */
}

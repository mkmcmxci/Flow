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
import java.util.List;

public class AnswerFragment extends Fragment {

    RecyclerView answeredRecView;
    AnswerAdapter answeredAdapter;
    List<Answer> answeredList;
    AnsweredTask answeredTask;
    ProgressDialog answeredDialog;
    String questionID, questionTitle, questionContent, questionUsername, questionAnswerSize;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_answer, container, false);

        answeredRecView = v.findViewById(R.id.fragment_answer_recycler_view);

        this.questionID = getArguments().getString("questionID");
        this.questionTitle = getArguments().getString("questionTitle");
        this.questionContent = getArguments().getString("questionContent");
        this.questionUsername = getArguments().getString("questionUsername");
        this.questionAnswerSize = getArguments().getString("questionAnswerSize");

        answeredList = new ArrayList<>();

        answeredList.add(new Answer(this.questionUsername,this.questionContent,this.questionTitle,0, true));

        answeredAdapter = new AnswerAdapter(getContext(), answeredList);

        answeredRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        answeredRecView.setAdapter(answeredAdapter);

        answeredTask = new AnsweredTask();

        answeredTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/answerbyquestion/" + questionID);

    return v;

    }

    public class AnsweredTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            answeredDialog = new ProgressDialog(getContext());
            answeredDialog.setTitle("Please Wait");
            answeredDialog.setMessage("Loading..");
            answeredDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            answeredDialog.show();

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

                    answeredList.add(new Answer(obj.getString("answer_username"),
                            obj.getString("answer_content"),
                            obj.getString("question_title"),
                            obj.getInt("answer_size"), false));
                }
                answeredAdapter.notifyDataSetChanged();

            } catch (JSONException e) {

                e.printStackTrace();
            }

            answeredDialog.dismiss();
        }
    }


}

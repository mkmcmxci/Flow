package com.mkmcmxci.flow.ui.find;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class FindResultFragment extends Fragment {

    FindResultAdapter searchAdapter;
    ListView searchListView;
    String newText;
    List<Question> searchQuestionList = new ArrayList<>();

    public FindResultFragment() {

    }


    public FindResultFragment(FindResultAdapter searchAdapter, String newText) {
        this.searchAdapter = searchAdapter;
        this.newText = newText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_find_result, container, false);

        searchListView = v.findViewById(R.id.fragment_find_result_list_view);

        SearchTask asyncTask = new SearchTask();

        asyncTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/getallquestions");

        return v;
    }

    public class SearchTask extends AsyncTask<String, Void, String> {


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

                    searchQuestionList.add(new Question(obj.getInt("QuestionID"),
                            obj.getString("QuestionTitle"),
                            obj.getString("QuestionContent"),
                            obj.getString("Username"),
                            obj.getInt("AnswerSize"),
                            obj.getInt("UserID"),
                            obj.getInt("UserQuestionSize"),
                            obj.getInt("UserAnswerSize")));
                }


            } catch (JSONException e) {

                e.printStackTrace();
            }

            if(getContext() != null){

                searchAdapter = new FindResultAdapter(getContext(), searchQuestionList);

                searchAdapter.getFilter().filter(newText);

                searchListView.setAdapter(searchAdapter);

                searchAdapter.notifyDataSetChanged();

            }





        }


    }


}

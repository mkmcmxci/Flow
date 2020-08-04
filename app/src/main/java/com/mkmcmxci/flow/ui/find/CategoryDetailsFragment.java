package com.mkmcmxci.flow.ui.find;

import android.app.ProgressDialog;
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

public class CategoryDetailsFragment extends Fragment {

    RecyclerView categoryDetailsRecView;
    CategoryDetailsAdapter categoryDetailsAdapter;
    List<Question> categoryDetailsQuestionList;
    CategoryDetailsTask categoryDetailsTask;
    ProgressDialog categoryDetailsDialog;
    String catID, catName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_category_details, container, false);

        categoryDetailsRecView = v.findViewById(R.id.fragment_category_details_recycler_view);

        categoryDetailsQuestionList = new ArrayList<>();



        categoryDetailsAdapter = new CategoryDetailsAdapter(getContext(),categoryDetailsQuestionList);

        categoryDetailsRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        categoryDetailsRecView.setAdapter(categoryDetailsAdapter);

        catID = getArguments().getString("catID");
        catName = getArguments().getString("catName");

        categoryDetailsTask = new CategoryDetailsTask();

        categoryDetailsTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/questionsbycategory/" + catID);

        return v;

    }

    public class CategoryDetailsTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            categoryDetailsDialog = new ProgressDialog(getContext());
            categoryDetailsDialog.setTitle("Please Wait");
            categoryDetailsDialog.setMessage("Loading..");
            categoryDetailsDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            categoryDetailsDialog.show();

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

                    categoryDetailsQuestionList.add(new Question(obj.getInt("question_id"),
                            obj.getString("title"),
                            obj.getString("content"),
                            obj.getString("username"),
                            obj.getInt("answer_size")));
                }
                categoryDetailsAdapter.notifyDataSetChanged();

            } catch (JSONException e) {

                e.printStackTrace();
            }

            categoryDetailsDialog.dismiss();
        }
    }
}

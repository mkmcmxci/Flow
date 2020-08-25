package com.mkmcmxci.flow.ui.find;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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

public class CategoryDetailsFragment extends Fragment {

    RecyclerView mRecView;
    CategoryDetailsAdapter mAdapter;
    List<Question> mQuestionList;
    CategoryDetailsTask mTask;
    ProgressDialog categoryDetailsDialog;
    String catID, catName;
    FloatingActionButton mFab;
    View mView;
    final String URL = "http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/questionsbycategory/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_category_details, container, false);


        /* Arguments from FindAdapter */

        catID = getArguments().getString("catID");
        catName = getArguments().getString("catName");

        getViews();
        init();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PostQuestionActivity.class);
                startActivity(i);
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(catName);


        setHasOptionsMenu(true);

        return mView;

    }

    public void getViews(){
        mRecView = mView.findViewById(R.id.fragment_category_details_recycler_view);
        mFab = mView.findViewById(R.id.fragment_category_details_floating_button);
    }

    public void init(){
        mQuestionList = new ArrayList<>();
        mAdapter = new CategoryDetailsAdapter(getContext(), mQuestionList);
        mRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecView.setAdapter(mAdapter);
        mTask = new CategoryDetailsTask();
        mTask.execute(URL + catID);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        getActivity().onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public class CategoryDetailsTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            categoryDetailsDialog = new ProgressDialog(getContext());
            categoryDetailsDialog.show();
            categoryDetailsDialog.setContentView(R.layout.custom_progress);
            categoryDetailsDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

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

                    mQuestionList.add(new Question(obj.getInt("QuestionID"),
                            obj.getString("Title"),
                            obj.getString("Content"),
                            obj.getString("Username"),
                            obj.getInt("AnswerSize"),
                            obj.getInt("UserID"),
                            obj.getInt("UserQuestionSize"),
                            obj.getInt("UserAnswerSize")));
                }
                mAdapter.notifyDataSetChanged();

            } catch (JSONException e) {

                e.printStackTrace();
            }

            categoryDetailsDialog.dismiss();
        }
    }
}

package com.mkmcmxci.flow.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import androidx.navigation.Navigation;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.MainActivity;
import com.mkmcmxci.flow.ui.flow.AnswerFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AnsweredSendTask extends AsyncTask<String, Void, String> {



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

        }

        catch (MalformedURLException e) {

            e.printStackTrace();

        }

        catch (IOException e) {

            e.printStackTrace();

        }

        return buffer.toString();

    }

    @Override
    protected void onPostExecute(String s) {



    }
}

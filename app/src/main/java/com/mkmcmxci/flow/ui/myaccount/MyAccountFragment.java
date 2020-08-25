package com.mkmcmxci.flow.ui.myaccount;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.User;
import com.mkmcmxci.flow.interfaces.PassToFrags;

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

public class MyAccountFragment extends Fragment implements PassToFrags {

    TextView myAccountFragmentQuestionCount, myAccountFragmentAnswerCount, myAccountFragmentUsername;
    View myAccountView;
    ArrayList<User> myAccountUserList;
    TabItem myAccountFragmentQuestionTab, myAccountFragmentAnswerTab;
    static int userID;
    static String password;
    String name, mail;
    TabLayout myAccountFragmentTabLayout;
    ViewPager myAccountFragmentViewPager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myAccountView = inflater.inflate(R.layout.fragment_my_account, container, false);
        getMyAccountViews();

        myAccountFragmentTabLayout.setupWithViewPager(myAccountFragmentViewPager);

        MyAccountPagerAdapter adapter = new MyAccountPagerAdapter(getChildFragmentManager(), myAccountFragmentTabLayout.getTabCount());

        myAccountUserList = new ArrayList<>();

        MyAccountTask mat = new MyAccountTask();

        mat.execute("http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/getuserbyid/" + userID);

        myAccountFragmentViewPager.setAdapter(adapter);

        myAccountFragmentViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        myAccountFragmentTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                myAccountFragmentViewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setHasOptionsMenu(true);

        return myAccountView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.profile_settings_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile_settings_menu_item:
                MyAccountSettingsFragment psf = new MyAccountSettingsFragment();
                psf.onPassToFrags(userID, name, mail, password);


                Navigation.findNavController(myAccountView).navigate(R.id.action_navigation_my_account_to_navigation_my_account_settings);
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void getMyAccountViews() {

        myAccountFragmentAnswerCount = myAccountView.findViewById(R.id.fragment_my_account_answer_count_text_view);
        myAccountFragmentUsername = myAccountView.findViewById(R.id.fragment_my_account_username_textview);
        myAccountFragmentQuestionCount = myAccountView.findViewById(R.id.fragment_my_account_question_count_text_view);
        myAccountFragmentQuestionTab = myAccountView.findViewById(R.id.fragment_my_account_tab_one);
        myAccountFragmentAnswerTab = myAccountView.findViewById(R.id.fragment_my_account_tab_two);
        myAccountFragmentViewPager = myAccountView.findViewById(R.id.fragment_my_account_viewpager);
        myAccountFragmentTabLayout = myAccountView.findViewById(R.id.fragment_my_account_tab_layout);
    }

    @Override
    public void onPassToFrags(int userID, String name, String mail, String password) {

        this.userID = userID;
        this.password = password;

    }

    public class MyAccountTask extends AsyncTask<String, Void, String> {

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

                JSONArray jArray = jObject.getJSONArray("Users");

                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject obj = (JSONObject) jArray.get(i);

                    myAccountUserList.add(new User(obj.getInt("UserID"),
                            obj.getString("Username"),
                            obj.getString("Mail"),
                            obj.getString("Password"), obj.getInt("UserAnswerSize"), obj.getInt("UserQuestionSize")));
                }


            } catch (JSONException e) {

                e.printStackTrace();

            }

            name = String.valueOf(myAccountUserList.get(0).getName());

            mail = String.valueOf(myAccountUserList.get(0).getMail());

            myAccountFragmentQuestionCount.setText(String.valueOf(myAccountUserList.get(0).getNumberOfQuestions()));

            myAccountFragmentAnswerCount.setText(String.valueOf(myAccountUserList.get(0).getNumberOfAnswers()));

            myAccountFragmentUsername.setText(name);



        }
    }


}

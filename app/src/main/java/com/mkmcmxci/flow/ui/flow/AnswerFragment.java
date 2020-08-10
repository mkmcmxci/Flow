package com.mkmcmxci.flow.ui.flow;

import android.app.ProgressDialog;
import android.bluetooth.le.ScanSettings;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Answer;
import com.mkmcmxci.flow.tasks.AnsweredSendTask;
import com.mkmcmxci.flow.tasks.AnsweredTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class AnswerFragment extends Fragment {

    RecyclerView answeredRecView;
    BottomSheetBehavior answeredBottomSheetBehavior;
    AnswerAdapter answeredAdapter;
    List<Answer> answeredItemList;
    String questionID, questionTitle, questionContent, questionUsername, questionAnswerSize;
    EditText answeredEditText;
    TextView answeredTexViewCancel, answeredTexViewSend;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_answer, container, false);

        answeredRecView = v.findViewById(R.id.fragment_answer_recycler_view);

        answeredEditText = v.findViewById(R.id.fragment_answer_bottom_sheet_editText);

        answeredTexViewCancel = v.findViewById(R.id.fragment_answer_cancel_text_view);

        answeredTexViewSend = v.findViewById(R.id.fragment_answer_send_text_view);

        View bottomSheet = v.findViewById(R.id.fragment_answer_bottom_sheet);

        answeredBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        answeredBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        questionID = getArguments().getString("questionID");
        questionTitle = getArguments().getString("questionTitle");
        questionContent = getArguments().getString("questionContent");
        questionUsername = getArguments().getString("questionUsername");
        questionAnswerSize = getArguments().getString("questionAnswerSize");

        answeredItemList = new ArrayList<>();

        answeredItemList.add(new Answer(questionUsername, questionContent, questionTitle, Integer.parseInt(questionAnswerSize)));

        answeredAdapter = new AnswerAdapter(getContext(), answeredItemList);

        answeredRecView.setLayoutManager(new LinearLayoutManager(getActivity()));

        answeredRecView.setAdapter(answeredAdapter);

        AnsweredTask answeredTask;

        answeredTask = new AnsweredTask(answeredAdapter, answeredItemList);

        answeredTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/answerwebservices/answerbyquestion/" + questionID);

        setHasOptionsMenu(true);

        answeredTexViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answeredBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

            }
        });

        answeredTexViewSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnsweredSendTask answeredSendTask = new AnsweredSendTask();

                answeredSendTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/answerwebservices/addanswer/" + answeredEditText.getText().toString() + "/" + questionID + "/1");

                Bundle bundle = new Bundle();
                bundle.putString("questionID", questionID);
                bundle.putString("questionTitle", questionTitle);
                bundle.putString("questionContent", questionContent);
                bundle.putString("questionUsername", questionUsername);
                bundle.putString("questionAnswerSize", questionAnswerSize);

                Navigation.findNavController(v).navigate(R.id.action_navigation_answer_self, bundle);

            }
        });

        return v;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.answer_add_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.answer_add_menu_item) {

            answeredBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        }

        return super.onOptionsItemSelected(item);

    }

}

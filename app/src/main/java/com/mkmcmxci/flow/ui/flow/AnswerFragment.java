package com.mkmcmxci.flow.ui.flow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Answer;
import com.mkmcmxci.flow.listeners.PassToFragmentsListener;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.tasks.AnsweredTask;

import java.util.ArrayList;
import java.util.List;

public class AnswerFragment extends Fragment {

    RecyclerView mRecView;
    AnswerAdapter mAdapter;
    List<Answer> mItemList;
    String questionID, questionTitle, questionContent, questionUsername, questionUserID, userQuestionSize, userAnswerSize;
    int questionAnswerSize;
    View mView;
    AnsweredTask answeredTask;
    //static int userID;
    //static String username, password, mail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_answer, container, false);
        getArgs();
        getViews();
        init();
        setHasOptionsMenu(true);
        closeKeyboard();

        return mView;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.answer_add_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.answer_add_menu_item) {
            Bundle bundle = new Bundle();
            bundle.putString("QuestionID", questionID);
            bundle.putString("QuestionTitle", questionTitle);
            bundle.putString("QuestionContent", questionContent);
            bundle.putString("Username", questionUsername);
            bundle.putInt("AnswerSize", questionAnswerSize);
            bundle.putString("UserID", questionUserID);
            bundle.putString("UserQuestionSize", userQuestionSize);
            bundle.putString("UserAnswerSize", userAnswerSize);

            Navigation.findNavController(mView).navigate(R.id.action_navigation_answer_to_navigation_send_answers, bundle);

        } else {

            getActivity().onBackPressed();

        }

        return false;

    }

/*
    @Override
    public void onPassToFragments(int userID, String name, String mail, String password) {
        this.userID = userID;
        this.username = name;
        this.password = password;
        this.mail = mail;
    }


 */

    private void getViews() {
        mRecView = mView.findViewById(R.id.fragment_answer_recycler_view);
    }

    private void getArgs() {
        questionID = getArguments().getString("QuestionID");
        questionTitle = getArguments().getString("QuestionTitle");
        questionContent = getArguments().getString("QuestionContent");
        questionUsername = getArguments().getString("Username");
        questionAnswerSize = getArguments().getInt("AnswerSize");
        questionUserID = getArguments().getString("UserID");
        userQuestionSize = getArguments().getString("UserQuestionSize");
        userAnswerSize = getArguments().getString("UserAnswerSize");
    }

    private void init() {
        mItemList = new ArrayList<>();
        mItemList.add(new Answer(questionUsername, questionContent, questionTitle, questionAnswerSize, Integer.parseInt(questionUserID), Integer.parseInt(userQuestionSize), Integer.parseInt(userAnswerSize), Integer.parseInt(questionID), Integer.parseInt(questionUserID), 0, questionContent, null));
        mAdapter = new AnswerAdapter(getContext(), mItemList, Integer.parseInt(questionUserID),questionUsername, questionTitle);
        mRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecView.setAdapter(mAdapter);
        answeredTask = new AnsweredTask(mAdapter, mItemList);
        answeredTask.execute(Services.answerByQuestion(Integer.parseInt(questionID)));
    }


    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

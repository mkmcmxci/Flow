package com.mkmcmxci.flow.ui.flow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.listeners.PassToFragmentsListener;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.PostDataTask;

public class AnswerSendFragment extends Fragment {

    View mView;
    TextView mTitle, mTitleContent;
    EditText mContent;

    String questionID, questionTitle, questionContent, questionUsername, questionUserID, userQuestionSize, userAnswerSize;
    int questionAnswerSize;

    //static int userID;
    //static String username, password, mail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_answer_send, container, false);
        getArgs();
        getViews();
        setHasOptionsMenu(true);
        mTitle.setText(questionTitle);
        mTitleContent.setText(questionContent);
        getKeyboard();



        return mView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.question_add_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.question_add_menu_item) {

            PostDataTask answeredSendTask = new PostDataTask();
            answeredSendTask.execute(Services.addAnswer(mContent.getText().toString(),Integer.parseInt(questionID), SessionManagement.loadUserID()));

            Bundle bundle = new Bundle();
            bundle.putString("QuestionID", questionID);
            bundle.putString("QuestionTitle", questionTitle);
            bundle.putString("QuestionContent", questionContent);
            bundle.putString("Username", questionUsername);
            bundle.putInt("AnswerSize", questionAnswerSize + 1);
            bundle.putString("UserID", questionUserID);
            bundle.putString("UserQuestionSize", userQuestionSize);
            bundle.putString("UserAnswerSize", userAnswerSize);

            Navigation.findNavController(mView).navigate(R.id.action_navigation_send_answers_to_navigation_answer, bundle);

        } else {

            getActivity().onBackPressed();

        }

        return false;

    }

    public void getViews() {
        mTitle = mView.findViewById(R.id.fragment_answer_send_title);
        mContent = mView.findViewById(R.id.fragment_answer_send_reply);
        mTitleContent = mView.findViewById(R.id.fragment_answer_send_title_content);
    }

    public void getArgs() {
        questionID = getArguments().getString("QuestionID");
        questionTitle = getArguments().getString("QuestionTitle");
        questionContent = getArguments().getString("QuestionContent");
        questionUsername = getArguments().getString("Username");
        questionAnswerSize = getArguments().getInt("AnswerSize");
        questionUserID = getArguments().getString("UserID");
        userQuestionSize = getArguments().getString("UserQuestionSize");
        userAnswerSize = getArguments().getString("UserAnswerSize");
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

    private void getKeyboard(){

        mContent.requestFocus();
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);

    }
}

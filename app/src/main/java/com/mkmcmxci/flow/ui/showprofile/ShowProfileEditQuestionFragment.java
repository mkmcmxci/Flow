package com.mkmcmxci.flow.ui.showprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.tasks.PostDataTask;

public class ShowProfileEditQuestionFragment extends Fragment {

    EditText mTitle, mContent;
    View mView;

    String questionID, questionTitle, questionContent, questionUsername, questionUserID, userQuestionSize, userAnswerSize;
    int questionAnswerSize;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_edit_question, container, false);
        getViews();
        getArgs();

        mTitle.setText(questionTitle);
        mContent.setText(questionContent);

        setHasOptionsMenu(true);


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

            PostDataTask task = new PostDataTask();
            task.execute(Services.editQuestion(Integer.parseInt(questionID), mTitle.getText().toString(), mContent.getText().toString()));


            Bundle bundle = new Bundle();
            bundle.putString("QuestionID", questionID);
            bundle.putString("QuestionTitle", mTitle.getText().toString());
            bundle.putString("QuestionContent", mContent.getText().toString());
            bundle.putString("Username", questionUsername);
            bundle.putInt("AnswerSize", questionAnswerSize + 1);
            bundle.putString("UserID", questionUserID);
            bundle.putString("UserQuestionSize", userQuestionSize);
            bundle.putString("UserAnswerSize", userAnswerSize);

            Navigation.findNavController(mView).navigate(R.id.action_navigation_edit_question_to_navigation_answer, bundle);


        } else {

            getActivity().onBackPressed();

        }

        return super.onOptionsItemSelected(item);

    }

    public void getViews() {

        mTitle = mView.findViewById(R.id.fragment_edit_question_spinner_title_edittext);
        mContent = mView.findViewById(R.id.fragment_edit_question_spinner_content_edittext);


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
}

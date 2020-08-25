package com.mkmcmxci.flow.ui.flow;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.MainActivity;
import com.mkmcmxci.flow.entities.Answer;
import com.mkmcmxci.flow.entities.Question;
import com.mkmcmxci.flow.interfaces.PassToFrags;
import com.mkmcmxci.flow.tasks.AnsweredSendTask;
import com.mkmcmxci.flow.tasks.AnsweredTask;
import com.mkmcmxci.flow.tasks.QuestionSendTask;

import java.util.ArrayList;
import java.util.List;

public class AnswerFragment extends Fragment implements PassToFrags {

    RecyclerView mRecView;
    BottomSheetBehavior mBottomBehavior, mBottomBehaviorEdit;
    AnswerAdapter mAdapter;
    List<Answer> mItemList;
    String questionID, questionTitle, questionContent, questionUsername, questionUserID, userQuestionSize, userAnswerSize;
    int questionAnswerSize;
    EditText mAnswerArea, mAnswerAreaEdit;
    TextView mCancel, mSend, mCancelEdit, mSendEdit;
    View mView, mBottomSheet, mBottomSheetEdit;
    AnsweredTask answeredTask;
    static int userID;
    static String username, password, mail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_answer, container, false);
        getArgs();
        getBottomSheet();
        getViews();
        init();
        getBottomSheet();
        setHasOptionsMenu(true);


        Log.i("Dev", "onCreateView");

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBottomBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AnsweredSendTask answeredSendTask = new AnsweredSendTask();

                answeredSendTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/answerwebservices/addanswer/" + mAnswerArea.getText().toString() + "/" + questionID + "/" + String.valueOf(userID));

                Bundle bundle = new Bundle();
                bundle.putString("QuestionID", questionID);
                bundle.putString("QuestionTitle", questionTitle);
                bundle.putString("QuestionContent", questionContent);
                bundle.putString("Username", questionUsername);
                bundle.putInt("AnswerSize", questionAnswerSize + 1);
                bundle.putString("UserID", questionUserID);
                bundle.putString("UserQuestionSize", userQuestionSize);
                bundle.putString("UserAnswerSize", userAnswerSize);

                Navigation.findNavController(v).navigate(R.id.action_navigation_answer_self, bundle);

                closeKeyboard();

            }
        });

        setHasOptionsMenu(true);


        MainActivity m = new MainActivity();


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
            mBottomBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        } else {

            getActivity().onBackPressed();



        }

        return false;

    }


    @Override
    public void onPassToFrags(int userID, String name, String mail, String password) {
        this.userID = userID;
        this.username = name;
        this.password = password;
        this.mail = mail;
    }

    private void getViews() {
        mRecView = mView.findViewById(R.id.fragment_answer_recycler_view);
        mAnswerArea = mView.findViewById(R.id.fragment_answer_bottom_sheet_editText);
        mAnswerAreaEdit = mView.findViewById(R.id.fragment_answer_bottom_sheet_editText_edit);
        mCancel = mView.findViewById(R.id.fragment_answer_cancel_text_view);
        mSend = mView.findViewById(R.id.fragment_answer_send_text_view);
        mCancelEdit = mView.findViewById(R.id.fragment_answer_cancel_text_view_edit);
        mSendEdit = mView.findViewById(R.id.fragment_answer_send_text_view_edit);
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
        mAdapter = new AnswerAdapter(getContext(), mItemList, Integer.parseInt(questionUserID), mBottomBehavior, questionUsername, questionTitle, mBottomBehaviorEdit, mSendEdit, mCancelEdit, mAnswerAreaEdit);
        mRecView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecView.setAdapter(mAdapter);
        answeredTask = new AnsweredTask(mAdapter, mItemList);
        answeredTask.execute("http://10.0.2.2:8080/BulletinBoard/rest/answerwebservices/answerbyquestion/" + questionID);
    }

    private void getBottomSheet() {
        mBottomSheet = mView.findViewById(R.id.fragment_answer_bottom_sheet);
        mBottomBehavior = BottomSheetBehavior.from(mBottomSheet);
        mBottomBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetEdit = mView.findViewById(R.id.fragment_answer_bottom_sheet_edit);
        mBottomBehaviorEdit = BottomSheetBehavior.from(mBottomSheetEdit);
        mBottomBehaviorEdit.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

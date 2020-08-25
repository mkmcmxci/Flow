package com.mkmcmxci.flow.ui.flow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Answer;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.AnsweredSendTask;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    List<Answer> mList;
    Context mContext;
    int userID;
    String questionUsername, questionTitle;
    final String answerURL = "http://10.0.2.2:8080/BulletinBoard/rest/answerwebservices/deleteanswer/";
    final String questionURL = "http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/deletequestion/";
    final String editAnswerURL = "http://10.0.2.2:8080/BulletinBoard/rest/answerwebservices/editanswer/";

    BottomSheetBehavior mBottomBehaviorEdit;
    TextView mCancelEdit, mSendEdit;
    EditText mAnswerAreaEdit;
    BottomSheetBehavior mBottomSheetEdit;

    public AnswerAdapter(Context context, List<Answer> list, int userID,
                         BottomSheetBehavior mBottomSheet,
                         String questionUsername,
                         String questionTitle,
                         BottomSheetBehavior mBottomBehavior,
                         TextView mSend,
                         TextView mCancel,
                         EditText mAnswerArea) {
        this.mContext = context;
        this.mList = list;
        this.userID = userID;
        this.mAnswerAreaEdit = mAnswerArea;
        this.mBottomSheetEdit = mBottomSheet;
        this.questionUsername = questionUsername;
        this.questionTitle = questionTitle;
        this.mBottomBehaviorEdit = mBottomBehavior;
        this.mCancelEdit = mCancel;
        this.mSendEdit = mSend;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {

            View headerView = LayoutInflater.from(mContext).inflate(R.layout.fragment_answer_header_row, parent, false);

            return new AnswerHeaderViewHolder(headerView);

        } else if (viewType == TYPE_ITEM) {

            View rowView = LayoutInflater.from(mContext).inflate(R.layout.fragment_answer_row, parent, false);

            return new AnswerItemViewHolder(rowView);

        } else {

            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof AnswerHeaderViewHolder) {
            AnswerHeaderViewHolder headerViewHolder = (AnswerHeaderViewHolder) holder;

            SessionManagement session = new SessionManagement(mContext);

            if(session.loadUserID() != mList.get(position).getQuestionUserID()){

                headerViewHolder.delete.setVisibility(View.GONE);
                headerViewHolder.edit.setVisibility(View.GONE);

            }

            headerViewHolder.answerCount.setText(String.valueOf(mList.get(position).getAnswerCount()));
            headerViewHolder.title.setText(mList.get(position).getTitle());
            headerViewHolder.username.setText(mList.get(position).getUsername());
            headerViewHolder.content.setText(mList.get(position).getContent());

            headerViewHolder.username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();

                    bundle.putInt("UserID", mList.get(position).getAnswerUserID());
                    bundle.putString("Username", mList.get(position).getUsername());
                    bundle.putInt("UserAnswerSize", mList.get(position).getUserAnswerSize());
                    bundle.putInt("UserQuestionSize", mList.get(position).getUserQuestionSize());

                    Navigation.findNavController(v).navigate(R.id.action_navigation_answer_to_navigation_show_profile, bundle);


                }
            });

            headerViewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putString("QuestionID", String.valueOf(mList.get(position).getQuestionID()));
                    bundle.putString("QuestionTitle", questionTitle);
                    bundle.putString("QuestionContent", mList.get(position).getQuestionContent());
                    bundle.putString("Username", questionUsername);
                    bundle.putInt("AnswerSize", mList.get(position).getAnswerCount() - 1);
                    bundle.putString("UserID", String.valueOf(mList.get(position).getQuestionUserID()));
                    bundle.putString("UserQuestionSize", String.valueOf(mList.get(position).getUserQuestionSize()));
                    bundle.putString("UserAnswerSize", String.valueOf(mList.get(position).getUserAnswerSize()));

                    Navigation.findNavController(v).navigate(R.id.action_navigation_answer_to_navigation_edit_question,bundle);

                }
            });



            headerViewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AnsweredSendTask task = new AnsweredSendTask();
                    task.execute(questionURL + mList.get(position).getQuestionID());
                    Navigation.findNavController(v).navigate(R.id.action_navigation_answer_to_navigation_flow);
                }
            });

        } else if (holder instanceof AnswerItemViewHolder) {

            AnswerItemViewHolder itemViewHolder = (AnswerItemViewHolder) holder;

            SessionManagement session = new SessionManagement(mContext);


            if(session.loadUserID() != mList.get(position).getAnswerUserID()){

                itemViewHolder.delete.setVisibility(View.GONE);
                itemViewHolder.edit.setVisibility(View.GONE);

            }

            itemViewHolder.answer.setText(mList.get(position).getContent());
            itemViewHolder.username.setText(mList.get(position).getUsername());

            itemViewHolder.username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();

                    bundle.putInt("UserID", mList.get(position).getAnswerUserID());
                    bundle.putString("Username", mList.get(position).getUsername());
                    bundle.putInt("UserAnswerSize", mList.get(position).getUserAnswerSize());
                    bundle.putInt("UserQuestionSize", mList.get(position).getUserQuestionSize());

                    Navigation.findNavController(v).navigate(R.id.action_navigation_answer_to_navigation_show_profile, bundle);



                }
            });

            itemViewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mBottomBehaviorEdit.setState(BottomSheetBehavior.STATE_EXPANDED);

                }
            });

            mAnswerAreaEdit.setText(mList.get(position).getContent());

            mSendEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AnsweredSendTask task = new AnsweredSendTask();
                    task.execute(editAnswerURL + mList.get(position).getAnswerID() + "/" + mAnswerAreaEdit.getText().toString());

                    Bundle bundle = new Bundle();

                    bundle.putString("QuestionID", String.valueOf(mList.get(position).getQuestionID()));
                    bundle.putString("QuestionTitle", questionTitle);
                    bundle.putString("QuestionContent", mList.get(position).getQuestionContent());
                    bundle.putString("Username", questionUsername);
                    bundle.putInt("AnswerSize", mList.get(position).getAnswerCount());
                    bundle.putString("UserID", String.valueOf(mList.get(position).getQuestionUserID()));
                    bundle.putString("UserQuestionSize", String.valueOf(mList.get(position).getUserQuestionSize()));
                    bundle.putString("UserAnswerSize", String.valueOf(mList.get(position).getUserAnswerSize()));

                    Navigation.findNavController(v).navigate(R.id.action_navigation_answer_self, bundle);


                }
            });

            mCancelEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBottomBehaviorEdit.setState(BottomSheetBehavior.STATE_HIDDEN);

                }
            });

            itemViewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnsweredSendTask task = new AnsweredSendTask();
                    task.execute(answerURL + mList.get(position).getAnswerID());

                    Bundle bundle = new Bundle();
                    bundle.putString("QuestionID", String.valueOf(mList.get(position).getQuestionID()));
                    bundle.putString("QuestionTitle", questionTitle);
                    bundle.putString("QuestionContent", mList.get(position).getQuestionContent());
                    bundle.putString("Username", questionUsername);
                    bundle.putInt("AnswerSize", mList.get(position).getAnswerCount() - 1);
                    bundle.putString("UserID", String.valueOf(mList.get(position).getQuestionUserID()));
                    bundle.putString("UserQuestionSize", String.valueOf(mList.get(position).getUserQuestionSize()));
                    bundle.putString("UserAnswerSize", String.valueOf(mList.get(position).getUserAnswerSize()));

                    Navigation.findNavController(v).navigate(R.id.action_navigation_answer_self, bundle);

                }
            });


        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {

            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class AnswerHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView title, content, username, answerCount;
        ImageView answerCountView, edit, delete, share;

        public AnswerHeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            answerCountView = itemView.findViewById(R.id.fragment_answer_header_row_count_imageview);
            edit = itemView.findViewById(R.id.fragment_answer_header_row_edit);
            delete = itemView.findViewById(R.id.fragment_answer_header_row_delete);
            share = itemView.findViewById(R.id.fragment_answer_header_row_share);
            title = itemView.findViewById(R.id.fragment_answer_header_row_title);
            content = itemView.findViewById(R.id.fragment_answer_header_row_content);
            username = itemView.findViewById(R.id.fragment_answer_header_row_username);
            answerCount = itemView.findViewById(R.id.fragment_answer_header_row_count_textview);
        }
    }

    public class AnswerItemViewHolder extends RecyclerView.ViewHolder {

        TextView answer, username;
        ImageView delete, edit, share;

        public AnswerItemViewHolder(@NonNull View itemView) {
            super(itemView);

            share = itemView.findViewById(R.id.fragment_answer_row_share);
            delete = itemView.findViewById(R.id.fragment_answer_row_delete);
            edit = itemView.findViewById(R.id.fragment_answer_row_edit);
            answer = itemView.findViewById(R.id.fragment_answer_row_answer);
            username = itemView.findViewById(R.id.fragment_answer_row_username);

        }
    }
}

package com.mkmcmxci.flow.ui.flow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Answer;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.sharedpreferences.SessionManagement;
import com.mkmcmxci.flow.tasks.PostDataTask;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    List<Answer> mList;
    Context mContext;
    int userID;
    String questionUsername, questionTitle;

    public AnswerAdapter(Context context, List<Answer> list, int userID, String questionUsername, String questionTitle) {
        this.mContext = context;
        this.mList = list;
        this.userID = userID;
        this.questionUsername = questionUsername;
        this.questionTitle = questionTitle;


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

            if (session.loadUserID() != mList.get(position).getQuestionUserID()) {

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

                    Navigation.findNavController(v).navigate(R.id.action_navigation_answer_to_navigation_edit_question, bundle);

                }
            });


            headerViewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PostDataTask task = new PostDataTask();
                    task.execute(Services.deleteQuestion(mList.get(position).getQuestionID()));

                    Navigation.findNavController(v).navigate(R.id.action_navigation_answer_to_navigation_flow);
                }
            });

        } else if (holder instanceof AnswerItemViewHolder) {

            AnswerItemViewHolder itemViewHolder = (AnswerItemViewHolder) holder;

            SessionManagement session = new SessionManagement(mContext);


            if (session.loadUserID() != mList.get(position).getAnswerUserID()) {

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


            itemViewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostDataTask task = new PostDataTask();
                    task.execute(Services.deleteAnswer(mList.get(position).getAnswerID()));


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

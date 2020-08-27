package com.mkmcmxci.flow.ui.showprofile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Question;

import java.util.List;

public class ShowProfileQuestionFragmentAdapter extends RecyclerView.Adapter<ShowProfileQuestionFragmentAdapter.ShowProfileQuestionViewHolder> {

    Context mContext;
    List<Question> mQuestionList;

    public ShowProfileQuestionFragmentAdapter(Context context, List<Question> list) {
        this.mContext = context;
        this.mQuestionList = list;

    }

    @NonNull
    @Override
    public ShowProfileQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_entry_row, parent, false);

        return new ShowProfileQuestionFragmentAdapter.ShowProfileQuestionViewHolder(view);     }

    @Override
    public void onBindViewHolder(@NonNull ShowProfileQuestionViewHolder holder, final int position) {
        holder.questionTitle.setText(mQuestionList.get(position).getTitle());

        if (mQuestionList.get(position).getAnswerSize() == 0) {

            holder.answerCount.setText("");

        } else {

            holder.answerCount.setText(String.valueOf(mQuestionList.get(position).getAnswerSize()));

        }


        holder.questionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putString("QuestionID", String.valueOf(mQuestionList.get(position).getId()));
                bundle.putString("QuestionTitle", mQuestionList.get(position).getTitle());
                bundle.putString("QuestionContent", mQuestionList.get(position).getContent());
                bundle.putString("Username", mQuestionList.get(position).getUsername());
                bundle.putInt("AnswerSize", mQuestionList.get(position).getAnswerSize());
                bundle.putString("UserID", String.valueOf(mQuestionList.get(position).getQuestionUserID()));
                bundle.putString("UserQuestionSize", String.valueOf(mQuestionList.get(position).getUserQuestionSize()));
                bundle.putString("UserAnswerSize", String.valueOf(mQuestionList.get(position).getAnswerSize()));

                Navigation.findNavController(v).navigate(R.id.action_navigation_show_profile_to_navigation_answer, bundle);


            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuestionList.size();
    }

    public class ShowProfileQuestionViewHolder extends RecyclerView.ViewHolder {

        TextView questionTitle;
        TextView answerCount;

        public ShowProfileQuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTitle = itemView.findViewById(R.id.fragment_entry_row_title_textview);
            answerCount = itemView.findViewById(R.id.fragment_entry_row_answer_textview);

        }
    }
}

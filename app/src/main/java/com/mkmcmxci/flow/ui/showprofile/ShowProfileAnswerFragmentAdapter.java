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
import com.mkmcmxci.flow.entities.Answer;

import java.util.List;

public class ShowProfileAnswerFragmentAdapter extends RecyclerView.Adapter<ShowProfileAnswerFragmentAdapter.ShowProfileAnswerViewHolder> {

    Context mContext;
    List<Answer> mAnswerList;

    public ShowProfileAnswerFragmentAdapter(Context mContext, List<Answer> mAnswerList) {
        this.mContext = mContext;
        this.mAnswerList = mAnswerList;

    }

    @NonNull
    @Override
    public ShowProfileAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_my_account_answer_row, parent, false);

        return new ShowProfileAnswerFragmentAdapter.ShowProfileAnswerViewHolder(view);      }

    @Override
    public void onBindViewHolder(@NonNull ShowProfileAnswerViewHolder holder, final int position) {
        holder.questionTitle.setText(mAnswerList.get(position).getTitle());
        holder.answerContent.setText(mAnswerList.get(position).getContent());

        holder.questionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("QuestionID", String.valueOf(mAnswerList.get(position).getQuestionID()));
                bundle.putString("QuestionTitle", mAnswerList.get(position).getTitle());
                bundle.putString("QuestionContent", mAnswerList.get(position).getQuestionContent());
                bundle.putString("Username", mAnswerList.get(position).getQuestionUsername());
                bundle.putInt("AnswerSize", mAnswerList.get(position).getAnswerCount());
                bundle.putString("UserID", String.valueOf(mAnswerList.get(position).getQuestionUserID()));
                bundle.putString("UserQuestionSize", String.valueOf(mAnswerList.get(position).getUserQuestionSize()));
                bundle.putString("UserAnswerSize", String.valueOf(mAnswerList.get(position).getUserAnswerSize()));

                Navigation.findNavController(v).navigate(R.id.action_navigation_show_profile_to_navigation_answer, bundle);




            }
        });

    }

    @Override
    public int getItemCount() {
        return mAnswerList.size();  }

    public class ShowProfileAnswerViewHolder extends RecyclerView.ViewHolder {

        TextView questionTitle;
        TextView answerContent;

        public ShowProfileAnswerViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTitle = itemView.findViewById(R.id.fragment_my_account_row_title_textview);
            answerContent = itemView.findViewById(R.id.fragment_my_account_row_answer_textview);

        }
    }
}

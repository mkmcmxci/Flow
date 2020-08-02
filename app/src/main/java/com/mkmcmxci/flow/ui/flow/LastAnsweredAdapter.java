package com.mkmcmxci.flow.ui.flow;

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

public class LastAnsweredAdapter extends RecyclerView.Adapter<LastAnsweredAdapter.LastAnsweredViewHolder> {

    Context lastAnsweredContext;
    List<Question> lastAnsweredQuestionList;

    public LastAnsweredAdapter(Context lastAnsweredContext, List<Question> lastAnsweredQuestionList) {
        this.lastAnsweredContext = lastAnsweredContext;
        this.lastAnsweredQuestionList = lastAnsweredQuestionList;
    }

    @NonNull
    @Override
    public LastAnsweredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(lastAnsweredContext).inflate(R.layout.fragment_entry_row_last_answered, parent, false);

        return new LastAnsweredAdapter.LastAnsweredViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastAnsweredViewHolder holder, final int position) {

        holder.questionTitle.setText(lastAnsweredQuestionList.get(position).getTitle());
        if (lastAnsweredQuestionList.get(position).getAnswerSize() == 0) {

            holder.answerCount.setText("");

        } else {

            holder.answerCount.setText(String.valueOf(lastAnsweredQuestionList.get(position).getAnswerSize()));

        }

        holder.questionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("questionID", String.valueOf(lastAnsweredQuestionList.get(position).getId()));
                bundle.putString("questionTitle", lastAnsweredQuestionList.get(position).getTitle());
                bundle.putString("questionContent", lastAnsweredQuestionList.get(position).getContent());
                bundle.putString("questionUsername", lastAnsweredQuestionList.get(position).getUsername());
                bundle.putString("questionAnswerSize", String.valueOf(lastAnsweredQuestionList.get(position).getAnswerSize()));


                Navigation.findNavController(v).navigate(R.id.action_navigation_flow_to_navigation_answer2, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lastAnsweredQuestionList.size();
    }

    public class LastAnsweredViewHolder extends RecyclerView.ViewHolder {

        TextView questionTitle;
        TextView answerCount;

        public LastAnsweredViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTitle = itemView.findViewById(R.id.fragment_entry_row_last_answered_title_textview);
            answerCount = itemView.findViewById(R.id.fragment_entry_row_last_answered_answer_textview);

        }
    }
}

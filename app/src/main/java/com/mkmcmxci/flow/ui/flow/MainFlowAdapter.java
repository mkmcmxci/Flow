package com.mkmcmxci.flow.ui.flow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Question;

import java.util.List;

public class MainFlowAdapter extends RecyclerView.Adapter<MainFlowAdapter.MainFlowViewHolder> {

    Context mainFlowContext;
    List<Question> mainFlowQuestionList;

    public MainFlowAdapter(Context mainFlowContext, List<Question> mainFlowQuestionList) {
        this.mainFlowContext = mainFlowContext;
        this.mainFlowQuestionList = mainFlowQuestionList;
    }

    @NonNull
    @Override
    public MainFlowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mainFlowContext).inflate(R.layout.fragment_entry_row, parent, false);

        return new MainFlowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainFlowViewHolder holder, int position) {

        holder.questionTitle.setText(mainFlowQuestionList.get(position).getTitle());

        if (mainFlowQuestionList.get(position).getAnswerSize() == 0) {

            holder.answerCount.setText("");

        } else {

            holder.answerCount.setText(String.valueOf(mainFlowQuestionList.get(position).getAnswerSize()));

        }

    }

    @Override
    public int getItemCount() {
        return mainFlowQuestionList.size();
    }

    public class MainFlowViewHolder extends RecyclerView.ViewHolder {

        TextView questionTitle;
        TextView answerCount;

        public MainFlowViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTitle = itemView.findViewById(R.id.fragment_entry_row_title_textview);
            answerCount = itemView.findViewById(R.id.fragment_entry_row_answer_textview);

        }
    }
}

package com.mkmcmxci.flow.ui.flow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Answer;

import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    List<Answer> answerItemList;
    Context answerContext;

    public AnswerAdapter(Context answerContext, List<Answer> answerItemList) {
        this.answerContext = answerContext;
        this.answerItemList = answerItemList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER) {

            View headerView = LayoutInflater.from(answerContext).inflate(R.layout.fragment_answer_header_row, parent, false);

            return new AnswerHeaderViewHolder(headerView);

        } else if (viewType == TYPE_ITEM) {

            View rowView = LayoutInflater.from(answerContext).inflate(R.layout.fragment_answer_row, parent, false);

            return new AnswerItemViewHolder(rowView);

        } else {

            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof AnswerHeaderViewHolder) {
            AnswerHeaderViewHolder headerViewHolder = (AnswerHeaderViewHolder) holder;

            headerViewHolder.questionAnswerCount.setText(String.valueOf(answerItemList.get(position).getAnswerCount()));
            headerViewHolder.questionTitle.setText(answerItemList.get(position).getTitle());
            headerViewHolder.questionUsername.setText(answerItemList.get(position).getUsername());
            headerViewHolder.questionContent.setText(answerItemList.get(position).getContent());

        } else if (holder instanceof AnswerItemViewHolder) {

            AnswerItemViewHolder itemViewHolder = (AnswerItemViewHolder) holder;

            itemViewHolder.answerContent.setText(answerItemList.get(position).getContent());

            itemViewHolder.answerUsername.setText(answerItemList.get(position).getUsername());

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
        return answerItemList.size();
    }


    public class AnswerHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView questionTitle, questionContent, questionUsername, questionAnswerCount;

        public AnswerHeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTitle = itemView.findViewById(R.id.fragment_answer_header_row_title_textview);
            questionContent = itemView.findViewById(R.id.fragment_answer_header_row_content_textview);
            questionUsername = itemView.findViewById(R.id.fragment_answer_header_row_username_textview);
            questionAnswerCount = itemView.findViewById(R.id.fragment_answer_header_row_answercount_textview);
        }
    }

    public class AnswerItemViewHolder extends RecyclerView.ViewHolder {

        TextView answerContent, answerUsername;

        public AnswerItemViewHolder(@NonNull View itemView) {
            super(itemView);

            answerContent = itemView.findViewById(R.id.fragment_answer_row_answer_textview);
            answerUsername = itemView.findViewById(R.id.fragment_answer_row_username_textview);

        }
    }
}

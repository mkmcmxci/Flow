package com.mkmcmxci.flow.ui.flow;

import android.content.Context;
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


    List<Answer> answerAdapterList;
    Context answerContext;


    public AnswerAdapter(Context answerContext, List<Answer> answerAdapterList) {
        this.answerAdapterList = answerAdapterList;
        this.answerContext = answerContext;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == TYPE_HEADER){

            View headerView = LayoutInflater.from(answerContext).inflate(R.layout.fragment_answer_header_row, parent, false);

            return new AnswerHeaderViewHolder(headerView);

        }
        else if(viewType == TYPE_ITEM){

            View rowView = LayoutInflater.from(answerContext).inflate(R.layout.fragment_answer_row, parent, false);

            return new AnswerItemViewHolder(rowView);


        }
        else{

            return null;
        }
            }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof AnswerHeaderViewHolder){
            AnswerHeaderViewHolder headerViewHolder = (AnswerHeaderViewHolder) holder;

            headerViewHolder.questionAnswerCount.setText(String.valueOf(answerAdapterList.get(position).getAnswerCount()));
            headerViewHolder.questionTitle.setText(answerAdapterList.get(position).getTitle());
            headerViewHolder.questionUsername.setText(answerAdapterList.get(position).getUsername());
            headerViewHolder.questionContent.setText(answerAdapterList.get(position).getContent());

        }
        else if (holder instanceof AnswerItemViewHolder){

            AnswerItemViewHolder itemViewHolder = (AnswerItemViewHolder) holder;

            itemViewHolder.answerContent.setText(answerAdapterList.get(position-1).getContent());
            itemViewHolder.answerUsername.setText(answerAdapterList.get(position-1).getUsername());

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {

            return TYPE_HEADER;
        }
        return TYPE_ITEM;    }




    @Override
    public int getItemCount() {
        return answerAdapterList.size() + 1;
    }




    public class AnswerHeaderViewHolder extends RecyclerView.ViewHolder{

            TextView questionTitle, questionContent, questionUsername, questionAnswerCount;

        public AnswerHeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTitle = itemView.findViewById(R.id.fragment_answer_header_row_title_textview);
            questionContent = itemView.findViewById(R.id.fragment_answer_header_row_content_textview);
            questionUsername = itemView.findViewById(R.id.fragment_answer_header_row_username_textview);
            questionAnswerCount = itemView.findViewById(R.id.fragment_answer_header_row_answercount_textview);
        }
    }




    public class AnswerItemViewHolder extends RecyclerView.ViewHolder{

        TextView answerContent, answerUsername;

        public AnswerItemViewHolder(@NonNull View itemView) {
            super(itemView);

            answerContent = itemView.findViewById(R.id.fragment_answer_row_answer_textview);
            answerUsername = itemView.findViewById(R.id.fragment_answer_row_username_textview);


        }
    }
}

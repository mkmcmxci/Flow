package com.mkmcmxci.flow.ui.myaccount;

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

public class MyAccountAnswerAdapter extends RecyclerView.Adapter<MyAccountAnswerAdapter.MyAccountAnswerViewHolder>{

    Context mContext;
    List<Answer> mList;

    public MyAccountAnswerAdapter(Context context, List<Answer> list) {
        this.mContext = context;
        this.mList = list;

    }

    @NonNull
    @Override
    public MyAccountAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_my_account_answer_row, parent, false);

        return new MyAccountAnswerAdapter.MyAccountAnswerViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull MyAccountAnswerViewHolder holder, final int position) {

        holder.questionTitle.setText(mList.get(position).getTitle());
        holder.answerContent.setText(mList.get(position).getContent());

        holder.questionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* bundle to AnswerFragment */

                Bundle bundle = new Bundle();
                bundle.putString("QuestionID", String.valueOf(mList.get(position).getQuestionID()));
                bundle.putString("QuestionTitle", mList.get(position).getTitle());
                bundle.putString("QuestionContent", mList.get(position).getQuestionContent());
                bundle.putString("Username", mList.get(position).getQuestionUsername());
                bundle.putInt("AnswerSize", mList.get(position).getAnswerCount());
                bundle.putString("UserID", String.valueOf(mList.get(position).getQuestionUserID()));
                bundle.putString("UserQuestionSize", String.valueOf(mList.get(position).getUserQuestionSize()));
                bundle.putString("UserAnswerSize", String.valueOf(mList.get(position).getUserAnswerSize()));

                Navigation.findNavController(v).navigate(R.id.action_navigation_my_account_to_navigation_answer, bundle);

                /*  bundle to AnswerFragment */


            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyAccountAnswerViewHolder extends RecyclerView.ViewHolder {

        TextView questionTitle;
        TextView answerContent;

        public MyAccountAnswerViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTitle = itemView.findViewById(R.id.fragment_my_account_row_title_textview);
            answerContent = itemView.findViewById(R.id.fragment_my_account_row_answer_textview);

        }
    }
}

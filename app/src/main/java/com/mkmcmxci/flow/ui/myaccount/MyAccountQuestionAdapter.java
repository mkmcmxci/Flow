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
import com.mkmcmxci.flow.entities.Question;

import java.util.List;

public class MyAccountQuestionAdapter extends RecyclerView.Adapter<MyAccountQuestionAdapter.MyAccountQuestionViewHolder> {

    Context mContext;
    List<Question> mList;

    public MyAccountQuestionAdapter(Context context, List<Question> list) {
        this.mContext = context;
        this.mList = list;

    }

    @NonNull
    @Override
    public MyAccountQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_entry_row, parent, false);

        return new MyAccountQuestionViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull MyAccountQuestionViewHolder holder, final int position) {

        holder.questionTitle.setText(mList.get(position).getTitle());

        if (mList.get(position).getAnswerSize() == 0) {

            holder.answerCount.setText("");

        } else {

            holder.answerCount.setText(String.valueOf(mList.get(position).getAnswerSize()));

        }

        holder.questionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* bundle to AnswerFragment */

                Bundle bundle = new Bundle();
                bundle.putString("QuestionID", String.valueOf(mList.get(position).getId()));
                bundle.putString("QuestionTitle", mList.get(position).getTitle());
                bundle.putString("QuestionContent", mList.get(position).getContent());
                bundle.putString("Username", mList.get(position).getUsername());
                bundle.putInt("AnswerSize", mList.get(position).getAnswerSize());
                bundle.putString("UserID", String.valueOf(mList.get(position).getQuestionUserID()));
                bundle.putString("UserQuestionSize", String.valueOf(mList.get(position).getUserQuestionSize()));
                bundle.putString("UserAnswerSize", String.valueOf(mList.get(position).getAnswerSize()));

                Navigation.findNavController(v).navigate(R.id.action_navigation_my_account_to_navigation_answer, bundle);


                /* bundle to AnswerFragment */

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyAccountQuestionViewHolder extends RecyclerView.ViewHolder {

        TextView questionTitle;
        TextView answerCount;

        public MyAccountQuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTitle = itemView.findViewById(R.id.fragment_entry_row_title_textview);
            answerCount = itemView.findViewById(R.id.fragment_entry_row_answer_textview);

        }
    }
}

package com.mkmcmxci.flow.ui.flow;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

public class MainFlowAdapter extends RecyclerView.Adapter<MainFlowAdapter.MainFlowViewHolder> {

    Context mContext;
    List<Question> mQuestionList;

    public MainFlowAdapter(Context context, List<Question> list) {
        this.mContext = context;
        this.mQuestionList = list;

    }

    @NonNull
    @Override
    public MainFlowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_entry_row, parent, false);


        return new MainFlowViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MainFlowViewHolder holder, final int position) {

        holder.title.setText(mQuestionList.get(position).getTitle());

        if (mQuestionList.get(position).getAnswerSize() == 0) {

            holder.answerCount.setText("");

        } else {

            holder.answerCount.setText(String.valueOf(mQuestionList.get(position).getAnswerSize()));

        }

        holder.title.setOnClickListener(new View.OnClickListener() {
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
                bundle.putString("UserAnswerSize", String.valueOf(mQuestionList.get(position).getUserAnswerSize()));

                Navigation.findNavController(v).navigate(R.id.action_navigation_flow_to_navigation_answer2, bundle);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mQuestionList.size();
    }

    public class MainFlowViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView answerCount;

        public MainFlowViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.fragment_entry_row_title_textview);
            answerCount = itemView.findViewById(R.id.fragment_entry_row_answer_textview);

        }
    }
}

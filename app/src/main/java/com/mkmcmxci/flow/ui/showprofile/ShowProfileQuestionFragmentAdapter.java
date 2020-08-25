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

    Context showProfileQuestionContext;
    List<Question> showProfileQuestionQuestionList;

    public ShowProfileQuestionFragmentAdapter(Context showProfileQuestionContext, List<Question> showProfileQuestionQuestionList) {
        this.showProfileQuestionContext = showProfileQuestionContext;
        this.showProfileQuestionQuestionList = showProfileQuestionQuestionList;

    }

    @NonNull
    @Override
    public ShowProfileQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(showProfileQuestionContext).inflate(R.layout.fragment_entry_row, parent, false);

        return new ShowProfileQuestionFragmentAdapter.ShowProfileQuestionViewHolder(view);     }

    @Override
    public void onBindViewHolder(@NonNull ShowProfileQuestionViewHolder holder, final int position) {
        holder.questionTitle.setText(showProfileQuestionQuestionList.get(position).getTitle());

        if (showProfileQuestionQuestionList.get(position).getAnswerSize() == 0) {

            holder.answerCount.setText("");

        } else {

            holder.answerCount.setText(String.valueOf(showProfileQuestionQuestionList.get(position).getAnswerSize()));

        }


        holder.questionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putString("QuestionID", String.valueOf(showProfileQuestionQuestionList.get(position).getId()));
                bundle.putString("QuestionTitle", showProfileQuestionQuestionList.get(position).getTitle());
                bundle.putString("QuestionContent", showProfileQuestionQuestionList.get(position).getContent());
                bundle.putString("Username", showProfileQuestionQuestionList.get(position).getUsername());
                bundle.putInt("AnswerSize", showProfileQuestionQuestionList.get(position).getAnswerSize());
                bundle.putString("UserID", String.valueOf(showProfileQuestionQuestionList.get(position).getQuestionUserID()));
                bundle.putString("UserQuestionSize", String.valueOf(showProfileQuestionQuestionList.get(position).getUserQuestionSize()));
                bundle.putString("UserAnswerSize", String.valueOf(showProfileQuestionQuestionList.get(position).getAnswerSize()));

                Navigation.findNavController(v).navigate(R.id.action_navigation_show_profile_to_navigation_answer, bundle);


            }
        });
    }

    @Override
    public int getItemCount() {
        return showProfileQuestionQuestionList.size();
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

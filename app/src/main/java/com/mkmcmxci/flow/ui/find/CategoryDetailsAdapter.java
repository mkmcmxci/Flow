package com.mkmcmxci.flow.ui.find;

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
import com.mkmcmxci.flow.ui.flow.MainFlowAdapter;

import java.util.List;

public class CategoryDetailsAdapter extends RecyclerView.Adapter<CategoryDetailsAdapter.CategoryDetailsViewHolder> {

    Context CategoryDetailsContext;
    List<Question> CategoryDetailsQuestionList;

    public CategoryDetailsAdapter(Context CategoryDetailsContext, List<Question> CategoryDetailsQuestionList) {
        this.CategoryDetailsContext = CategoryDetailsContext;
        this.CategoryDetailsQuestionList = CategoryDetailsQuestionList;

    }

    @NonNull
    @Override
    public CategoryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(CategoryDetailsContext).inflate(R.layout.fragment_entry_row, parent, false);

        return new CategoryDetailsAdapter.CategoryDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryDetailsViewHolder holder, final int position) {
        holder.questionTitle.setText(CategoryDetailsQuestionList.get(position).getTitle());

        if (CategoryDetailsQuestionList.get(position).getAnswerSize() == 0) {

            holder.answerCount.setText("");

        } else {

            holder.answerCount.setText(String.valueOf(CategoryDetailsQuestionList.get(position).getAnswerSize()));

        }

        holder.questionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("questionID", String.valueOf(CategoryDetailsQuestionList.get(position).getId()));
                bundle.putString("questionTitle", CategoryDetailsQuestionList.get(position).getTitle());
                bundle.putString("questionContent", CategoryDetailsQuestionList.get(position).getContent());
                bundle.putString("questionUsername", CategoryDetailsQuestionList.get(position).getUsername());
                bundle.putString("questionAnswerSize", String.valueOf(CategoryDetailsQuestionList.get(position).getAnswerSize()));

                Navigation.findNavController(v).navigate(R.id.action_navigation_find_category_row_to_navigation_answer, bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return CategoryDetailsQuestionList.size();
    }

    public class CategoryDetailsViewHolder extends RecyclerView.ViewHolder {

        TextView questionTitle;
        TextView answerCount;

        public CategoryDetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            questionTitle = itemView.findViewById(R.id.fragment_entry_row_title_textview);
            answerCount = itemView.findViewById(R.id.fragment_entry_row_answer_textview);

        }
    }
}

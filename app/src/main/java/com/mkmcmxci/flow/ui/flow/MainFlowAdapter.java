package com.mkmcmxci.flow.ui.flow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Question;

import java.util.List;

public class MainFlowAdapter extends RecyclerView.Adapter<MainFlowAdapter.MainFlowViewHolder> {

    Context context;
    List<Question> questionList;

    public MainFlowAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public MainFlowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_entry_row, parent, false);

        return new MainFlowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainFlowViewHolder holder, int position) {

        holder.title.setText(questionList.get(position).getTitle());
        holder.username.setText(questionList.get(position).getUsername());
        holder.questionId.setText(String.valueOf(questionList.get(position).getId()));
        holder.answer.setText(String.valueOf(questionList.get(position).getAnswerSize()));
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class MainFlowViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView username;
        TextView questionId;
        TextView answer;

        public MainFlowViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.fragment_entry_row_title_textview);
            username = itemView.findViewById(R.id.fragment_entry_row_username_textview);
            questionId =  itemView.findViewById(R.id.fragment_entry_row_entry_id_textview);
            answer = itemView.findViewById(R.id.fragment_entry_row_answer_textview);

        }
    }
}

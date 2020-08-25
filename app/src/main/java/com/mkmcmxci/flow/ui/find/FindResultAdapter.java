package com.mkmcmxci.flow.ui.find;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.MainActivity;
import com.mkmcmxci.flow.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class FindResultAdapter extends ArrayAdapter<Question> {

    Context mContext;

    List<Question> mListFull;

    public FindResultAdapter(@NonNull Context context, List<Question> list) {
        super(context, 0, list);
        mListFull = new ArrayList<>(list);
        this.mContext = context;

    }

    @NonNull
    @Override
    public Filter getFilter() {
        return titleFilter;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_search_result_row, parent, false
            );
        }
        TextView title = convertView.findViewById(R.id.text_view_name);
        Question question = getItem(position);
        if (question != null) {
            title.setText(question.getTitle());
        }

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Bundle to AnswerFragment */

                Bundle bundle = new Bundle();
                bundle.putString("QuestionID", String.valueOf(mListFull.get(position).getId()));
                bundle.putString("QuestionTitle", mListFull.get(position).getTitle());
                bundle.putString("QuestionContent", mListFull.get(position).getContent());
                bundle.putString("Username", mListFull.get(position).getUsername());
                bundle.putString("AnswerSize", String.valueOf(mListFull.get(position).getAnswerSize()));
                bundle.putString("UserID", String.valueOf(mListFull.get(position).getQuestionUserID()));
                bundle.putString("UserQuestionSize", String.valueOf(mListFull.get(position).getUserQuestionSize()));
                bundle.putString("UserAnswerSize", String.valueOf(mListFull.get(position).getUserAnswerSize()));

                /* Bundle to AnswerFragment */


                Navigation.findNavController(v).navigate(R.id.action_navigation_find_to_navigation_answer, bundle);

            }
        });


        return convertView;
    }

    @Nullable
    @Override
    public Question getItem(int position) {
        return super.getItem(position);
    }

    private Filter titleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Question> suggestions = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(mListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Question item : mListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Question) resultValue).getTitle();
        }
    };


}

package com.mkmcmxci.flow.ui.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.entities.Category;

import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindViewHolder> {

    Context findFragmentContext;
    List<Category> findFragmentCategoryList;

    public FindAdapter(Context findFragmentContext, List<Category> findFragmentCategoryList) {
        this.findFragmentContext = findFragmentContext;
        this.findFragmentCategoryList = findFragmentCategoryList;
    }

    @NonNull
    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(findFragmentContext).inflate(R.layout.fragment_category_row, parent, false);

        return new FindAdapter.FindViewHolder(view);     }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {

        holder.categoryName.setText(findFragmentCategoryList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return findFragmentCategoryList.size();
    }

    public class FindViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;

        public FindViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.fragment_category_row_category_textview);

        }
    }
}

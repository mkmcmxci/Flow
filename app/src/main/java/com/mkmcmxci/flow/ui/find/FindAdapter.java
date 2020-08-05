package com.mkmcmxci.flow.ui.find;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.activity.MainActivity;
import com.mkmcmxci.flow.entities.Category;
import com.mkmcmxci.flow.ui.flow.FlowFragment;
import com.mkmcmxci.flow.ui.flow.MainFlowAdapter;

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
    public void onBindViewHolder(@NonNull FindViewHolder holder, final int position) {

        holder.categoryName.setText(findFragmentCategoryList.get(position).getName());

        holder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("catID", String.valueOf(findFragmentCategoryList.get(position).getId()));
                bundle.putString("catName", findFragmentCategoryList.get(position).getName());
                Navigation.findNavController(v).navigate(R.id.action_navigation_find_to_navigation_find_category_row, bundle);

            }
        });

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

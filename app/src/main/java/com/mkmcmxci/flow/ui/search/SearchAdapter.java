package com.mkmcmxci.flow.ui.search;

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
import com.mkmcmxci.flow.entities.Category;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.FindViewHolder> {

    Context mContext;
    List<Category> mList;

    public SearchAdapter(Context context, List<Category> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_category_row, parent, false);

        return new SearchAdapter.FindViewHolder(view);     }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, final int position) {

        holder.categoryName.setText(mList.get(position).getName());

        holder.categoryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("catID", String.valueOf(mList.get(position).getId()));
                bundle.putString("catName", mList.get(position).getName());


                Navigation.findNavController(v).navigate(R.id.action_navigation_find_to_navigation_find_category_row, bundle);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FindViewHolder extends RecyclerView.ViewHolder{

        TextView categoryName;

        public FindViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryName = itemView.findViewById(R.id.fragment_category_row_category_textview);

        }
    }
}

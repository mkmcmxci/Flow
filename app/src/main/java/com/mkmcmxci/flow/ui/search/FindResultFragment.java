package com.mkmcmxci.flow.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mkmcmxci.flow.R;
import com.mkmcmxci.flow.sharedpreferences.Services;
import com.mkmcmxci.flow.tasks.SearchTask;


public class FindResultFragment extends Fragment {

    FindResultAdapter searchAdapter;
    ListView mListView;
    String newText;
    View mView;

    public FindResultFragment() {

    }
    public FindResultFragment(FindResultAdapter searchAdapter, String newText) {
        this.searchAdapter = searchAdapter;
        this.newText = newText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_find_result, container, false);
        mListView = mView.findViewById(R.id.fragment_find_result_list_view);

        SearchTask task = new SearchTask(getContext(), searchAdapter, newText, mListView);
        task.execute(Services.getAllQuestions());

        return mView;
    }

}

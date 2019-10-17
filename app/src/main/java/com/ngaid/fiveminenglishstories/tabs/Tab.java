package com.ngaid.fiveminenglishstories.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ngaid.fiveminenglishstories.R;

public class Tab extends Fragment {

    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.tabs_layout, container, false);

        // setting list from RecView with the help of LayoutManager (measuring and positioning item views within a RecyclerView)
        recyclerView = v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        // calling data to fill the list
        readDataFromDb();
        return v;
    }

    public void readDataFromDb(){}
}

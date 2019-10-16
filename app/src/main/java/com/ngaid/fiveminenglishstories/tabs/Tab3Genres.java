package com.ngaid.fiveminenglishstories.tabs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ngaid.fiveminenglishstories.FireStoreW;
import com.ngaid.fiveminenglishstories.R;
import com.ngaid.fiveminenglishstories.adapters.RecyclerViewAdapterExpandable;
import com.ngaid.fiveminenglishstories.objects.ExtendableItem;
import com.ngaid.fiveminenglishstories.objects.StoriesGS;

import java.util.ArrayList;
import java.util.List;

public class Tab3Genres extends Fragment {

    final String LOG_TAG = "myLogs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.tabs_layout, container, false);

        // setting list from RecView with the help of LayoutManager (measuring and positioning item views within a RecyclerView)
        final RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        FireStoreW.readData(new FireStoreW.FirestoreCallback() {
            @Override
            public void onCallBackB(ArrayList<ExtendableItem> listB) {
                // use a linear layout manager
                recyclerView.setLayoutManager(layoutManager);
                RecyclerViewAdapterExpandable adapter = new RecyclerViewAdapterExpandable(listB, getContext(), false);
                Log.d(LOG_TAG, "listB " + listB.size());
                recyclerView.setAdapter(adapter);
            }

            public void onCallBack(List<StoriesGS> list){}
            public void onCallBackA(ArrayList<ExtendableItem> listA){}
        });

        return v;
    }

}

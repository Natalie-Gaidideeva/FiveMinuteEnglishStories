package com.ngaid.fiveminenglishstories.tabs;

import android.content.Context;
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

public class Tab2Authors extends Fragment {

    final String LOG_TAG = "myLogs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.tabs_layout, container, false);
        Log.d(LOG_TAG, "Fragment2 onCreateView");

        // setting list from RecView with the help of LayoutManager (measuring and positioning item views within a RecyclerView)
        final RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        FireStoreW.readData(new FireStoreW.FirestoreCallback() {
            @Override
            public void onCallBackA(ArrayList<ExtendableItem> listA) {
                // use a linear layout manager
                recyclerView.setLayoutManager(layoutManager);
                RecyclerViewAdapterExpandable adapter = new RecyclerViewAdapterExpandable(listA, getContext(), true);
                Log.d(LOG_TAG, "listA " + listA.size());
                recyclerView.setAdapter(adapter);
            }
            public void onCallBack(List<StoriesGS> list){}
            public void onCallBackB(ArrayList<ExtendableItem> listB){}
        });

        return v;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(LOG_TAG, "Fragment2 onAttach");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Fragment2 onCreate");
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "Fragment2 onActivityCreated");
    }

    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "Fragment2 onStart");
    }

    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Fragment2 onResume");
    }

    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Fragment2 onPause");
    }

    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "Fragment2 onStop");
    }

    public void onDestroyView() {
        super.onDestroyView();
        Log.d(LOG_TAG, "Fragment2 onDestroyView");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Fragment2 onDestroy");
    }

    public void onDetach() {
        super.onDetach();
        Log.d(LOG_TAG, "Fragment2 onDetach");
    }

}

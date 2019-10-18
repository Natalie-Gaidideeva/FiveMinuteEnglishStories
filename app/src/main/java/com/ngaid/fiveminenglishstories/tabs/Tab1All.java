package com.ngaid.fiveminenglishstories.tabs;

import com.ngaid.fiveminenglishstories.FireStoreW;
import com.ngaid.fiveminenglishstories.adapters.RecyclerViewAdapterSimple;
import com.ngaid.fiveminenglishstories.objects.ExtendableItem;
import com.ngaid.fiveminenglishstories.objects.StoriesGS;

import java.util.ArrayList;
import java.util.List;

public class Tab1All extends Tab {

    @Override
    public void readDataFromDb(){
        FireStoreW.readData(new FireStoreW.FirestoreCallback() {
            @Override
            public void onCallBack(List<StoriesGS> list) {
                RecyclerViewAdapterSimple adapter = new RecyclerViewAdapterSimple(list, getContext());
                recyclerView.setAdapter(adapter);
            }
            public void onCallBackA(ArrayList<ExtendableItem> listA) {}
            public void onCallBackB(ArrayList<ExtendableItem> listB){}
        });
    }
}
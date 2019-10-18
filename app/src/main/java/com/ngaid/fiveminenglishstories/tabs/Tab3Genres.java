package com.ngaid.fiveminenglishstories.tabs;

import com.ngaid.fiveminenglishstories.FireStoreW;
import com.ngaid.fiveminenglishstories.adapters.AdapterForGenreMenu;
import com.ngaid.fiveminenglishstories.objects.ExtendableItem;
import com.ngaid.fiveminenglishstories.objects.StoriesGS;

import java.util.ArrayList;
import java.util.List;

public class Tab3Genres extends Tab {

    @Override
    public void readDataFromDb(){
        FireStoreW.readData(new FireStoreW.FirestoreCallback() {
            @Override
            public void onCallBackB(ArrayList<ExtendableItem> listB) {
                AdapterForGenreMenu adapter = new AdapterForGenreMenu(listB, getContext());
                recyclerView.setAdapter(adapter);
            }
            public void onCallBack(List<StoriesGS> list){}
            public void onCallBackA(ArrayList<ExtendableItem> listA){}
        });
    }
}

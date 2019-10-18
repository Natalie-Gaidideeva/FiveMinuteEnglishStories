package com.ngaid.fiveminenglishstories.tabs;

import com.ngaid.fiveminenglishstories.FireStoreW;
import com.ngaid.fiveminenglishstories.adapters.AdapterForAuthorMenu;
import com.ngaid.fiveminenglishstories.objects.ExtendableItem;
import com.ngaid.fiveminenglishstories.objects.StoriesGS;

import java.util.ArrayList;
import java.util.List;

public class Tab2Authors extends Tab {

    @Override
    public void readDataFromDb(){
        FireStoreW.readData(new FireStoreW.FirestoreCallback() {
            @Override
            public void onCallBackA(ArrayList<ExtendableItem> listA) {
                AdapterForAuthorMenu adapter = new AdapterForAuthorMenu(listA, getContext());
                recyclerView.setAdapter(adapter);
            }
            public void onCallBack(List<StoriesGS> list){}
            public void onCallBackB(ArrayList<ExtendableItem> listB){}
        });
    }
}

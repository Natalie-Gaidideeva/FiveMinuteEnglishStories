package com.ngaid.fiveminenglishstories.adapters;

import android.content.Context;

import com.ngaid.fiveminenglishstories.objects.ExtendableItem;

import java.util.ArrayList;

public class AdapterForAuthorMenu extends RecyclerViewAdapterExpandable {

    public AdapterForAuthorMenu(ArrayList<ExtendableItem> allStoriesList, Context context) {
        this.allStoriesList = allStoriesList;
        this.context = context;
    }

    @Override
    public void setAdditionalField() {
        currentTextView.setText(currentStory.getTitle() + "\n" + currentStory.getGenre());
    }
}

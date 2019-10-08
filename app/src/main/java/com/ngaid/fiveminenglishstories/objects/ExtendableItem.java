package com.ngaid.fiveminenglishstories.objects;

import java.util.ArrayList;

public class ExtendableItem {
    private String parent;
    private ArrayList<StoriesGS> child;

    public ExtendableItem (String parent, ArrayList<StoriesGS> child){
        this.child = child;
        this.parent = parent;
    }

    public ExtendableItem(){}

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public ArrayList getChild() {
        return child;
    }

    public void setChild(ArrayList<StoriesGS> child) {
        this.child = child;
    }

}

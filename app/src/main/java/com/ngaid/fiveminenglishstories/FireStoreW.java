package com.ngaid.fiveminenglishstories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ngaid.fiveminenglishstories.objects.ExtendableItem;
import com.ngaid.fiveminenglishstories.objects.StoriesGS;

import java.util.ArrayList;
import java.util.List;


public class FireStoreW {

    final static String LOG_TAG = "myLogs";
    private static int allStoriesQuantity;

    public static int getQ() {
        return allStoriesQuantity;
    }

    public static void setQ(int q) {
        allStoriesQuantity = q;
    }


    //to prevent asynchronous behavior
    public interface FirestoreCallback{
        void onCallBack(List<StoriesGS> list);
        void onCallBackA(ArrayList<ExtendableItem> listA);
        void onCallBackB(ArrayList<ExtendableItem> listB);
    }

    public interface FirestoreCallback3{
        void onCallBack3(List<StoriesGS> list);
    }

    public static void readData(final FirestoreCallback firestoreCallback){
        final List<StoriesGS> storiesDescriptions = new ArrayList<>();
        final ArrayList<ExtendableItem> storiesDescriptions2 = new ArrayList<>();
        final ArrayList<ExtendableItem> storiesDescriptions3 = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("stories")
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                //Log.d(LOG_TAG, document.getId() + " => " + document.getData());
                                StoriesGS storyDescription = new StoriesGS(document.get("title").toString(),
                                        document.get("author").toString(), document.get("genre").toString(),
                                        Integer.parseInt(document.getId()));
                                storiesDescriptions.add(storyDescription);
                                // building collection for expandable lists
                                if (storiesDescriptions2.size()==0){
                                    ArrayList<StoriesGS> l = new ArrayList<>();
                                    l.add(storyDescription);
                                    ArrayList<StoriesGS> l2 = new ArrayList<>();
                                    l2.add(storyDescription);
                                    ExtendableItem extendableItem = new ExtendableItem(storyDescription.getAuthor(), l);
                                    ExtendableItem extendableItem1 = new ExtendableItem(storyDescription.getGenre(), l2);
                                    storiesDescriptions2.add(extendableItem);
                                    storiesDescriptions3.add(extendableItem1);
                                }
                                else {
                                    boolean yes = false;
                                    boolean yes1 = false;
                                    for (ExtendableItem e : storiesDescriptions2){
                                        if (storyDescription.getAuthor().equals(e.getParent())){
                                            e.getChild().add(storyDescription);
                                            yes = true;
                                        }
                                    }
                                    if (!yes){
                                        ArrayList<StoriesGS> l = new ArrayList<>();
                                        l.add(storyDescription);
                                        ExtendableItem extendableItem = new ExtendableItem(storyDescription.getAuthor(), l);
                                        storiesDescriptions2.add(extendableItem);
                                    }
                                    for (ExtendableItem e : storiesDescriptions3){
                                        if (storyDescription.getGenre().equals(e.getParent())){
                                            e.getChild().add(storyDescription);
                                            yes1 = true;
                                        }
                                    }
                                    if (!yes1){
                                        ArrayList<StoriesGS> l = new ArrayList<>();
                                        l.add(storyDescription);
                                        ExtendableItem extendableItem = new ExtendableItem(storyDescription.getGenre(), l);
                                        storiesDescriptions3.add(extendableItem);
                                    }
                                }

                                Log.d(LOG_TAG, storyDescription.getTitle() + storyDescription.getFBKey());
                                Log.d(LOG_TAG, "length:" + storiesDescriptions.size() + storiesDescriptions2.size() + storiesDescriptions3.size());
                            }
                            firestoreCallback.onCallBack(storiesDescriptions);
                            firestoreCallback.onCallBackA(storiesDescriptions2);
                            firestoreCallback.onCallBackB(storiesDescriptions3);
                            setQ(storiesDescriptions.size());
                        }
                    }
                    //        for (QueryDocumentSnapshot document : task.getResult()) {
                });
    }

    public static void readStory(int id, final FirestoreCallback3 firestoreCallback3){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("stories").document(String.format(Integer.toString(id)));
        final List<StoriesGS> storiesFull = new ArrayList<>();
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                        StoriesGS storyFull = new StoriesGS(document.get("title").toString(),
                                document.get("author").toString(), document.get("genre").toString(),
                                document.get("text").toString());
                        storiesFull.add(storyFull);
                        Log.d(LOG_TAG, "length2:" + storiesFull.size());
                firestoreCallback3.onCallBack3(storiesFull);
            }
        });
    }

}

package com.ngaid.fiveminenglishstories.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ngaid.fiveminenglishstories.Book;
import com.ngaid.fiveminenglishstories.FireStoreW;
import com.ngaid.fiveminenglishstories.R;
import com.ngaid.fiveminenglishstories.objects.ExtendableItem;
import com.ngaid.fiveminenglishstories.objects.StoriesGS;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter2 extends RecyclerView.Adapter<MyRecyclerViewAdapter2.MyViewHolder2> implements View.OnClickListener {
    // makes tree-list with expandable items

    private ArrayList<ExtendableItem> allStoriesList;
    final String LOG_TAG = "myLogs";
    private Context context;
    private boolean ifAuthors;

    public MyRecyclerViewAdapter2(ArrayList<ExtendableItem> allStoriesList, Context context, boolean ifAuthors) {
        this.ifAuthors = ifAuthors;
        this.allStoriesList = allStoriesList;
        this.context = context;
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context context;
        private TextView parentName;
        private LinearLayout childLayout;
        public MyViewHolder2(View v) {
            super(v);
            context = itemView.getContext();
            parentName = (TextView) v.findViewById(R.id.parentName);
            childLayout = (LinearLayout) v.findViewById(R.id.child_layout);
            childLayout.setVisibility(View.GONE);
            //counting max size of children in parents
            int maxNo = 0;
            for (int index = 0; index < allStoriesList.size(); index++) {
                int maxSize = allStoriesList.get(index).getChild().size();
                if (maxSize > maxNo) maxNo = maxSize;
            }
            for (int indexView = 0; indexView < maxNo; indexView++) {   // creating child items for expandable list-items
                TextView textView = new TextView(context);
                textView.setId(indexView);
                textView.setGravity(Gravity.LEFT);
                textView.setTextSize(20);
                textView.setTextColor(Color.parseColor("#272727"));
                textView.setClickable(true);
                textView.setFocusable(true);

                TypedValue backValue = new TypedValue();
                context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, backValue, true);
                textView.setBackgroundResource(backValue.resourceId);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(5, 13, 5, 0);
                childLayout.addView(textView, layoutParams);
            }
            parentName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {    //getting parent-items openable
            if (childLayout.getVisibility() == View.VISIBLE) {
                childLayout.setVisibility(View.GONE);
            } else {
                childLayout.setVisibility(View.VISIBLE);
            }
        }

    }

    // Create parent views (invoked by the layout manager)
    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_extandable_item, parent, false);
        return new MyViewHolder2(itemView);
    }

    // Replace the contents of parent views-items (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder2 holder, int position) {
        ExtendableItem story = allStoriesList.get(position);
        holder.parentName.setText(story.getParent());
        int noOfChildTextViews = holder.childLayout.getChildCount();
        for (int index = 0; index < noOfChildTextViews; index++) {
            TextView currentTextView = (TextView) holder.childLayout.getChildAt(index);
            currentTextView.setVisibility(View.VISIBLE);
        }

        int noOfChild = story.getChild().size();
        if (noOfChild < noOfChildTextViews) {
            for (int index = noOfChild; index < noOfChildTextViews; index++) {
                TextView currentTextView = (TextView) holder.childLayout.getChildAt(index);
                currentTextView.setVisibility(View.GONE);
            }
        }
        for (int textViewIndex = 0; textViewIndex < noOfChild; textViewIndex++) {
            TextView currentTextView = (TextView) holder.childLayout.getChildAt(textViewIndex);
            StoriesGS st = (StoriesGS) story.getChild().get(textViewIndex);
            if (ifAuthors){
                currentTextView.setText(st.getTitle() + "\n" + st.getGenre());
            }
            else {
                currentTextView.setText(st.getTitle() + "\n" + st.getAuthor());
            }
            currentTextView.setId(st.getFBKey());
            currentTextView.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {    // listener for children-items getting story from db
        Log.d(LOG_TAG, "itemN:" + view.getId());

        FireStoreW.readStory(view.getId(), new FireStoreW.FirestoreCallback3(){
            @Override
            public void onCallBack3(List<StoriesGS> list) {
                Book.setMyStory(list.get(0));
                Intent intent = new Intent(context, Book.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allStoriesList.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }
}


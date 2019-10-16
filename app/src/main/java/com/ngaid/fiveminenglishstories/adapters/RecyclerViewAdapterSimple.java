package com.ngaid.fiveminenglishstories.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ngaid.fiveminenglishstories.Book;
import com.ngaid.fiveminenglishstories.FireStoreW;
import com.ngaid.fiveminenglishstories.R;
import com.ngaid.fiveminenglishstories.objects.StoriesGS;

import java.util.List;

public class RecyclerViewAdapterSimple extends RecyclerView.Adapter<RecyclerViewAdapterSimple.ViewHolder> implements View.OnClickListener {

    private List<StoriesGS> allStoriesList;
    private Context context;
    final String LOG_TAG = "myLogs";

    public RecyclerViewAdapterSimple(List<StoriesGS> allStoriesList, Context context) {
        this.allStoriesList = allStoriesList;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView, authorView, genreView;
        private ViewHolder(View v) {
            super(v);
            titleView = (TextView) v.findViewById(R.id.tvTitle);
            authorView = (TextView) v.findViewById(R.id.tvAuthor);
            genreView = (TextView) v.findViewById(R.id.tvGenre);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View item1 = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(item1);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StoriesGS story = allStoriesList.get(position);
        holder.titleView.setText(story.getTitle());
        holder.authorView.setText(story.getAuthor());
        holder.genreView.setText(story.getGenre());
        holder.itemView.setId(story.getFBKey());
        holder.itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {    // listener for items, getting story from db
        Log.d(LOG_TAG, "itemN:" + view.getId());

        FireStoreW.readStory(view.getId(), new FireStoreW.FirestoreCallback3(){
            @Override
            public void onCallBack3(List<StoriesGS> list) {
                Book.setTheStory(list.get(0));
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

package com.ngaid.fiveminenglishstories;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.ngaid.fiveminenglishstories.objects.StoriesGS;

public class Book extends BaseActivity implements View.OnClickListener{

    final static String LOG_TAG = "myLogs";
    private static SharedPreferences sharedPreferences;
    private static Context context;

    // to set new story
    private static StoriesGS s;

    // for menu items
    private static CoordinatorLayout coordinatorLayout;
    private static TextView titleBook;
    private static TextView authorBook;
    private static TextView genreBook;
    private static TextView textBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //setting needed ui
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.book_layout, contentFrameLayout);
        Log.d(LOG_TAG, "Book: onCreate()");

        // setting toolbar with back-arrow
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ImageView backArrow = (ImageView) findViewById(R.id.backButton);
        backArrow.setOnClickListener(this);

        // setting story
        titleBook = findViewById(R.id.book_title);
        Log.d(LOG_TAG, "result:" + titleBook.getText().toString());
        authorBook = findViewById(R.id.book_author);
        genreBook = findViewById(R.id.book_genre);
        textBook = findViewById(R.id.book_text);
        titleBook.setText(s.getTitle());
        authorBook.setText(s.getAuthor());
        genreBook.setText(s.getGenre());
        textBook.setText(("\t" + s.getStory()).replaceAll("\\\\N\\\\N", System.getProperty("line.separator") + System.getProperty("line.separator") + "\t"));

        // recalling user settings
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        setColours();
        setBookFont(PreferencesUtil.getPrefFont(context));
        setBookSize(PreferencesUtil.getPrefSize(context));
    }

    public static StoriesGS getMyStory() {
        return s;
    }

    public static void setMyStory(StoriesGS st) {
        s = st;
        Log.d(LOG_TAG, "result:" + s.getAuthor());
    }   //setting new story


    @Override
    public void onClick(View view) {
        finish();
    }   // for back arrow

    public static void setColours(){
        if (context!=null) {
            coordinatorLayout.setBackgroundColor(Color.parseColor(PreferencesUtil.getPrefColCoordinator(context)));
            authorBook.setTextColor(Color.parseColor(PreferencesUtil.getPrefColText(context)));
            genreBook.setTextColor(Color.parseColor(PreferencesUtil.getPrefColText(context)));
            titleBook.setTextColor(Color.parseColor(PreferencesUtil.getPrefColText(context)));
            textBook.setTextColor(Color.parseColor(PreferencesUtil.getPrefColText(context)));
        }
    }

    public static void setBookFont(String f){
        if (context!=null) {
            Typeface fontStyle = Typeface.createFromAsset(context.getAssets(), f);
            textBook.setTypeface(fontStyle);
        }
    }

    public static void setBookSize(int s){
        if (context!=null) {
            textBook.setTextSize(s);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "Book: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Book: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Book: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "Book: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Book: onDestroy()");
    }

}

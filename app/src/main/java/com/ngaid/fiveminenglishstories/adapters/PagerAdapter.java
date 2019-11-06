package com.ngaid.fiveminenglishstories.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ngaid.fiveminenglishstories.R;
import com.ngaid.fiveminenglishstories.tabs.Tab1All;
import com.ngaid.fiveminenglishstories.tabs.Tab2Authors;
import com.ngaid.fiveminenglishstories.tabs.Tab3Genres;
import com.ngaid.fiveminenglishstories.tabs.Tab4ReadingTime;

public class PagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] tabTitles;


    public PagerAdapter(FragmentManager fm, Context con) {
        super(fm);
        context = con;
    }

    @Override
    public CharSequence getPageTitle(int position){
        tabTitles = context.getResources().getStringArray(R.array.tabNames);
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab1All tab1 = new Tab1All();
                return tab1;
            case 1:
                Tab2Authors tab2 = new Tab2Authors();
                return tab2;
            case 2:
                Tab3Genres tab3 = new Tab3Genres();
                return tab3;
            case 3:
                Tab4ReadingTime tab4 = new Tab4ReadingTime();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}

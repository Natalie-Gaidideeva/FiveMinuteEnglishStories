package com.ngaid.fiveminenglishstories;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.ngaid.fiveminenglishstories.adapters.MyPagerAdapter;
import com.ngaid.fiveminenglishstories.menuItems.FragmentDeveloper;
import com.ngaid.fiveminenglishstories.menuItems.FragmentTextSize;
import com.ngaid.fiveminenglishstories.menuItems.FragmentTextType;
import com.ngaid.fiveminenglishstories.objects.StoriesGS;

import java.util.List;
import java.util.Random;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class BaseActivity extends AppCompatActivity {
    //BaseActivity because of drawer needed in the Book-activity

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawer;
    private AppBarConfiguration appBarConfiguration;
    private Context context;
    final String LOG_TAG = "myLogs";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Log.d(LOG_TAG, "MainActivity: onCreate()");

        //setting toolbar as actionbar and setting title
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);


        // setting tabs with the help of ViewPager (makes them slide) and its adapter (populates pages-fragments inside of a ViewPager)
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout.setupWithViewPager(viewPager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), context);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        adapter.notifyDataSetChanged(); // to update data in fragments
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //making menu-drawer with the help of navigationView (contains menu items)
        drawer = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close); //connects drawer with ActionBar
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();  //Synchronize the state of the drawer indicator/affordance with the linked DrawerLayout

        NavigationView navigationView = findViewById(R.id.navView);
        NavController navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);    //replaces NavHost with drawer
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.menu_item_one, R.id.menu_item_two, R.id.menu_item_three, R.id.menu_item_four,
                R.id.menu_item_five).setDrawerLayout(drawer).build();   //connects all element to make drawer
        setupActionBarWithNavController(this, navController, appBarConfiguration);
        setupWithNavController(navigationView, navController);

        //  Listener for menu items
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.menu_item_one:    //get random story
                        Random ran = new Random();
                        int x = ran.nextInt(FireStoreW.getQ()) + 1;
                        FireStoreW.readStory(x, new FireStoreW.FirestoreCallback3(){
                            @Override
                            public void onCallBack3(List<StoriesGS> list) {
                                Book.setMyStory(list.get(0));
                                Intent intent = new Intent(context, Book.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        });
                        drawer.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.menu_item_two:    //set day/night mode
                        if (!PreferencesUtil.getPrefColour(context)){
                            PreferencesUtil.setPrefColCoordinator(context, "#333333");
                            PreferencesUtil.setPrefColText(context, "#FFFFFF");
                            Book.setColours();
                            PreferencesUtil.setPrefColour(context, true);
                            Toast.makeText(context, "NIGHT MODE", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            PreferencesUtil.setPrefColCoordinator(context, "#fcfce9");
                            PreferencesUtil.setPrefColText(context, "#000000");
                            Book.setColours();
                            PreferencesUtil.setPrefColour(context, false);
                            Toast.makeText(context, "DAY MODE", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu_item_three:  // set text font face
                        FragmentTextType fragmentTextType = new FragmentTextType();
                        fragmentTextType.show(getSupportFragmentManager(), getString(R.string.dialog1));
                        break;
                    case R.id.menu_item_four:   // set text size
                        FragmentTextSize fragmentTextSize = new FragmentTextSize();
                        fragmentTextSize.show(getSupportFragmentManager(), getString(R.string.dialog2));
                        break;
                    case R.id.menu_item_five:   // info about dev
                        FragmentDeveloper fragmentDeveloper = new FragmentDeveloper();
                        fragmentDeveloper.show(getSupportFragmentManager(), getString(R.string.dialog3));
                        drawer.closeDrawer(Gravity.LEFT);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {  //handles the back navigation from drawer to activity
        NavController navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    } // for toggle to inform about config changes

    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    } // syncs the toggle state after onRestoreInstanceState has occurred

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "MainActivity: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "MainActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "MainActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "MainActivity: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MainActivity: onDestroy()");
    }

}

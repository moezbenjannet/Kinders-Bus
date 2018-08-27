package com.supming.test;


import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.supming.test.KinderGarten.DatabaseHelper;
import com.supming.test.fragment_main.Favorite_Main;
import com.supming.test.fragment_main.FragmentAdapter;
import com.supming.test.fragment_main.Home_fragment;
import com.supming.test.fragment_main.Info_Main;

import java.sql.Time;
import java.util.Calendar;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout ;
    Toolbar toolbar;
    ViewPager viewPager ;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imageView = (ImageView) findViewById(R.id.search_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbarsearch);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(null);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        fragmentAdapter.addFragments(new Home_fragment(),"home");
        fragmentAdapter.addFragments(new Info_Main(),"info");
        fragmentAdapter.addFragments(new Favorite_Main(),"favorit");

        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_selected);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_info_black);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_favorite_black);

        tabLayout.addOnTabSelectedListener(listener(viewPager));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_selected);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_info_black);
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_favorite_black);
                        break;
                    case 1:
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_info_black_selected);
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_favorite_black);
                        break;
                    case 2:
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_info_black);
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_favorite_black_selected);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
    public TabLayout.OnTabSelectedListener listener(final ViewPager pager){
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }


}



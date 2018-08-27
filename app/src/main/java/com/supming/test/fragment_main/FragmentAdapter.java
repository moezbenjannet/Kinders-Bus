package com.supming.test.fragment_main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Omar Bouhamed on 08/11/2017.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments =new ArrayList<>();
    ArrayList<String> tabTittles = new ArrayList<>();
    private  HashMap<Integer,String> mFragmentTags;
    private FragmentManager fragmentManager;

    public void addFragments(Fragment fragment, String titles){
        this.fragments.add(fragment);
        this.tabTittles.add(titles);
    }

    public FragmentAdapter(FragmentManager fm){
        super(fm);
        mFragmentTags = new HashMap<Integer,String>();
        fragmentManager = fm ;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
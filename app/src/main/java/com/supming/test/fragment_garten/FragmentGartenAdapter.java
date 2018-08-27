package com.supming.test.fragment_garten;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Omar Bouhamed on 08/11/2017.
 */

public class FragmentGartenAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments =new ArrayList<>();
    ArrayList<String> tabTittles = new ArrayList<>();

    public void addFragments(Fragment fragment, String titles){
        this.fragments.add(fragment);
        this.tabTittles.add(titles);
    }

    public FragmentGartenAdapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTittles.get(position);
    }

    public void setTabTittles(ArrayList<String> tabTittles) {
        this.tabTittles = tabTittles;
    }
}
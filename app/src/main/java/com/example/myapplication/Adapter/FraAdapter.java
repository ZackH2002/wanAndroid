package com.example.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FraAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> titles;



    public FraAdapter(FragmentManager fragmentManager, List<Fragment> fragments, List<String>titles) {
        super(fragmentManager);
        this.mFragments = fragments;
        this.titles=titles;

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}

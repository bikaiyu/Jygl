package com.kyle.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kyle.fragment.HomePageFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> list;
    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
        list.add(new HomePageFragment());
        list.add(new HomePageFragment());
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle=new Bundle();
        bundle.putInt("position", position);
        Fragment fragment = list.get(position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

package com.kyle.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kyle.config.Config;
import com.kyle.fragment.InformationFragment;

/**
 * Created by Administrator on 2015/3/26.
 */
public class Info_viewPager_Adapter extends FragmentPagerAdapter {
    private String[] title_name,title_id;

    public Info_viewPager_Adapter(FragmentManager fm, String[] title_name, String[] title_id) {
        super(fm);
        this.title_name = title_name;
        this.title_id = title_id;
    }

    public Info_viewPager_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        InformationFragment fragment = new InformationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sa", title_id[position]);
        bundle.putInt("start_type", Config.INFO);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return title_name.length;
    }
    //设置头菜单上的文字
    @Override
    public CharSequence getPageTitle(int position) {
        return title_name[position];
    }


}

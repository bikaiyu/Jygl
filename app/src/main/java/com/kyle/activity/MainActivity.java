package com.kyle.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import com.kyle.adapter.HomeViewPagerAdapter;

import static com.kyle.helper.BitmapHelper.initUtils;


public class MainActivity extends FragmentActivity {

    private ViewPager viewPager_main_content;
    private TextView textView_main_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUtils(this);
        initView();
        initData();
    }
    private void initView(){
        viewPager_main_content = (ViewPager)findViewById(R.id.viewPager_main_content);
        textView_main_page = (TextView)findViewById(R.id.textView_main_page);
    }
    private void initData(){
        viewPager_main_content.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager()));
        viewPager_main_content.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                textView_main_page.setText((position+1)+"/2");
            }
            @Override
            public void onPageSelected(int position) {}
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}

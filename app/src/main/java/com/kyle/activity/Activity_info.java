package com.kyle.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kyle.adapter.Info_viewPager_Adapter;
import com.kyle.view.SlidingTabLayout;
import com.lidroid.xutils.view.annotation.ViewInject;


public class Activity_info extends ActionBarActivity implements View.OnClickListener {
    private TextView title;
    private ViewPager pager;

    private SlidingTabLayout silding_tab_title;
    private String[] title_name;
    private String[] title_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        title_name = getResources().getStringArray(R.array.info_menu);
        title_id = getResources().getStringArray(R.array.info_menu_id);
        //修改标题
        title = ((TextView) findViewById(R.id.textView_title_show));
        ((ImageView)findViewById(R.id.imageView_title_back)).setOnClickListener(this);
        title.setText("旅游信息");
        RelativeLayout layout_title = (RelativeLayout)findViewById(R.id.layout_title);
        layout_title.setBackgroundResource(R.mipmap.banner_hong);

        silding_tab_title = ((SlidingTabLayout) findViewById(R.id.sliding_actinfo_titlemenu));
        pager = ((ViewPager) findViewById(R.id.actinfo_viewpager));
        Info_viewPager_Adapter adapter = new Info_viewPager_Adapter(getSupportFragmentManager(),title_name,title_id);
        pager.setAdapter(adapter);
        silding_tab_title.setViewPager(pager);
    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}

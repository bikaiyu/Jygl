package com.kyle.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.kyle.adapter.Recom_detailAdpater;
import com.kyle.bean.Beans;
import com.kyle.helper.BitmapHelper;
import com.kyle.utils.UrlPath;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity_recomm extends FragmentActivity implements PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener{
    private Recom_detailAdpater adapter;
    private HttpUtils http;
    @ViewInject(R.id.refresh_detail)
    private PullToRefreshListView refresh_detail;
    private int offset = 0;
    private int count = 15;
    private String ztid;
    private String icon;
    private String des;
    private String title;
    private List<Beans> beans;
    private View head;
    private ImageView imageView_recomhead_icon;
    private TextView textView_recomhead_title;
    private TextView textView_recomhead_des;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomm_detail);

        ztid = getIntent().getExtras().getString("id");
        icon=getIntent().getExtras().getString("icon");
        des=getIntent().getExtras().getString("des");
        title=getIntent().getExtras().getString("title");

        Log.e("ztid", "==========================" + ztid);
        Toast.makeText(this, "跳转成功"+ztid, Toast.LENGTH_SHORT).show();

        ViewUtils.inject(this);

        head = LayoutInflater.from(this).inflate(R.layout.header_recom, null);
        imageView_recomhead_icon= (ImageView) head.findViewById(R.id.imageView_recomhead_icon);
        textView_recomhead_title= (TextView) head.findViewById(R.id.textView_recomhead_title);
        textView_recomhead_des= (TextView) head.findViewById(R.id.textView_recomhead_des);

        ListView refreshableView = refresh_detail.getRefreshableView();
        //添加list的头
        refreshableView.addHeaderView(head);
        adapter = new Recom_detailAdpater(this, new ArrayList<Beans>());
        http = new HttpUtils();
        refresh_detail.setAdapter(adapter);
        refresh_detail.setMode(PullToRefreshBase.Mode.BOTH);
        refresh_detail.setOnRefreshListener(this);
        refresh_detail.setOnItemClickListener(this);
        loadData();

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        adapter.clear();
        offset = 0;
        loadData();
        refresh_detail.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        offset = offset + count;
        loadData();
        refresh_detail.onRefreshComplete();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
        intent.setClass(DetailActivity_recomm.this, Activity_information_Detail.class);
        Bundle bundle=new Bundle();
        bundle.putString("id", beans.get(position).getId()+"");
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void loadData() {
        textView_recomhead_title.setText(title);
        textView_recomhead_des.setText(des);
        BitmapHelper.getUtils().display(imageView_recomhead_icon, UrlPath.picturehome + icon,
                new BitmapLoadCallBack<ImageView>(){

                    @Override
                    public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                        imageView.setImageBitmap(bitmap);

                    }

                    @Override
                    public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {
                        imageView.setImageDrawable(drawable);
                    }
                });



        http.send(HttpRequest.HttpMethod.GET, UrlPath.getRecom_detail(offset, count, ztid), new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject object = new JSONObject(responseInfo.result);
                    JSONArray list = object.getJSONArray("list");
                    beans = new ArrayList<Beans>();
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject data = list.getJSONObject(i);
                        Beans bean = new Beans();
                        bean.setId(data.getInt("id"));
                        bean.setAdddate(data.getString("adddate"));
                        bean.setDes(data.getString("des"));
                        bean.setTitle(data.getString("title"));
                        bean.setIcon(data.getString("icon"));
                        beans.add(bean);
                    }
                    Log.d("beans", "-------" + beans);
                    adapter.addAll(beans);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(HttpException e, String s) {
                refresh_detail.setRefreshing(false);
            }
        });
    }
}

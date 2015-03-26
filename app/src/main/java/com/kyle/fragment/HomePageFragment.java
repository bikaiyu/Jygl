package com.kyle.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kyle.activity.Activity_cityLook;
import com.kyle.activity.Activity_info;
import com.kyle.activity.Activity_information;
import com.kyle.activity.Activity_map;
import com.kyle.activity.Activity_more;
import com.kyle.activity.Activity_phone;
import com.kyle.activity.Activity_recom;
import com.kyle.activity.Activity_strategy;
import com.kyle.activity.Activity_traffic;
import com.kyle.activity.Activity_weather;
import com.kyle.activity.R;
import com.kyle.helper.BitmapHelper;
import com.kyle.utils.UrlPath;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

public class HomePageFragment extends Fragment implements View.OnClickListener{


    private int page;

    private TextView textView_item_weather;
    private ImageView imageView_item_strategy;
    private ImageView imageView_item_cityLook;
    private ImageView imageView_item_information;
    private ImageView imageView_item_weather;
    private ImageView imageView_item_recom;
    private ImageView imageView_item2_info;
    private ImageView imageView_item2_map;
    private ImageView imageView_item2_move;
    private ImageView imageView_item2_phone;
    private ImageView imageView_item2_traffic;
    private BitmapUtils bitmapUtils;
    private Handler handler = new Handler();
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        bitmapUtils = BitmapHelper.getUtils();
        page=getArguments().getInt("position");

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view=null;
        if(page==0){
            view= inflater.inflate(R.layout.item_homepage_fragment, container, false);
        }else if(page==1){
            view= inflater.inflate(R.layout.item_homepage2_fragment, container, false);
        }
        initView(view);
        initDate();
        return view;
    }

    //初始化控件
    private void initView(View view){
        //推荐(头条)
            imageView_item_recom = (ImageView)view.findViewById(R.id.imageView_item_recom);
           //攻略
            imageView_item_strategy = (ImageView) view.findViewById(R.id.imageView_item_strategy);
           //资讯
            imageView_item_information = (ImageView)view.findViewById(R.id.imageView_item_information);
           //天气
            imageView_item_weather = (ImageView)view.findViewById(R.id.imageView_item_weather);
           //城市风貌
            imageView_item_cityLook = (ImageView)view.findViewById(R.id.imageView_item_cityLook);
            textView_item_weather =(TextView) view.findViewById(R.id.textView_item_weather);
            imageView_item2_info = (ImageView)view.findViewById(R.id.imageView_item2_info);
            imageView_item2_map = (ImageView)view.findViewById(R.id.imageView_item2_map);
            imageView_item2_move = (ImageView)view.findViewById(R.id.imageView_item2_move);
            imageView_item2_phone =(ImageView) view.findViewById(R.id.imageView_item2_phone);
            imageView_item2_traffic = (ImageView)view.findViewById(R.id.imageView_item2_traffic);
        getAnimation(imageView_item_recom,R.drawable.shouye1);
    }
    //为控件初始化数据
    private void initDate(){
        if(page==0) {
            imageRequest(UrlPath.getTitlePicture(), imageView_item_recom, R.drawable.shouye1);
            imageRequest(UrlPath.getStrategyPicture(), imageView_item_strategy, R.drawable.shouye2);
            imageRequest(UrlPath.getInfoPicture(), imageView_item_information, R.drawable.shouye3);
            imageView_item_weather.setImageResource(R.drawable.shouye4);
            imageView_item_weather.setOnClickListener(this);
            imageRequest(UrlPath.getCityPicture(), imageView_item_cityLook, R.drawable.shouye5);
            textView_item_weather.setText("北京");
        }else if(page==1) {
            imageRequest(UrlPath.getTravelPicture(), imageView_item2_info, R.drawable.shouye8);
            imageRequest(UrlPath.getMapPicture(), imageView_item2_map, R.drawable.shouye7);
            imageRequest(UrlPath.getTraggicPicture(), imageView_item2_traffic, R.drawable.shouye6);
            imageView_item2_phone.setImageResource(R.drawable.shouye9);
            imageView_item2_phone.setOnClickListener(this);
            imageView_item2_move.setImageResource(R.drawable.shouye10);
            imageView_item2_move.setOnClickListener(this);
        }
    }
    //网络加载图片
    private void imageRequest(String url,ImageView view,int imageId){
        view.setOnClickListener(this);
        bitmapUtils.display(view, url, new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {

                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {
                imageView.setImageResource(R.drawable.empty);
            }
        });

    }
    private void getAnimation(final ImageView imageView, final int imageId){
        new Thread(){
            @Override
            public void run() {
                while (imageView != null) {
                    TranslateAnimation animation = new TranslateAnimation(0, imageView.getWidth(), 0, 0);
                    animation.setDuration(5000);
                    imageView.setAnimation(animation);
                    animation.setRepeatCount(Animation.INFINITE);
                    try {
                        Thread.sleep(10000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageResource(imageId);
                        }
                    });
                }
            }
        }.start();
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.imageView_item_recom://推荐
                intent.setClass(getActivity(), Activity_recom.class);
                Toast.makeText(getActivity(),""+view.getId(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_item_strategy://攻略
                intent.setClass(getActivity(), Activity_strategy.class);
                Toast.makeText(getActivity(),""+view.getId(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_item_information://资讯
                intent.setClass(getActivity(), Activity_information.class);
                Toast.makeText(getActivity(),""+view.getId(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_item_weather://天气
                intent.setClass(getActivity(), Activity_weather.class);
                Toast.makeText(getActivity(),""+view.getId(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_item_cityLook://城市面貌
                intent.setClass(getActivity(), Activity_cityLook.class);
                Toast.makeText(getActivity(),""+view.getId(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_item2_info://旅游信息
                intent.setClass(getActivity(), Activity_info.class);
                Toast.makeText(getActivity(),""+view.getId(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_item2_map://地图
                intent.setClass(getActivity(), Activity_map.class);
                Toast.makeText(getActivity(),""+view.getId(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_item2_phone://电话
                intent.setClass(getActivity(), Activity_phone.class);
                Toast.makeText(getActivity(),""+view.getId(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_item2_traffic://交通
                intent.setClass(getActivity(), Activity_traffic.class);
                Toast.makeText(getActivity(),""+view.getId(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.imageView_item2_move://更多
                intent.setClass(getActivity(), Activity_more.class);
                Toast.makeText(getActivity(),""+view.getId(),Toast.LENGTH_SHORT).show();
                break;
        }
        startActivity(intent);
    }
}

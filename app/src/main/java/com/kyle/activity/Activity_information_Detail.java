package com.kyle.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kyle.helper.SDCardHelper;
import com.kyle.utils.UrlPath;
import com.kyle.view.MyWebView;

public class Activity_information_Detail extends ActionBarActivity {
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);

        String id = getIntent().getExtras().getString("id");
        Log.e("id",id);
        String urlInformationDetail = UrlPath.getUrlInformationDetail(id);
        webview = (WebView)findViewById(R.id.fragment_information_Detai_webview);
        webview.loadUrl(urlInformationDetail);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setAppCachePath(SDCardHelper.getSDCardCachePath(this));
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(this, "loadhtml");
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient(){
            //让其拥有进度条
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                setProgress(newProgress*100);
            }
        });

    }
    public void articleOptions(String s1,String s2,String s3,String s4){
        Log.d("模式编码==1", s1);
        Log.d("城市:", s2);
        Log.d("经纬度", s3);
        Log.d("jd", s4);
    }

    public void articleOptions(String s1,String s2){
        Log.d("模式编码==2", s1);
        Log.d("电话号码 ", s2);

        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + s2));
        startActivity(intent);

    }
    public void articleOptions(String s1){
        Log.d("单个参数", s1);


    }


}

package com.kyle.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.DownloadListener;

import com.kyle.activity.R;
import com.kyle.utils.UrlPath;
import com.kyle.view.MyWebView;

public class WebActivity extends Activity {

    private MyWebView webview;
    private String url;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        String id = getIntent().getExtras().getString("id");
        Log.e("id", id);
        url = UrlPath.getUrlInformationDetail(id);
        // ~~~ 获取参数
//        url = getIntent().getStringExtra("url");
//        name = getIntent().getStringExtra("name");

        // ~~~ 绑定控件
        webview = (MyWebView) findViewById(R.id.webview);

        // ~~~ 设置数据
//        titleText.setText(name);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                if (url != null && url.startsWith("http://"))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });

        webview.loadUrl(url);
    }
}
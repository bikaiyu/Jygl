package com.kyle.fragment;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.media.MediaRouter;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kyle.activity.R;
import com.kyle.utils.UrlPath;

public class InformationFragment_detail extends Fragment {

    private WebView webview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_information_fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments().getString("id");
        String urlInformationDetail = UrlPath.getUrlInformationDetail(id);
        webview = ((WebView) view.findViewById(R.id.fragment_information_Detai_webview));
        webview.loadUrl(urlInformationDetail);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(this, "loadhtml");
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                getActivity().setProgress(newProgress*100);
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void articleOptions(String s1,String s2,String s3,String s4){
        Log.d("articleOptions", s1);
        Log.d("articleOptions", s2);
        Log.d("articleOptions", s3);
        Log.d("articleOptions", s4);
    }



}

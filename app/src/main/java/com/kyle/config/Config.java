package com.kyle.config;

import android.app.Application;

import com.kyle.helper.SDCardHelper;

/**
 * Created by Administrator on 2015/3/24.
 */
public class Config extends Application {
    //刷新
    public static final int REFRESH = 1;
    //加载下一页
    public static final int LOAD_MORE = 0;
    //webview缓存目录为 SD卡私有目录
    //SDCardHelper.getSDCardCachePath(this)
    //启动标识位
    public static final int INFORM = 1;
    public static final int INFO = 2;


}

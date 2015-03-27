package com.kyle.helper;

import android.content.Context;
import android.os.Environment;


import com.kyle.activity.R;
import com.lidroid.xutils.BitmapUtils;

import java.io.File;

public class BitmapHelper {
	private static BitmapUtils utils;
	public static void initUtils(Context context){
		utils = new BitmapUtils(context,
				new File(Environment.getExternalStorageDirectory(), "Beijing").getAbsolutePath(),
				0.25f);

		utils.configDefaultLoadingImage(R.mipmap.londing);
		utils.configDefaultLoadFailedImage(R.mipmap.londfaild);
		utils.configDefaultBitmapMaxSize(800,800);

		utils.configDefaultCacheExpiry(1000 * 60 * 60 * 24);

		utils.configDefaultConnectTimeout(5000);

		utils.configDefaultReadTimeout(10000);
	}
	public static BitmapUtils getUtils() {
		return utils;
	}
	
}

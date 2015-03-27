package com.kyle.adapter;

import com.kyle.activity.R;
import com.kyle.bean.Beans;
import com.kyle.helper.BitmapHelper;
import com.kyle.utils.UrlPath;
import com.lidroid.xutils.ViewUtils;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;


public class RecomAdapter extends BaseAdapter {
    private Context context;
    private List<Beans> list;

    public RecomAdapter(Context context, List<Beans> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
           convertView= LayoutInflater.from(context) .inflate(R.layout.item_recommended,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder mholder= (ViewHolder) convertView.getTag();
        Beans bean=list.get(position);
        mholder.textView.setText(bean.getTitle());

        BitmapHelper.getUtils().display(mholder.imageView, UrlPath.picturehome + bean.getIcon(),
            new BitmapLoadCallBack<ImageView>(){

                @Override
                public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                    //int width=bitmap.getWidth();
                   // Log.d("String","=================="+s);
                    //Bitmap bitmap1=Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
                    imageView.setImageBitmap(bitmap);

                }

                @Override
                public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {
                    imageView.setImageDrawable(drawable);
                }
            });
        return convertView;

    }


    public void addAll(List<Beans> beans){
        list.addAll(beans);
        notifyDataSetChanged();
    }

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }


    public void data(){

    }

    public static class ViewHolder{
        private View itemView;
        @ViewInject(R.id.item_image)
        private ImageView imageView;
        @ViewInject(R.id.item_text)
        private TextView textView;

        public ViewHolder(View itemView){
            ViewUtils.inject(this, itemView);
        }
    }
}

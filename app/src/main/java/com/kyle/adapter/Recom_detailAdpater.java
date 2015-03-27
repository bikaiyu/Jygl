package com.kyle.adapter;

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

import com.kyle.activity.R;
import com.kyle.bean.Beans;
import com.kyle.helper.BitmapHelper;
import com.kyle.utils.UrlPath;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by ADMIN on 2015/3/25.
 */
public class Recom_detailAdpater extends BaseAdapter{
    private Context context;
    private List<Beans> list;

    public Recom_detailAdpater(Context context, List<Beans> list){
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
            convertView= LayoutInflater.from(context) .inflate(R.layout.item_detail_fragment,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder mholder= (ViewHolder) convertView.getTag();
        Beans bean=list.get(position);
        mholder.textView_detail_title.setText(bean.getTitle());
        mholder.textView_detail_des.setText(bean.getDes());
        mholder.textView_detail_adddate.setText(bean.getAdddate());


        BitmapHelper.getUtils().display(mholder.imageView_fragment_detail, UrlPath.picturehome + bean.getIcon(),
                new BitmapLoadCallBack<ImageView>(){

                    @Override
                    public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                        Log.d("String", "==================" + s);
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


    public static class ViewHolder{
        private View itemView;
        @ViewInject(R.id.imageView_fragment_detail)
        private ImageView imageView_fragment_detail;
        @ViewInject(R.id.textView_detail_title)
        private TextView textView_detail_title;
        @ViewInject(R.id.textView_detail_des)
        private TextView textView_detail_des;
        @ViewInject(R.id.textView_detail_adddate)
        private TextView textView_detail_adddate;

        public ViewHolder(View itemView){
            ViewUtils.inject(this, itemView);
        }
    }
}

package com.kyle.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kyle.activity.R;
import com.kyle.bean.Beans;
import com.kyle.helper.BitmapHelper;
import com.kyle.utils.UrlPath;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {
    private final BitmapUtils bitmapUtils;
    private Context context;
    private List<Beans> list;
    private View.OnClickListener listener;

    public InformationAdapter(Context context,List<Beans> list,View.OnClickListener listener) {
        this.context = context;
        this.list = list;
        bitmapUtils = BitmapHelper.getUtils();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_fragment_information,parent,false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text_item_title.setText(list.get(position).getTitle());
        holder.text_item_des.setText(list.get(position).getDes());
        holder.text_item_adddate.setText(list.get(position).getAdddate());
        String imageUrl = UrlPath.picturehome + list.get(position).getIcon();


        bitmapUtils.display(holder.imageview_item_icon, imageUrl
                , new BitmapLoadCallBack<ImageView>() {

            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                imageView.setImageBitmap(bitmap);
            }
            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {
                imageView.setImageResource(R.drawable.listitem_tjicon_bg_h);
            }
        }
        );

    }
    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView text_item_title;
        private TextView text_item_des;
        private TextView text_item_adddate;
        private ImageView imageview_item_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            text_item_title = ((TextView) itemView.findViewById(R.id.text_item_title));
            text_item_des = ((TextView) itemView.findViewById(R.id.text_item_des));
            text_item_adddate = ((TextView) itemView.findViewById(R.id.text_item_adddate));
            imageview_item_icon = ((ImageView) itemView.findViewById(R.id.imageView_item_information_icon));
        }
    }

    public void addAll(List<Beans> newlist){
        int size = list.size();
        list.addAll(newlist);
        notifyItemRangeInserted(size, newlist.size());
    }
    public void clear(){
        bitmapUtils.cancel();
        int size = list.size();
        list.clear();
        notifyItemRangeRemoved(0, size);

    }
}

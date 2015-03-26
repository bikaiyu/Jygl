package com.kyle.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.kyle.activity.R;

/**
 * Created by Administrator on 2015/3/13.
 */
public class pullRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public pullRefreshRecyclerView(Context context) {
        super(context);
    }

    public pullRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public pullRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public pullRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }
    //获得滚动的方向,水平或垂直
    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }
    //创建一个包含的对象(RecyclerView)
    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView view = new RecyclerView(context,attrs);
        view.setId(R.id.recycleView);
        return view;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        RecyclerView recycler = getRefreshableView();
        //获得展示的最后一个item
        View view = recycler.getChildAt(recycler.getChildCount()-1);
        //获得总的item个数
        int count = recycler.getAdapter().getItemCount();
        //如果展示出来了最后一个item
        if(recycler.getChildPosition(view) == count -1){
            // 如果这个容器的底边高度 和 展示项总和的高度 相等那么就到底了..
            if(view==null || recycler==null)
                return true;
            return view.getBottom()==recycler.getHeight();
            //recycler.getHeight() 获得屏幕上展示容器的高度.在android中Y轴是向下生长..
            //view.getBottom() 是最后一条item的底部的位置,如果底部的位置和容器的底部重合了,那么就展示完全了
        }

        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        RecyclerView recycler = getRefreshableView();
        View view = recycler.getChildAt(0);
        if(recycler.getChildPosition(view) == 0){
            //显示的第一个view显示的第一条数据
            return view.getTop()==0;
        }
        return false;
    }
}

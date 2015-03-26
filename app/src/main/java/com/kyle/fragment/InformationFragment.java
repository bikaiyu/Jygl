package com.kyle.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.kyle.activity.Activity_information_Detail;
import com.kyle.activity.R;
import com.kyle.adapter.InformationAdapter;
import com.kyle.bean.Beans;
import com.kyle.config.Config;
import com.kyle.utils.UrlPath;
import com.kyle.view.pullRefreshRecyclerView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class InformationFragment extends Fragment implements View.OnClickListener,PullToRefreshBase.OnRefreshListener2<RecyclerView> {

    private pullRefreshRecyclerView pullturefreshview;
    private LinearLayoutManager layout;
    private RecyclerView recyclerView;
    private TextView title;
    private List<Beans> list;
    private ProgressBar emptyProgressbar;
    private InformationAdapter adapter;
    private int page =1 ;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case Config.REFRESH:
//                    adapter.clear();
                    load(Config.REFRESH);
                    break;
                case Config.LOAD_MORE:
                    load(Config.LOAD_MORE);
                    break;
            }
        }
    };
    private ProgressBar progress_network;
    private int start_type = 0;
    private String sa;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list = new ArrayList<>();
//        start_type = getArguments().getInt("typeId", Config.INFORM);
        sa = getArguments().getString("sa","news");
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //修改标题
//        title = ((TextView) view.findViewById(R.id.textView_title_show));
//        ((ImageView) view.findViewById(R.id.imageView_title_back)).setOnClickListener(this);
//        title.setText("资讯");
//        RelativeLayout layout_title = (RelativeLayout) view.findViewById(R.id.layout_title);
//        layout_title.setBackgroundResource(R.mipmap.banner_zx);
//        progress_network = ((ProgressBar) view.findViewById(R.id.progressBar_networking));
//        progress_network.setVisibility(View.GONE);
        //列表
        pullturefreshview = ((pullRefreshRecyclerView)view.findViewById(R.id.recycle));
        pullturefreshview.setMode(PullToRefreshBase.Mode.BOTH);
        pullturefreshview.setOnRefreshListener(this);
        layout = new LinearLayoutManager(getActivity());

        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = pullturefreshview.getRefreshableView();

        recyclerView.setLayoutManager(layout);


        //item项的点击事件
        adapter = new InformationAdapter(getActivity(),list, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildPosition(v);
               // Toast.makeText(getActivity(),""+list.get(position).getTitle(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), Activity_information_Detail.class);
                //Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("id",list.get(position).getId()+"");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        emptyProgressbar = (ProgressBar) view.findViewById(R.id.progressBar);
        load(0);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),"单击了",Toast.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 1;
        handler.sendEmptyMessage(Config.REFRESH);
        pullturefreshview.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        handler.sendEmptyMessage(Config.LOAD_MORE);
        pullturefreshview.onRefreshComplete();

    }

    public void load(final int type){
        HttpUtils http = new HttpUtils(1000 * 15);
        String news = UrlPath.getUrlInformation(sa, page);
        Log.e("资讯页获取Item项", news);
//        progress_network.setVisibility(View.VISIBLE);
        http.send(HttpRequest.HttpMethod.GET,//模式
                news,//url
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
//                    Log.e("网络访问成功",responseInfo.result);
                        List<Beans> list = new ArrayList<Beans>();
                        try {
                            JSONArray jsonArray = new JSONObject(responseInfo.result).getJSONArray("list");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                Beans beans = new Beans();
                                beans.setAdddate(obj.getString("adddate"));
                                beans.setDes(obj.getString("des"));
                                beans.setIcon(obj.getString("icon"));
                                beans.setTitle(obj.getString("title"));
                                beans.setId(obj.getInt("id"));
                                list.add(beans);
                            }
                            emptyProgressbar.setVisibility(View.GONE);
                            //如果标记位 = refresh 则先清空一次缓存
                            //之所以在这里写,因为如果清空旧数据后新数据还没来,就会白屏..
                            if (type == Config.REFRESH)
                                adapter.clear();
                            adapter.addAll(list);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        progress_network.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                        Log.e("网络错误", s);
                        emptyProgressbar.setVisibility(View.GONE);
                    }
                }
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }
}

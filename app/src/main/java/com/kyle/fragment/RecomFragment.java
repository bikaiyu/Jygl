package com.kyle.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.kyle.activity.Activity_recom;
import com.kyle.activity.DetailActivity_recomm;
import com.kyle.activity.MainActivity;
import com.kyle.activity.R;
import com.kyle.adapter.RecomAdapter;
import com.kyle.bean.Beans;
import com.kyle.helper.BitmapHelper;
import com.kyle.utils.UrlPath;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class RecomFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2<ListView>,AdapterView.OnItemClickListener {

    private RecomAdapter adapter;
    private HttpUtils http;
    @ViewInject(R.id.refresh)
    private PullToRefreshListView refresh;
    private int offset=0;
    private int count=15;
    private List<Beans> beans;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout,container,false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewUtils.inject(this, view);
        BitmapHelper.initUtils(getActivity());
        beans = new ArrayList<Beans>();
        adapter=new RecomAdapter(getActivity(),beans);
        http=new HttpUtils();


        refresh.setAdapter(adapter);
        refresh.setMode(PullToRefreshBase.Mode.BOTH);
        refresh.setOnRefreshListener(this);
        refresh.setOnItemClickListener(this);
        loadData();

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        adapter.clear();
        offset=0;
        loadData();
        refresh.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        offset=offset+count;
        loadData();
        refresh.onRefreshComplete();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
        intent.setClass(getActivity(), DetailActivity_recomm.class);
        Bundle bundle=new Bundle();
        Log.e("bans.size","==>"+beans.size());
        Log.e("position","==>"+position);
        bundle.putString("id", beans.get(position-1).getId()+"");
        bundle.putString("icon",beans.get(position-1).getIcon());
        bundle.putString("des",beans.get(position-1).getDes());
        bundle.putString("title",beans.get(position-1).getTitle());
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void loadData() {

        http.send(HttpRequest.HttpMethod.GET, UrlPath.getRecommended(offset, count), new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject object = new JSONObject(responseInfo.result);
                    JSONArray list = object.getJSONArray("list");
                    List<Beans> temp = new ArrayList<Beans>();
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject data = list.getJSONObject(i);
                        Beans bean = new Beans();
                        bean.setId(data.getInt("id"));
                        bean.setAdddate(data.getString("adddate"));
                        bean.setDes(data.getString("des"));
                        bean.setTitle(data.getString("title"));
                        bean.setIcon(data.getString("icon"));
                        temp.add(bean);
                    }
                    adapter.addAll(temp);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                refresh.onRefreshComplete();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
//                refresh.setRefreshing(false);
                refresh.onRefreshComplete();
            }
        });
    }


}

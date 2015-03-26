package com.kyle.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.kyle.activity.DetailActivity_recomm;
import com.kyle.activity.R;
import com.kyle.adapter.RecomAdapter;
import com.kyle.bean.Beans;
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

public class RecomFragment extends Fragment implements PullToRefreshBase.OnRefreshListener2<ListView>,AdapterView.OnItemClickListener {

    private RecomAdapter adapter;
    private HttpUtils http;
    @ViewInject(R.id.refresh)
    private PullToRefreshListView refresh;
    private int offset=0;
    private int count=10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout,container,false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewUtils.inject(this,view);
        adapter=new RecomAdapter(getActivity(),new ArrayList<Beans>());
        http=new HttpUtils();
        refresh.setAdapter(adapter);
        refresh.setMode(PullToRefreshBase.Mode.BOTH);
        refresh.setOnRefreshListener(this);
        refresh.setOnItemClickListener(this);

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        adapter.clear();
        offset=0;
        loadData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        offset=offset+count;
        loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
        intent.setClass(getActivity(),DetailActivity_recomm.class);
        Bundle bundle=new Bundle();
        bundle.putString("id",id+"");
        intent.putExtras(bundle);

    }

    private void loadData() {
        http.send(HttpRequest.HttpMethod.GET, UrlPath.getRecommended(offset, count), new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    JSONObject object=new JSONObject(responseInfo.result);
                    JSONArray list=object.getJSONArray("list");
                    List<Beans> beans=new ArrayList<Beans>();
                    for(int i=0;i<list.length();i++){
                        JSONObject data=list.getJSONObject(i);
                        Beans bean=new Beans();
                        bean.setId(data.getInt("id"));
                        bean.setAdddate(data.getString("adddate"));
                        bean.setDes(data.getString("des"));
                        bean.setTitle(data.getString("title"));
                        bean.setIcon(data.getString("icon"));
                        beans.add(bean);
                    }
                    adapter.addAll(beans);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                refresh.setRefreshing(false);
            }
        });
    }
}

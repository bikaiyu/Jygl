package com.kyle.utils;

import android.util.Log;

import com.kyle.bean.Tel;
import com.kyle.bean.TelList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/26.
 */
public class JsonTel {

    public static List<TelList> paseJsonToList(String json){
        List<TelList> list = new ArrayList<>();
        int count = 0;
        try {
            JSONArray jsonArray = new JSONObject(json).getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                TelList tellist = new TelList();
                JSONObject jsonobject = jsonArray.getJSONObject(i);
                tellist.setTitle(jsonobject.getString("title"));
                JSONArray jaNumbers = jsonobject.getJSONArray("numbers");
                for (int j = 0; j <jaNumbers.length(); j++) {
                    JSONObject jsonObject = jaNumbers.getJSONObject(j);
                    Tel tel = new Tel();
                    tel.setName(jsonObject.getString("name"));
                    tel.setTel(jsonObject.getString("tel"));
                    tellist.getNumber().add(tel);
                    count++;
                }

                list.add(tellist);

            }
            Log.e("JsonTel", "list.size:==>" + list.size());
            Log.e("JsonTel", "all.size:==>" + count);
            return list;
        } catch (JSONException e) {
            e.printStackTrace();

        }

    return  null;
    }

}

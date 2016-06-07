package com.marco.www.mvp_mysample_demo.model;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by pc on 2016/6/7.
 */
public class RoadDataListModelImpl implements RoadDataListModel
{

    ArrayList<String> list;

    @Override
    public void GetRoadList(final String road_url, final String type, final GetRoadDataListenter listener)
    {
        OkHttpUtils.get().url(road_url +"?Type="+type).build().execute(new MyCallback(listener));
    }

    public interface GetRoadDataListenter
    {
        void onSuccess(List<String> mRoadList);

        void onError(Exception e);
    }

    private class MyCallback extends Callback
    {

        GetRoadDataListenter listener;

        public MyCallback(GetRoadDataListenter listener)
        {
            super();
            this.listener = listener;
        }

        @Override
        public Object parseNetworkResponse(Response response) throws Exception
        {
            String json = response.body().string();
            JSONObject jsonObject = new JSONObject(json);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray array = result.getJSONArray("RoadName");

            list = new ArrayList<String>();

            for (int i = 0; i < array.length(); i++)
            {
                list.add((String) array.opt(i));
            }

            listener.onSuccess(list);
            return list;
        }

        @Override
        public void onError(Call call, Exception e)
        {
            listener.onError(e);
        }

        @Override
        public void onResponse(Object response)
        {

        }
    }
}

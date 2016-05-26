package com.jack.www.volley.app;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by pc on 2016/5/26.
 */
public class MyApplication extends Application
{

    //请求队列
    public static RequestQueue mRequestQueue;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueues()
    {
        return mRequestQueue;
    }

}

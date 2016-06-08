package com.marco.www.mvp_mysample_demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.marco.www.mvp_mysample_demo.Adapter.MyAdapter;
import com.marco.www.mvp_mysample_demo.persenter.RoadDataListPersenter;
import com.marco.www.mvp_mysample_demo.persenter.RoadDataListPersenterImpl;
import com.marco.www.mvp_mysample_demo.view.JsonShowInText;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements JsonShowInText, SwipeRefreshLayout.OnRefreshListener
{

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.sw_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private RoadDataListPersenter mRoadDataListPersenter;
    private LinearLayoutManager mLayoutManager;
    String mCurrentPage;
    private MyAdapter myAdapter;
    private List<String> mList;


    Handler myHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    mRecyclerview.setLayoutManager(mLayoutManager);
                    mRecyclerview.setItemAnimator(new DefaultItemAnimator());
                    mRecyclerview.setAdapter(myAdapter);
                    mSwipeRefreshLayout.setRefreshing(false);
                    myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(View view, int position)
                        {
                            Toast.makeText(MainActivity.this, "position======" + position, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onItemLongClick(View view, int position)
                        {
                            Toast.makeText(MainActivity.this, "position======" + position, Toast.LENGTH_SHORT).show();
                        }
                    });

                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData()
    {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRoadDataListPersenter = new RoadDataListPersenterImpl(this);
        mRoadDataListPersenter.startGetRoadData(Constant.ROAD_URL, "1");
        mLayoutManager = new LinearLayoutManager(this);
    }

    @Override
    public void showLoading()
    {
        mSwipeRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showLoadingFaild(Exception e)
    {
        e.printStackTrace();
        Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void receiveData(List<String> mRoadList)
    {
        if (mRoadList == null || mRoadList.size() == 0)
        {
            Toast.makeText(MainActivity.this, "请求失败！", Toast.LENGTH_SHORT).show();
            return;
        }

        mList = mRoadList;
        myAdapter = new MyAdapter<String>(this, mList);
        myHandler.sendEmptyMessage(1);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }

    @Override
    public void onRefresh()
    {
        mCurrentPage = "1";
        mRoadDataListPersenter.startGetRoadData(Constant.ROAD_URL, mCurrentPage);
    }
}

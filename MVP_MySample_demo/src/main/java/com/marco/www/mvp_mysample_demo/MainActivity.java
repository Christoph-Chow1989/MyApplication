package com.marco.www.mvp_mysample_demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class MainActivity extends AppCompatActivity implements JsonShowInText
{

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;

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
        mRoadDataListPersenter = new RoadDataListPersenterImpl(this);
        mRoadDataListPersenter.startGetRoadData(Constant.ROAD_URL, "1");
        mLayoutManager = new LinearLayoutManager(this);
    }

    @Override
    public void showLoading()
    {
    }

    @Override
    public void hideLoading()
    {

    }

    @Override
    public void showLoadingFaild(Exception e)
    {
        e.printStackTrace();
    }

    @Override
    public void receiveData(List<String> mRoadList)
    {
        System.out.println("---------mRoadList----------" + mRoadList);

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
}

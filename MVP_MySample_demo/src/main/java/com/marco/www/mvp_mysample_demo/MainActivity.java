package com.marco.www.mvp_mysample_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.marco.www.mvp_mysample_demo.persenter.RoadDataListPersenter;
import com.marco.www.mvp_mysample_demo.persenter.RoadDataListPersenterImpl;
import com.marco.www.mvp_mysample_demo.view.JsonShowInText;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements JsonShowInText
{
    @Bind(R.id.button)
    Button mButton;
    @Bind(R.id.textview)
    TextView mTextview;

    private RoadDataListPersenter mRoadDataListPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRoadDataListPersenter = new RoadDataListPersenterImpl(this);
    }

    @OnClick(R.id.button)
    public void onClick()
    {
        mRoadDataListPersenter.startGetRoadData(Constant.ROAD_URL, "1");
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
       mTextview.setText(mRoadList.get(0));
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}

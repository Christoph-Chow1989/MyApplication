package com.marco.www.mvp_mysample_demo.persenter;

import com.marco.www.mvp_mysample_demo.model.RoadDataListModel;
import com.marco.www.mvp_mysample_demo.model.RoadDataListModelImpl;
import com.marco.www.mvp_mysample_demo.view.JsonShowInText;

import java.util.List;

/**
 * Created by pc on 2016/6/7.
 */
public class RoadDataListPersenterImpl implements RoadDataListPersenter, RoadDataListModelImpl.GetRoadDataListenter
{
    JsonShowInText mJsonShowInText;
    RoadDataListModel mRoadDataListModel;

    public RoadDataListPersenterImpl(JsonShowInText mJsonShowInText)
    {
        this.mJsonShowInText = mJsonShowInText;
        this.mRoadDataListModel = new RoadDataListModelImpl();
    }

    @Override
    public void startGetRoadData(String url_road, String type)
    {
        mJsonShowInText.showLoading();
        mRoadDataListModel.GetRoadList(url_road, type, this);
    }

    @Override
    public void onSuccess(List<String> mRoadList)
    {
        mJsonShowInText.receiveData(mRoadList);
        mJsonShowInText.hideLoading();
    }

    @Override
    public void onError(Exception e)
    {
        mJsonShowInText.showLoadingFaild(e);
        mJsonShowInText.hideLoading();
    }
}

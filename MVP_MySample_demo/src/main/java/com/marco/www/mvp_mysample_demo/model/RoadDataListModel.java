package com.marco.www.mvp_mysample_demo.model;

/**
 * Created by pc on 2016/6/7.
 */
public interface RoadDataListModel
{
    void GetRoadList(String road_url, String type, RoadDataListModelImpl.GetRoadDataListenter listener);
}

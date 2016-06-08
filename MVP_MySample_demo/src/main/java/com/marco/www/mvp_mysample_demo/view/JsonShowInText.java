package com.marco.www.mvp_mysample_demo.view;

import java.util.List;

/**
 * Created by pc on 2016/6/7.
 */
public interface JsonShowInText
{
    void showLoading();
    void showLoadingFaild(Exception e);
    void receiveData(List<String> mRoadList);
}

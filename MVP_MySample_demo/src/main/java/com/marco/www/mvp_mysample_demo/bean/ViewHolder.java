package com.marco.www.mvp_mysample_demo.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.marco.www.mvp_mysample_demo.R;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder{

    public TextView tv;

    public ViewHolder(View itemView) {
        super(itemView);

        tv = (TextView) itemView.findViewById(R.id.id_tv);

    }
}

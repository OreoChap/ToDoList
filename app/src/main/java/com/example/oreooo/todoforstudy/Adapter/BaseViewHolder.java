package com.example.oreooo.todoforstudy.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import java.util.List;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2019/3/3
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    public BaseViewHolder(View itemView){
        super(itemView);
        mViews = new SparseArray<>();
    }

}

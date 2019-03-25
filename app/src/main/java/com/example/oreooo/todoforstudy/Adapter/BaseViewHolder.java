package com.example.oreooo.todoforstudy.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2019/3/3
 */
public final class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private Context mContext;
    private View mView;

    public BaseViewHolder(Context context, View itemView){
        super(itemView);
        mViews = new SparseArray<View>();
        this.mContext = context;
        this.mView = itemView;
    }

    public static BaseViewHolder createViewHolder(Context context,
                                     ViewGroup parent, int layoutId) {
        BaseViewHolder viewHolder = new BaseViewHolder(context, LayoutInflater.from(context).inflate(layoutId, parent, false));
        return viewHolder;
    }

    public static BaseViewHolder createViewHolder(Context context, View itemView) {
        BaseViewHolder viewHolder = new BaseViewHolder(context, itemView);
        return viewHolder;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T)view;
    }
}

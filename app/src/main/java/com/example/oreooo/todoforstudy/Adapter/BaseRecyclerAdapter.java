package com.example.oreooo.todoforstudy.Adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2019/3/3
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    private Context mContext;
    private List mDatas;
    private int mLayout;

    //传入数据、viewHolder 的layoutID
    public BaseRecyclerAdapter(List list, @IdRes int layoutId){
        this.mDatas = list;
        this.mLayout = layoutId;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }



    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(mLayout,
                parent, false);
        BaseViewHolder viewHolder = new BaseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        bindHolder(holder, position);
    }

    protected abstract void bindHolder(BaseViewHolder holder, int position);
}

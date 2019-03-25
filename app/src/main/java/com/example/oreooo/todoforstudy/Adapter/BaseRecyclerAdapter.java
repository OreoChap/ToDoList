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
// 参考鸿洋大神的文章https://blog.csdn.net/lmj623565791/article/details/51118836
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{
    private Context mContext;
    private List<T> mDatas;
    private int mLayout;

    //传入数据、viewHolder 的layoutID
    public BaseRecyclerAdapter(Context context, List<T> list, @IdRes int layoutId){
        this.mDatas = list;
        this.mLayout = layoutId;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BaseViewHolder viewHolder = BaseViewHolder.createViewHolder(mContext, parent, mLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        bindHolder(holder, mDatas.get(position));
    }

    public abstract void bindHolder(BaseViewHolder holder, T t);
}

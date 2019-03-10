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
public abstract class BaseRecyclerAdapter<S extends BaseViewHolder> extends RecyclerView.Adapter<S>{

    private Context mContext;
    private List mDate;
    private int mLayout;

    public BaseRecyclerAdapter(List list, @IdRes int layoutId){
        this.mDate = list;
        this.mLayout = layoutId;
    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    abstract void bindHolder(S holder, int position);

    @Override
    public S onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(mLayout,
                parent, false);
        S viewHolder = (S) new BaseViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull S holder, int position) {
        bindHolder(holder, position);
    }
}

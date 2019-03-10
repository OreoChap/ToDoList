package com.example.oreooo.todoforstudy.Adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oreooo.todoforstudy.R;
import com.example.oreooo.todoforstudy.entity.Project;

import java.util.List;

/**
 * https://github.com/OreoChap
 * @author Oreo
 * @date 2018/11/3
 */

public class DoneFragmentRVA extends BaseRecyclerAdapter<DoneFragmentRVA.RVHolder> {
    private Context mContext;
    private List<Project> mDate;

    public DoneFragmentRVA(List<Project> list, @IdRes int layoutId) {
        super(list, layoutId);
        mDate = list;
    }

    @Override
    void bindHolder(RVHolder holder, int position) {
        holder.bindViewHolder(mDate.get(position), position);
    }

     class RVHolder extends BaseViewHolder {
        TextView mTxtDescription;
        Project mProject;

        RVHolder(View view) {
            super(view);
            mTxtDescription = view.findViewById(R.id.txt_description);
        }

        void bindViewHolder(Project p, int position) {
            mProject = p;
            String done = String.valueOf(position + 1) + ". " + mProject.getThePlan();
            mTxtDescription.setText(done);
        }
    }
}

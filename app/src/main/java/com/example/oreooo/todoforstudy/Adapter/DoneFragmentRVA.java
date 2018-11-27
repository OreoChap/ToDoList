package com.example.oreooo.todoforstudy.Adapter;

import android.content.Context;
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

public class DoneFragmentRVA extends RecyclerView.Adapter<DoneFragmentRVA.RVHolder> {

    private Context mContext;
    private List<Project> mlist;

    public DoneFragmentRVA(List<Project> list, Context context) {
        mContext = context;
        mlist = list;
    }

    @NonNull
    @Override
    public RVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_donefragment, viewGroup, false);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(RVHolder rVholder, int position) {
        rVholder.bindViewHolder(mlist.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


     class RVHolder extends RecyclerView.ViewHolder {

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

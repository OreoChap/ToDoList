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

import java.util.ArrayList;
import java.util.List;

public class RVadapterdone extends RecyclerView.Adapter<RVadapterdone.RVholder> {

    private Context mContext;
    private List<Project> mlist;

    public RVadapterdone(List<Project> list, Context context) {
        mContext = context;
        mlist = list;
    }

    @NonNull
    @Override
    public RVholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_donefragment, viewGroup, false);
        return new RVholder(view);
    }

    @Override
    public void onBindViewHolder(RVholder rVholder, int position) {
        rVholder.bindViewHolder(mlist.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

     class RVholder extends RecyclerView.ViewHolder {

        TextView mTxtDescription;
        Project mProject;

        RVholder(View view) {
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

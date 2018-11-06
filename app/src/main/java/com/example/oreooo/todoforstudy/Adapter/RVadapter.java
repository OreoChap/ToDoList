package com.example.oreooo.todoforstudy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oreooo.todoforstudy.Project;
import com.example.oreooo.todoforstudy.R;

import java.util.List;
import java.util.zip.Inflater;

public class RVadapter extends RecyclerView.Adapter<RVadapter.RVHolder>{

    Context mContext;
    List<Project> items;

    public RVadapter(List<Project> list, Context context){
        this.items = list;
        this.mContext = context;
    }


    @NonNull
    @Override
    public RVadapter.RVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_rv, viewGroup, false);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVadapter.RVHolder viewHolder, int i) {

        viewHolder.bindViewHolder(items.get(i));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RVHolder extends RecyclerView.ViewHolder{
        Project mProject;
        TextView time;
        TextView description;

        public RVHolder(View view){
            super(view);
            time = (TextView) view.findViewById(R.id.rv_item_time);
            description = (TextView) view.findViewById(R.id.rv_item_description);
        }

        public void bindViewHolder(Project project) {
            mProject = project;
            time.setText(mProject.getTime());
            description.setText(mProject.getThePlan());
        }
    }
}

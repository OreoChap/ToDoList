package com.example.oreooo.todoforstudy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oreooo.todoforstudy.Date.ProjectLab;
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
        return new RVHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RVadapter.RVHolder viewHolder, int i) {
        Project project = items.get(i);
        viewHolder.bindViewHolder(project);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setProjects(List<Project> list) {
        items = list;
    }

    class RVHolder extends RecyclerView.ViewHolder implements CheckBox.OnCheckedChangeListener{
        Project mProject;
        TextView time;
        AppCompatTextView description;
        CheckBox button;
        View line;
        Context mContext;

        RVHolder(View view, Context context){
            super(view);
            time = (TextView) view.findViewById(R.id.rv_item_time);
            description = (AppCompatTextView) view.findViewById(R.id.rv_item_description);
            button = (CheckBox) view.findViewById(R.id.rv_item_button);
            line = (View) view.findViewById(R.id.rv_time_line);
            mContext = context;
        }

        void bindViewHolder(Project project) {
            mProject = project;
            time.setText(mProject.getTime());
            description.setText(mProject.getThePlan());
            switch (mProject.getDone()) {
                case 1:
                    line.setVisibility(View.VISIBLE);
                    button.setChecked(true);
                    break;
                case 2:
                    line.setVisibility(View.GONE);
                    button.setChecked(false);
                default:
            }
            button.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b == true) {
                line.setVisibility(View.VISIBLE);
                mProject.setDone(1);
                ProjectLab.get(mContext).updateProject(mProject);
            } else {
                line.setVisibility(View.GONE);
                mProject.setDone(2);
                ProjectLab.get(mContext).updateProject(mProject);
            }
        }
    }
}

package com.example.oreooo.todoforstudy.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.example.oreooo.todoforstudy.Date.ProjectLab;
import com.example.oreooo.todoforstudy.Project;
import com.example.oreooo.todoforstudy.R;
import java.util.List;

public class RVadapter extends RecyclerView.Adapter<RVadapter.RVHolder>{

    Context mContext;
    List<Project> items;

    public enum IsDone {
        IS_DONE(1), NOT_DONE(2);
        private int DONE;

        IsDone(int Do) {DONE = Do;}

        public static IsDone getDone(int i) {
            for (IsDone isDone: IsDone.values()) if (isDone.DONE == i) return isDone;
            return NOT_DONE;
        }
    }

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
        if (i > 0) {
            Project first = items.get(i - 1);
            Project last = items.get(i);
            if (first.getTime().equals(last.getTime())) {
                viewHolder.bindViewHolder(last, true);
            } else {
                viewHolder.bindViewHolder(last, false);
            }
        } else {
            viewHolder.bindViewHolder(project, false);
        }
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
        TextView description;
        CheckBox button;
        Context mContext;

        RVHolder(View view, Context context){
            super(view);
            time = (TextView) view.findViewById(R.id.rv_item_time);
            description = (TextView) view.findViewById(R.id.rv_item_description);
            button = (CheckBox) view.findViewById(R.id.rv_item_button);
            mContext = context;
        }

        void bindViewHolder(Project project, boolean isSameTime) {
            mProject = project;
            time.setText(mProject.getTime());
            description.setText(mProject.getThePlan());

            checkedChange(mProject.getDone());
            boolean checked = (mProject.getDone() == 1)?true:false;
            button.setChecked(checked);

            if (isSameTime == true) {
                time.setVisibility(View.GONE);
            } else if (isSameTime == false) {
                time.setVisibility(View.VISIBLE);
            }

            button.setOnCheckedChangeListener(this);
        }



        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            int isDone = b ? 1:2;

            checkedChange(isDone);
            mProject.setDone(isDone);
            ProjectLab.get(mContext).updateProject(mProject);
        }

        private void setColor(int color) {
            description.setTextColor(mContext.getResources().getColor(color));
        }

        private void checkedChange(int isDone) {
            switch (IsDone.getDone(isDone)){
                case NOT_DONE:
                    description.getPaint().setFlags(0);
                    setColor(R.color.rv_item_black);
                    break;

                case IS_DONE:
                    description.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    setColor(R.color.rv_item_gray);
                    break;
            }
        }
    }
}

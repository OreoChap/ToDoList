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
import com.example.oreooo.todoforstudy.entity.Project;
import com.example.oreooo.todoforstudy.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RVadapter extends RecyclerView.Adapter<RVadapter.RVHolder>{

    private Context mContext;
    private List<Project> items;

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
    public RVadapter.RVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_doingfragment, viewGroup, false);
        return new RVHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RVadapter.RVHolder viewHolder, int position) {
        Project project = items.get(position);
        if (position > 0) {
            Project first = items.get(position - 1);
            Project last = items.get(position);
            if (first.getAddTime().equals(last.getAddTime())) {
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
        TextView timeTxt;
        TextView description;
        CheckBox button;
        Context mContext;
        String doneTimeStr;

        RVHolder(View view, Context context){
            super(view);
            timeTxt = (TextView) view.findViewById(R.id.rv_item_time);
            description = (TextView) view.findViewById(R.id.rv_item_description);
            button = (CheckBox) view.findViewById(R.id.rv_item_button);
            mContext = context;
        }

        void bindViewHolder(Project project, boolean isSameTime) {
            mProject = project;
            timeTxt.setText(mProject.getAddTime());
            description.setText(mProject.getThePlan());

            checkedChange(mProject.getDone());
            button.setChecked(mProject.getDone() == 1);

            if (isSameTime) {
                timeTxt.setVisibility(View.GONE);
            } else {
                timeTxt.setVisibility(View.VISIBLE);
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

        private void checkedChange(int isDone) {
            switch (IsDone.getDone(isDone)){
                case NOT_DONE:
                    description.getPaint().setFlags(0);
                    setColor(R.color.rv_item_black);
                    mProject.setDoneTime("0");
                    break;

                case IS_DONE:
                    description.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    setColor(R.color.rv_item_gray);
                    checkDate();
                    mProject.setDoneTime(doneTimeStr);
                    break;
            }
        }

        private void setColor(int color) {
            description.setTextColor(mContext.getResources().getColor(color));
        }

        void checkDate() {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                doneTimeStr = sdf.format(d);
        }
    }
}

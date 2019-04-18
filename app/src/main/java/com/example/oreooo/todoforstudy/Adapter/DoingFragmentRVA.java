package com.example.oreooo.todoforstudy.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.oreooo.todoforstudy.Date.ProjectLab;
import com.example.oreooo.todoforstudy.MessageEvent;
import com.example.oreooo.todoforstudy.ProjectDialog;
import com.example.oreooo.todoforstudy.R;
import com.example.oreooo.todoforstudy.entity.Project;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * https://github.com/OreoChap
 * @author Oreo
 * @date 2018/11/3
 */

public class DoingFragmentRVA extends RecyclerView.Adapter<DoingFragmentRVA.RVHolder>{
    private Context mContext;
    private List<Project> items;
    private static final String TAG = "DoingFragmentRVA";
    private static boolean SHOW_DONE_PROJECT = true;

    public enum IsDone {
        IS_DONE(1), NOT_DONE(2);

        private int DONE;

        IsDone(int Do) {DONE = Do;}

        public static IsDone getDone(int i) {
            for (IsDone isDone: IsDone.values()) if (isDone.DONE == i) return isDone;
            return NOT_DONE;
        }
    }

    public DoingFragmentRVA(List<Project> list, Context context){
        this.items = list;
    }

    @NonNull
    @Override
    public DoingFragmentRVA.RVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                        int position) {
        mContext = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_doingfragment,
                viewGroup, false);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoingFragmentRVA.RVHolder viewHolder,
                                 int position) {
        Project project = items.get(position);
        if (position > 0) {
            Project first = items.get(position - 1);
            if (first.getAddTime().equals(project.getAddTime())) {
                viewHolder.bindViewHolder(project, true);
            } else {
                viewHolder.bindViewHolder(project, false);
            }
        }  else {
            viewHolder.bindViewHolder(project, false);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void SetShowDoneProjects() {SHOW_DONE_PROJECT = !SHOW_DONE_PROJECT;}

    class RVHolder extends RecyclerView.ViewHolder implements CheckBox.OnCheckedChangeListener{
        Project mProject;
        TextView timeTxt;
        TextView description;
        CheckBox button;

        String doneTimeStr;
        String doneDateStr;

        RVHolder(View view){
            super(view);
            timeTxt = (TextView) view.findViewById(R.id.rv_item_time);
            description = (TextView) view.findViewById(R.id.rv_item_description);
            button = (CheckBox) view.findViewById(R.id.rv_item_button);
        }

        void bindViewHolder(Project project, boolean isSameTime) {
            mProject = project;
            timeTxt.setText(mProject.getAddTime());
            description.setText(mProject.getThePlan());
            description.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ProjectDialog dialog = ProjectDialog.getInstance(mContext);
                    dialog.showDialog(mProject, SHOW_DONE_PROJECT);
                    return true;
                }
            });
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
        public void onCheckedChanged(final CompoundButton compoundButton, final boolean changeTo) {
            final int isDone = changeTo ? 1:2;
            if (mProject.getDone() == 1 && !changeTo ) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(mContext);
                dialog.setTitle("是否更改为未完成")
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                updateDoneFragmentUI(isDone);
                            }
                        })
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                compoundButton.setChecked(!changeTo);
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                compoundButton.setChecked(!changeTo);
                            }
                        })
                        .show();
            } else if (mProject.getDone() == 2){
                updateDoneFragmentUI(isDone);
            }
        }

        private void updateDoneFragmentUI(int isDone) {
            checkedChange(isDone);
            mProject.setDone(isDone);
            ProjectLab.getInstance(mContext).updateProject(mProject);
            EventBus.getDefault().post(new MessageEvent.DoneFragmentUpdateUIEvent("Send DoneFragmentUpdateUI Event"));
        }

        private void checkedChange(int isDone) {
            switch (IsDone.getDone(isDone)){
                case NOT_DONE:
                    description.getPaint().setFlags(0);
                    description.setTextColor(mContext.getResources()
                            .getColor(R.color.rv_item_black));
                    mProject.setDoneTime("0");
                    mProject.setDoneDate("0");
                    Log.d(TAG, "Project not done");
                    break;

                case IS_DONE:
                    description.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    description.setTextColor(mContext.getResources()
                            .getColor(R.color.rv_item_gray));
                    checkDate();
                    mProject.setDoneTime(doneTimeStr);
                    mProject.setDoneDate(doneDateStr);
                    Log.d(TAG, "Project done");
                    break;
            }
        }

        private void checkDate() {
                Date d = new Date();
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
                doneDateStr = sdf1.format(d);
                doneTimeStr = sdf2.format(d);
        }

        // 通过设置每个子项的宽高，隐藏 Done子项
        private void hideDoneProject(boolean showDoneProjects) {
            RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) itemView.getLayoutParams();
            if (showDoneProjects) {
                params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                itemView.setVisibility(View.VISIBLE);
            } else {
                itemView.setVisibility(View.GONE);
                params.height = 0;
                params.width = 0;
            }
            itemView.setLayoutParams(params);
        }
    }
}

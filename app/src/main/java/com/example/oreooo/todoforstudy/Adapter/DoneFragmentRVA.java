package com.example.oreooo.todoforstudy.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.oreooo.todoforstudy.R;
import com.example.oreooo.todoforstudy.LItePalDB.Project;
import com.oreooo.library.ListBase.BaseRecyclerAdapter;

import java.util.List;

public class DoneFragmentRVA extends BaseRecyclerAdapter<Project> {

    public DoneFragmentRVA(Context context, List<Project> list, int layoutId,
                           @Nullable OnViewHolderClickListener listener) {
        super(context, list, layoutId, listener);
    }

    @Override
    public void bindHolder(ViewHolder holder, Project project) {
        TextView textView = (TextView)holder.getView(R.id.txt_description);
        String done = String.valueOf(holder.getPosition() + 1) + ". " + project.getThePlan();
        textView.setText(done);
    }
}

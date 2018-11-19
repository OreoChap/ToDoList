package com.example.oreooo.todoforstudy.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oreooo.todoforstudy.Adapter.RVadapterdone;
import com.example.oreooo.todoforstudy.Date.ProjectLab;
import com.example.oreooo.todoforstudy.R;
import com.example.oreooo.todoforstudy.entity.Project;

import java.util.List;

public class DoneFragment extends Fragment{

    RecyclerView rV;
    Context mContext;

    public static DoneFragment newInstance() {
        return new DoneFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        mContext = getActivity();
        rV = (RecyclerView)view.findViewById(R.id.recycler_doneFragment);
        rV.setLayoutManager(new LinearLayoutManager(mContext));
        /**
         *   假定时间为2018-11-19
         */
        String time =  "2018-11-19";
        updateUI(time);
        return view;
    }

    void updateUI(String time) {
        List<Project> list = ProjectLab.get(mContext).getProjectsByTime(time);
        RVadapterdone adapter = new RVadapterdone(list, mContext);
        rV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

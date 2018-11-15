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
import com.example.oreooo.todoforstudy.Adapter.RVadapter;
import com.example.oreooo.todoforstudy.Date.ProjectLab;
import com.example.oreooo.todoforstudy.Project;
import com.example.oreooo.todoforstudy.R;
import java.util.ArrayList;
import java.util.List;

public class PlanFragment extends Fragment{

    RecyclerView rV;
    List<Project> mList = new ArrayList<>();
    RVadapter rVadapter;
    Context mContext;

    public static PlanFragment newInstance(){
        return new PlanFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        mContext = getActivity();
        rV = view.findViewById(R.id.pFRV);
        rV.setLayoutManager(new LinearLayoutManager(mContext));
        return view;
    }

    public void upDateUI() {
        mList = ProjectLab.get(mContext).getProjects();
        rVadapter = new RVadapter(mList, mContext);
        rV.setAdapter(rVadapter);
        rVadapter.notifyDataSetChanged();
    }
}

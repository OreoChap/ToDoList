package com.example.oreooo.todoforstudy.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.oreooo.todoforstudy.Adapter.DoingFragmentRVA;
import com.example.oreooo.todoforstudy.Date.ProjectLab;
import com.example.oreooo.todoforstudy.entity.Project;
import com.example.oreooo.todoforstudy.R;
import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/OreoChap
 * @author Oreo
 * @date 2018/11/3
 */

public class DoingFragment extends Fragment{

    RecyclerView rV;
    List<Project> mList = new ArrayList<>();
    DoingFragmentRVA doingFragmentRVA;
    Context mContext;

    public static DoingFragment newInstance(){
        return new DoingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doing, container, false);
        mContext = getActivity();
        rV = view.findViewById(R.id.doingFragmentRV);
        rV.setLayoutManager(new LinearLayoutManager(mContext));
        upDateUI();
        return view;
    }

    public void upDateUI() {
        mList = ProjectLab.get(mContext).getProjects();
        doingFragmentRVA = new DoingFragmentRVA(mList, mContext);
        rV.setAdapter(doingFragmentRVA);
        doingFragmentRVA.notifyDataSetChanged();
    }
}

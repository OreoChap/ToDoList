package com.example.oreooo.todoforstudy.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.oreooo.todoforstudy.Adapter.RVadapterdone;
import com.example.oreooo.todoforstudy.Date.ProjectLab;
import com.example.oreooo.todoforstudy.R;
import com.example.oreooo.todoforstudy.entity.Project;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DoneFragment extends Fragment{

    RecyclerView rV;
    Context mContext;
    TextView timeTxt;
    Boolean isCreated = false;
    String mDate;

    public static DoneFragment newInstance() {
        return new DoneFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        mContext = getActivity();
        rV = (RecyclerView)view.findViewById(R.id.recycler_doneFragment);
        timeTxt = (TextView)view.findViewById(R.id.txt_time);
        rV.setLayoutManager(new LinearLayoutManager(mContext));

        //todo: 系统日期变更时，更新date
        checkSysTime();
        updateUI(mDate);
        isCreated = true;
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isCreated) {
            checkSysTime();
            updateUI(mDate);
        }
    }

    void updateUI(String time) {
        List<Project> list = ProjectLab.get(mContext).getProjectsByTodayDone(time);
        RVadapterdone adapter = new RVadapterdone(list, mContext);
        rV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        timeTxt.setText(time);
    }

    void checkSysTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mDate = sdf.format(d);
    }
}

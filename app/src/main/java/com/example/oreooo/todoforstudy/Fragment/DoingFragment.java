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
import com.example.oreooo.todoforstudy.Interface;
import com.example.oreooo.todoforstudy.R;
import com.example.oreooo.todoforstudy.Test.LitepalHelper;
import com.example.oreooo.todoforstudy.Test.Project;

import java.util.List;

/**
 * https://github.com/OreoChap
 * @author Oreo
 * @date 2018/11/3
 */

public class DoingFragment extends Fragment implements Interface.Dialog{
    private RecyclerView rV;

    private DoingFragmentRVA doingFragmentRVA;
    private Context mContext;
    private static DoingFragment mFragment = null;

    public static DoingFragment getInstance(){
        if (null == mFragment) {
            synchronized (DoingFragment.class) {
                if (null == mFragment) {
                    mFragment = new DoingFragment();
                }
            }
        }
        return mFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doing, container, false);
        mContext = getActivity();
        rV = view.findViewById(R.id.doingFragmentRV);
        rV.setLayoutManager(new LinearLayoutManager(mContext));
        upDateUI(true);
        return view;
    }

    public void upDateUI(boolean showDoneProjects) {
        List<Project> mList;
        if (showDoneProjects) {
            mList = LitepalHelper.getInstance().getAllProject();
        } else {
            mList = LitepalHelper.getInstance().getNotDoneProject();
        }
        doingFragmentRVA = new DoingFragmentRVA(mList, mContext);
        rV.setAdapter(doingFragmentRVA);
        doingFragmentRVA.notifyDataSetChanged();
    }

    // 显示或隐藏 Done项目
    public void showProjects(boolean showDoneProjects) {
        doingFragmentRVA.SetShowDoneProjects();
        upDateUI(showDoneProjects);
    }

    @Override
    public void update(boolean showDoneProjects) {
        upDateUI(showDoneProjects);
    }
}

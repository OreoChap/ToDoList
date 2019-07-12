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

import com.example.oreooo.todoforstudy.Adapter.DoneFragmentRVA;
import com.example.oreooo.todoforstudy.R;
import com.example.oreooo.todoforstudy.Test.LitepalHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * https://github.com/OreoChap
 * @author Oreo
 * @date 2018/11/3
 */

public class DoneFragment extends Fragment{
    private RecyclerView rV;
    private Context mContext;
    private TextView timeTxt;
    private String mDate;
    private static DoneFragment mFragment = null;

    public static DoneFragment getInstance(){
        if (null == mFragment) {
            synchronized (DoneFragment.class) {
                if (null == mFragment) {
                    mFragment = new DoneFragment();
                }
            }
        }
        return mFragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        mContext = getActivity();
        rV = (RecyclerView)view.findViewById(R.id.recycler_doneFragment);
        timeTxt = (TextView)view.findViewById(R.id.txt_time);
        rV.setLayoutManager(new LinearLayoutManager(mContext));
        checkSysTime();
        updateUI();
        return view;
    }

    public void updateUI() {
        update(mDate);
    }

    private void update(String time) {
        rV.setAdapter(new DoneFragmentRVA(mContext, LitepalHelper.getInstance().getDoneProjectsByToday(time), R.id.recycler_doneFragment, null));
        timeTxt.setText(time);
    }

    public void checkSysTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mDate = sdf.format(d);
    }
}

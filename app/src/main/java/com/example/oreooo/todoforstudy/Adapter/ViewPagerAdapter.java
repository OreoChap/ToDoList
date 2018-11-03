package com.example.oreooo.todoforstudy.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.oreooo.todoforstudy.Fragment.DoneFragment;
import com.example.oreooo.todoforstudy.Fragment.PlanFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    private PlanFragment pFragment;
    private DoneFragment dFragment;
    Context mCntext;
    private String[] titleName;
    private final int pageCount = 2;

    public ViewPagerAdapter(FragmentManager fm, Context context,
                            PlanFragment planFragment, DoneFragment doneFragment){
        super(fm);
        mCntext = context;
        titleName = new String[]{"Plan", "Done"};
        this.pFragment = planFragment;
        this.dFragment = doneFragment;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0: fragment = pFragment;break;
            case 1: fragment = dFragment;break;
            default:fragment = pFragment;break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleName[position];
    }
}

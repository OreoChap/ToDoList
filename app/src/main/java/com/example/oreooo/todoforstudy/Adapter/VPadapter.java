package com.example.oreooo.todoforstudy.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

public class VPadapter extends FragmentStatePagerAdapter {

    private List<Fragment> mlist;

    public VPadapter(FragmentManager fm, List<Fragment>list){
        super(fm);
        mlist = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String[] title = new String[] {"DOING", "DONE"};
        return title[position];
    }
}

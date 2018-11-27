package com.example.oreooo.todoforstudy.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * https://github.com/OreoChap
 * @author Oreo
 * @date 2018/11/3
 */

public class VPadapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public VPadapter(FragmentManager fm, List<Fragment>list){
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String[] title = new String[] {"DOING", "DONE"};
        return title[position];
    }
}

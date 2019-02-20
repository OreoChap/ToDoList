package com.example.oreooo.todoforstudy;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.example.oreooo.todoforstudy.Date.ProjectLab;
import com.example.oreooo.todoforstudy.Fragment.DoingFragment;
import com.example.oreooo.todoforstudy.Fragment.DoneFragment;
import com.example.oreooo.todoforstudy.entity.Project;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * https://github.com/OreoChap
 * @author Oreo
 * @date 2018/11/3
 */

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout pagerTitle;
    List<Fragment> pagers = new ArrayList<>();
    DoingFragment doingFragment;
    DoneFragment doneFragment;
    private static final String TAG = "MainActivity";
    ProjectDialog mDialog;
    private static boolean SHOW_DONE_PROJECT = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (doneFragment == null) {
            doneFragment = DoneFragment.getInstance();
        }
        doneFragment.checkSysTime();
        doneFragment.updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        MenuItem showDoneProjects = menu.findItem(R.id.menu_item_show_done_project);
        if (SHOW_DONE_PROJECT) {
            showDoneProjects.setTitle(R.string.hide_done_project);
        } else {
            showDoneProjects.setTitle(R.string.show_done_project);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add_project:
                if (null == mDialog) {
                    mDialog = ProjectDialog.getInstance(this);
                }
                mDialog.showDialog(SHOW_DONE_PROJECT);
                Log.d(TAG, "Dialog Created");
                return true;
            case R.id.menu_item_show_done_project:
                SHOW_DONE_PROJECT = !SHOW_DONE_PROJECT;
                doingFragment.showProjects(SHOW_DONE_PROJECT);
                this.invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initFragment() {
        doingFragment = DoingFragment.getInstance();
        doneFragment = DoneFragment.getInstance();
        pagers.add(doingFragment);
        pagers.add(doneFragment);
    }

    private void initView() {
        viewPager = findViewById(R.id.pager);
        pagerTitle = findViewById(R.id.pager_title);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return pagers.get(position);
            }

            @Override
            public int getCount() {
                return pagers.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                String[] title = {"DOING", "DONE"};
                return title[position];
            }
        });
    }

    /*
    private void doingFragmentUpdateUI() {
        doingFragment.upDateUI();
        Log.d(TAG, "DoingFragmentUpdateUI");
    }
    */

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.DoneFragmentUpdateUIEvent event) {
        doneFragment.updateUI();
        Log.d(TAG, event.message);
        Log.d(TAG, "DoneFragmentUpdateUI");
    }
}

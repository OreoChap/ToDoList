package com.example.oreooo.todoforstudy;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.example.oreooo.todoforstudy.Adapter.VPadapter;
import com.example.oreooo.todoforstudy.Date.ProjectLab;
import com.example.oreooo.todoforstudy.Fragment.DoingFragment;
import com.example.oreooo.todoforstudy.Fragment.DoneFragment;
import com.example.oreooo.todoforstudy.entity.Project;
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

    EditText dEdit;
    ViewPager viewPager;
    TabLayout pagerTitle;
    List<Fragment> pagers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_mission:
                showAddDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFragment() {
        DoingFragment doingFragment = DoingFragment.newInstance();
        DoneFragment doneFragment = DoneFragment.newInstance();
        pagers.add(doingFragment);
        pagers.add(doneFragment);
    }

    private void initView() {
        viewPager = findViewById(R.id.pager);
        pagerTitle = findViewById(R.id.pager_title);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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

    private void showAddDialog() {
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(MainActivity.this);
        final View view = LayoutInflater.from(this)
                .inflate(R.layout.dialog_add_plan, null);
        dEdit = view.findViewById(R.id.description_edit);

        mDialog.setTitle("创建")
                .setView(view)
                .setPositiveButton("取消", null)
                .setNegativeButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String descriptionInput = dEdit.getText().toString();
                                if (descriptionInput.isEmpty()) {
                                    dialogInterface.dismiss();
                                    return;
                                }
                                Date d = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String timeInput = sdf.format(d);
                                Project p = new Project(timeInput, descriptionInput);
                                ProjectLab.get(MainActivity.this).addProject(p);
                                fragmentUpdate();
                            }
                        })
                .show();
    }

    public void fragmentUpdate() {
        DoingFragment fragment = (DoingFragment) pagers.get(0);
        fragment.upDateUI();
    }
}

package com.example.oreooo.todoforstudy;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.oreooo.todoforstudy.Adapter.RVadapter;
import com.example.oreooo.todoforstudy.Adapter.ViewPagerAdapter;
import com.example.oreooo.todoforstudy.Fragment.AddPlanDialog;
import com.example.oreooo.todoforstudy.Fragment.DoneFragment;
import com.example.oreooo.todoforstudy.Fragment.PlanFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    //@BindView(R.id.main_viewPager)ViewPager mainVP;
    //TabLayout tabLayout;

    private PlanFragment planFragment;
    private DoneFragment doneFragment;
    private static final String DIALOG_DATE = "Dialog_Date";
    private RecyclerView recyclerView;

    private List<Project> list;
    private RVadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no1);

        //ButterKnife.bind(this);
        initView();
        initFragment();
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



    private void initView() {
        /**
         mainVP = findViewById(R.id.main_viewPager);
         tabLayout = findViewById(R.id.main_tabLayout);
         mainVP.setAdapter(new ViewPagerAdapter
         (getSupportFragmentManager(), this, planFragment, doneFragment));
         tabLayout.setupWithViewPager(mainVP);
         */

        //recyclerView = findViewById(R.id.no1_Fragment_RV);
        //adapter = new RVadapter(list, MainActivity.this);
        //recyclerView.setAdapter(adapter);
    }


    private void initFragment() {
        PlanFragment fragment = new PlanFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        /**
         * planFragment = PlanFragment.newInstance();
         * doneFragment = DoneFragment.newInstance();
         */
    }


    /**
     * 菜单栏添加PLAN 按钮的Dialog 初始化
     */

    EditText tEdit;
    EditText dEdit;

    private void showAddDialog() {
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(MainActivity.this);

        final View view = LayoutInflater.from(this)
                .inflate(R.layout.dialog_add_plan, null);
        tEdit = view.findViewById(R.id.time_edit);
        dEdit = view.findViewById(R.id.description_edit);

        mDialog.setTitle("创建")
                .setView(view)
                .setPositiveButton("取消", null)
                .setNegativeButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String timeInput = tEdit.getText().toString();
                                String descriptionInput = dEdit.getText().toString();
                                Project p = new Project();
                                p.setTime(timeInput);
                                p.setThePlan(descriptionInput);
                                fragmentUpdate(p);
                            }
                        })
                .show();
    }

    private void fragmentUpdate(Project p) {
        PlanFragment fragment = (PlanFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        fragment.upDateUI(p);
    }
}

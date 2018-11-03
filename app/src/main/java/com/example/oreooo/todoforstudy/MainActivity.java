package com.example.oreooo.todoforstudy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.oreooo.todoforstudy.Adapter.ViewPagerAdapter;
import com.example.oreooo.todoforstudy.Fragment.DoneFragment;
import com.example.oreooo.todoforstudy.Fragment.PlanFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_viewPager)ViewPager mainVP;
    TabLayout tabLayout;

    private PlanFragment planFragment;
    private DoneFragment doneFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    private void initFragment() {
        planFragment = PlanFragment.newInstance();
        doneFragment = DoneFragment.newInstance();
    }

    private void initView() {
        mainVP = findViewById(R.id.main_viewPager);
        tabLayout = findViewById(R.id.main_tabLayout);
        mainVP.setAdapter(new ViewPagerAdapter
                (getSupportFragmentManager(), this, planFragment, doneFragment));
        tabLayout.setupWithViewPager(mainVP);
    }
}

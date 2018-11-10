package com.example.oreooo.todoforstudy;

import android.content.DialogInterface;
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
import com.example.oreooo.todoforstudy.Date.ProjectLab;
import com.example.oreooo.todoforstudy.Fragment.PlanFragment;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    EditText dEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no1);
        initFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fragmentUpdate();
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
        PlanFragment fragment = new PlanFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    /**
     * 菜单栏添加PLAN 按钮的Dialog 初始化
     */

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
                                String timeInput = d.toLocaleString();
                                Project p = new Project(timeInput, descriptionInput);
                                ProjectLab.get(MainActivity.this).addProject(p);
                                fragmentUpdate();
                            }
                        })
                .show();
    }

    public void fragmentUpdate() {
        PlanFragment fragment = (PlanFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        fragment.upDateUI();
    }
}

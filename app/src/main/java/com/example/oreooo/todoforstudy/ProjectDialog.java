package com.example.oreooo.todoforstudy;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.oreooo.todoforstudy.Date.ProjectLab;
import com.example.oreooo.todoforstudy.Fragment.DoingFragment;
import com.example.oreooo.todoforstudy.entity.Project;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Oreo https://github.com/OreoChap
 * @date 2019/1/21
 */
public class ProjectDialog {

    private static ProjectDialog dialog = null;
    private Context mContext;
    private EditText mEdit;
    private Interface.Dialog interFace = DoingFragment.getInstance();

    public static ProjectDialog getInstance(Context context) {
        if (null == dialog) {
            synchronized (ProjectDialog.class) {
                if (null == dialog) {
                    dialog = new ProjectDialog(context);
                }
            }
        }
        return dialog;
    }

    private ProjectDialog(Context context) {
        mContext = context;
    }

    public void showDialog(){
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(mContext);
        final View view = LayoutInflater.from(mContext)
                .inflate(R.layout.dialog_add_plan, null);
        mEdit = view.findViewById(R.id.description_edit);

        mDialog.setTitle("创建")
                .setView(view)
                .setPositiveButton("取消", null)
                .setNegativeButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String descriptionInput = mEdit.getText().toString();
                                if (descriptionInput.isEmpty()) {
                                    dialogInterface.dismiss();
                                    return;
                                }
                                Date d = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String timeInput = sdf.format(d);
                                Project p = new Project(timeInput, descriptionInput);
                                ProjectLab.getInstance(mContext).addProject(p);
                                interFace.update();
                            }
                        })
                .show();
    }

}

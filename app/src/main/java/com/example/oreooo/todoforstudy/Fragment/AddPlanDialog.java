package com.example.oreooo.todoforstudy.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.oreooo.todoforstudy.Project;
import com.example.oreooo.todoforstudy.R;

import java.util.List;

public class AddPlanDialog extends DialogFragment implements DialogInterface.OnClickListener{

    EditText tEdit;
    EditText dEdit;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_add_plan, null);
        tEdit = view.findViewById(R.id.time_edit);
        dEdit = view.findViewById(R.id.description_edit);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("创建")
                .setPositiveButton(android.R.string.ok, this)
                .setNegativeButton("取消", null)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Project p = new Project();

        p.setThePlan(dEdit.getText().toString());
    }
}

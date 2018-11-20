package com.example.oreooo.todoforstudy.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import com.example.oreooo.todoforstudy.entity.Project;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static com.example.oreooo.todoforstudy.Date.ProjectsBaseHelper.ProjectDnSchema.ProjectTable.*;
import static com.example.oreooo.todoforstudy.Date.ProjectsBaseHelper.ProjectDnSchema.ProjectTable.Cols.*;

public class ProjectLab {

    private Context mContext;
    private SQLiteDatabase mDateBase;
    private static ProjectLab mProjectLab;

    public static ProjectLab get(Context context) {
        if (mProjectLab == null) {
            mProjectLab = new ProjectLab(context);
        }
        return mProjectLab;
    }

    private ProjectLab(Context context) {
        mContext = context;
        mDateBase = new ProjectsBaseHelper(mContext).getWritableDatabase();
    }


    public void addProject(Project p) {
        ContentValues values = getContentValues(p);

        mDateBase.insert(NAME,
                null, values);
    }

    public void updateProject(Project p) {
        String uuidString = p.getUuid().toString();
        ContentValues values = getContentValues(p);
        mDateBase.update(NAME, values, UUID + " =?", new String[] { uuidString });
    }

    public Project getProject(UUID uuid) {
        ProjectCursorWrapper cursor = queryProjects(UUID + " = ?",
                new String[] { uuid.toString() }, null);
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getP();
        } finally {
            cursor.close();
        }
    }

    public List<Project> getProjects(){
        List<Project> projects = new ArrayList<>();
        ProjectCursorWrapper cursor = queryProjects(
                null, null, "_id DESC");
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                projects.add(cursor.getP());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return projects;
    }

    public List<Project> getProjectsByTime(String time) {
        List<Project> projects = new ArrayList<>();
        ProjectCursorWrapper cursor = queryProjects(
                "time = ? and done = ?",
                new String[]{time, "1"}, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                projects.add(cursor.getP());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return projects;
    }

    public List<Project> getProjectsByTodayDone() {
        List<Project> projects = new ArrayList<>();
        ProjectCursorWrapper cursor = queryProjects(
                "done = ?",
                new String[]{"1"}, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                projects.add(cursor.getP());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return projects;
    }

    private ContentValues getContentValues(Project project) {
        ContentValues values = new ContentValues();
        values.put(UUID, project.getUuid().toString());
        values.put(TIME, project.getTime());
        values.put(DESCRIPTION, project.getThePlan());
        values.put(DONE, project.getDone().toString());
        return values;
    }

    private ProjectCursorWrapper queryProjects(String whereClause, String[] whereArgs, String orderBy) {
        Cursor cursor = mDateBase.query(
                NAME, null,
                whereClause, whereArgs,
                null, null, orderBy);
        return new ProjectCursorWrapper(cursor);
    }


    public class ProjectCursorWrapper extends CursorWrapper {

        public ProjectCursorWrapper(Cursor cursor) {
            super(cursor);
        }

        public Project getP() {
            String uuidString = getString(getColumnIndex(UUID));
            String time = getString(getColumnIndex(TIME));
            String description = getString(getColumnIndex(DESCRIPTION));
            Integer done = getInt(getColumnIndex(DONE));

            Project p = new Project(java.util.UUID.fromString(uuidString));
            p.setTime(time);
            p.setThePlan(description);
            p.setDone(done);

            return p;
        }
    }
}

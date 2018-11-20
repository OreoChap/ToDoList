package com.example.oreooo.todoforstudy.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.oreooo.todoforstudy.Date.ProjectsBaseHelper.ProjectDnSchema.ProjectTable.*;
import static com.example.oreooo.todoforstudy.Date.ProjectsBaseHelper.ProjectDnSchema.ProjectTable.Cols.*;

public class ProjectsBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATEBASE_NAME = "projectBase.db";

    ProjectsBaseHelper(Context context) {
        super(context, DATEBASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NAME + "(" +
                " _id integer primary key autoincrement, " +
                UUID + ", " + ADDTIME + ", " + DESCRIPTION + ", " + DONE + "," + DONETIME + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int odlVersion, int newVersion) { }

    class ProjectDnSchema {
        final class ProjectTable {
            static final String NAME = "Project";
            final class Cols {
                static final String UUID = "uuid";
                static final String ADDTIME = "time";
                static final String DESCRIPTION = "description";
                static final String DONE = "done";
                static final String DONETIME = "doneTime";
            }
        }
    }
}

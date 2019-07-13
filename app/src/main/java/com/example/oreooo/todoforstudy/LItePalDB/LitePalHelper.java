package com.example.oreooo.todoforstudy.LItePalDB;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LitePalHelper {

    public static LitePalHelper getInstance() {
        return LitepalHelperHolder.Instance;
    }

    private static class LitepalHelperHolder {
        private static LitePalHelper Instance = new LitePalHelper();
    }

//    public Project getProject (Project project) {
//        List data = new ArrayList<>();
//        data = Arrays.asList(LitePal.findAll(Project.class).toArray());
//
//    }

    public void addProject (Project project) {
        project.save();
    }

    public void updateProject (Project project) {
        project.updateAll();
    }

    public List<Project> getAllProject () {
        List data = new ArrayList<>();
        data = Arrays.asList(LitePal.findAll(Project.class).toArray());
        return data;
    }

    public List<Project> getNotDoneProject () {
        List data = new ArrayList<>();
        data = LitePal.where("done = ?", "false")
                .order("doneTime DESC")
                .find(Project.class);
        return data;
    }

    public List<Project> getDoneProjectsByToday (String doneDate) {
        List data = new ArrayList<>();
        data = LitePal.where("doneDate = ?", doneDate)
                .order("doneTime")
                .find(Project.class);
        return data;
    }
}

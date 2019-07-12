package com.example.oreooo.todoforstudy.Test;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LitepalHelper {

    public static LitepalHelper getInstance() {
        return LitepalHelperHolder.Instance;
    }

    private static class LitepalHelperHolder {
        private static LitepalHelper Instance = new LitepalHelper();
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
        data = LitePal.where("done = ?", "2")
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

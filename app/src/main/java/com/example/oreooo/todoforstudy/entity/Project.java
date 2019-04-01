package com.example.oreooo.todoforstudy.entity;

import java.util.UUID;

public class Project {
    private String addTime;
    private String thePlan;
    private UUID uuid;
    private String doneTime;
    private String doneDate;
    private int done;

    public Project(String addTime, String thePlan, UUID uuid, String doneTime, String doneDate, int done) {
        this.addTime = addTime;
        this.thePlan = thePlan;
        this.uuid = UUID.randomUUID();
        this.doneTime = doneTime;
        this.doneDate = doneDate;
        this.done = done;
    }

    public Project(String addTime, String thePlan) {
        this.addTime = addTime;
        this.thePlan = thePlan;
        this.uuid = UUID.randomUUID();
        this.doneTime = "0";
        this.doneDate = "0";
        this.done = 2;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getThePlan() {
        return thePlan;
    }

    public void setThePlan(String thePlan) {
        this.thePlan = thePlan;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public String getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(String doneDate) {
        this.doneDate = doneDate;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }
}

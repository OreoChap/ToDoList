package com.example.oreooo.todoforstudy.entity;

import java.util.UUID;

public class Project {

    private UUID uuid;
    private String addTime;
    private String thePlan;
    private Integer done;
    private String doneTime;

    public Project() {
        this(UUID.randomUUID());
    }

    public Project(UUID id) {
        uuid = id;
    }

    public Project(String addTime, String description) {
        this.uuid = UUID.randomUUID();
        this.addTime = addTime;
        this.thePlan = description;
        this.done = 2;
        this.doneTime = "0";
    }

    public UUID getUuid() {
        return uuid;
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

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }
}

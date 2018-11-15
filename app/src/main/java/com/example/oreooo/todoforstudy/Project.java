package com.example.oreooo.todoforstudy;

import java.util.UUID;

public class Project {

    private UUID uuid;
    private String time;
    private String thePlan;
    private Integer done;

    public Project() {
        this(UUID.randomUUID());
    }

    public Project(UUID id) {
        uuid = id;
    }

    public Project(String time, String description) {
        this.uuid = UUID.randomUUID();
        this.time = time;
        this.thePlan = description;
        this.done = 2;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}

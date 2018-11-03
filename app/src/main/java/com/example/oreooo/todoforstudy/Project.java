package com.example.oreooo.todoforstudy;

import java.util.Date;
import java.util.List;

public class Project {

    private Date time;
    private List<String> thePlan;


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<String> getThePlan() {
        return thePlan;
    }

    public void setThePlan(List<String> thePlan) {
        this.thePlan = thePlan;
    }
}

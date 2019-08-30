package com.example.oreooo.todoforstudy.LItePalDB

import org.litepal.LitePal
import java.util.*

class LitePalHelper {

    val allProject: List<Project>
        get() {
            var data: List<Project>
            data = Arrays.asList<Project>(*LitePal.findAll(Project::class.java).toTypedArray())
            return data
        }

    val notDoneProject: List<Project>
        get() {
            var data: List<Project>
            data = LitePal.where("done = ?", "false")
                    .order("doneTime DESC")
                    .find(Project::class.java)
            return data
        }

    private object LitepalHelperHolder {
        private val Instance = LitePalHelper()
    }

    fun addProject(project: Project) {
        project.save()
    }

    fun updateProject(project: Project) {
        project.updateAll("addTime=? and thePlan=?", project.addTime, project.thePlan)
    }

    fun getDoneProjectsByToday(doneDate: String): List<Project> {
        var data: List<*>
        data = LitePal.where("doneDate = ?", doneDate)
                .order("doneTime")
                .find(Project::class.java)
        return data
    }

    companion object {
        val instance: LitePalHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LitePalHelper()
        }
    }
}
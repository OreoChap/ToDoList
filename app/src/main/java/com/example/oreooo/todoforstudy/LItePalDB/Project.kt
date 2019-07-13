package com.example.oreooo.todoforstudy.LItePalDB

import org.litepal.crud.LitePalSupport

class Project(var addTime:String = "", var thePlan:String = "", var doneTime:String = "",
              var doneDate: String = "", var done:Boolean = false): LitePalSupport() {
    init {
    }

    // done的取值：1为完成，2为未完成
    constructor(addTime:String, thePlan:String):this(addTime, thePlan,
            "0", "0", false)
}
package com.example.oreooo.todoforstudy.SQLiteDB

import java.util.*

data class Project(var addTime:String, var thePlan:String, var uuid: UUID,
                   var doneTime:String, var doneDate: String, var done:Int) {

    init {    }

    // done的取值：1为完成，2为未完成
    constructor(addTime:String, thePlan:String):this(addTime, thePlan, UUID.randomUUID(), "0", "0", 2)
}



package com.example.oreooo.todoforstudy.entity

import java.util.*

data class Project(var addTime:String, var thePlan: String, var uuid:UUID, var doneTime:String,
                   var doneDate:String, var done:Int) {
    constructor(addTime: String, thePlan: String): this(addTime, thePlan, UUID.randomUUID(), "0",
            "0", 2)
}
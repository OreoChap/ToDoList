package com.example.oreooo.todoforstudy.Test

import org.litepal.crud.LitePalSupport
import java.util.*

class Project(var addTime:String, var thePlan:String, var uuid: UUID,
              var doneTime:String, var doneDate: String, var done:Int): LitePalSupport() {
    init { }

    // done的取值：1为完成，2为未完成
    constructor(addTime:String, thePlan:String):this(addTime, thePlan, UUID.randomUUID(), "0", "0", 2)
}
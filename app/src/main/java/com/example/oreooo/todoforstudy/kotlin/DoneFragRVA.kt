package com.example.oreooo.todoforstudy.kotlin

import android.content.Context
import android.support.annotation.Nullable
import android.view.View
import android.widget.TextView
import com.example.oreooo.todoforstudy.LItePalDB.Project
import com.example.oreooo.todoforstudy.R
import com.oreooo.library.ListBase.BaseRecyclerAdapter

class DoneFragRVA(val context: Context, private val list: List<Project>, private val layoutId: Int,
                  @Nullable val listener: OnViewHolderClickListener) :
        BaseRecyclerAdapter<Project>(context, list, layoutId, listener) {

    override fun bindHolder(holder: ViewHolder, item: Project) {
        val textView = holder.getView<View>(R.id.txt_description) as TextView
        val done = (holder.position + 1).toString() + ". " + item.thePlan
        textView.text = done
    }
}
package com.example.oreooo.todoforstudy.Adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.TextView
import com.example.oreooo.todoforstudy.LItePalDB.LitePalHelper
import com.example.oreooo.todoforstudy.LItePalDB.Project
import com.example.oreooo.todoforstudy.MainActivity
import com.example.oreooo.todoforstudy.MessageEvent
import com.example.oreooo.todoforstudy.ProjectDialog
import com.example.oreooo.todoforstudy.R
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat
import java.util.*

class DoingFragRVA(var list: List<Project>) : RecyclerView.Adapter<DoingFragRVA.RVHolder>() {
    val instance by lazy { this }
    private var mContext: Context? = null
    private var items: List<Project>? = null

    init {
        items = list
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RVHolder {
        mContext = p0.context
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.list_item_doingfragment,
                p0, false)
        return RVHolder(view, p0.context)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: RVHolder, position: Int) {
        val project = items?.get(position) ?: return
        if (position > 0) {
            val first = items?.get(position - 1)
            if (first?.addTime == project.addTime) {
                viewHolder.bindViewHolder(project, true)
            } else {
                viewHolder.bindViewHolder(project, false)
            }
        } else {
            viewHolder.bindViewHolder(project, false)
        }
    }

    class RVHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView), CompoundButton.OnCheckedChangeListener {
        var mProject: Project? = null
        var mView: View = itemView
        private var timeTxt: TextView? = null
        private var button: CheckBox? = null
        private var context: Context? = null

        var doneTimeStr: String = ""
        var doneDateStr: String = ""

        init {
            timeTxt = itemView.findViewById<View>(R.id.rv_item_time) as TextView

            button = itemView.findViewById<View>(R.id.rv_item_button) as CheckBox
            this.context = context
        }

        fun bindViewHolder(project: Project, isSameTime: Boolean) {
            mProject = project
            timeTxt?.setText(project.addTime)
            val description = mView.findViewById<View>(R.id.rv_item_description) as TextView
            description.setText(project.thePlan)
            description.setOnLongClickListener(View.OnLongClickListener {
                val dialog = ProjectDialog.getInstance(context)
                dialog.showDialog(project, MainActivity.SHOW_DONE_PROJECT)
                true
            })
            checkedChange(project.done)
            button?.setChecked(project.done)

            if (isSameTime) {
                timeTxt?.setVisibility(View.GONE)
            } else {
                timeTxt?.setVisibility(View.VISIBLE)
            }
            button?.setOnCheckedChangeListener(this)
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            if (mProject!!.done && !isChecked) {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("是否更改为未完成")
                        .setNegativeButton("确定") { _, _ ->
                            updateDoneFragmentUI(isChecked)
                        }
                        .setPositiveButton("取消") { _, _ -> buttonView?.setChecked(true) }
                        .setOnCancelListener { buttonView?.setChecked(true) }
                        .show()
            } else if (!mProject!!.done) {
                updateDoneFragmentUI(isChecked)
            }
        }

        private fun updateDoneFragmentUI(isDone: Boolean) {
            checkedChange(isDone)
            mProject?.done = isDone
            LitePalHelper.instance.updateProject(mProject!!)
            EventBus.getDefault().post(MessageEvent.DoneFragmentUpdateUIEvent("Send DoneFragmentUpdateUI Event"))
        }

        private fun checkedChange(isDone: Boolean) {
            val description = mView.findViewById<View>(R.id.rv_item_description) as TextView
            if (isDone) {
                description.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
                description.setTextColor(context!!.resources.getColor(R.color.rv_item_gray))
                checkDate()
                mProject?.doneTime = doneTimeStr
                mProject?.doneDate = doneDateStr
            } else {
                description.paint.flags = 0
                description.setTextColor((context!!.resources).getColor(R.color.rv_item_black))
                mProject?.doneTime = "0"
                mProject?.doneDate = "0"
            }
        }

        private fun checkDate() {
            val d = Date()
            val sdf1 = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
            val sdf2 = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
            doneDateStr = sdf1.format(d)
            doneTimeStr = sdf2.format(d)
        }

        // 通过设置每个子项的宽高，隐藏 Done子项
        private fun hideDoneProject(showDoneProjects: Boolean) {
            val params = itemView.layoutParams as RecyclerView.LayoutParams
            if (showDoneProjects) {
                params.height = LinearLayout.LayoutParams.WRAP_CONTENT
                params.width = LinearLayout.LayoutParams.MATCH_PARENT
                itemView.visibility = View.VISIBLE
            } else {
                itemView.visibility = View.GONE
                params.height = 0
                params.width = 0
            }
            itemView.layoutParams = params
        }
    }
}
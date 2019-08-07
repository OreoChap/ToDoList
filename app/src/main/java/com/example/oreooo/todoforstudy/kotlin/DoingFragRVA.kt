package com.example.oreooo.todoforstudy.kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import com.example.oreooo.todoforstudy.LItePalDB.Project
import com.example.oreooo.todoforstudy.R

class DoingFragRVA : RecyclerView.Adapter<DoingFragRVA.RVHolder>() {

    private var mContext: Context? = null
    private val items: List<Project>? = null
    private val TAG = "DoingFragmentRVA"

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RVHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: RVHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class RVHolder(itemView: View) : RecyclerView.ViewHolder(itemView), CompoundButton.OnCheckedChangeListener {
        var mProject: Project? = null
        private var timeTxt: TextView? = null
        private var description: TextView? = null
        private var button: CheckBox? = null

        var doneTimeStr: String = ""
        var doneDateStr: String = ""

        fun RVHolder(view: View) {
            timeTxt = view.findViewById<View>(R.id.rv_item_time) as TextView
            description = view.findViewById<View>(R.id.rv_item_description) as TextView
            button = view.findViewById<View>(R.id.rv_item_button) as CheckBox
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
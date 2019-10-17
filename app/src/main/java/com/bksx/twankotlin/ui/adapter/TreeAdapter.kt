package com.bksx.twankotlin.ui.adapter

import android.content.Context
import android.widget.TextView
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.TreeBean

/**
 * @Author JoneChen
 * @Date 2019\7\25 0025-16:06
 */
class TreeAdapter(
    context: Context,
    layoutRes:Int,
    private val mutableList: MutableList<TreeBean.Data>
): BaseAdapter<TreeBean.Data>(context,layoutRes,mutableList) {

    override fun convert(holder: BaseViewHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_tree).apply {
            text = mutableList[position].name
        }
    }
}
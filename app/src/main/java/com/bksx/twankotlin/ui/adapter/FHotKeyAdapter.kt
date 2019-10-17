package com.bksx.twankotlin.ui.adapter

import android.content.Context
import android.widget.TextView
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.HotKeyBean


/**
 * @Author JoneChen
 * @Date 2019\7\25 0025-14:40
 */
class FHotKeyAdapter(
    context: Context,
    layoutRes: Int,
    private var mutableList: MutableList<String>
) : BaseAdapter<String>(context,layoutRes,mutableList) {
    override fun convert(holder: BaseViewHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_fhotkey).apply {
            text = mutableList[position]
        }
    }
}
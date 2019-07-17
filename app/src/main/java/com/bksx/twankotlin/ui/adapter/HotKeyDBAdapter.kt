package com.bksx.twankotlin.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.TextView
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.db.HotKeyDB

/**
 * @Author JoneChen
 * @Date 2019\7\15 0015-17:14
 */
class HotKeyDBAdapter(
    context: Context,
    resId: Int,
    private var allHotKeyList: MutableList<HotKeyDB>
) : BaseAdapter<HotKeyDB>(context,resId,allHotKeyList) {

    override fun convert(holder: BaseViewHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_item_hotkey).apply {
            textSize = 14f
            text = allHotKeyList[position].key
        }

        holder.getView<TextView>(R.id.tv_item_hotkey_times).apply {
            textSize = 10f
            text = allHotKeyList[position].times.toString()
        }
    }
}
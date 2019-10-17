package com.bksx.twankotlin.ui.adapter

import android.content.Context
import android.provider.Settings
import android.widget.TextView
import com.bksx.twankotlin.App
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.ThirdBean
import com.bksx.twankotlin.bean.TreeBean
import com.bksx.twankotlin.utils.formatDate2Day
import com.bksx.twankotlin.utils.toast
import java.util.*

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-14:04
 */
class SecondTreeAdapter (context: Context,
                         layoutRes: Int,
                         private var mData: MutableList<TreeBean.Data.Children> = mutableListOf()
): BaseAdapter<TreeBean.Data.Children>(context,layoutRes,mData) {
    override fun convert(holder: BaseViewHolder, position: Int) {
        val name: String = mData[position].name
        holder.getView<TextView>(R.id.tv_item_second_title).apply {
            text = name
            setTextColor(resources.getColor(R.color.colorAccent))
        }

        holder.getView<TextView>(R.id.tv_item_second_time).text = System.currentTimeMillis().formatDate2Day()

        holder.getView<TextView>(R.id.tv_item_second_tag).text = "JohnChen"
    }
}
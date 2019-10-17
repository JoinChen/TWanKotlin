package com.bksx.twankotlin.ui.adapter

import android.content.Context
import android.widget.TextView
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.ThirdBean
import com.bksx.twankotlin.utils.formatDate2Day

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-10:24
 */
class ThirdTreeAdapter(
    context: Context,
    layoutRes: Int,
    private val mData: MutableList<ThirdBean.Data.DataX>
): BaseAdapter<ThirdBean.Data.DataX>(context,layoutRes,mData) {
    override fun convert(holder: BaseViewHolder, position: Int) {

        holder.getView<TextView>(R.id.tv_item_third_title).text = mData[position].title

        holder.getView<TextView>(R.id.tv_item_third_time).text = mData[position].publishTime.formatDate2Day()

        holder.getView<TextView>(R.id.tv_item_third_tag).text = mData[position].author
    }

}